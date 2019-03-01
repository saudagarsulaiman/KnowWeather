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

    @BindView(R.id.txt_lbl_loc)
    TextView txt_lbl_loc;
    @BindView(R.id.txt_loc)
    TextView txt_loc;
    @BindView(R.id.txt_lbl_temp)
    TextView txt_lbl_temp;
    @BindView(R.id.txt_temp)
    TextView txt_temp;
    @BindView(R.id.txt_lbl_humid)
    TextView txt_lbl_humid;
    @BindView(R.id.txt_humid)
    TextView txt_humid;
    @BindView(R.id.txt_lbl_desc)
    TextView txt_lbl_desc;
    @BindView(R.id.txt_desc)
    TextView txt_desc;
    @BindView(R.id.txt_lbl_latlong)
    TextView txt_lbl_latlong;
    @BindView(R.id.txt_latlong)
    TextView txt_latlong;


//    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

//        typeface = Typeface.createFromAsset(getAssets(), "fonts/Fabulous_PERSONAL_USE.ttf");

        String data = getIntent().getStringExtra(CONSTANTS.data);

        setData(data);

    }

    private void setData(String data) {
        try {
            WeatherData weatherData = GsonUtils.getInstance().fromJson(data, WeatherData.class);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
