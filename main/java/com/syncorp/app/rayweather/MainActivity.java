package com.syncorp.app.rayweather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
            Snackbar snackbar = Snackbar.make(temp, "Cannot load weather information", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*finish();*/
                }
            });
            snackbar.show();
        }


        registerReceiver(receiver, new IntentFilter(AppConstants.Intents.WeatherUpdates.ACTION_NEW_WEATHER_UPDATES));

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
        temp.setText(String.format("%.2f", weatherUtils.getTemperature()));
        hum.setText(String.format("%d", weatherUtils.getHumidity()));
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
}
