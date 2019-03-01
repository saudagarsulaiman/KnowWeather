package com.weather.knowweather.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.weather.knowweather.R;
import com.weather.knowweather.UI.Models.WeatherData;
import com.weather.knowweather.Utilities.CONSTANTS;
import com.weather.knowweather.Utilities.CommonUtilities;
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
    @BindView(R.id.txt_lbl_sunrise)
    TextView txt_lbl_sunrise;
    @BindView(R.id.txt_sunrise)
    TextView txt_sunrise;
    @BindView(R.id.txt_lbl_sunset)
    TextView txt_lbl_sunset;
    @BindView(R.id.txt_sunset)
    TextView txt_sunset;


    //    private Typeface typeface;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

//        typeface = Typeface.createFromAsset(getAssets(), "fonts/Fabulous_PERSONAL_USE.ttf");

        //        *************ADMOB STARTS*************
//        Initialization
        // Sample AdMob app ID: ca-app-pub-1128305322475918~5980126163
        MobileAds.initialize(this, "ca-app-pub-1128305322475918~5980126163");

//        WidgetAdView Implementation
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//        *************ADMOB ENDS*************


        String data = getIntent().getStringExtra(CONSTANTS.data);

        setData(data);

    }

    private void setData(String data) {
        try {
            WeatherData weatherData = GsonUtils.getInstance().fromJson(data, WeatherData.class);
            txt_loc.setText(weatherData.getStr_name() /*+ "," + weatherData.getMdl_sys().getStr_country()*/);
            txt_temp.setText(String.format("%.2f", Double.parseDouble(weatherData.getMdl_main().getStr_temp())) + "F" + " / " + String.format("%.2f", Double.parseDouble(CommonUtilities.convertFtoC(weatherData.getMdl_main().getStr_temp()))) + "C");
            txt_humid.setText(String.format("%.2f", Double.parseDouble(weatherData.getMdl_main().getStr_humidity())));
            txt_desc.setText(weatherData.getMdlist_weather().get(0).getStr_description());
            txt_latlong.setText(String.format("%.5f", Double.parseDouble(weatherData.getMdl_coord().getStr_lat())) + " , " + String.format("%.5f", Double.parseDouble(weatherData.getMdl_coord().getStr_lon())));
            txt_sunrise.setText(CommonUtilities.covertMsToDate(Long.parseLong(weatherData.getMdl_sys().getStr_sunrise())));
            txt_sunset.setText(CommonUtilities.covertMsToDate(Long.parseLong(weatherData.getMdl_sys().getStr_sunset())));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
