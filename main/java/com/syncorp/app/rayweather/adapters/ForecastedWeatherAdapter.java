package com.syncorp.app.rayweather.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.activities.DetailsActivity;
import com.syncorp.app.rayweather.constants.AppConstants;
import com.syncorp.app.rayweather.datamodels.WeatherForecastsModel;

import java.util.List;

/**
 * Created by victor on 3/30/2016.
 */
public class ForecastedWeatherAdapter extends RecyclerView.Adapter<ForecastedWeatherAdapter.WeatherForecastViewHolder> {

    private Context context;
    private List<WeatherForecastsModel> weatherForecastsModels;
    private int layout;


    public ForecastedWeatherAdapter(Context context, int layout, List<WeatherForecastsModel> weatherForecastsModels) {

        this.context = context;
        this.layout = layout;
        this.weatherForecastsModels = weatherForecastsModels;
    }

    @Override
    public WeatherForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_feed, parent, false);
        final WeatherForecastViewHolder weatherForecastViewHolder = new WeatherForecastViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onWeatherForecastClicked(weatherForecastsModels.get(weatherForecastViewHolder.getAdapterPosition()));
            }
        });
        return weatherForecastViewHolder;
    }

    /**
     * Call back called when a weather forecast is clicked
     *
     * @param weatherForecastsModel
     */
    private void onWeatherForecastClicked(WeatherForecastsModel weatherForecastsModel) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(AppConstants.Intents.WeatherForecastDetails.EXTRA_ICON, weatherForecastsModel.getWeatherIconFileUri());
        intent.putExtra(AppConstants.Intents.WeatherForecastDetails.EXTRA_CONDITION, weatherForecastsModel.getCondition());
        intent.putExtra(AppConstants.Intents.WeatherForecastDetails.EXTRA_WEATHER_DESCRIPTION, weatherForecastsModel.getWeatherDescription());
        intent.putExtra(AppConstants.Intents.WeatherForecastDetails.EXTRA_WEEK_DAY, weatherForecastsModel.getDay());
        intent.putExtra(AppConstants.Intents.WeatherForecastDetails.EXTRA_DATE, weatherForecastsModel.getDate());
        intent.putExtra(AppConstants.Intents.WeatherForecastDetails.TEMPERATURES, weatherForecastsModel.getTemperature());
        intent.putExtra(AppConstants.Intents.WeatherForecastDetails.HUMIDITY, weatherForecastsModel.getHumidity());
        context.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(WeatherForecastViewHolder holder, int position) {
        WeatherForecastsModel weatherForecastsModel = getWeatherForecastsModels().get(position);
        holder.date.setText(weatherForecastsModel.getDate());
        holder.wIcon.setImageBitmap(weatherForecastsModel.getWeatherIcon());
        holder.day.setText(weatherForecastsModel.getDay());
        holder.condition.setText(weatherForecastsModel.getCondition());

        Log.i("SETTING_TEXT", weatherForecastsModel.getDay());
    }

    @Override
    public int getItemCount() {
        return getWeatherForecastsModels().size();
    }

    public Context getContext() {
        return context;
    }

    public int getLayout() {
        return layout;
    }

    public List<WeatherForecastsModel> getWeatherForecastsModels() {
        return weatherForecastsModels;
    }

    public class WeatherForecastViewHolder extends RecyclerView.ViewHolder {
        ImageView wIcon;
        TextView day;
        TextView condition;
        TextView date;

        public WeatherForecastViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            wIcon = (ImageView) itemView.findViewById(R.id.wIcon);
            day = (TextView) itemView.findViewById(R.id.day);
            condition = (TextView) itemView.findViewById(R.id.condition);

        }
    }
}
