package statistics.showstatistics;

import com.coders.R;

import statistic.entry.MainActivity;
import statistic.shared.GlobalStatistics;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebView;

public class StatisticActivity extends Activity {
	
	
	private int height;
	private int width;
	private float density;
	private WebView statArea;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charts4j);
		statArea = (WebView) findViewById(R.id.statistic_area);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		setGrapheSize();
		displayStat();
	}

	private void setGrapheSize() {
		width = statArea.getWidth();
		height = statArea.getHeight();
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		density = metrics.density;
		if (density > 0.f) {
			GlobalStatistics.GlobalChart.setSize(width, height, density);
		}
	}

	
	private void displayStat() {
		statArea.loadUrl(GlobalStatistics.GlobalChart.getUrl());
	}
	
	
	
	public void export(View view) {

	}
	
    public void  newStat(View view) {
    	Intent displayActivityIntent = new Intent(this,	MainActivity.class);
		startActivity(displayActivityIntent); 
    }
	
	
}