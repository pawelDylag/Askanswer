package com.aghacks.askanswer.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.aghacks.askanswer.Settings;
import com.aghacks.askanswer.http.CurrentQuestion;

import java.util.Timer;
import java.util.TimerTask;

public class PollService extends Service {

    private static final String TAG = PollService.class.getSimpleName();
    private static Timer timer;
    private String id = "";

    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "PollService: onCreate");
    }

    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        Log.i(TAG, "PollService: StartService");
        timer = new Timer();
        timer.scheduleAtFixedRate(new dataUpdate(), 0, Settings.INTERVAL_TIME);
        id = intent.getStringExtra("id");
        return Service.START_NOT_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }

    private class dataUpdate extends TimerTask {
        @Override
        public void run() {
            CurrentQuestion.INSTANCE.request(id);
        }
    }


}

