package statistics.sdp.statsuiprototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import statistics.showstatistics.StatisticActivity;

import com.coders.R;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Statistics extends Activity {

	private ArrayList<Spinner> spinners;
	private HashMap<Spinner, ArrayAdapter<CharSequence>> arrayAdapters;
	//private Controller controller;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		controller = new Controller();
		spinners = new ArrayList<Spinner>();
		arrayAdapters = new HashMap<Spinner, ArrayAdapter<CharSequence>>();
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_statistics);

		spinners.add((Spinner) findViewById(R.id.prefix_spinner));
		spinners.add((Spinner) findViewById(R.id.category_item_spinner));
		spinners.add((Spinner) findViewById(R.id.value_category_item_spinner));
		spinners.add((Spinner) findViewById(R.id.stat_spinner));
		spinners.add((Spinner) findViewById(R.id.option_spinner));
		
		initSpinner(spinners.get(0), R.array.prefix_choice);
		
		spinners.get(4).setOnItemSelectedListener( new SpinnerListener());
		spinners.get(3).setOnItemSelectedListener( new SpinnerListener());
		spinners.get(2).setOnItemSelectedListener( new SpinnerListener());
		spinners.get(1).setOnItemSelectedListener( new SpinnerListener());
		spinners.get(0).setOnItemSelectedListener( new SpinnerListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_statistics, menu);
		return true;
	}
	
	public void initSpinner(Spinner s, int arrayId) {
		ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) s.getAdapter();
		if (adapter != null) {
			adapter.clear();
			arrayAdapters.remove(s);
		}
		Resources res = getResources();
		String[] array = res.getStringArray(arrayId);
		adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, 
				new ArrayList<CharSequence> (Arrays.asList(array)));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(adapter);
		arrayAdapters.put(s, adapter);
	}
	
	/**
	 * update the next spinner according to the value pressed
	 * @author david
	 *
	 */
	public class SpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position,
				long id) {
			//get the position of the spinner
			int spinnerPosition = getPositionSpinner((Spinner) parentView);
			if (spinnerPosition == spinners.size() - 1) {
				return;
			}
			String itemText = ((TextView) selectedItemView).getText().toString();
			//clear all the spinner under it
			for (int i = spinnerPosition + 1; i < arrayAdapters.size(); i++) {
				if (arrayAdapters.get(i) != null) {
					arrayAdapters.get(i).clear();
				}
			}
			int categories = -1;
			//display correct list in the spinner under
			if (spinnerPosition == 0) {
				if (itemText.equals("Number")) {
					categories = R.array.number_prefix;
				} else if (itemText.equals("Which")) {
					categories = R.array.which_prefix;
				} else if (itemText.equals("Distribution")) {
					categories = R.array.distribution_prefix;
				} else if (itemText.equals("<Select>")) {
					categories =  R.array.empty;
				}
			}
			if (spinnerPosition == 1) {
				categories = R.array.list_course;
			}
			if (spinnerPosition == 2) {
				categories = R.array.number_course;
			}
			
			if (spinnerPosition + 1 < spinners.size() && categories != -1) {
				initSpinner(spinners.get(spinnerPosition + 1), categories);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void computeStat(View v) {
		Toast mess = Toast.makeText(this, "Your statistic is being computed", 0);
		mess.show();
		
		String statsDefinition = "";
		for(Spinner s : spinners) {
			TextView selection = (TextView) s.getSelectedView();
			if (selection != null) {
				statsDefinition += selection.getText().toString();
			}
		}
		MockStats mock = new MockStats();
		mock.getData(statsDefinition, this);

	}
	
	public void displayStats() {
		Intent i = new Intent(this, StatisticActivity.class);
		startActivity(i);
	}
	
	public int getPositionSpinner(Spinner v) {
		int r = 0;
		for(Spinner s : spinners) {
			if (s == v) {
				break;
			}
			r++;
		}
		return r;
	}
}
