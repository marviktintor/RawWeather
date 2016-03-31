package com.syncorp.app.rayweather.utils.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.syncorp.app.coreutils.downloader.Downloader;
import com.syncorp.app.coreutils.utils.Utilities;
import com.syncorp.app.rayweather.MainActivity;
import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.constants.AppConstants;
import com.syncorp.app.rayweather.extra.apis.openweathermap.OpenWeatherAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by victor on 3/31/2016.
 */
public class RayWeatherUtils {
    public static void updateWeather(final Context applicationContext) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String weatherDownloadUrl = OpenWeatherAPI.TODAY_WEATHER_URI;
                String storeFileName = AppConstants.WEATHER_TODAY_STORE_FILE;
                downloadWeatherJSON(applicationContext, weatherDownloadUrl, storeFileName);
            }
        }).start();

        notify(applicationContext, 2, "Weather", "Information has been updated at " + new Utilities(applicationContext).getDateProperty("hh:mm a", System.currentTimeMillis()));
        new Utilities(applicationContext).sendBroadcast(AppConstants.Intents.WeatherUpdates.ACTION_NEW_WEATHER_UPDATES);
    }

    private static void downloadWeatherJSON(Context applicationContext, String weatherDownloadUrl, String storeFileName) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        String geoGraphicalLocation = sharedPrefs.getString(AppConstants.Preferences.GEO_LOCATION, "Kakamega");
        weatherDownloadUrl = weatherDownloadUrl.replace(OpenWeatherAPI.Tags.GEO_LOCATION, geoGraphicalLocation);
        Downloader.getInstance(applicationContext).downloadFile(weatherDownloadUrl, storeFileName);
    }


    public static void updateForecast(final Context applicationContext) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String weatherDownloadUrl = OpenWeatherAPI.WEEKLY_FORECAST_URI;
                String storeFileName = AppConstants.WEATHER_FORECAST_STORE_FILE;
                downloadWeatherJSON(applicationContext, weatherDownloadUrl, storeFileName);
            }
        }).start();

        notify(applicationContext, 2, "Forecast", "Information has been updated at " + new Utilities(applicationContext).getDateProperty("hh:mm a", System.currentTimeMillis()));
    }

    private static void notify(Context applicationContext, int notificationId, String title, String message) {

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(applicationContext);
        notificationCompat.mContentTitle = title;
        notificationCompat.mContentText = message;
        notificationCompat.setSmallIcon(R.mipmap.ic_launcher);

        Intent intent = new Intent(applicationContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationCompat.addAction(0, "Open Application", PendingIntent.getActivity(applicationContext, 1,
                intent, PendingIntent.FLAG_UPDATE_CURRENT));
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, notificationCompat.build());
    }

    public static void updateAll(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        long lastUpdated = preferences.getLong(AppConstants.Preferences.LAST_UPDATED, System.currentTimeMillis());

        int half_hr = 1000 * 60 * 30;

        if ((System.currentTimeMillis() - lastUpdated) > half_hr) {
            RayWeatherUtils.updateWeather(context);
            RayWeatherUtils.updateForecast(context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(AppConstants.Preferences.LAST_UPDATED, lastUpdated);
            editor.commit();
            notify(context, 99, "Weather Updates", "Information has  been updated");
        } else {
            notify(context, 99, "Weather Updates", "Information has been not been updated");
        }
    }

    /**
     * Returns the today weather json as string
     *
     * @return today weather as string json
     */
    public static String getWeatherJSON() {
        return readJSON(AppConstants.WEATHER_TODAY_STORE_FILE);
    }

    /**
     * Returns the forecast json as string
     *
     * @return forecast string json
     */
    public static String getForecastJSON() {
        return readJSON(AppConstants.WEATHER_FORECAST_STORE_FILE);
    }

    /**
     * Reads test json
     *
     * @param filename
     * @return
     */
    public static String readJSON(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filename));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String readData = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((readData = bufferedReader.readLine()) != null) {
                stringBuffer.append(readData);
            }
            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
