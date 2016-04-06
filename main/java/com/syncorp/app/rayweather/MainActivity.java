package com.syncorp.app.rayweather;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.syncorp.app.coreutils.intents.Intents;
import com.syncorp.app.coreutils.utils.Utilities;
import com.syncorp.app.rayweather.activities.MyPreferencesActivity;
import com.syncorp.app.rayweather.activities.SettingsActivity;
import com.syncorp.app.rayweather.constants.AppConstants;
import com.syncorp.app.rayweather.fragments.NavDrawerFragment;
import com.syncorp.app.rayweather.services.WeatherUpdateService;
import com.syncorp.app.rayweather.utils.app.RayWeatherUtils;
import com.syncorp.app.rayweather.utils.weather.WeatherUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private ImageView wicon;

    private TextView tvCondition;
    private TextView tvConditionDescription;

    private TextView dday;
    private TextView ddate;
    private TextView temp;
    private TextView hum;

    private WeatherUtils weatherUtils;
    private Utilities utilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utilities = new Utilities(MainActivity.this);

        startService(new Intent(getApplicationContext(), WeatherUpdateService.class));

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavDrawerFragment navDrawerFragment = (NavDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navDrawerFragment);
        navDrawerFragment.init(R.id.navDrawerFragment, (DrawerLayout) findViewById(R.id.drawerLayout), toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyPreferencesActivity.class));
            }
        });

        //Data to be replaced with actual real time data once OpenWeatherMap API key is acquired
        tvCondition = (TextView) findViewById(R.id.condition);
        tvConditionDescription = (TextView) findViewById(R.id.condition_description);

        wicon = (ImageView) findViewById(R.id.wIcon);
        dday = (TextView) findViewById(R.id.dday);
        ddate = (TextView) findViewById(R.id.ddate);
        temp = (TextView) findViewById(R.id.temphi);
        hum = (TextView) findViewById(R.id.hum);

        try {
            weatherUtils = new WeatherUtils(new JSONObject(RayWeatherUtils.getWeatherJSON()));
            showWeatherInfo();
        } catch (Exception e) {RayWeatherUtils.updateAll(MainActivity.this);
            RayWeatherUtils.updateAll(MainActivity.this);
            e.printStackTrace();
            Snackbar snackbar = Snackbar.make(temp, "Showing latest weather information", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*finish();*/
                }
            });
            snackbar.show();
        }


        registerReceiver(receiver, new IntentFilter(AppConstants.Intents.WeatherUpdates.ACTION_NEW_WEATHER_UPDATES));
        try {
            handleWeatherUpdates(MainActivity.this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showWeatherInfo() throws Exception {
        String weatherIcon = weatherUtils.getWeatherIcon();
        String weatherIconFileUri = AppConstants.WEATHER_ICONS_STORAGE_DIR + "/" + weatherIcon + ".png";
        Bitmap weatherBitmap = utilities.getFileBitmap(weatherIconFileUri);
        wicon.setImageBitmap(weatherBitmap);

        dday.setText(new SimpleDateFormat("EEE").format(new Date(System.currentTimeMillis())));
        ddate.setText(new SimpleDateFormat("yyy-MM-dd").format(new Date(System.currentTimeMillis())));

        tvCondition.setText(weatherUtils.getWeatherMain());
        tvConditionDescription.setText(weatherUtils.getWeatherDescription());
        temp.setText("" + weatherUtils.getTemperature().toString() + " C");
        hum.setText("" + weatherUtils.getHumidity());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppConstants.Intents.WeatherUpdates.ACTION_NEW_WEATHER_UPDATES)) {
                try {
                    showWeatherInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (intent.getAction().equals(Intents.Broadcasts.ACTION_FILE_DOWNLOADED)) {

            }
        }
    };
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
