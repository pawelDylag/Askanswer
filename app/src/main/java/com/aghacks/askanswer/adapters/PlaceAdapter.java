package com.aghacks.askanswer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aghacks.askanswer.R;
import com.aghacks.askanswer.data.Place;

import java.util.ArrayList;

/**
 * Created by paweldylag on 25/10/14.
 */
public class PlaceAdapter extends ArrayAdapter<Place> {

    // declaring our ArrayList of items
    private ArrayList<Place> places;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public PlaceAdapter(Context context, int textViewResourceId, ArrayList<Place> places) {
        super(context, textViewResourceId, R.id.answerText, places);
        this.places = places;
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
            v = inflater.inflate(R.layout.place_layout, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        String i = places.get(position).getName();

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView tt = (TextView) v.findViewById(R.id.placeLabel);
            tt.setText(i);

        }

        // the view must be returned to our activity
        return v;

    }

}