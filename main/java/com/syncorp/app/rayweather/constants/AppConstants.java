package com.syncorp.app.rayweather.constants;

import com.syncorp.app.coreutils.constants.Constants;

/**
 * Created by victor on 3/31/2016.
 */
public class AppConstants {

    /**
     * WEATHER_ICON_STORAGE_DIR
     */
    public static final String WEATHER_ICONS_STORAGE_DIR = Constants.MARVIK_APPS_STORAGE_PATH + "/RawWeather/Weather/Images";

    /**
     * WEATHER_FORECAST_STORAGE_DIR
     */
    public static final String WEATHER_FORECAST_STORAGE_DIR = Constants.MARVIK_APPS_STORAGE_PATH + "/RawWeather/JSON/Forecasts";

    /**
     * WEATHER_TODAY_STORAGE_DIR
     */
    public static final String WEATHER_TODAY_STORAGE_DIR = Constants.MARVIK_APPS_STORAGE_PATH + "/RawWeather/JSON/Today";

    public class Intents {
        public class WeatherForecastDetails {
            public static final String EXTRA_ICON = "weather_icon";
            public static final String EXTRA_CONDITION = "weather_condition";
            public static final String EXTRA_WEATHER_DESCRIPTION = "weather_description";
            public static final String EXTRA_WEEK_DAY = "weekday";
            public static final String EXTRA_DATE = "date";
            public static final String TEMPERATURES = "temperatures";
            public static final String HUMIDITY = "humidity";
        }
    }
}
