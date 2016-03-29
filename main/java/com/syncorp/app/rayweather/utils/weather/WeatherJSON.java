package com.syncorp.app.rayweather.utils.weather;

/**
 * Created by victor on 3/28/2016.
 */
public class WeatherJSON {
    public static final String COORDS = "coord";

    public static final class Coord {
        public static final String LONGITUDE = "lon";
        public static final String LATITUDE = "lat";
    }

    public static final String WEATHER = "weather";

    public static final class Weather {
        public static final String ID = "id";
        public static final String MAIN = "main";
        public static final String DESCRIPTION = "description";
        public static final String ICON = "icon";
    }

    public static final String BASE = "base";
    public static final String MAIN = "main";

    public static final class Main {
        public static final String TEMPERATURE = "temp";
        public static final String PRESSURE = "pressure";
        public static final String HUMIDITY = "humidity";
        public static final String TEMPERATURE_MAX = "temp_max";
        public static final String TEMPERATURE_MIN = "temp_min";
    }

    public static final String VISIBILITY = "visibility";
    public static final String WIND = "wind";

    public static final class Wind {
        public static final String SPEED = "speed";
        public static final String DEGREE = "deg";
    }

    public static final String CLOUDS = "clouds";

    public static final class Clouds {
        public static final String ALL = "all";
    }

    public static final String DAY_TIME = "dt";
    public static final String SYSTEM = "sys";

    public static final class System {
        public static final String TYPE = "type";
        public static final String ID = "id";
        public static final String MESSAGE = "message";
        public static final String COUNTRY = "country";
        public static final String SUNRISE = "sunrise";
        public static final String SUNSET = "sunset";
    }

    public static final class City {
        public static final String ID = "id";
        public static final String NAME = "name";
    }

    public static final String COD = "cod";

}