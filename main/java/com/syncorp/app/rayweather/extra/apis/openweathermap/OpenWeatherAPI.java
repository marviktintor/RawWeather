package com.syncorp.app.rayweather.extra.apis.openweathermap;

/**
 * Created by victor on 3/28/2016.
 */
public class OpenWeatherAPI {

    public static final String OPEN_WEATHER_MAP_URI = "http://api.openweathermap.org/data/2.5/";
    public static final String FORECAST = "forecast";
    public static final String WEATHER = "weather";
    public static final String LOCATION = "weather";

    public static final String WEEKLY_FORECAST_URI = "http://api.openweathermap.org/data/2.5/forecast?q=Kakamega,ke&mode=json&units=metrics&appid=d7eb855384351f5b48409282edce9c33";
    public static final String TODAY_WEATHER_URI = "http://api.openweathermap.org/data/2.5/weather?q=Kakamega&appid=d7eb855384351f5b48409282edce9c33&units=metric";
    public static final String APP_ID = "d7eb855384351f5b48409282edce9c33";
    public static final String COUNTRY_CODE = "ke";
    public static final String CITY_CODE = "Kakamega";
    public static final String WEATHE_ICON_URI = "http://openweathermap.org/img/w/${IMAGE_ID}.png";

    /**
     * Tags used in the open weather map api
     */
    public class Tags {
        /**
         * Weather icon image tag
         */
        public static final String WEATHER_ICON_IMAGE = "${IMAGE_ID}";
    }
}
