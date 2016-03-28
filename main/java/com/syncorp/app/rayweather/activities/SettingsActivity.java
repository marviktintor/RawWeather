package com.syncorp.app.rayweather.activities;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.fragments.SettingsFragment;

import java.util.List;

public class SettingsActivity extends PreferenceActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prepareLayout();

        buildLegacyPreferences();
    }

    private void prepareLayout() {
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        View content = root.getChildAt(0);
        LinearLayout toolbarContainer = (LinearLayout) View.inflate(this, R.layout.activity_settings, null);

        root.removeAllViews();
        toolbarContainer.addView(content);
        root.addView(toolbarContainer);

        toolbar = (Toolbar) toolbarContainer.findViewById(R.id.appbar);
        toolbar.setTitle(getTitle());
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void buildLegacyPreferences() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            String action = getIntent().getAction();
            if (action == null) {
                addPreferencesFromResource(R.xml.headers_legacy);
            } else if (action.equals(getString(R.string.category_general))) {
                toolbar.setTitle(getString(R.string.header_general));
                addPreferencesFromResource(R.xml.prefs_general);
            } else if (action.equals(getString(R.string.category_advanced))) {
                toolbar.setTitle(getString(R.string.header_general));
                addPreferencesFromResource(R.xml.prefs_advanced);
            } else if (action.equals(getString(R.string.category_upgrade))) {
                toolbar.setTitle(getString(R.string.header_upgrade));
                Toast.makeText(this, "This is the Beta version. Stable version coming soon", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onBuildHeaders(List<PreferenceActivity.Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.headers, target);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected boolean isValidFragment(String fragmentName) {
        return fragmentName.equals(SettingsFragment.class.getName());
    }
}
