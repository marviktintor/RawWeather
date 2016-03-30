package com.syncorp.app.rayweather.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.syncorp.app.coreutils.utils.Utilities;
import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.adapters.FullDayForecastedWeatherAdapter;
import com.syncorp.app.rayweather.adapters.WeeklyWeatherAdapter;
import com.syncorp.app.rayweather.datamodels.WeatherForecastsModel;
import com.syncorp.app.rayweather.datamodels.WeatherModel;
import com.syncorp.app.rayweather.utils.forecast.ForecastListUtils;
import com.syncorp.app.rayweather.utils.forecast.ForecastUtils;
import com.syncorp.app.rayweather.worker.DummyWorker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FullDayForecastActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private RecyclerView weatherInfo;
    //private LinearLayoutManager layoutManager;
    StaggeredGridLayoutManager layoutManager;
    private WeeklyWeatherAdapter weatherAdapter;

    private Utilities utilities;
    private ForecastUtils forecastUtils;
    private ForecastListUtils foreacastListUtils;
    private DummyWorker dummyWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_forecast);

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        getWeatherForecastJSON();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        weatherAdapter = new WeeklyWeatherAdapter(this, init());
        //layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        weatherInfo = (RecyclerView) findViewById(R.id.weatherInfo);
        weatherInfo.setLayoutManager(layoutManager);
        try {
            weatherInfo.setAdapter(new FullDayForecastedWeatherAdapter(getApplicationContext(),R.layout.item_feed,createForecast()));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void getWeatherForecastJSON() {

        utilities = new Utilities(this);

        try {
            dummyWorker = new DummyWorker(FullDayForecastActivity.this);
            forecastUtils = new ForecastUtils(new JSONObject(dummyWorker.getForecastJSON()));
            foreacastListUtils = new ForecastListUtils();
        } catch (Exception e) {
            Snackbar snackbar = Snackbar.make(toolbar, "Cannot load weather information", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            snackbar.show();
        }
    }

    public List<WeatherForecastsModel> createForecast() throws JSONException {
        List<WeatherForecastsModel> weatherForecastsModels = new ArrayList<>();
        JSONArray forecast = forecastUtils.getForecastList();
        for (int i = 0; i < forecast.length(); i++) {
            JSONObject forecastObject = forecast.getJSONObject(i);
            long dateTimeMillis = foreacastListUtils.getDt(forecastObject);

            //Skip all old days
            if(dateTimeMillis < System.currentTimeMillis()){
                //continue;
            }
            String weekDay = utilities.getDateProperty("dd hh:mm a", dateTimeMillis);
            String date = utilities.getDateProperty("dd-MM-yyy", dateTimeMillis);

            JSONArray weather = foreacastListUtils.getWeather(forecastObject);
            String weatherIcon = foreacastListUtils.getWeatherIcon(weather);
            String condition = foreacastListUtils.getWeatherMain(weather);
            String conditionDescription = foreacastListUtils.getWeatherDescription(weather);

            JSONObject mainWeatherInfo = foreacastListUtils.getMain(forecastObject);
            int humidity = foreacastListUtils.getHumidty(mainWeatherInfo);
            double temperatures = foreacastListUtils.getTemperature(mainWeatherInfo);

            Bitmap weatherBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fog);
            weatherForecastsModels.add(new WeatherForecastsModel(weekDay, date, weatherBitmap, condition, conditionDescription, temperatures, humidity));

            Log.i("WEATHER",weekDay +" " +date +" " +condition +" " +conditionDescription);

        }
        return weatherForecastsModels;
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
