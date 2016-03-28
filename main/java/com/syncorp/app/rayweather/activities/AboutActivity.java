package com.syncorp.app.rayweather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.utils.weather.WeatherUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AboutActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About Rayweather");

        Snackbar.make(toolbar, getSimpleWeather(), Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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

    public String getSimpleWeather() {

        try {
            InputStream open = getResources().getAssets().open("forecast.json");
            InputStreamReader inputStreamReader = new InputStreamReader(open);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String readData = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((readData = bufferedReader.readLine()) != null) {
                stringBuffer.append(readData);
            }
            return new WeatherUtils(new JSONObject(stringBuffer.toString())).getWeatherDescription();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
