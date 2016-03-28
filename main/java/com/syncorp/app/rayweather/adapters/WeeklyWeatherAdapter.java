package com.syncorp.app.rayweather.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.syncorp.app.rayweather.R;
import com.syncorp.app.rayweather.activities.DetailsActivity;
import com.syncorp.app.rayweather.datamodels.WeatherModel;

import java.util.Collections;
import java.util.List;


public class WeeklyWeatherAdapter extends RecyclerView.Adapter<WeeklyWeatherAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<WeatherModel> weatherData = Collections.emptyList();

    public WeeklyWeatherAdapter(Context context, List<WeatherModel> weatherData) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.weatherData = weatherData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_feed, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WeatherModel weatherModel = weatherData.get(position);
        holder.wIcon.setImageResource(weatherModel.wIcon);
        holder.day.setText(weatherModel.day);
        holder.condition.setText(weatherModel.condition);
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView wIcon;
        TextView day;
        TextView condition;

        public MyViewHolder(View itemView) {
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
