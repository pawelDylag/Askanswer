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

public abstract class HttpRequestorAbs {

    protected static final String BASE_URL = "http://104.131.177.52:80/";

    protected void getResponse(final String url, final JSONObject parameters) {
        new AsyncTask<Void, Void, HttpResponse>() {
            @Override
            protected HttpResponse doInBackground(final Void... params) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpost = new HttpPost(url);
                StringEntity se = null;
                try {
                    String msgToSend = parameters.toString();
                    se = new StringEntity(msgToSend);
                    Log.i("Sending:", msgToSend);
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
                    processResponse(resp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    public abstract void processResponse(String response);
}