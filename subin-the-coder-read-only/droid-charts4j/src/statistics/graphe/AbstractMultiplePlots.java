package statistics.graphe;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.charts4j.AbstractAxisChart;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
public abstract class AbstractMultiplePlots extends AbstractChart {

	
	

	protected ArrayList<ArrayList<Double>> mValues;
	protected ArrayList<String> mPlotsName;
	protected ArrayList<String> mXAxis;
	protected ArrayList<?> mPlots;
	protected String mYLabel;
	
	
	public AbstractMultiplePlots(String title, JSONObject data, StatisticResulType type) throws JSONException {
		super(title, type);
		mValues = new ArrayList<ArrayList<Double>>();
		mXAxis = new ArrayList<String>();
		mPlotsName = new ArrayList<String>();
		setData(data);
	}
	
	public AbstractMultiplePlots(	String title, 
									ArrayList<ArrayList<Double>> values, 
									ArrayList<String> xAxis, 
									ArrayList<String> barNames,
									StatisticResulType type) {
		super(title, type);
		mValues = values;
		mXAxis = xAxis;
		mPlotsName = barNames;
	}


	@Override	
	protected void setData(JSONObject data) throws JSONException {
		JSONArray jsonArray;
		try {
			jsonArray = data.getJSONArray("plots");
		} catch (JSONException e) {
			jsonArray = new JSONArray();
			jsonArray.put(data.getString("plots"));
		}
		for(int i=0; i<jsonArray.length(); i++) {
			mPlotsName.add(jsonArray.getString(i));
			JSONArray subJsonArray = data.getJSONArray(jsonArray.getString(i));	
			ArrayList<Double> localList = new ArrayList<Double>();
			for (int j=0; j<subJsonArray.length(); j++) {
				localList.add(subJsonArray.getDouble(j));
			}
			mValues.add(localList);
		}
		jsonArray = data.getJSONArray("xAxis");
		for (int i=0; i<jsonArray.length(); i++) {
			mXAxis.add(jsonArray.getString(i));
		}
		mYLabel = data.getString("yAxisLabel");
	}

	@Override
	public void setLayout() {
		super.setLayout();
		
		AxisLabels yAxis = AxisLabelsFactory.newAxisLabels(mYLabel);
		((AbstractAxisChart) mChart).addTopAxisLabels(yAxis);

		// Adding axis info to chart.
		((AbstractAxisChart) mChart).addXAxisLabels(AxisLabelsFactory.newAxisLabels(mXAxis));
		((AbstractAxisChart) mChart).addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, mType.maxScaleY()));
		((AbstractAxisChart) mChart).setGrid(100, 10, 3, 2);
	}

	@Override
	public String getData() {
		return mValues.toString();
	}
}
