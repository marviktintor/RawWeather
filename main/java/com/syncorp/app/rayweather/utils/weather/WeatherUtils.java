package com.syncorp.app.rayweather.utils.weather;


import com.syncorp.app.coreutils.json.JSONUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by victor on 3/28/2016.
 */
public strictfp class WeatherUtils {

    private JSONObject weatherJSON;

    /**
     * Weather utils
     * Provides  API's for getting the weather information
     *
     * @param weatherJSON
     */
    public WeatherUtils(JSONObject weatherJSON) {
        this.weatherJSON = weatherJSON;
    }

    public JSONObject getWeatherJSON() {
        return weatherJSON;
    }

    /**
     * Get Weather location coordinates
     *
     * @return
     * @throws Exception
     */
    public final String[] getCoordinates() throws Exception {
        String coordinatesJSONObject = JSONUtils.optString(getWeatherJSON(), WeatherJSON.COORDS);
        JSONObject coordinatesJson = new JSONObject(coordinatesJSONObject);
        return new String[]{JSONUtils.optString(coordinatesJson, WeatherJSON.Coord.LONGITUDE), JSONUtils.optString(coordinatesJson, WeatherJSON.Coord.LATITUDE)};
    }

    /**
     * Gets the weather json array that contains the following data
     * <p/>
     * "id": of the weather ,
     * "main": main type of weather
     * "description": description of the weather
     * "icon": icon of the weather
     *
     * @return
     * @throws Exception
     */
    public final JSONArray getWeather() throws Exception {
        return JSONUtils.optJSONArray(getWeatherJSON(), WeatherJSON.WEATHER);
    }

    /**
     * Gets the weather id
     *
     * @return weather id
     * @throws Exception
     */
    public final int getWeatherId() throws Exception {
        int weatherID = -1;
        for (int i = 0; i < getWeather().length(); i++) {
            JSONObject weatherInfo = (JSONObject) getWeather().get(i);
            weatherID = weatherInfo.getInt(WeatherJSON.Weather.ID);
        }
        return weatherID;
    }

    /**
     * Gets the main weather of a place e.g Clouds e.t.c
     *
     * @return
     * @throws Exception
     */
    public final String getWeatherMain() throws Exception {
        String weatherMain = "";
        for (int i = 0; i < getWeather().length(); i++) {
            JSONObject weatherInfo = (JSONObject) getWeather().get(i);
            weatherMain = weatherInfo.getString(WeatherJSON.Weather.MAIN);
        }
        return weatherMain;
    }

    /**
     * Gets the weather description of a place e.g scattered clouds
     *
     * @return
     * @throws Exception
     */
    public final String getWeatherDescription() throws Exception {
        String weatherDescription = "";
        for (int i = 0; i < getWeather().length(); i++) {
            JSONObject weatherInfo = (JSONObject) getWeather().get(i);
            weatherDescription = weatherInfo.getString(WeatherJSON.Weather.DESCRIPTION);
        }
        return weatherDescription;
    }

    /**
     * Gets the weather icon for the weather in a place use the url below to download the icon of the weather
     * http://openweathermap.org/img/w/${IMAGE_ID}.png
     *
     * @return weather icon image id
     * @throws Exception
     */
    public final String getWeatherIcon() throws Exception {
        String weatherID = null;
        for (int i = 0; i < getWeather().length(); i++) {
            JSONObject weatherInfo = (JSONObject) getWeather().get(i);
            weatherID = weatherInfo.getString(WeatherJSON.Weather.ICON);
        }
        return weatherID;
    }

    /**
     * Returns the base e.g stations
     *
     * @return
     */
    public final String getBase() {
        return JSONUtils.optString(getWeatherJSON(), WeatherJSON.BASE);
    }

    /**
     * Return main weather info <br /> This JSON
     * Contains   <ul>
     * <li>"temp": e.g. 27.87,</li>
     * <li>"pressure": e.g.  1019,</li>
     * <li>"humidity": e.g.  45,</li>
     * <li>"temp_min": e.g.  26,</li>
     * <li>"temp_max": e.g.  30</li>
     * </ul>
     *
     * @return
     * @throws Exception
     */
    public final JSONObject getMain() throws Exception {
        return JSONUtils.optJSONObject(getWeatherJSON(), WeatherJSON.MAIN);
    }


    /**
     * get the temperatures
     *
     * @return temperature value in metrics
     * @throws Exception
     */
    public final Object getTemperature() throws Exception {
        return JSONUtils.opt(getMain(), WeatherJSON.Main.TEMPERATURE);
    }

    /**
     * Returns the pressure
     *
     * @return pressure value
     * @throws Exception
     */
    public final int getPressure() throws Exception {
        return JSONUtils.optInt(getMain(), WeatherJSON.Main.PRESSURE);
    }

    /**
     * Returns the humidity
     *
     * @return humidity value
     * @throws Exception
     */
    public final int getHumidity() throws Exception {
        return JSONUtils.optInt(getMain(), WeatherJSON.Main.HUMIDITY);
    }

    /**
     * Get the maximum temperatures of the day
     *
     * @return max temps
     * @throws Exception
     */
    public final int getMaxTemperatures() throws Exception {
        return JSONUtils.optInt(getMain(), WeatherJSON.Main.TEMPERATURE_MAX);
    }

    /**
     * Gets the minimum temperatures of the day
     *
     * @return min temps
     * @throws Exception
     */
    public final int getMinTemperatures() throws Exception {
        return JSONUtils.optInt(getMain(), WeatherJSON.Main.TEMPERATURE_MIN);
    }

    /**
     * Gets the weather visibility
     *
     * @return visibility
     * @throws Exception
     */
    public final int getVisibility() throws Exception {
        return JSONUtils.optInt(getWeatherJSON(), WeatherJSON.VISIBILITY);
    }

    /**
     * Returns the wind json object
     * <p>
     * This contains the following
     * <ul>
     * <li>Speed e.g 4.1</li>
     * <li>Degrees e.g. 240</li>
     * </ul>
     * </p>
     *
     * @return wind json object
     */
    public final JSONObject getWind() {
        return JSONUtils.optJSONObject(getWeatherJSON(), WeatherJSON.WIND);
    }

    /**
     * Get the speed of the wind
     *
     * @return wind speed
     */
    public final double getWindSpeed() {
        return JSONUtils.optDouble(getWind(), WeatherJSON.Wind.SPEED);
    }

    /**
     * Get the degree of the wind
     *
     * @return wind degrees
     */
    public final int getWindDegrees() {
        return JSONUtils.optInt(getWind(), WeatherJSON.Wind.DEGREE);
    }

    /**
     * Gets the cloud JSON Object
     *
     * @return cloud json object
     */
    public final JSONObject getClouds() {
        return JSONUtils.optJSONObject(getWeatherJSON(), WeatherJSON.CLOUDS);
    }

    /**
     * Gets the cloud cover
     *
     * @return cloud cover percentage
     */
    public final int getCloudsAll() {
        return JSONUtils.optInt(getClouds(), WeatherJSON.Clouds.ALL);
    }

    /**
     * Returns the day time
     *
     * @return day time
     */
    public final long getDayTime() {
        return JSONUtils.optLong(getWeatherJSON(), WeatherJSON.DAY_TIME);
    }

    /**
     * Get the system meta data
     *
     * @return system meta data
     */
    public final JSONObject getSystem() {
        return JSONUtils.optJSONObject(getWeatherJSON(), WeatherJSON.SYSTEM);
    }

    /**
     * @return
     */
    public final int getType() {
        return JSONUtils.optInt(getSystem(), WeatherJSON.System.TYPE);
    }

    /**
     * @return
     */
    public final int getSystemId() {
        return JSONUtils.optInt(getSystem(), WeatherJSON.System.ID);
    }

    /**
     * @return
     */
    public final float getMessage() {
        return JSONUtils.optFloat(getSystem(), WeatherJSON.System.MESSAGE);
    }

    /**
     * @return
     */
    public final String getCountry() {
        return JSONUtils.optString(getSystem(), WeatherJSON.System.COUNTRY);
    }

    /**
     * @return
     */
    public final long getSunrise() {
        return JSONUtils.optLong(getSystem(), WeatherJSON.System.SUNRISE);
    }

    /**
     * @return
     */
    public final long getSunset() {
        return JSONUtils.optLong(getSystem(), WeatherJSON.System.SUNSET);
    }

    /**
     * Gets the city id
     *
     * @return city id
     */
    public final int getCityId() {
        return JSONUtils.optInt(getWeatherJSON(), WeatherJSON.City.ID);
    }

    /**
     * Gets the city name
     *
     * @return city name
     */
    public final String getCityName() {
        return JSONUtils.optString(getWeatherJSON(), WeatherJSON.City.NAME);
    }

    /**
     * Gets the cod
     *
     * @return cod
     */
    public final int getCod() {
        return JSONUtils.optInt(getWeatherJSON(), WeatherJSON.COD);
    }
}
