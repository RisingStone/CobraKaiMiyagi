package cobrakai.com.miyagi.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import cobrakai.com.miyagi.network.Webservice;
import cobrakai.com.miyagi.views.ColorStrobeActivity;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class EnteringGeoFenceService extends Service {
    private static final String TAG = EnteringGeoFenceService.class.getSimpleName();
    public EnteringGeoFenceService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate -- START");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand -- START");

        final String hubId = intent.getStringExtra("hubId");

        Log.d(TAG, "hubId: " + hubId);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Log.d(TAG, "Bring app back to forground");
//                Intent intent = new Intent(getApplicationContext(), ColorStrobeActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(intent);

                //Enter que after 10 seconds
                Webservice.queRider(hubId, "Gary");
                stopSelf();
            }
        }, 10000);

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy -- START");

        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged -- START");

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        Log.d(TAG, "onLowMemory -- START");

        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.d(TAG, "onTrimMemory -- START");

        super.onTrimMemory(level);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind -- START");

        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind -- START");

        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind -- START");

        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.d(TAG, "onTaskRemoved -- START");

        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        Log.d(TAG, "dump -- START");
        super.dump(fd, writer, args);
    }
}
