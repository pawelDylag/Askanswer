package com.aghacks.askanswer.http;

import android.util.Log;

import com.aghacks.askanswer.Settings;

import org.json.JSONException;
import org.json.JSONObject;

public class EndQuestion extends HttpRequestorAbs{
    public static EndQuestion INSTANCE = new EndQuestion();

    private String id;
    private int pid;

    public void request(String id, int pid) {
        this.id = id;
        this.pid = pid;
        String url = BASE_URL+"stop_question/";
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
        Log.i("End question", response);
        ShowStats.INSTANCE.request(id, pid);
    }
}
