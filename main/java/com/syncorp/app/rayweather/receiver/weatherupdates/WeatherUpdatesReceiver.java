package com.syncorp.app.rayweather.receiver.weatherupdates;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.constants.AppConstants;
import com.syncorp.app.rayweather.services.WeatherUpdateService;
import com.syncorp.app.rayweather.utils.app.RayWeatherUtils;
import com.syncorp.app.rayweather.utils.weather.WeatherUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by victor on 3/31/2016.
 */
public class WeatherUpdatesReceiver extends BroadcastReceiver {

    private WeatherUtils weatherUtils;

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            weatherUtils = new WeatherUtils(new JSONObject(RayWeatherUtils.getWeatherJSON()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (intent.getAction().equals(AppConstants.Intents.WeatherUpdates.ACTION_NEW_WEATHER_UPDATES)) {
            try {
                handleWeatherUpdates(context);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (intent.getAction().equals(AppConstants.Intents.WeatherUpdates.ACTION_REQUEST_NEW_WEATHER_UPDATES)) {
            requestNewWeatherUpdates(context);
        }
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Toast.makeText(context, "Boot completed", Toast.LENGTH_SHORT).show();
            context.startService(new Intent(context, WeatherUpdateService.class));
        }
    }

    private void requestNewWeatherUpdates(Context context) {
        //RayWeatherUtils.updateAll(context);

    }

    private void handleWeatherUpdates(Context context) throws JSONException {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String cloudCover = sharedPreferences.getString("key_cloud_cover", "20").trim();
        String temperature = sharedPreferences.getString("key_temperatures", "20").trim();
        String humidity = sharedPreferences.getString("key_humidity", "40").trim();
        String windSpeed = sharedPreferences.getString("key_wind_speed", "3").trim();
        String visibility = sharedPreferences.getString("key_visibility", "500").trim();

        boolean alertCloudCover = sharedPreferences.getBoolean("key_cloud_cover_notification", false);
        boolean alertTemperature = sharedPreferences.getBoolean("key_temperature_notification", false);
        boolean alertHumidity = sharedPreferences.getBoolean("key_humidity_notification", false);
        boolean alertWindSpeed = sharedPreferences.getBoolean("key_wind_speed_notification", false);
        boolean alertVisibility = sharedPreferences.getBoolean("key_visibility_notification", false);

        if (cloudCover.length() > 0 && TextUtils.isDigitsOnly(cloudCover)) {
            try {
                int iCloudCover = weatherUtils.getCloudsAll();
                if (iCloudCover > Integer.parseInt(cloudCover)) {
                    if (alertCloudCover)
                        sendNotification(context, 5, "Cloud Cover", "High cloud cover ");
                }
            } catch (Exception e) {
            }
        }
        if (temperature.length() > 0 && TextUtils.isDigitsOnly(temperature)) {
            try {
                int iTemperatures = Integer.parseInt(weatherUtils.getTemperature().toString());
                if (iTemperatures > Integer.parseInt(temperature)) {
                    if (alertTemperature)
                        sendNotification(context, 6, "Temperatures", "The weather temperatures are higher than the ideal temperatures");
                }
            } catch (Exception e) {
                sendNotification(context, 9, "Visibility", "Cannot get temperature information from " + weatherUtils.getBase() + " for " + weatherUtils.getCityName() + "," + weatherUtils.getCountry());
            }
        }
        if (humidity.length() > 0 && TextUtils.isDigitsOnly(humidity)) {
            try {
                int iHumidity = weatherUtils.getHumidity();
                if (iHumidity > Integer.parseInt(humidity)) {
                    if (alertHumidity)
                        sendNotification(context, 7, "Humidity", "The weather humidity is higher than the ideal humidity");
                }
            } catch (Exception e) {
                sendNotification(context, 9, "Humidity", "Cannot get humidity information from " + weatherUtils.getBase() + " for " + weatherUtils.getCityName() + "," + weatherUtils.getCountry());
            }
        }
        if (windSpeed.length() > 0 && TextUtils.isDigitsOnly(windSpeed)) {
            try {
                double iWindSpeed = weatherUtils.getWindSpeed();
                if (iWindSpeed > Double.valueOf(windSpeed)) {
                    if (alertWindSpeed)
                        sendNotification(context, 8, "Wind Speed", "The wind speed is higher than the ideal humidity");
                }
            } catch (Exception e) {
                sendNotification(context, 9, "Wind speed", "Cannot get wind speed information from " + weatherUtils.getBase() + " for " + weatherUtils.getCityName() + "," + weatherUtils.getCountry());
            }
        }
        if (visibility.length() > 0 && TextUtils.isDigitsOnly(visibility)) {

            try {
                int iVisibility = weatherUtils.getVisibility();
                if (iVisibility < Integer.parseInt(visibility)) {
                    if (alertVisibility)
                        sendNotification(context, 9, "Visibility", "The visibility is lower than the ideal visibility");
                }
            } catch (Exception e) {
                sendNotification(context, 9, "Visibility", "Cannot get visibility information from " + weatherUtils.getBase() + " for " + weatherUtils.getCityName() + "," + weatherUtils.getCountry());
            }

        }

    }

    private void sendNotification(Context context, int notificationId, String title, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.mContentTitle = title;
        notification.mContentText = message;
        notificationManager.notify(notificationId, notification.build());
    }
}
