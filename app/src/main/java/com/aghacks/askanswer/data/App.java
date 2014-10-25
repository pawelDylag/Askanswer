package com.aghacks.askanswer.data;

import android.app.Application;

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    private static App obj;

    public static App getObj() {
        return obj;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        obj = this;
    }

}