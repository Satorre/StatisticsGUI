package statistic.entry;

import org.json.JSONException;
import org.json.JSONObject;

import statistic.shared.GlobalStatistics;
import statistics.graphe.AbstractChart.StatisticResulType;
import statistics.graphe.CustomBarChart;
import statistics.graphe.CustomPieChart;
import statistics.graphe.CustomRadarChart;
import statistics.graphe.CustomScatterChart;
import statistics.graphe.CustomLineChart;
import statistics.graphe.SimpleNumberChart;
import statistics.showstatistics.StatisticActivity;
import statistics.showstatistics.OneNumberActivity;

import com.coders.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    public void bar(View view) {
					
			String[] bars = {"6-5","5-4","4-3","3-2","2-0"};
			double[] val1 = {50,40,60,30,50,60,40};
			double[] val2 = {20,40,30,40,10,10,50};
			double[] val3 = {15,10,5,20,10,20,5};
			double[] val4 = {10,5,3,5,20,5,4};
			double[] val5 = {5,5,2,5,10,5,1};
			String[] xAxis ={"2011","2010","2009","2008","2007"};

			JSONObject data = new JSONObject();

			try {
				for(int i = bars.length-1; i>=0 ; i--) {
						data.accumulate("plots", bars[i]);
						data.accumulate(bars[0], val1[i]);
						data.accumulate(bars[1], val2[i]);
						data.accumulate(bars[2], val3[i]);
						data.accumulate(bars[3], val4[i]);
						data.accumulate(bars[4], val5[i]);
						data.accumulate("xAxis", xAxis[i]);
				}
				data.accumulate("yAxisLabel","Grades distribution");
			GlobalStatistics.GlobalChart = new CustomBarChart("Course X", data, StatisticResulType.PERCENTAGE);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Intent displayActivityIntent = new Intent(this,	StatisticActivity.class);
		startActivity(displayActivityIntent); 
    }
    
public void scatter(View view) {

			double[] xValue = {10,50,80,95,15,95,20,95};
			double[] yValue = {50,20,10,13,95,13,80,13};

			JSONObject data = new JSONObject();

			try {
				for(int i = 0; i< yValue.length; i++) {
						data.accumulate("xValue", 6*xValue[i]/100);
						data.accumulate("yValue", yValue[i]);
				}
				
			GlobalStatistics.GlobalChart = new CustomScatterChart("Test", data, StatisticResulType.XGRADE_YPERCENTAGE);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Intent displayActivityIntent = new Intent(this,	StatisticActivity.class);
		startActivity(displayActivityIntent); 
    }
    
	public void number(View view) {
	   
		GlobalStatistics.GlobalChart = new SimpleNumberChart("Percentagge of success in SDP course in 2012", "100", StatisticResulType.PERCENTAGE);
 	
		Intent displayActivityIntent = new Intent(this,	OneNumberActivity.class);
		startActivity(displayActivityIntent);     
	}   
	
	public void pie(View view) {
		
		double[] percentage = {10,20,5,40,15,10};
		String[] label = {"girl", "mouse", "genious","boy","idiot","bi" };

		JSONObject data = new JSONObject();

		try {
			for(int i = 0; i< label.length; i++) {
					data.accumulate("label", label[i]);
					data.accumulate("percentage", percentage[i]);
			}
			
			GlobalStatistics.GlobalChart = new CustomPieChart("Population of EPFL", data, StatisticResulType.PERCENTAGE);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent displayActivityIntent = new Intent(this,	StatisticActivity.class);
		startActivity(displayActivityIntent);     
	}
	
	public void radar(View view) {
		
		double[] data = {5.5,4,5,5,4.5};
		String[] label = {"Teacher", "Docs", "Ex/Labs/Tp","cost","TA" };

		JSONObject datas = new JSONObject();

		try {
			for(int i = 0; i< label.length; i++) {
					datas.accumulate("label", label[i]);
					datas.accumulate("data", data[i]);
			}
			
			GlobalStatistics.GlobalChart = new CustomRadarChart("Statistics of SDP Course", datas, StatisticResulType.GRADE);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent displayActivityIntent = new Intent(this,	StatisticActivity.class);
		startActivity(displayActivityIntent);     
	}
	
	
	public void linear(View view) {
		
		String[] bars = {"6-5","5-4","4-3","3-2","2-0"};
		double[] val1 = {50,40,60,30,50,60,40};
		double[] val2 = {20,40,30,40,10,10,50};
		double[] val3 = {15,10,5,20,10,20,5};
		double[] val4 = {10,5,3,5,20,5,4};
		double[] val5 = {5,5,2,5,10,5,1};
		String[] xAxis ={"2011","2010","2009","2008","2007"};

		JSONObject data = new JSONObject();

		try {
			for(int i = bars.length-1; i>=0 ; i--) {
					data.accumulate("plots", bars[i]);
					data.accumulate(bars[0], val1[i]);
					data.accumulate(bars[1], val2[i]);
					data.accumulate(bars[2], val3[i]);
					data.accumulate(bars[3], val4[i]);
					data.accumulate(bars[4], val5[i]);
					data.accumulate("xAxis", xAxis[i]);
			}
			data.accumulate("yAxisLabel","Grades distribution");
			GlobalStatistics.GlobalChart = new CustomLineChart("Statistics of SDP Course", data, StatisticResulType.PERCENTAGE);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent displayActivityIntent = new Intent(this,	StatisticActivity.class);
		startActivity(displayActivityIntent);     
	}
}
