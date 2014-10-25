package com.aghacks.askanswer.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class GetBeacon extends HttpRequestorAbs{
    public static GetBeacon INSTANCE = new GetBeacon();

    public void request(int id) {
        String url = BASE_URL+"get_beacon/";
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
        Log.i("GetBeacon", response);
//        {
//            "error": false,
//            "name": "Tester"
//        }
    }
}
