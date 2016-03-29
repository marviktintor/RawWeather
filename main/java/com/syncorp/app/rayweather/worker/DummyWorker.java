package com.syncorp.app.rayweather.worker;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by victor on 3/29/2016.
 */
public class DummyWorker {

    /**
     * Context
     */
    public  Context context;

    /**
     * Private constructor to make sure the class cannot be instantiated
     *
     * @param context
     */
    public DummyWorker(Context context) {
        this.context = context;
    }


    /**
     * Returns the today weather json as string
     *
     * @return today weather as string json
     */
    public  String getTodayWeatherJSON() {
        return readTestJSON("today.json");
    }

    /**
     * Returns the forecast json as string
     *
     * @return forecast string json
     */
    public  String getForecastJSON() {
        return readTestJSON("forecast.json");
    }

    /**
     * Reads test json
     *
     * @param filename
     * @return
     */
    public  String readTestJSON(String filename) {
        try {
            InputStream open =context.getResources().getAssets().open(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(open);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String readData = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((readData = bufferedReader.readLine()) != null) {
                stringBuffer.append(readData);
            }
            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
