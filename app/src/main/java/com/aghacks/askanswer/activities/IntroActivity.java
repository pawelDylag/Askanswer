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

import java.util.ArrayList;

import com.aghacks.askanswer.data.Place;
import com.aghacks.askanswer.data.UserData;


public class IntroActivity extends ListActivity {

    private ArrayList<Place> places;
    private ArrayAdapter<String> adapter;
    private UserData userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        places = new ArrayList<Place>();
        if (getIntent().hasExtra("userData")) {
            userData = (UserData) getIntent().getSerializableExtra("userData");
        } else userData = new UserData();

        // debug:
        places.add(new Place("Pan Mariusz"));
        places.add(new Place("Matematyka dyskretna wyklad"));
        //
        ArrayList <String> placeLabel = new ArrayList<String>();
        for(Place x: places) {
            String s = x.getName();
            placeLabel.add(s);
        }
        adapter  = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, placeLabel);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);

        // TODO: tu nalezy zmienic na pobieranie place z tablicy
        userData.changeMonitoredPlace(new Place(item));
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
