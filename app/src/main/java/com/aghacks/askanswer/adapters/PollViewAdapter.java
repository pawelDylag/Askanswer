package com.aghacks.askanswer.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aghacks.askanswer.R;

import java.util.ArrayList;

/**
 * Created by paweldylag on 25/10/14.
 */
public class PollViewAdapter extends ArrayAdapter<String> {

    // declaring our ArrayList of items
    private ArrayList<String> answers;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public PollViewAdapter(Context context, int textViewResourceId, ArrayList<String> answers) {
        super(context, textViewResourceId, R.id.answerText, answers);
        this.answers = answers;
    }

    /*
     * we are overriding the getView method here - this is what defines how each
     * list item will look.
     */
    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.poll_answer, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        String i = answers.get(position);

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView tt = (TextView) v.findViewById(R.id.answerText);
            TextView tl = (TextView) v.findViewById(R.id.answerLabel);

            // check to see if each individual textview is null.
            // if not, assign some text!
            if (tt != null){
                tt.setText(i);
            }
            if (tl != null){
                switch (position) {
                    case 0:
                        tl.setText("A.");
                        break;
                    case 1:
                        tl.setText("B.");
                        break;
                    case 2:
                        tl.setText("C.");
                        break;
                    case 3:
                        tl.setText("D.");
                        break;
                    default:
                        tl.setText(""+position);
                        break;
                }



            }
        }

        // the view must be returned to our activity
        return v;

    }

}