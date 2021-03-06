package com.aghacks.askanswer.http;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.aghacks.askanswer.data.App;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentQuestion extends HttpRequestorAbs {
    public static CurrentQuestion INSTANCE = new CurrentQuestion();

    public void request(String id) {
        String url = BASE_URL + "current_question/";
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getResponse(url, parameters);
    }

    @Override
    public void processResponse(String response) {
        Log.i("CurrentQuestion", response);
        Intent intent = new Intent("new-poll");
        intent.putExtra("message", response);
        try {
            JSONObject json = new JSONObject(response);
            if (json.getBoolean("question_asked") == true) {
                LocalBroadcastManager.getInstance(App.getObj()).sendBroadcast(intent);
            }
        } catch ( JSONException e) {
            e.printStackTrace();
        }

//        {
//            "question_answers": {
//              "a": 0,
//              "b": 1,
//              "c": 0
//            },
//            "question_asked": true,
//            "question_str": "Q?"
//        }
    }
}
