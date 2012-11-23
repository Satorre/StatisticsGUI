package statistics.graphe;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.charts4j.AbstractAxisChart;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.ScatterPlotData;
import static com.googlecode.charts4j.Color.BLACK;

public class CustomScatterChart extends AbstractChart {

	

	private ArrayList<Double> mXValue;
	private ArrayList<Double> mYValue;
	private HashMap<String,Double> mPointSize;
	
	
	public CustomScatterChart(String title, JSONObject data, StatisticResulType type) throws JSONException {
		super(title, type);
		mXValue = new ArrayList<Double>();
		mYValue = new ArrayList<Double>();
		mPointSize = new HashMap<String,Double>();
		setData(data);
		setPointSize();
		createchart();
	}

	public CustomScatterChart(String title, ArrayList<Double> xValue, ArrayList<Double> yValue, StatisticResulType type) {
		super(title, type);
		mXValue = xValue;
		mYValue = yValue;
		mPointSize = new HashMap<String,Double>();
		setPointSize();
		createchart();
	}
	
	
	private void setPointSize() {
		for(int i = 0; i < mXValue.size(); i++) {
			String key = String.valueOf(mXValue.get(i)) +String.valueOf(mYValue.get(i));
			if(mPointSize.containsKey(key)) {
				mPointSize.put(key, mPointSize.get(key)*1.1);
			} else {
				mPointSize.put(key, 20.0);
			}
		}
	}

	@Override
	protected void setData(JSONObject data) throws JSONException {
		JSONArray jsonArray = data.getJSONArray("xValue");
		for (int i=0; i<jsonArray.length(); i++) {
			mXValue.add(jsonArray.getDouble(i));
		}
		
		jsonArray = data.getJSONArray("yValue");
		for (int i=0; i<jsonArray.length(); i++) {
			mYValue.add(jsonArray.getDouble(i));
		}
		if (mXValue.size() != mYValue.size()) {
			throw new JSONException("yValue and xValue should have the same number of element");
		}
	}

	@Override
	public String getData() {
		return mXValue.toString() + mYValue.toString();
	}
	@Override
	public void setLayout() {
		super.setLayout();

        //TODO set the grid according to mType
        ((AbstractAxisChart) mChart).setGrid(20, 20, 3, 2);

        AxisLabels xAxisLabels = AxisLabelsFactory.newNumericRangeAxisLabels(0, mType.maxScaleX());
        xAxisLabels.setAxisStyle(AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER));
        AxisLabels yAxisLabels = AxisLabelsFactory.newNumericRangeAxisLabels(0, mType.maxScaleY());
        yAxisLabels.setAxisStyle(AxisStyle.newAxisStyle(BLACK, 13, AxisTextAlignment.CENTER));

        ((AbstractAxisChart) mChart).addXAxisLabels(xAxisLabels);
        ((AbstractAxisChart) mChart).addYAxisLabels(yAxisLabels);
		
	}

	@Override
	protected void createchart() {
		Data dx = DataUtil.scaleWithinRange(0, mType.maxScaleX(), mXValue);
        Data dy = DataUtil.scaleWithinRange(0, mType.maxScaleY(), mYValue);
        ArrayList<Double> pointSize= new ArrayList<Double>();
		for(int i = 0; i < mXValue.size(); i++) {
			String key = String.valueOf(mXValue.get(i)) +String.valueOf(mYValue.get(i));
			pointSize.add(mPointSize.get(key));
		}
        Data pointSizes = Data.newData(pointSize);
        ScatterPlotData data = Plots.newScatterPlotData(dx, dy, pointSizes);
        Color pointColor = BLACK;
        data.setColor(pointColor);
        
        mChart = GCharts.newScatterPlot(data);
	
	}

}
