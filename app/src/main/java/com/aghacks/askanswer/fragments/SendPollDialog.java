package com.aghacks.askanswer.fragments;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aghacks.askanswer.R;
import com.aghacks.askanswer.data.App;
import com.aghacks.askanswer.data.Poll;
import com.aghacks.askanswer.http.AskQuestion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paweldylag on 25/10/14.
 */
public class SendPollDialog extends DialogFragment {

    private EditText questionEdit, a, b, c, d;
    private Button submit, cancel;

    public SendPollDialog() {
        // Empty constructor required for DialogFragment
    }

    public static SendPollDialog newInstance() {
        return new SendPollDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_poll , container);
        questionEdit = (EditText) view.findViewById(R.id.questionEdit);
        a = (EditText) view.findViewById(R.id.a);
        b = (EditText) view.findViewById(R.id.b);
        c = (EditText) view.findViewById(R.id.c);
        d = (EditText) view.findViewById(R.id.d);
        submit = (Button) view.findViewById(R.id.button_submit);
        cancel = (Button) view.findViewById(R.id.button_cancel);

        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> answers = new ArrayList<String>();
                if ( a.getText().length() != 0){
                    answers.add(a.getText().toString());
                }
                if ( b.getText().length() != 0){
                    answers.add(b.getText().toString());
                }
                if ( c.getText().length() != 0){
                    answers.add(c.getText().toString());
                }
                if ( d.getText().length() != 0){
                    answers.add(d.getText().toString());
                }

                long endtime = System.currentTimeMillis() + 1000 * 60;
                Poll poll = new Poll(questionEdit.getText().toString(), answers, endtime, 0);
                Intent intent = new Intent("poll");
                intent.putExtra("poll", poll);
                LocalBroadcastManager.getInstance(App.getObj()).sendBroadcast(intent);
                getDialog().dismiss();
            }
        });

        cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        getDialog().setTitle("Enter question and answers");

        return view;
    }


}