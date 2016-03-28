package com.syncorp.app.rayweather.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.syncorp.app.rayweather.R;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyPreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String category = getArguments().getString("category");
        if (category != null) {
            if (category.equals(getString(R.string.category_myprefs))) {
                addPreferencesFromResource(R.xml.prefs_ideal);
            } else if (category.equals(getString(R.string.category_notifications))) {
                addPreferencesFromResource(R.xml.prefs_notifications);
            }
        }
    }
}
