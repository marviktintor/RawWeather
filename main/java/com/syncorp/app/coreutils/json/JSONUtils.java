package com.syncorp.app.coreutils.json;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by victor on 3/28/2016.
 */
public class JSONUtils {

    /**
     * Get double
     *
     * @param jsonObject
     * @param name
     * @return
     */
    public static final Double optDouble(JSONObject jsonObject, String name) {
        return opt(jsonObject, name);
    }

    /**
     * Get int values
     *
     * @param jsonObject
     * @param name
     * @return
     */
    public static final int optInt(JSONObject jsonObject, String name) {
        return (Integer) opt(jsonObject, name);
    }

    /**
     * Get float values
     *
     * @param jsonObject
     * @param name
     * @return
     */
    public static final float optFloat(JSONObject jsonObject, String name) {
        return (Float) opt(jsonObject, name);
    }

    /**
     * Get JSON Object
     *
     * @param jsonObject
     * @param name
     * @return
     */
    public static final JSONObject optJSONObject(JSONObject jsonObject, String name) {
        return (JSONObject) opt(jsonObject, name);
    }

    /**
     * Get JSON Array
     *
     * @param jsonObject
     * @param name
     * @return
     */
    public static final JSONArray optJSONArray(JSONObject jsonObject, String name) {
        return (JSONArray) opt(jsonObject, name);
    }

    /**
     * Get Long
     *
     * @param jsonObject
     * @param name
     * @return
     */
    public static final Long optLong(JSONObject jsonObject, String name) {
        return (Long) opt(jsonObject, name);
    }

    /**
     * Get String
     *
     * @param jsonObject
     * @param name
     * @return
     */
    public static final String optString(JSONObject jsonObject, String name) {
        return (String) opt(jsonObject, name);
    }


    /**
     * Opt any form of data from the json object passed
     *
     * @param jsonObject
     * @param name
     * @param <T>
     * @return
     */
    public static final <T> T opt(JSONObject jsonObject, String name) {
        return (T) jsonObject.opt(name);
    }
}
