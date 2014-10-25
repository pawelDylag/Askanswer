package com.aghacks.askanswer.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SubmitAnswer extends HttpRequestorAbs{
    public static SubmitAnswer INSTANCE = new SubmitAnswer();

    public void request(String id, String answer_id) {
        String url = BASE_URL+"submit_answer/";
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("id", id);
            parameters.put("answer_id", answer_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getResponse(url, parameters);
    }

    @Override
    public void processResponse(String response) {
        Log.i("SubmitAnswer", response);
//        {
//            "error": false
//        }
//        {
//            "error": true,
//            "error_str": "Too late"
//        }
    }
}
