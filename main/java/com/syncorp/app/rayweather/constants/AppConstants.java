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
     * Forecast weather JSON store file
     */
    public static final String WEATHER_FORECAST_STORE_FILE = WEATHER_FORECAST_STORAGE_DIR + "/forecast.json";
    /**
     * WEATHER_TODAY_STORAGE_DIR
     */
    public static final String WEATHER_TODAY_STORAGE_DIR = Constants.MARVIK_APPS_STORAGE_PATH + "/RawWeather/JSON/Today";

    /**
     * Today weather JSON store file
     */
    public static final String WEATHER_TODAY_STORE_FILE = WEATHER_TODAY_STORAGE_DIR + "/today.json";

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

        /**
         * Weather updates
         */

        public class WeatherUpdates {

            /**
             * Intent sent when the weather information has been updated
             */
            public static final String ACTION_NEW_WEATHER_UPDATES = "com.syncorp.app.intent.ACTION_NEW_WEATHER_UPDATES";
            public static final String ACTION_REQUEST_NEW_WEATHER_UPDATES = "com.syncorp.app.intent.ACTION_REQUEST_NEW_WEATHER_UPDATES";
        }
    }

    /**
     * Preferences
     */
    public class Preferences {

        /**
         * Geo location for the preference region
         */
        public static final String GEO_LOCATION = "key_geographical";

        /**
         * Last updated
         * The last time the weather information was updated
         */
        public static final String LAST_UPDATED = "last_updated";
    }
}
