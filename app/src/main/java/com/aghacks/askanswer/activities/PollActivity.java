package com.aghacks.askanswer.activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.aghacks.askanswer.R;
import com.aghacks.askanswer.adapters.PlaceAdapter;
import com.aghacks.askanswer.adapters.PollViewAdapter;
import com.aghacks.askanswer.adapters.YourPolls;
import com.aghacks.askanswer.data.Poll;
import com.aghacks.askanswer.fragments.SendPollDialog;
import com.aghacks.askanswer.http.GetBeacon;
import com.aghacks.askanswer.http.RegisterBeacon;
import com.aghacks.askanswer.services.TrackService;

import java.util.ArrayList;

public class PollActivity extends Activity {

    private String name;
    private String beaconId;
    private EditText beaconChooser;
    private YourPolls adapter;
    private EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        ListView lv = (ListView) findViewById(R.id.pollListView);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("poll"));

        Button addPoll = (Button) findViewById(R.id.addButton);
        addPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = SendPollDialog.newInstance();
                newFragment.show(getFragmentManager(), "dialog");

            }
        });

        EditText editName = (EditText) findViewById(R.id.editName);
        EditText chooseBeacons = (EditText) findViewById(R.id.beacon_spinner);
        chooseBeacons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        // TODO: Dodac zapisywanie polli i pobieranie z pliku
        adapter = new YourPolls(this,
                R.layout.place_layout, new ArrayList<Poll>());
        lv.setAdapter(adapter);

    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Poll message = (Poll) intent.getSerializableExtra("poll");
            Log.d("NEWPOLL", message.getQuestion());
            adapter.addPoll(message);

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.poll, menu);
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
}
