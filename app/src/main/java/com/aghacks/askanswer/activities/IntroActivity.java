package com.aghacks.askanswer.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.aghacks.askanswer.R;
import com.aghacks.askanswer.adapters.PlaceAdapter;
import com.aghacks.askanswer.http.AskQuestion;
import com.aghacks.askanswer.http.CurrentQuestion;
import com.aghacks.askanswer.http.GetBeacon;
import com.aghacks.askanswer.http.HttpRequestorAbs;
import com.aghacks.askanswer.http.RegisterBeacon;
import com.aghacks.askanswer.http.SubmitAnswer;
import com.aghacks.askanswer.services.TrackService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.aghacks.askanswer.data.Place;
import com.aghacks.askanswer.data.UserData;


public class IntroActivity extends ListActivity {


    public static ArrayAdapter<Place> adapter;
    private UserData userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra("userData")) {
            userData = (UserData) getIntent().getSerializableExtra("userData");
        } else userData = new UserData();
        Intent trackIntent = new Intent(getApplicationContext(), TrackService.class);
        getApplicationContext().startService(trackIntent);


        adapter = new PlaceAdapter(this,
                R.layout.place_layout, TrackService.places);
        setListAdapter(adapter);


    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Place item = (Place) getListAdapter().getItem(position);

        // TODO: tu nalezy zmienic na pobieranie place z tablicy
        userData.changeMonitoredPlace(item);
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        intent.putExtra("userData", userData);
        startActivity(intent);
        finish();
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
            //RegisterBeacon.INSTANCE.request("442023991", "Janusz Tester");
            new GetBeacon().request("442023991");

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
