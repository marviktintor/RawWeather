package com.syncorp.app.rayweather.utils.forecast;

import com.syncorp.app.coreutils.json.JSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by victor on 3/28/2016.
 */
public  strictfp class ForecastUtils {

    private JSONObject forecastJSON;

    /**
     * Forecast utils
     * Provides  API's for getting the forecast information
     *
     * @param forecastJSON
     */
    public ForecastUtils(JSONObject forecastJSON) {
        this.forecastJSON = forecastJSON;
    }

    /**
     * Get the forecast json
     *
     * @return forecast json
     * @throws JSONException
     */
    public final JSONObject getForecast() throws JSONException {
        return (forecastJSON);
    }

    /**
     * Gets the city json
     *
     * @return city json
     * @throws JSONException
     */
    public final JSONObject getCityJSON() throws JSONException {
        return JSONUtils.optJSONObject(getForecast(), ForecastJSON.CITY);
    }

    /**
     * Return the city id
     *
     * @return city id
     * @throws JSONException
     */
    public final int getCityId() throws JSONException {
        return JSONUtils.optInt(getCityJSON(), ForecastJSON.City.CITY_ID);
    }

    /**
     * Return the city name
     *
     * @return city name
     * @throws JSONException
     */
    public final String getCityName() throws JSONException {
        return JSONUtils.optString(getCityJSON(), ForecastJSON.City.CITY_NAME);
    }

    /**
     * Get the city coordinates json
     *
     * @return city coordinates json
     * @throws JSONException
     */
    public final JSONObject getCityCoordinates() throws JSONException {
        return JSONUtils.optJSONObject(getCityJSON(), ForecastJSON.City.COORDINATES);
    }

    /**
     * Get the latitude of the city
     *
     * @return city latitude
     * @throws JSONException
     */
    public final float getCityLatitude() throws JSONException {
        return JSONUtils.optFloat(getCityJSON(), ForecastJSON.City.Coordinates.LATITUDE);
    }

    /**
     * Get the longitude of the city
     *
     * @return city longitude
     * @throws JSONException
     */
    public final float getCityLongitude() throws JSONException {
        return JSONUtils.optFloat(getCityJSON(), ForecastJSON.City.Coordinates.LONGITUDE);
    }

    /**
     * Gets the name of the county
     *
     * @return country name
     * @throws JSONException
     */
    public final String getCounty() throws JSONException {
        return JSONUtils.optString(getCityJSON(), ForecastJSON.City.COUNTRY);
    }

    /**
     * Gets the population of the city
     *
     * @return city population
     * @throws JSONException
     */
    public final String getPopulation() throws JSONException {
        return JSONUtils.optString(getCityJSON(), ForecastJSON.City.POPULATION);
    }

    /**
     * Get the sys
     *
     * @return sys
     * @throws JSONException
     */
    public final JSONObject getSys() throws JSONException {
        return JSONUtils.optJSONObject(getCityJSON(), ForecastJSON.City.SYS);
    }

    /**
     * Return the population of the country
     *
     * @return
     * @throws JSONException
     */
    public final int getCountryPopulation() throws JSONException {
        return JSONUtils.optInt(getCityJSON(), ForecastJSON.City.Sys.POPULATION);
    }

    /**
     * Gets the cod
     *
     * @return cod
     * @throws JSONException
     */
    public final String getCod() throws JSONException {
        return JSONUtils.optString(getForecast(), ForecastJSON.COD);
    }

    /**
     * Gets the message
     *
     * @return msg
     * @throws JSONException
     */
    public final int getMessage() throws JSONException {
        return JSONUtils.optInt(getForecast(), ForecastJSON.MESSAGE);
    }

    /**
     * Gets the cnt
     *
     * @return cnt
     * @throws JSONException
     */
    public final int getCnt() throws JSONException {
        return JSONUtils.optInt(getForecast(), ForecastJSON.CNT);
    }

    /**
     * Returns the forecast list of various weather conditions of various days/times
     *
     * @return forecast json array
     * @throws JSONException
     */
    public final JSONArray getForecastList() throws JSONException {
        return JSONUtils.optJSONArray(getForecast(), ForecastJSON.LIST);
    }

    /**
     * Utility class for getting the various items in the list of forecasts
     */
    public strictfp final class ForecastListUtils {

        /**
         * Gets the dt from an object from the forecast list
         *
         * @param forecastListJSON
         * @return
         */
        public final long getDt(JSONObject forecastListJSON) {
            return JSONUtils.optLong(forecastListJSON, ForecastJSON.List.DT);
        }

        /**
         * Get main weather information
         *
         * @param forecastListJSON
         * @return main weather information
         */
        public final JSONObject getMain(JSONObject forecastListJSON) {
            return JSONUtils.optJSONObject(forecastListJSON, ForecastJSON.List.MAIN);
        }

        /**
         * Get temperature
         *
         * @param mainForecastListJSON
         * @return temperature
         */
        public final float getTemperature(JSONObject mainForecastListJSON) {
            return JSONUtils.optFloat(mainForecastListJSON, ForecastJSON.List.Main.TEMPERATURE);
        }

        /**
         * Gets the min temperatures
         *
         * @param mainForecastListJSON
         * @return min temperatures
         */
        public final float getMinTemperature(JSONObject mainForecastListJSON) {
            return JSONUtils.optFloat(mainForecastListJSON, ForecastJSON.List.Main.TEMPERATURE_MIN);
        }

        /**
         * Gets the max temperature
         *
         * @param mainForecastListJSON
         * @return max temperature
         */
        public final float getMaxTemperature(JSONObject mainForecastListJSON) {
            return JSONUtils.optFloat(mainForecastListJSON, ForecastJSON.List.Main.TEMPERATURE_MAX);
        }

        /**
         * Gets the pressure
         *
         * @param mainForecastListJSON
         * @return pressure
         */
        public final float getPressure(JSONObject mainForecastListJSON) {
            return JSONUtils.optFloat(mainForecastListJSON, ForecastJSON.List.Main.PRESSURE);
        }

        /**
         * Gets the sea level
         *
         * @param mainForecastListJSON
         * @return sea level
         */
        public final float getSeaLevel(JSONObject mainForecastListJSON) {
            return JSONUtils.optFloat(mainForecastListJSON, ForecastJSON.List.Main.SEA_LEVEL);
        }

        /**
         * Gets the ground level
         *
         * @param mainForecastListJSON
         * @return ground level
         */
        public final float getGroundLevel(JSONObject mainForecastListJSON) {
            return JSONUtils.optFloat(mainForecastListJSON, ForecastJSON.List.Main.GROUND_LEVEL);
        }

        /**
         * Gets the humidity
         *
         * @param mainForecastListJSON
         * @return humidity
         */
        public final int getHumidty(JSONObject mainForecastListJSON) {
            return JSONUtils.optInt(mainForecastListJSON, ForecastJSON.List.Main.HUMIDITY);
        }

        /**
         * Gets the kf temperature
         *
         * @param mainForecastListJSON
         * @return kf temperature
         */
        public final float getTemperatureKF(JSONObject mainForecastListJSON) {
            return JSONUtils.optFloat(mainForecastListJSON, ForecastJSON.List.Main.TEMPERATURE_KF);
        }

        /**
         * Gets the weather json array
         *
         * @param forecastListJSON
         * @return
         */
        public final JSONArray getWeather(JSONObject forecastListJSON) {
            return JSONUtils.optJSONArray(forecastListJSON, ForecastJSON.List.WEATHER);
        }

        /**
         * Gets the weather id
         *
         * @param weatherJSONArray
         * @return weather id
         * @throws JSONException
         */
        public final int getWeatherId(JSONArray weatherJSONArray) throws JSONException {
            return JSONUtils.optInt(weatherJSONArray.getJSONObject(0), ForecastJSON.List.Weather.ID);
        }

        /**
         * Gets the main weather
         *
         * @param weatherJSONArray
         * @return main weather
         * @throws JSONException
         */
        public final String getWeatherMain(JSONArray weatherJSONArray) throws JSONException {
            return JSONUtils.optString(weatherJSONArray.getJSONObject(0), ForecastJSON.List.Weather.MAIN);
        }

        /**
         * Gets the weather description
         *
         * @param weatherJSONArray
         * @return
         * @throws JSONException
         */
        public final String getWeatherDescription(JSONArray weatherJSONArray) throws JSONException {
            return JSONUtils.optString(weatherJSONArray.getJSONObject(0), ForecastJSON.List.Weather.DESCRIPTION);
        }

        /**
         * Gets the weather icon
         *
         * @param weatherJSONArray
         * @return weather  icon
         * @throws JSONException
         */
        public final String getWeatherIcon(JSONArray weatherJSONArray) throws JSONException {
            return JSONUtils.optString(weatherJSONArray.getJSONObject(0), ForecastJSON.List.Weather.ICON);
        }

        /**
         * Get clouds json object
         *
         * @param forecastListJSON
         * @return cloud json object
         */
        public final JSONObject getClouds(JSONObject forecastListJSON) {
            return JSONUtils.optJSONObject(forecastListJSON, ForecastJSON.List.CLOUDS);
        }

        public final int getCloudsAll(JSONObject cloudJSON) {
            return JSONUtils.optInt(cloudJSON, ForecastJSON.List.Clouds.ALL);
        }

        /**
         * Get wind json object
         *
         * @param forecastListJSON
         * @return wind json object
         */
        public final JSONObject getWind(JSONObject forecastListJSON) {
            return JSONUtils.optJSONObject(forecastListJSON, ForecastJSON.List.WIND);
        }

        /**
         * Get wind speed
         *
         * @param windJSON
         * @return wind speed
         */
        public final float getWindSpeed(JSONObject windJSON) {
            return JSONUtils.optInt(windJSON, ForecastJSON.List.Wind.SPEED);
        }

        /**
         * Get wind degrees
         *
         * @param windJSON
         * @return wind degrees
         */
        public final float getWindDegrees(JSONObject windJSON) {
            return JSONUtils.optInt(windJSON, ForecastJSON.List.Wind.DEGREE);
        }

        /**
         * Get sys json object
         *
         * @param forecastListJSON
         * @return sys
         */
        public final JSONObject getSys(JSONObject forecastListJSON) {
            return JSONUtils.optJSONObject(forecastListJSON, ForecastJSON.List.SYS);
        }

        /**
         * Get pod
         *
         * @param sysJSON
         * @return pod
         */
        public final String getSysPod(JSONObject sysJSON) {
            return JSONUtils.optString(sysJSON, ForecastJSON.List.Sys.POD);
        }

        /**
         * Get rain json
         *
         * @param forecastListJSON
         * @return rain json
         */
        public final JSONObject getRain(JSONObject forecastListJSON) {
            return JSONUtils.optJSONObject(forecastListJSON, ForecastJSON.List.RAIN);
        }

        /**
         * Get 3h
         *
         * @param rainJSON
         * @return 3h
         */

        public final int get3h(JSONObject rainJSON) {
            return JSONUtils.optInt(rainJSON, ForecastJSON.List.Rain.THEE_H);
        }

        /**
         * Get the date time
         *
         * @param forecastListJSON
         * @return date time
         */
        public final String getDateTime(JSONObject forecastListJSON) {
            return JSONUtils.optString(forecastListJSON, ForecastJSON.List.DAY_TIME);
        }
    }
}
