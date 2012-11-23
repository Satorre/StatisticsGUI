package statistics.graphe;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Plots;


public class CustomBarChart extends AbstractMultiplePlots {
	
	public CustomBarChart(String title, JSONObject data, StatisticResulType type) throws JSONException {
		super(title, data, type);
		mPlots = new ArrayList<BarChartPlot>();
		createchart();
	}
	public CustomBarChart(	String title, 
							ArrayList<ArrayList<Double>> values, 
							ArrayList<String> xAxis, 
							ArrayList<String> barNames,
							StatisticResulType type) {
		super(title, values, xAxis, barNames, type);
		createchart();
	}
	
	@Override
	public void setLayout() {
		super.setLayout();			
		
		((BarChart) mChart).setBarWidth(BarChart.AUTO_RESIZE);

        ((BarChart) mChart).setDataStacked(true);
	}


	@SuppressWarnings("unchecked")
	@Override
	protected void createchart() {
		for(int i = 0; i < mValues.size(); i++) {
			Data data = DataUtil.scaleWithinRange(0, mType.maxScaleY(), mValues.get(i));
			((ArrayList<BarChartPlot>) mPlots).add(Plots.newBarChartPlot(data, colorList[i],mPlotsName.get(i)));
		}
		// Instantiating chart.
		mChart = GCharts.newBarChart((ArrayList<BarChartPlot>) mPlots);

	}

}
