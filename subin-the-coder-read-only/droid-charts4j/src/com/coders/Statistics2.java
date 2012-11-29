package com.coders;

import immutableTree.ComputeTree;
import immutableTree.ImmutableTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Statistics2 extends Activity {

	ImmutableTree root = null;
	ImmutableTree currentNode = null;
	List<View> listOfView = new ArrayList<View>();
	ViewGroup mainView = null;
	
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
        
        LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.choice, mainView, false);
        
        TextView textView = (TextView) v.findViewById(R.id.choice);
        Spinner spinner = (Spinner) v.findViewById(R.id.choice_spinner);
        initSpinner(currentNode, spinner);
        textView.setText("prefix");
		
        mainView.addView(v);
        listOfView.add(v);
    }

    public class SpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position,
				long id) {
			
			/**for now, erase all the spinner under position*/
			/*for(int i = position + 1; i < listOfView.size(); i++) {
				mainView.removeViewAt(position + 1);
				listOfView.remove(position + 1);
			}*/
			
			//move current node
			//currentNode = currentNode.goUpBy(2);
			
			/**inflate a new one*/
			LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = li.inflate(R.layout.choice, mainView, false);
	        
	        TextView textView = (TextView) v.findViewById(R.id.choice);
	        Spinner spinner = (Spinner) v.findViewById(R.id.choice_spinner);
	        initSpinner(currentNode, spinner);
	        textView.setText("prefix");
	        
	        mainView.addView(v);
	        onContentChanged();
	        listOfView.add(v);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
    	
    }
    
    public void initSpinner(ImmutableTree node, Spinner spinner) {
    
    	List<String> choices = node.getChildrenString();
    	
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, choices);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner.setAdapter(adapter);
    	adapter.notifyDataSetChanged();
    	
    	spinner.setOnItemSelectedListener(new SpinnerListener());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_statistics2, menu);
        return true;
    }
}
