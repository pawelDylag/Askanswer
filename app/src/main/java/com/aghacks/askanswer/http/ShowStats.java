package com.aghacks.askanswer.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowStats extends HttpRequestorAbs{
    public static ShowStats INSTANCE = new ShowStats();

    public void request(String id, int pid) {
        String url = BASE_URL+"show_stats/";
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("id", id);
            parameters.put("pid", pid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getResponse(url, parameters);
    }

    @Override
    public void processResponse(String response) {
        Log.i("Show stats", response);
    }
}
