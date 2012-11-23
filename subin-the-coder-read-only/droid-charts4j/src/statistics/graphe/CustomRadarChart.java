package statistics.graphe;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.googlecode.charts4j.Color.BLACK;
import static com.googlecode.charts4j.Color.WHITE;
import static com.googlecode.charts4j.UrlUtil.normalize;

import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.RadarPlot;
import com.googlecode.charts4j.RadialAxisLabels;
import com.googlecode.charts4j.Shape;

public class CustomRadarChart extends AbstractChart {
	

	private ArrayList<Double> mData;
	private ArrayList<String> mLabel;

	public CustomRadarChart(String title, JSONObject data, StatisticResulType type) throws JSONException {
		super(title, type);
		
		mData = new ArrayList<Double>();
		mLabel = new ArrayList<String>();
		setData(data);		
		createchart();
	}
	
	public CustomRadarChart(String title, ArrayList<Double> data,  ArrayList<String> label, StatisticResulType type) {
		super(title, type);
		
		mData = new ArrayList<Double>();
		mLabel = new ArrayList<String>();
		createchart();
	}


	@Override
	protected void setData(JSONObject data) throws JSONException {
		JSONArray jsonArray = data.getJSONArray("data");
		for (int i=0; i<jsonArray.length(); i++) {
			mData.add(jsonArray.getDouble(i));
		}
		mData.add(mData.get(0));
		
		jsonArray = data.getJSONArray("label");
		for (int i=0; i<jsonArray.length(); i++) {
			mLabel.add(jsonArray.getString(i));
		}
		if ((mData.size() - 1) != mLabel.size()) {
			throw new JSONException("data and label should have the same number of element");
		}
	}

	@Override
	public String getData() {
		return mLabel.toString() + mData.toString();
	}

	@Override
	protected void createchart() {

		Data data = DataUtil.scaleWithinRange(0, mType.maxScale(), mData);
		RadarPlot plot = Plots.newRadarPlot(data);
		Color plotColor = Color.newColor("CC3366");
		plot.addShapeMarkers(Shape.SQUARE, plotColor, 12);
		plot.addShapeMarkers(Shape.SQUARE, WHITE, 8);
		plot.setColor(plotColor);
		plot.setLineStyle(LineStyle.newLineStyle(4, 1, 0));
		
		
		mRadarChart = GCharts.newRadarChart(plot);


	}
	
	@Override
	public void setLayoutRadar() {
		super.setLayoutRadar();
		

		RadialAxisLabels radialAxisLabels = AxisLabelsFactory.newRadialAxisLabels(mLabel);
		
		radialAxisLabels.setRadialAxisStyle(BLACK, 10);
		mRadarChart.addRadialAxisLabels(radialAxisLabels);
		
		AxisLabels contrentricAxisLabels = null;
		if(mType.equals(StatisticResulType.GRADE)) {
			contrentricAxisLabels = AxisLabelsFactory.newNumericRangeAxisLabels(0, mType.maxScale(),1);
		} else if (mType.equals(StatisticResulType.PERCENTAGE)) {
			contrentricAxisLabels = AxisLabelsFactory.newNumericRangeAxisLabels(0, mType.maxScale(),20);			
		} else {
			contrentricAxisLabels = AxisLabelsFactory.newNumericRangeAxisLabels(0, mType.maxScale(),2);
		}
		contrentricAxisLabels.setAxisStyle(AxisStyle.newAxisStyle(BLACK, 12, AxisTextAlignment.RIGHT));
		mRadarChart.addConcentricAxisLabels(contrentricAxisLabels);
	}
	
	@Override
	public String getUrl() {
		setLayoutRadar();
	    String url = mRadarChart.toURLString();
		return normalize(url);
	}

}
