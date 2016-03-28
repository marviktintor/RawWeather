package com.syncorp.app.rayweather.utils.forecast;

/**
 * Created by victor on 3/28/2016.
 */
public class ForecastJSON {
    public static final String CITY = "city";

    public static class City {
        public static final String CITY_NAME = "name";
        public static final String CITY_ID = "id";
        public static final String COORDINATES = "coord";

        public static class Coordinates {
            public static final String LONGITUDE = "lon";
            public static final String LATITUDE = "lat";
        }

        public static final String COUNTRY = "country";
        public static final String POPULATION = "population";
        public static final String SYS = "sys";

        public static class Sys {
            public static final String POPULATION = "population";
        }
    }

    public static final String COD = "cod";

    public static final String MESSAGE = "message";

    public static final String CNT = "cnt";

    public static final String LIST = "list";

    public static class List {

        public static final String DT = "dt";

        public static final String MAIN = "main";

        public static class Main {
            public static final String TEMPERATURE = "temp";
            public static final String TEMPERATURE_MIN = "temp_min";
            public static final String TEMPERATURE_MAX = "temp_max";
            public static final String PRESSURE = "pressure";
            public static final String SEA_LEVEL = "sea_level";
            public static final String GROUND_LEVEL = "grnd_level";
            public static final String HUMIDITY = "humidity";
            public static final String TEMPERATURE_KF = "temp_kf";
        }

        public static final String WEATHER = "weather";

        public static final class Weather {

            public static final String ID = "id";
            public static final String MAIN = "main";
            public static final String DESCRIPTION = "description";
            public static final String ICON = "icon";

        }

        public static final String CLOUDS = "clouds";

        public static final class Clouds {

            public static final String ALL = "all";
        }

        public static final String WIND = "wind";

        public static final class Wind {

            public static final String SPEED = "speed";
            public static final String DEGREE = "deg";
        }

        public static final String RAIN = "rain";

        public static final class Rain {

            public static final String THEE_H = "3h";

        }

        public static final String SYS = "sys";

        public static class Sys {
            public static final String POD = "pod";
        }

        public static final String DAY_TIME = "dt_txt";
    }


}
