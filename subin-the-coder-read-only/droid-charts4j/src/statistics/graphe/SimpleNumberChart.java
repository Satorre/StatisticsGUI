package statistics.graphe;

import org.json.JSONException;
import org.json.JSONObject;

public class SimpleNumberChart extends AbstractChart {
	
	private String mNumber;

	public SimpleNumberChart(String title, String number, StatisticResulType type) {
		super(title, type);
		mNumber = number;
	}
	
	@Override
	public String getData() {
		switch(mType) {
		case PERCENTAGE :
			return mNumber + " %";
		case GRADE :
			return mNumber;
		default : 
			return null;
		}
	}

	@Override
	public String getUrl() {return null;}

	@Override
	protected void createchart() {}

	@Override
	protected void setData(JSONObject data) throws JSONException {

	}
	
}
