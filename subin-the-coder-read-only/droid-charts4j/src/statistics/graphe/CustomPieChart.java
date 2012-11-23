package statistics.graphe;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

public class CustomPieChart extends AbstractChart {
	
	private ArrayList<Double> mPercentage;
	private ArrayList<String> mLabel;
	

	public CustomPieChart(String title, JSONObject data, StatisticResulType type) throws JSONException {
		super(title, type);
		mPercentage = new ArrayList<Double>();
		mLabel = new ArrayList<String>();
		setData(data);
		createchart();
	}
	
	public CustomPieChart(String title, ArrayList<Double> percentage, ArrayList<String> label, StatisticResulType type) {
		super(title, type);
		mPercentage = percentage;
		mLabel = label;
		createchart();
	}

	@Override
	protected void setData(JSONObject data) throws JSONException {
		JSONArray jsonArray = data.getJSONArray("percentage");
		for (int i=0; i<jsonArray.length(); i++) {
			mPercentage.add(jsonArray.getDouble(i));
		}
		
		jsonArray = data.getJSONArray("label");
		for (int i=0; i<jsonArray.length(); i++) {
			mLabel.add(jsonArray.getString(i));
		}
		if (mPercentage.size() != mLabel.size()) {
			throw new JSONException("percentage and label should have the same number of element");
		}
	}

	@Override
	public String getData() {
		return null;
	}
	
	@Override
	public void setLayout() {
		super.setLayout();
	    ((PieChart) mChart).setThreeD(true);		
	}

	@Override
	protected void createchart() {
		ArrayList<Slice> slices = new ArrayList<Slice>();	
		for(int i = 0; i<mLabel.size(); i++){
			Slice s = Slice.newSlice(mPercentage.get(i).intValue(), colorList[i], null, mLabel.get(i));
			slices.add(s);
		}

	    mChart = GCharts.newPieChart(slices);
	}

}
