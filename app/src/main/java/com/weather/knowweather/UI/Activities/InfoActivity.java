package com.weather.knowweather.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weather.knowweather.R;
import com.weather.knowweather.UI.Models.WeatherData;
import com.weather.knowweather.Utilities.CONSTANTS;
import com.weather.knowweather.Utilities.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {

    @BindView(R.id.txt_data)
    TextView txt_data;
    @BindView(R.id.lnr_parent)
    LinearLayout lnr_parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        String data = getIntent().getStringExtra(CONSTANTS.data);

        setData(data);

    }

    private void setData(String data) {
        try {

            WeatherData weatherData = GsonUtils.getInstance().fromJson(data, WeatherData.class);

            txt_data.setText(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
