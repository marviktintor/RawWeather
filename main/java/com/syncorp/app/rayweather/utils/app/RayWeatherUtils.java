package com.syncorp.app.rayweather.utils.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.syncorp.app.coreutils.utils.Utilities;
import com.syncorp.app.rayweather.MainActivity;
import com.syncorp.app.rayweather.R;

/**
 * Created by victor on 3/31/2016.
 */
public class RayWeatherUtils {
    public static void updateWeather(Context applicationContext) {


        notify(applicationContext, 2, "Weather", "Information has been updated at " + new Utilities(applicationContext).getDateProperty("hh:mm a", System.currentTimeMillis()));
    }


    public static void updateForecast(Context applicationContext) {
        notify(applicationContext, 2, "Forecast", "Information has been updated at " + new Utilities(applicationContext).getDateProperty("hh:mm a", System.currentTimeMillis()));
    }

    private static void notify(Context applicationContext, int notificationId, String title, String message) {
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(applicationContext);
        notificationCompat.mContentTitle = title;
        notificationCompat.mContentText = message;


        Intent intent = new Intent(applicationContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationCompat.addAction(R.mipmap.ic_launcher, "Open Application", PendingIntent.getActivity(applicationContext, 1,
                intent, PendingIntent.FLAG_UPDATE_CURRENT));
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationCompat.build());
    }

}
