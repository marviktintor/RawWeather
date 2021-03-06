package com.syncorp.app.rayweather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.adapters.WeeklyWeatherAdapter;
import com.syncorp.app.rayweather.datamodels.WeatherModel;

import java.util.ArrayList;
import java.util.List;

public class WeeklyForecastActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private RecyclerView weatherInfo;
    //private LinearLayoutManager layoutManager;
    StaggeredGridLayoutManager layoutManager;
    private WeeklyWeatherAdapter weatherAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_forecast);

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        weatherAdapter = new WeeklyWeatherAdapter(this, init());
        //layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        weatherInfo = (RecyclerView) findViewById(R.id.weatherInfo);
        weatherInfo.setAdapter(weatherAdapter);
        weatherInfo.setLayoutManager(layoutManager);

    }

    public static List<WeatherModel> init() {
        List<WeatherModel> weatherData = new ArrayList<>();
        int[] icons = {R.drawable.sunny, R.drawable.hazy, R.drawable.light_clouds, R.drawable.light_rain, R.drawable.storm, R.drawable.fog, R.drawable.calm};
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
        String[] conditions = {"Sunny", "Haze", "Cloudy", "Light Rain", "Stormy", "Foggy", "Calm"};
        for (int i = 0; i < icons.length; i++) {
            WeatherModel current = new WeatherModel();
            current.wIcon = icons[i];
            current.day = days[i];
            current.condition = conditions[i];
            weatherData.add(current);
        }
        return weatherData;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weekly_forecast, menu);
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
}
