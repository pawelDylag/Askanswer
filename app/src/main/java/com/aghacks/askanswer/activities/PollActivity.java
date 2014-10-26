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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.aghacks.askanswer.R;
import com.aghacks.askanswer.adapters.PlaceAdapter;
import com.aghacks.askanswer.adapters.PollViewAdapter;
import com.aghacks.askanswer.adapters.YourPolls;
import com.aghacks.askanswer.data.Poll;
import com.aghacks.askanswer.fragments.SendPollDialog;
import com.aghacks.askanswer.http.AskQuestion;
import com.aghacks.askanswer.http.EndQuestion;
import com.aghacks.askanswer.http.GetBeacon;
import com.aghacks.askanswer.http.RegisterBeacon;
import com.aghacks.askanswer.services.TrackService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PollActivity extends Activity {

    private String name;
    static  String beaconId;
    private int pollCounter;
    private YourPolls adapter;
    private EditText nameText;
    private Spinner chooseBeacons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        ListView lv = (ListView) findViewById(R.id.pollListView);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("poll"));
        LocalBroadcastManager.getInstance(this).registerReceiver(sendPollReceiver,
                new IntentFilter("toSend"));
        pollCounter = 0;
        chooseBeacons = (Spinner) findViewById(R.id.beacon_spinner);

        Button addPoll = (Button) findViewById(R.id.addButton);
        addPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = SendPollDialog.newInstance();
                newFragment.show(getFragmentManager(), "dialog");

            }
        });

       nameText = (EditText) findViewById(R.id.editName);

        final List<String> list = new LinkedList<String>();
        list.addAll(TrackService.beaconIds);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseBeacons.setAdapter(dataAdapter);
        chooseBeacons.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                beaconId = chooseBeacons.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

    private BroadcastReceiver sendPollReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Poll message = (Poll) intent.getSerializableExtra("poll");
            if (registerBeacon()) {
                AskQuestion.INSTANCE.request(beaconId, pollCounter, message.getLength(), message.getQuestion(), message.getAnswers());
                getStats();
            } else Toast.makeText(context, "Fill name and beacon.", Toast.LENGTH_SHORT).show();
        }
    };

    public boolean registerBeacon () {
        if (chooseBeacons.getSelectedItem() != null) {
            String s = (String) chooseBeacons.getSelectedItem();
            if (nameText.getText().toString().length() != 0) {
                String n = nameText.getText().toString();
                RegisterBeacon.INSTANCE.request(s, n);
                return true;
            }
        } return false;
    }

    public void getStats() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                EndQuestion.INSTANCE.request(beaconId, pollCounter);
                pollCounter++;
            }
        }, 15000);
    }


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
