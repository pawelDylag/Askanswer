package com.aghacks.askanswer.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentQuestion extends HttpRequestorAbs{
    public static CurrentQuestion INSTANCE = new CurrentQuestion();

    public void request(int id) {
        String url = BASE_URL+"current_question/";
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
