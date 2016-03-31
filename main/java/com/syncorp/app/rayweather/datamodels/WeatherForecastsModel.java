package com.syncorp.app.rayweather.datamodels;

import android.graphics.Bitmap;

/**
 * Created by victor on 3/29/2016.
 */
public class WeatherForecastsModel {

    private String day;
    private String date;
    private Bitmap weatherIcon;

    private String condition;
    private String weatherDescription;
    private double temperature;
    private int humidity;
    private String weatherIconFileUri;

    public WeatherForecastsModel(String day, String date, Bitmap weatherIcon, String condition, String weatherDescription, double temperature, int humidity, String weatherIconFileUri) {
        this.day = day;
        this.date = date;
        this.weatherIcon = weatherIcon;
        this.condition = condition;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.humidity = humidity;
        this.weatherIconFileUri = weatherIconFileUri;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public Bitmap getWeatherIcon() {
        return weatherIcon;
    }

    public String getCondition() {
        return condition;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getWeatherIconFileUri() {
        return weatherIconFileUri;
    }
}
