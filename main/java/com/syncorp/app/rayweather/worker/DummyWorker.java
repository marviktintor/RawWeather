package com.syncorp.app.rayweather.worker;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by victor on 3/29/2016.
 */
public class DummyWorker {

    /**
     * Context
     */
    public  Context context;

    /**
     * Private constructor to make sure the class cannot be instantiated
     *
     * @param context
     */
    public DummyWorker(Context context) {
        this.context = context;
    }



}
