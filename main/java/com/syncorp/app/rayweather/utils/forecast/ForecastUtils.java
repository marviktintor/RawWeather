package com.syncorp.app.rayweather.utils.forecast;

import com.syncorp.app.coreutils.json.JSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by victor on 3/28/2016.
 */
public strictfp class ForecastUtils {

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

}
