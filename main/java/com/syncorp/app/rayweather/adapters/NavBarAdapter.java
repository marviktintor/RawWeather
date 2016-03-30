package com.syncorp.app.rayweather.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.activities.AboutActivity;
import com.syncorp.app.rayweather.activities.MyPreferencesActivity;
import com.syncorp.app.rayweather.activities.SettingsActivity;
import com.syncorp.app.rayweather.activities.FullDayForecastActivity;
import com.syncorp.app.rayweather.datamodels.NavBarModel;

import java.util.Collections;
import java.util.List;


public class NavBarAdapter extends RecyclerView.Adapter<NavBarAdapter.NavigationBarAdapterViewHolder> {
    private LayoutInflater layoutInflater;
    List<NavBarModel> data = Collections.emptyList();
    private Context context;

    public NavBarAdapter(Context context, List<NavBarModel> data) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public NavigationBarAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int pos) {
        View view = layoutInflater.inflate(R.layout.navbar_row, viewGroup, false);
        NavigationBarAdapterViewHolder navigationBarAdapterViewHolder = new NavigationBarAdapterViewHolder(view);
        return navigationBarAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(NavigationBarAdapterViewHolder navigationBarAdapterViewHolder, int pos) {
        NavBarModel navBarModel = data.get(pos);
        navigationBarAdapterViewHolder.icon.setImageResource(navBarModel.iconID);
        navigationBarAdapterViewHolder.title.setText(navBarModel.navEntry);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NavigationBarAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;

        public NavigationBarAdapterViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (getLayoutPosition()) {
                        case 0:
                            Toast.makeText(context, "My Weather Preferences", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, MyPreferencesActivity.class));
                            break;
                        case 1:
                            Toast.makeText(context, "This Week's Forecast", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, FullDayForecastActivity.class));
                            break;
                        case 2:
                            Toast.makeText(context, "App Settings", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, SettingsActivity.class));
                            break;
                        case 3:
                            Toast.makeText(context, "About Rayweather", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, AboutActivity.class));
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }
}
