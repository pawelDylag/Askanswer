package com.aghacks.askanswer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aghacks.askanswer.R;


public class IntroActivity extends Activity {

    private final String TAG = this.getLocalClassName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        setButtons();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setButtons() {
        Button button_q = (Button) findViewById(R.id.button_q);
        Button button_r = (Button) findViewById(R.id.button_r);

        button_q.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "button_q onClick ");
            }
        });

        button_r.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "button_r onClick ");
            }
        });

    }
}
