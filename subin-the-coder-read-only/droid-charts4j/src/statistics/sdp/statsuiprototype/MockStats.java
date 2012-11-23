package statistics.sdp.statsuiprototype;

import org.json.JSONException;
import org.json.JSONObject;

import statistic.shared.GlobalStatistics;
import statistics.graphe.CustomBarChart;
import statistics.graphe.CustomRadarChart;
import statistics.graphe.AbstractChart.StatisticResulType;

public class MockStats {

	public void getData(String statsDefinition, Statistics statActivity) {

		if(statsDefinition.matches("Distribution\\S*by grades\\S*Sweng\\S*\\(over the years\\)")) {
			
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
						data.accumulate("bars", bars[i]);
						data.accumulate(bars[0], val1[i]);
						data.accumulate(bars[1], val2[i]);
						data.accumulate(bars[2], val3[i]);
						data.accumulate(bars[3], val4[i]);
						data.accumulate(bars[4], val5[i]);
						data.accumulate("xAxis", xAxis[i]);
				}
				data.accumulate("yAxisLabel","Grades distribution (%)");
			GlobalStatistics.GlobalChart = new CustomBarChart("Course : Sweng", data, StatisticResulType.PERCENTAGE);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else {

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
		}
		
		
		statActivity.displayStats();
	}

}
