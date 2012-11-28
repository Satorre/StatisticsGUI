package com.coders;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Statistics2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics2);
        ViewGroup mainView = (ViewGroup) findViewById(R.id.mainView);
        
        LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.choice, null);
        
        TextView textView = (TextView) v.findViewById(R.id.choice);
        Spinner spinner = (Spinner) v.findViewById(R.id.choice_spinner);
        
        textView.setText("prefix");
        
        Resources res = getResources();
		String[] array = res.getStringArray(R.array.prefix_choice);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, 
				new ArrayList<CharSequence> (Arrays.asList(array)));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
		
        mainView.addView(v);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_statistics2, menu);
        return true;
    }
}
