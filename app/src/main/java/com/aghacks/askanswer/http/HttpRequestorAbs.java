package com.aghacks.askanswer.http;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpRequestorAbs {
    private static void getResponse(final String url, final JSONObject parameters) {
        new AsyncTask<Void, Void, HttpResponse>() {
            @Override
            protected HttpResponse doInBackground(final Void... params) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpost = new HttpPost(url);
                StringEntity se = null;
                try {
                    se = new StringEntity(parameters.toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                httpost.setEntity(se);
                httpost.setHeader("Accept", "application/json");
                httpost.setHeader("Content-type", "application/json");
                try {
                    return httpclient.execute(httpost);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(HttpResponse httpResponse) {
                HttpEntity entity = httpResponse.getEntity();
                try {
                    String resp = EntityUtils.toString(entity);
                    Log.i("Request Test", "Response: " + resp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    public static String registerBeacon(int id, String name) {
        String url = "http://104.131.177.52:80/register_beacon/";
        JSONObject parameters = new JSONObject();
        try {
            parameters.put("id", id);
            parameters.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("Request Test", "Address: " + url);
        Log.i("Request Test", "Request: " + parameters.toString());
        getResponse(url, parameters);
        return "";
    }
}