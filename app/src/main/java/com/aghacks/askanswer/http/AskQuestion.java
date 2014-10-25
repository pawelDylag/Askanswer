package com.aghacks.askanswer.http;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AskQuestion extends HttpRequestorAbs {
    public static AskQuestion INSTANCE = new AskQuestion();

    public void request(String id, int pid, long endtime, String question, List<String> answersList) {
        String url = BASE_URL+"ask_question/";
        JSONObject parameters = new JSONObject();
        JSONArray answers = new JSONArray();
        try {
            parameters.put("id", id);
            parameters.put("pid", pid);
            parameters.put("endtime", endtime);
            parameters.put("question", question);
            for (String answer : answersList) {
                answers.put(answer);
            }
            parameters.put("answers", answers);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getResponse(url, parameters);
    }

    @Override
    public void processResponse(String response) {
        Log.i("AskQuestion", response);
//        {
//            "error": false
//        }
    }
}
