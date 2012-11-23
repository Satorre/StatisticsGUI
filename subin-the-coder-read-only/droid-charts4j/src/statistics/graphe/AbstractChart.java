/**
 * 
 */
package statistics.graphe;

import static com.googlecode.charts4j.Color.BLACK;
//import static com.googlecode.charts4j.Color.LIGHTCORAL;
import static com.googlecode.charts4j.Color.WHITE;
import static com.googlecode.charts4j.UrlUtil.normalize;

import org.json.JSONException;
import org.json.JSONObject;

import com.googlecode.charts4j.AbstractGraphChart;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Fills;
//import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.RadarChart;

/**
 * @author AAA
 *
 */
public abstract class AbstractChart {
	
	public final static Color[] colorList = {	
		Color.RED, 
		Color.YELLOW,
		Color.BLUE,
		Color.GREEN,
		Color.GRAY,
		Color.ORANGE};
	
	public static enum StatisticResulType {
		PERCENTAGE(100.0),
		GRADE(6.0), 
		RATING(10.0), 
		XPERCENTAGE_YPERCENTAGE(100.0,100.0),
		XGRADE_YGRADE(6.0,6.0),
		XRATING_YRATING(10.0,10.0),
		XPERCENTAGE_YGRADE(100.0,6.0),
		XPERCENTAGE_YRATING(100.0,10.0),
		XGRADE_YPERCENTAGE(6.0, 100.0),
		XGRADE_YRATING(6.0,10.0),
		XRATING_YPERCENTAGE(10.0,100.0),
		XRATING_YGRADE(10.0,6.0);
		
		private double maxScaleY;
		private double maxScaleX;
	    StatisticResulType(double max) {
	        maxScaleY = max;
	        maxScaleX = max;
	    }
	    
	    StatisticResulType(double maxX, double maxY) {
	    	maxScaleX = maxX;
	    	maxScaleY = maxY;
	    }

	    double maxScaleY() {return maxScaleY;}
	    double maxScaleX() {return maxScaleX;}
	    double maxScale() {
	    	if(maxScaleX == maxScaleY) {
	    		return maxScaleX;
	    	} else {
	    		return -1;
	    	}
	    }
	}

	protected static String TITLE;
	protected static int WIDTH;
	protected static int HEIGHT;
	private float mDensity;
	protected AbstractGraphChart mChart;
	protected RadarChart mRadarChart;
	protected StatisticResulType mType;
	private boolean isLayoutSet;
	
	public AbstractChart(String title, StatisticResulType type) {
		TITLE = title;
		mType = type;
		isLayoutSet = false;
	}
	
	public int getHeight() {return HEIGHT;}
	public int getWidth() {return WIDTH;}
	public float getDensity() {return mDensity;}
	public String getTitle() {return TITLE;}
	public void setSize(int width, int height, float density) {
		if (density > 0.f) {
			mDensity = density;
			WIDTH = (int) (width/mDensity);
			HEIGHT = (int) (height/mDensity);
		}
	}
	
	public void setLayout() {

		mChart.setTitle(TITLE, BLACK, 25);
		mChart.setBackgroundFill(Fills.newSolidFill(WHITE));
//		LinearGradientFill fill = Fills.newLinearGradientFill(90, LIGHTCORAL, 100);
//		fill.addColorAndOffset(WHITE, 0);
//		mChart.setAreaFill(fill);
		isLayoutSet=true;
	}
	

	public void setLayoutRadar() {

		mRadarChart.setTitle(TITLE, BLACK, 25);
		mRadarChart.setSize(WIDTH,HEIGHT);
	}
	
	
	public String getUrl() {
		if(!isLayoutSet) {
			setLayout();
		}
		setsize();
	    String url = mChart.toURLString();
		return normalize(url);
	}
	
	private void setsize() {
		mChart.setSize(WIDTH,HEIGHT);
	}

	protected abstract void createchart();
	protected abstract void setData(JSONObject data) throws JSONException;
	public abstract String getData();
	
}
