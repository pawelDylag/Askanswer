package com.aghacks.askanswer.activities;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aghacks.askanswer.R;

import com.aghacks.askanswer.adapters.PlaceAdapter;
import com.aghacks.askanswer.adapters.PollViewAdapter;
import com.aghacks.askanswer.data.Place;
import com.aghacks.askanswer.data.Poll;
import com.aghacks.askanswer.data.UserData;
import com.aghacks.askanswer.http.AskQuestion;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private UserData userData;
    Fragment pollFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userData = (UserData) getIntent().getSerializableExtra("userData");

        TextView label = (TextView) findViewById(R.id.label);
        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToIntroActivity();
            }
        });
        label.setText("Current session: " + userData.getMonitoredPlace().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    /**
     * A placeholder fragment containing poll dialog.
     */
    public static class PollDialogFragment extends Fragment {

        private PollDialogFragment thisFragment;
        private Poll poll;
        private ListView listView;
        private ArrayAdapter<String> adapter;
        private boolean[] answersCache;

        public PollDialogFragment () {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments().containsKey("poll")) {
                poll = (Poll) getArguments().get("poll");
            } else poll = null;
            answersCache = new boolean [poll.getAnswersNumber()];
            thisFragment = this;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            listView = (ListView) rootView.findViewById(R.id.answersListView);
            adapter = new PollViewAdapter(getActivity(), R.layout.poll_answer, poll.getAnswers());
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (answersCache[i] != true) {
                        for (int j = 0; j < answersCache.length; j++) {
                            if (answersCache[j]) {
                                answersCache[j] = false;
                                View x = listView.getChildAt(j);
                                x.findViewById(R.id.answer_row).setBackgroundColor(getResources().getColor(R.color.background));
                            }
                        }
                        answersCache[i] = true;
                        view.findViewById(R.id.answer_row).setBackgroundColor(getResources().getColor(R.color.green));
                    } else {
                        // TODO: uzupełnić 2 pierwsze dane

                        Toast.makeText(getActivity(), "Answer sent: " + i, Toast.LENGTH_SHORT).show();
                        getActivity().getFragmentManager().beginTransaction().remove(thisFragment).commit();
                    }
                }
            });
            return rootView;
        }
    }


    public void returnToIntroActivity() {
        userData.changeMonitoredPlace(new Place(null, ""));
        if (pollFragment != null) {
            pollFragment = null;
        }
        Intent intent = new Intent(this.getApplicationContext(), IntroActivity.class);
        intent.putExtra("userData", userData);
        startActivity(intent);
        finish();
    }

    public void showNewPoll(Poll p) {
        pollFragment = new PollDialogFragment();
        Bundle b = new Bundle();
        b.putSerializable("poll", p);
        pollFragment.setArguments(b);
        getFragmentManager().beginTransaction()
                .add(R.id.container, pollFragment).commit();
    }

    public void dismissPoll() {
        getFragmentManager().beginTransaction().hide(pollFragment).commit();
    }
}
