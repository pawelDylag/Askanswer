package com.aghacks.askanswer.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.aghacks.askanswer.R;
import com.aghacks.askanswer.http.AskQuestion;
import com.aghacks.askanswer.http.CurrentQuestion;
import com.aghacks.askanswer.http.GetBeacon;
import com.aghacks.askanswer.http.HttpRequestorAbs;
import com.aghacks.askanswer.http.RegisterBeacon;
import com.aghacks.askanswer.http.SubmitAnswer;

import java.util.ArrayList;
import java.util.List;

import data.Place;


public class IntroActivity extends ListActivity {

    private ArrayList<Place> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

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
            RegisterBeacon.INSTANCE.request(1, "Tester");
            GetBeacon.INSTANCE.request(1);

            List<String> answers = new ArrayList<String>();
            answers.add("a");
            answers.add("b");
            answers.add("c");
            long endtime = System.currentTimeMillis() - 1000 * 60;
            AskQuestion.INSTANCE.request(1, 1, endtime, "Q?", answers);

            SubmitAnswer.INSTANCE.request(1, "b");
            CurrentQuestion.INSTANCE.request(1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
