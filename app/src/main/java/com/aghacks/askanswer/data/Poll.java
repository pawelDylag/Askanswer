package com.aghacks.askanswer.data;

import java.util.ArrayList;

/**
 * Created by paweldylag on 25/10/14.
 */

public class Poll {

    private String question;
    private ArrayList<String> answers;
    private int length;
    private int launchedAt;
    private boolean done;

    public Poll (String q, ArrayList<String> a, int l, int la) {
        this.question = q;
        this.answers = a;
        this.length = l;
        this.launchedAt = la;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLaunchedAt() {
        return launchedAt;
    }

    public void setLaunchedAt(int launchedAt) {
        this.launchedAt = launchedAt;
    }

    public int getAnswersNumber() {
        return answers.size();
    }

    public ArrayList<String> getAnswers () {
        return this.answers;
    }


}
