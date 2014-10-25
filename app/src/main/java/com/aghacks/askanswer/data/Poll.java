package com.aghacks.askanswer.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by paweldylag on 25/10/14.
 */

public class Poll implements Serializable{

    private String question;
    private ArrayList<String> answers;
    private long length;
    private long launchedAt;
    private String id;
    private boolean done;

    public Poll (String q, ArrayList<String> a, int l, int la) {
        this.question = q;
        this.answers = a;
        this.length = l;
        this.launchedAt = la;
    }

    public Poll (JSONObject json) {
        try {
            this.question = json.getString("question_str");
            JSONArray jsonAnswers = json.getJSONArray("question_answers");
            ArrayList<String> answers = new ArrayList<String>();
            for (int i = 0; i < jsonAnswers.length(); i++) {
                answers.add(jsonAnswers.get(i).toString());
            }
            this.answers = answers;
            this.length = 1000 * 30;
            this.launchedAt = System.currentTimeMillis();
            this.done = false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getLaunchedAt() {
        return launchedAt;
    }

    public void setLaunchedAt(long launchedAt) {
        this.launchedAt = launchedAt;
    }

    public int getAnswersNumber() {
        return answers.size();
    }

    public ArrayList<String> getAnswers () {
        return this.answers;
    }


}
