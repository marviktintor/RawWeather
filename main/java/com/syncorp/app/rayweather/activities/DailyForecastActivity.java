package com.syncorp.app.rayweather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.utils.weather.WeatherUtils;
import com.syncorp.app.rayweather.worker.DummyWorker;

import org.json.JSONException;
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
    private DummyWorker dummyWorker;

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
            dummyWorker = new DummyWorker(this);
            weatherUtils = new WeatherUtils(new JSONObject(dummyWorker.getTodayWeatherJSON()));
            showWeatherInfo();
        } catch (JSONException e) {
            e.printStackTrace();
            Snackbar snackbar = Snackbar.make(temp, "Cannot load weather information", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("Close", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            snackbar.show();
        }
        if (false) {
            wicon.setImageResource(R.drawable.sunny);
            dday.setText(new SimpleDateFormat("dd EEE").format(new Date(currentTime)));
            ddate.setText(new SimpleDateFormat("yyy-MM-dd").format(new Date(currentTime)));
            temp.setText("76/51");
            hum.setText("60 hpa");
        }
    }

    private void showWeatherInfo() throws JSONException {

        wicon.setImageResource(R.drawable.sunny); // TODO USE DOWNLOADED WEATHER ICON

        dday.setText(new SimpleDateFormat("EEE").format(new Date(System.currentTimeMillis())));
        ddate.setText(new SimpleDateFormat("yyy-MM-dd").format(new Date(System.currentTimeMillis())));


        temp.setText(String.format("%.2f", weatherUtils.getTemperature()));
        hum.setText(String.format("%d", weatherUtils.getHumidity()));
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
}
