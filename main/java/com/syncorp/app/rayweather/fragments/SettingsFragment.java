package com.syncorp.app.rayweather.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.syncorp.app.rayweather.R;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String category = getArguments().getString("category");
        if (category != null) {
            if (category.equals(getString(R.string.category_general))) {
                addPreferencesFromResource(R.xml.prefs_general);
            } else if (category.equals(getString(R.string.category_advanced))) {
                addPreferencesFromResource(R.xml.prefs_advanced);
            } else if (category.equals(getString(R.string.category_upgrade))) {
                Toast.makeText(getActivity(), "This is the Beta version. Stable version coming soon", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
