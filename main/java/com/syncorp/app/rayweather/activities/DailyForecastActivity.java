package com.syncorp.app.rayweather.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.syncorp.app.coreutils.intents.Intents;
import com.syncorp.app.coreutils.utils.Utilities;
import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.constants.AppConstants;
import com.syncorp.app.rayweather.utils.app.RayWeatherUtils;
import com.syncorp.app.rayweather.utils.weather.WeatherUtils;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyForecastActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView wicon;
    private TextView dday;
    private TextView ddate;
    private TextView temp;
    private TextView hum;

    private WeatherUtils weatherUtils;
    private Utilities utilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);


        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Today's");

        wicon = (ImageView) findViewById(R.id.wIcon);
        dday = (TextView) findViewById(R.id.dday);
        ddate = (TextView) findViewById(R.id.ddate);
        temp = (TextView) findViewById(R.id.temphi);
        hum = (TextView) findViewById(R.id.hum);

        long currentTime = System.currentTimeMillis();


        try {
            weatherUtils = new WeatherUtils(new JSONObject(RayWeatherUtils.getForecastJSON()));
            showWeatherInfo();
        } catch (Exception e) {
            e.printStackTrace();
            RayWeatherUtils.updateAll(DailyForecastActivity.this);
            Snackbar snackbar = Snackbar.make(temp, "Showing latest weather information", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*finish();*/
                }
            });
            snackbar.show();
        }


        utilities = new Utilities(DailyForecastActivity.this);
        registerReceiver(receiver, new IntentFilter(AppConstants.Intents.WeatherUpdates.ACTION_NEW_WEATHER_UPDATES));

    }

    private void showWeatherInfo() throws Exception {

        String weatherIcon = weatherUtils.getWeatherIcon();
        String weatherIconFileUri = AppConstants.WEATHER_ICONS_STORAGE_DIR + "/" + weatherIcon + ".png";
        Bitmap weatherBitmap = utilities.getFileBitmap(weatherIconFileUri);
        wicon.setImageBitmap(weatherBitmap);

        dday.setText(new SimpleDateFormat("EEE").format(new Date(System.currentTimeMillis())));
        ddate.setText(new SimpleDateFormat("yyy-MM-dd").format(new Date(System.currentTimeMillis())));


        temp.setText("" + weatherUtils.getTemperature() + " C");
        hum.setText("" + weatherUtils.getHumidity());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daily_forecast, menu);
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
}
