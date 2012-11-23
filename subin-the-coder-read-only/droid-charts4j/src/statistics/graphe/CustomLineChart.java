package statistics.graphe;

import static com.googlecode.charts4j.Color.BLACK;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;

/**
 * 
 * 
 */
public class CustomLineChart extends AbstractMultiplePlots {

	public CustomLineChart(String title, JSONObject data,
			StatisticResulType type) throws JSONException {
		super(title, data, type);
		mPlots = new ArrayList<Line>();
		createchart();
	}
	
	public CustomLineChart(	String title, 
							ArrayList<ArrayList<Double>> values, 
							ArrayList<String> xAxis, 
							ArrayList<String> barNames,
							StatisticResulType type) {
		super(title, values, xAxis, barNames, type);
		mPlots = new ArrayList<Line>();
		createchart();
	}
	
	@Override
	public void setLayout() {
		super.setLayout();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void createchart() {
		for(int i = 0; i < mValues.size(); i++) {
			Data data = DataUtil.scaleWithinRange(0, mType.maxScaleY(), mValues.get(i));
			Line l = Plots.newLine(data, colorList[i],mPlotsName.get(i));
			l.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
			l.addShapeMarkers(Shape.CIRCLE, colorList[i], 10);
			l.addShapeMarkers(Shape.CIRCLE, BLACK, 7);
			//l.setFillAreaColor(colorList[i]);
			
			((ArrayList<Line>) mPlots).add(l);
		}
		// Instantiating chart.
		mChart = GCharts.newLineChart((ArrayList<Line>) mPlots);

	}
}
