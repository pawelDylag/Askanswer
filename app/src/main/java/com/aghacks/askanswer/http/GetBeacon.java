package com.aghacks.askanswer.http;

import android.util.Log;

import com.aghacks.askanswer.services.TrackService;

import org.json.JSONException;
import org.json.JSONObject;

public class GetBeacon extends HttpRequestorAbs{
    private String id = "";

    public void request(String id) {
        this.id = id;
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
        try {
            JSONObject json = new JSONObject(response);
            TrackService.nameMap.put(id, (String) json.get("name"));
        } catch (JSONException e) {
            //e.printStackTrace();
        }
//        {
//            "error": false,
//            "name": "Tester"
//        }
    }
}
