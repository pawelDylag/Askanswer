package com.aghacks.askanswer.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterBeacon extends HttpRequestorAbs{
    public static RegisterBeacon INSTANCE = new RegisterBeacon();

    public void request(int id, String name) {
        String url = BASE_URL+"register_beacon/";
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("id", id);
            parameters.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getResponse(url, parameters);
    }

    @Override
    public void processResponse(String response) {
        Log.i("RegisterBeacon", response);
//        {
//            "err": false
//        }
    }
}
