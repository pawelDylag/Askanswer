package com.aghacks.askanswer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.aghacks.askanswer.R;
import com.aghacks.askanswer.data.Poll;
import com.aghacks.askanswer.http.AskQuestion;

import java.util.ArrayList;

public class YourPolls extends ArrayAdapter<Poll> {

    // declaring our ArrayList of items
    private ArrayList<Poll> polls;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public YourPolls(Context context, int textViewResourceId, ArrayList<Poll> polls) {
        super(context, textViewResourceId, R.id.answerText, polls);
        this.polls = polls;
    }

    public void addPoll(Poll p) {
        polls.add(p);
        notifyDataSetChanged();
    }

    /*
     * we are overriding the getView method here - this is what defines how each
     * list item will look.
     */
    public View getView(final int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.poll_view, null);
        }

        Poll i = polls.get(position);

        if (i != null) {
            TextView tt = (TextView) v.findViewById(R.id.questionLabel);
            Button sendButton = (Button) v.findViewById(R.id.sendButton);
            sendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AskQuestion.INSTANCE.request("442023991", 1, polls.get(position).getLength(), polls.get(position).getQuestion(), polls.get(position).getAnswers());
                }
            });
            if (tt != null){
                tt.setText(i.getQuestion());
            }

        }

        return v;

    }

}