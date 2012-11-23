package statistics.showstatistics;

import statistic.entry.MainActivity;
import statistic.shared.GlobalStatistics;

import com.coders.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class OneNumberActivity extends Activity {
	
	private TextView mTitle;
	private TextView mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_number);
        
        mTitle = (TextView) findViewById(R.id.number_title);
        mData = (TextView) findViewById(R.id.number_area);

        mTitle.setText(GlobalStatistics.GlobalChart.getTitle());
        mData.setText(GlobalStatistics.GlobalChart.getData());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_one_number, menu);
        return true;
    }
    
    void export(View view) {
    	
    }
    
    public void  newStat(View view) {
    	Intent displayActivityIntent = new Intent(this,	MainActivity.class);
		startActivity(displayActivityIntent); 
    }
}
