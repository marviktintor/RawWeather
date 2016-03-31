package com.syncorp.app.rayweather.services;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.syncorp.app.coreutils.utils.Utilities;
import com.syncorp.app.rayweather.MainActivity;
import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.constants.AppConstants;

/**
 * Created by victor on 3/31/2016.
 */
public class WeatherUpdateService extends Service {

    private Utilities utilities;

    @Override
    public void onCreate() {
        super.onCreate();
        utilities = new Utilities(WeatherUpdateService.this);
        sendNotification("Ray Weather", "Ray Weather is running");
    }

    private void sendNotification(String title, String message) {
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(WeatherUpdateService.this);
        notificationCompat.mContentTitle = title;
        notificationCompat.mContentText = message;
        notificationCompat.mLargeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationCompat.addAction(R.mipmap.ic_launcher, "Open Application", PendingIntent.getActivity(WeatherUpdateService.this, 1,
                intent, PendingIntent.FLAG_UPDATE_CURRENT));
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationCompat.build());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 3000, 10000, PendingIntent.getBroadcast(getApplicationContext(), 1,
                new Intent(AppConstants.Intents.WeatherUpdates.ACTION_REQUEST_NEW_WEATHER_UPDATES), PendingIntent.FLAG_UPDATE_CURRENT));
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sendNotification("Ray Weather", "All daemons killed");
    }

}
