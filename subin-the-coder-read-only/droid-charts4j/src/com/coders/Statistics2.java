package com.coders;

import immutableTree.ComputeTree;
import immutableTree.ImmutableTree;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.content.DialogInterface;

public class Statistics2 extends Activity {

	ViewGroup mainView = null;
	ImmutableTree root = null;
	
	ImmutableTree currentNode = null;
	List<View> listOfView = new ArrayList<View>();
	int oldSpinnerPosition = 0;
	int hitEnd = 0;
	LayoutInflater li = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics2);
    
        mainView = (ViewGroup) findViewById(R.id.mainView);
        
        root = ImmutableTree.readFromFile(this, "StatsDecisionTree4.ser");
        if (root == null) {
        	root = ComputeTree.computeTree();
        	ImmutableTree.writeToFile(this, "StatsDecisionTree4.ser", root);
        }
        currentNode = root;
        
        li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addNextSpinner(null, -1);        
    }

    public class SpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position,
				long id) {
			
			int spinnerPosition = searchSpinnerPositionInScrollView((Spinner) parentView);
			int size = listOfView.size();
			for (int i = spinnerPosition + 1; i < size; i++) {
				mainView.removeViewAt(spinnerPosition + 1);
				listOfView.remove(spinnerPosition + 1);
			}
			
			int goUpBy = 0;
			if(oldSpinnerPosition > spinnerPosition) {
				goUpBy = oldSpinnerPosition - spinnerPosition;
				oldSpinnerPosition = oldSpinnerPosition - goUpBy;
				goUpBy -= hitEnd*2;
				currentNode = currentNode.goUpBy(goUpBy + 1);
				hitEnd = 0;
			}
			
			if (position == 0) {
				return;
			}
			if (currentNode.getChild(position - 1).hasChildren()) {
				currentNode = currentNode.getChild(position - 1);
				if (currentNode.hasChildren()) {
					addNextSpinner(null, oldSpinnerPosition);
					oldSpinnerPosition++;
				}
			} else {
				oldSpinnerPosition++;
				hitEnd = 1;
				submitStats();
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
    	
    }
    
    public int searchSpinnerPositionInScrollView(Spinner spinner) {
    	int pos = 0;
    	for(View v : listOfView) {
    		Spinner spinner2 = (Spinner) v.findViewById(R.id.choice_spinner);
    		if (spinner2 == spinner) {
    			return pos;
    		}
    		pos++;
    	}
    	return -1;
    }
    
    public void addNextSpinner(View b, int positionSpinnerAbove) {
    	/**inflate a new one*/
		View v = li.inflate(R.layout.choice, null);
        
        TextView textView = (TextView) v.findViewById(R.id.choice);
        Spinner spinner = (Spinner) v.findViewById(R.id.choice_spinner);
        initSpinner(currentNode, spinner);
        textView.setText(currentNode.getLabel());
        
        mainView.addView(v);
        listOfView.add(v);
    }
    
    public void submitStats() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Submit Stats ?");
    	builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Call here the method that compute the stat
			}
    	});
    	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();	
			}
    	});
    	AlertDialog alert = builder.create();
    	alert.show();
    }
    
    public void initSpinner(ImmutableTree node, final Spinner spinner) {
    
    	List<String> choices;
    	choices = node.getChildrenString();
    	/**I add the select line here because I don't want it to count as 
    	 * a child
    	 */
    	choices.add(0, "--Select--");
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, choices);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner.setAdapter(adapter);
    	adapter.notifyDataSetChanged();

    	spinner.post(new Runnable() {
    		public void run() {
    			spinner.setOnItemSelectedListener(new SpinnerListener());
    		}
    	});
    } 
}
