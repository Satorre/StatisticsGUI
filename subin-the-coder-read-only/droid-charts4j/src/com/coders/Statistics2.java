package com.coders;

import immutableTree.ComputeTree;
import immutableTree.ImmutableTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Statistics2 extends Activity {

	ViewGroup mainView = null;
	ImmutableTree root = null;
	
	ImmutableTree currentNode = null;
	List<View> listOfView = new ArrayList<View>();
	/**include the --Select--*/
	HashMap<Spinner, Integer> spinnerChoice = new HashMap<Spinner, Integer>();
	
	int lastViewSelected = -1;
	LayoutInflater li = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics2);
    
        mainView = (ViewGroup) findViewById(R.id.mainView);
        
        root = ImmutableTree.readFromFile(this, "tree2.ser");
        if (root == null) {
        	root = ComputeTree.computeTree();
        	ImmutableTree.writeToFile(this, "tree2.ser", root);
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
			spinnerChoice.put((Spinner) parentView, position);
			if (spinnerPosition == lastViewSelected) {
				/**keep track of which currentNode is wich spinner*/
				currentNode = currentNode.getChild(position - 1); //because of the --Select-- value
				spinnerChoice.put((Spinner)selectedItemView, position);
				if (currentNode.hasChildren()) {
					addNextSpinner(null, spinnerPosition);
				} else {
					//enable submit button
				}
			} else {
				int goUpBy = lastViewSelected - spinnerPosition + 1;
				currentNode = currentNode.goUpBy(goUpBy);
				for (int i = spinnerPosition + 1; i < listOfView.size(); i++) {
					mainView.removeViewAt(spinnerPosition + 1);
					listOfView.remove(spinnerPosition + 1);
				}
				lastViewSelected = goUpBy;
				if (currentNode.hasChildren()) {
					currentNode = currentNode.getChild(position - 1);
					addNextSpinner(null, lastViewSelected);
				}
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
        
        //TextView textView = (TextView) v.findViewById(R.id.choice);
        Spinner spinner = (Spinner) v.findViewById(R.id.choice_spinner);
        initSpinner(currentNode, spinner);
        //textView.setText("prefix");
        
        mainView.addView(v);
        listOfView.add(v);
        lastViewSelected = positionSpinnerAbove + 1;
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_statistics2, menu);
        return true;
    }
}
