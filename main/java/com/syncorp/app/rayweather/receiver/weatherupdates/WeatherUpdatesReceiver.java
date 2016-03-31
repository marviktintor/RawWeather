package com.syncorp.app.rayweather.receiver.weatherupdates;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.syncorp.app.rayweather.constants.AppConstants;
import com.syncorp.app.rayweather.utils.app.RayWeatherUtils;

/**
 * Created by victor on 3/31/2016.
 */
public class WeatherUpdatesReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(AppConstants.Intents.WeatherUpdates.ACTION_NEW_WEATHER_UPDATES)) {
            handleWeatherUpdates(context);
        }
        if (intent.getAction().equals(AppConstants.Intents.WeatherUpdates.ACTION_REQUEST_NEW_WEATHER_UPDATES)) {
            requestNewWeatherUpdates(context);
        }
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Toast.makeText(context, "Boot completed", Toast.LENGTH_SHORT).show();
            requestNewWeatherUpdates(context);
        }
    }

    private void requestNewWeatherUpdates(Context context) {
        RayWeatherUtils.updateAll(context);

    }

    private void handleWeatherUpdates(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String temperature = "" + sharedPreferences.getString("key_cloud_cover", "");
        Toast.makeText(context, temperature, Toast.LENGTH_SHORT).show();
    }
}
