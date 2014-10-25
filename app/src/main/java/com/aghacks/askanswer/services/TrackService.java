package com.aghacks.askanswer.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.aghacks.askanswer.Settings;
import com.aghacks.askanswer.http.GetBeacon;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackService extends Service {
    private static final String TAG = TrackService.class.getSimpleName();

    private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId", Settings.PROXIMITY_UUID, null, null);
    private static List<Beacon> currentBeacons = new ArrayList<Beacon>();
    public static Map<String, String> nameMap = new HashMap<String, String>();
    private final BeaconManager beaconManager = new BeaconManager(this);
    private final Handler handler = new Handler();

    public TrackService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "TrackService: Create ranging service");
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, final List<Beacon> beacons) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        currentBeacons = beacons;
                        for (Beacon beacon : beacons) {
                            new GetBeacon().request(beacon.getMajor() + "" + beacon.getMinor());
                        }
                    }
                });
            }
        });
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                try {
                    beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
                    Log.i(TAG, "TrackService: Start ranging");
                } catch (RemoteException e) {
                    Log.e(TAG, "TrackService: Cannot start ranging", e);
                }
            }
        });

        return Service.START_NOT_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();
        beaconManager.disconnect();
        Toast.makeText(this, "TrackService: Service stopped", Toast.LENGTH_LONG).show();
    }

    void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }
}