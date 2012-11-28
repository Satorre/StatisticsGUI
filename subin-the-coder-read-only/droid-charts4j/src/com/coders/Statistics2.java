package com.coders;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Statistics2 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_statistics2, menu);
        return true;
    }
}
