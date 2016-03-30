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
import android.widget.Toast;

import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.activities.DetailsActivity;
import com.syncorp.app.rayweather.datamodels.WeatherForecastsModel;

import java.util.List;

/**
 * Created by victor on 3/30/2016.
 */
public class FullDayForecastedWeatherAdapter extends RecyclerView.Adapter<FullDayForecastedWeatherAdapter.WeatherForecastViewHolder> {

    private Context context;
    private List<WeatherForecastsModel> weatherForecastsModels;
    private int layout;


    public FullDayForecastedWeatherAdapter(Context context, int layout, List<WeatherForecastsModel> weatherForecastsModels) {

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
        Toast.makeText(getContext(),weatherForecastsModel.getCondition(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBindViewHolder(WeatherForecastViewHolder holder, int position) {
        WeatherForecastsModel weatherForecastsModel = getWeatherForecastsModels().get(position);
        holder.wIcon.setImageBitmap(weatherForecastsModel.getWeatherIcon());
        holder.day.setText(weatherForecastsModel.getDay());
        holder.condition.setText(weatherForecastsModel.getCondition());

        Log.i("SETTING_TEXT",weatherForecastsModel.getDay());
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

        public WeatherForecastViewHolder(View itemView) {
            super(itemView);
            wIcon = (ImageView) itemView.findViewById(R.id.wIcon);
            day = (TextView) itemView.findViewById(R.id.day);
            condition = (TextView) itemView.findViewById(R.id.condition);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    switch (getLayoutPosition()) {
                        case 0:
                            intent = new Intent(context, DetailsActivity.class);
                            intent.putExtra("rID", R.drawable.sunny);
                            intent.putExtra("day", "Sunday");
                            intent.putExtra("date", "24-01-2016");
                            intent.putExtra("temp", "76/51");
                            intent.putExtra("hum", "60");
                            context.startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(context, DetailsActivity.class);
                            intent.putExtra("rID", R.drawable.hazy);
                            intent.putExtra("day", "Monday");
                            intent.putExtra("date", "25-01-2016");
                            intent.putExtra("temp", "77/60");
                            intent.putExtra("hum", "62");
                            context.startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(context, DetailsActivity.class);
                            intent.putExtra("rID", R.drawable.light_clouds);
                            intent.putExtra("day", "Tuesday");
                            intent.putExtra("date", "26-01-2016");
                            intent.putExtra("temp", "75/53");
                            intent.putExtra("hum", "61");
                            context.startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(context, DetailsActivity.class);
                            intent.putExtra("rID", R.drawable.light_rain);
                            intent.putExtra("day", "Wednesday");
                            intent.putExtra("date", "26-01-2016");
                            intent.putExtra("temp", "76/50");
                            intent.putExtra("hum", "59");
                            context.startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(context, DetailsActivity.class);
                            intent.putExtra("rID", R.drawable.storm);
                            intent.putExtra("day", "Thursday");
                            intent.putExtra("date", "26-01-2016");
                            intent.putExtra("temp", "76/51");
                            intent.putExtra("hum", "62");
                            context.startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent(context, DetailsActivity.class);
                            intent.putExtra("rID", R.drawable.fog);
                            intent.putExtra("day", "Friday");
                            intent.putExtra("date", "26-01-2016");
                            intent.putExtra("temp", "77/54");
                            intent.putExtra("hum", "60");
                            context.startActivity(intent);
                            break;
                        case 6:
                            intent = new Intent(context, DetailsActivity.class);
                            intent.putExtra("rID", R.drawable.calm);
                            intent.putExtra("day", "Saturday");
                            intent.putExtra("date", "26-01-2016");
                            intent.putExtra("temp", "76/60");
                            intent.putExtra("hum", "59");
                            context.startActivity(intent);
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }
}
