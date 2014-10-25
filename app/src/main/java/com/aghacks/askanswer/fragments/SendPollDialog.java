package com.aghacks.askanswer.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aghacks.askanswer.R;
import com.aghacks.askanswer.http.AskQuestion;

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
                List<String> answers = new ArrayList<String>();
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
                AskQuestion.INSTANCE.request("442023991", 1, endtime, questionEdit.getText().toString(), answers);
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