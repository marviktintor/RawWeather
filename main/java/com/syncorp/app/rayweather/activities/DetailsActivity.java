package com.syncorp.app.rayweather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.syncorp.app.coreutils.utils.Utilities;
import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.constants.AppConstants;

public class DetailsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView condition;
    private TextView conditionDescription;
    private ImageView wicon;
    private TextView dday;
    private TextView ddate;
    private TextView temp;
    private TextView hum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details");

        condition = (TextView) findViewById(R.id.condition);
        conditionDescription = (TextView) findViewById(R.id.condition_description);
        wicon = (ImageView) findViewById(R.id.wIcon);
        dday = (TextView) findViewById(R.id.dday);
        ddate = (TextView) findViewById(R.id.ddate);
        temp = (TextView) findViewById(R.id.temphi);
        hum = (TextView) findViewById(R.id.hum);

        receiveExtras();
    }

    private void receiveExtras() {
        Intent intent = getIntent();
        String weatherIconFileUri = intent.getStringExtra(AppConstants.Intents.WeatherForecastDetails.EXTRA_ICON);
        wicon.setImageBitmap(new Utilities(DetailsActivity.this).getFileBitmap(weatherIconFileUri));
        dday.setText(intent.getStringExtra(AppConstants.Intents.WeatherForecastDetails.EXTRA_WEEK_DAY));
        ddate.setText(intent.getStringExtra(AppConstants.Intents.WeatherForecastDetails.EXTRA_DATE));
        temp.setText("" + intent.getDoubleExtra(AppConstants.Intents.WeatherForecastDetails.TEMPERATURES, 23) +"C");
        hum.setText("" + intent.getIntExtra(AppConstants.Intents.WeatherForecastDetails.HUMIDITY, 0) +"hpa");

        condition.setText(intent.getStringExtra(AppConstants.Intents.WeatherForecastDetails.EXTRA_CONDITION));
        conditionDescription.setText(intent.getStringExtra(AppConstants.Intents.WeatherForecastDetails.EXTRA_WEATHER_DESCRIPTION));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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
