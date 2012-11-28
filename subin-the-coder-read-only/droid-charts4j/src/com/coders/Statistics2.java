package com.coders;

import java.util.ArrayList;
import immutableTree.ImmutableTree;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics2);
        ViewGroup mainView = (ViewGroup) findViewById(R.id.mainView);
        
        LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.choice, null);
        
        TextView textView = (TextView) v.findViewById(R.id.choice);
        Spinner spinner = (Spinner) v.findViewById(R.id.choice_spinner);
        initSpinner(spinner, R.array.prefix_choice);
        textView.setText("prefix");
		
        mainView.addView(v);
        root = ImmutableTree.readFromFile("tree.ser");
        root.print(0);
    }

    public class SpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
		}
    	
    }
    
    public void initSpinner(Spinner spinner, int layout) {
    	Resources res = getResources();
    	String[] array = res.getStringArray(layout);
    	ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, 
    			new ArrayList<CharSequence> (Arrays.asList(array)));
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
