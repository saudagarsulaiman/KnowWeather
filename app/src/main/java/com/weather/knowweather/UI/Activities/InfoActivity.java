package com.weather.knowweather.UI.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.weather.knowweather.R;
import com.weather.knowweather.UI.Models.WeatherData;
import com.weather.knowweather.Utilities.CONSTANTS;
import com.weather.knowweather.Utilities.CommonUtilities;
import com.weather.knowweather.Utilities.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity implements OnMapReadyCallback {

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
    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.btn_map)
    Button btn_map;


    //    private Typeface typeface;
    private AdView mAdView;
    WeatherData weatherData;

    //    MAPS
    SupportMapFragment mapFragment;
    GoogleMap mMap;
    //    LatLng origin = new LatLng(30.739834, 76.782702);
    LatLng dest;/* = new LatLng(30.705493, 76.801256);*/
    ProgressDialog progressDialog;

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

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


/*
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUri = "http://maps.google.com/maps?q=loc:" + weatherData.getMdl_coord().getStr_lat() + "," + weatherData.getMdl_coord().getStr_lon() + " (" + weatherData.getStr_name() + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
*/
        String data = getIntent().getStringExtra(CONSTANTS.data);

        setData(data);

        if (CommonUtilities.isConnectionAvailable(InfoActivity.this)) {
            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            CommonUtilities.ShowToastMessage(InfoActivity.this, getResources().getString(R.string.internetconnection));
        }

    }

    private void setData(String data) {
        try {
            weatherData = GsonUtils.getInstance().fromJson(data, WeatherData.class);
            txt_loc.setText(weatherData.getStr_name() /*+ "," + weatherData.getMdl_sys().getStr_country()*/);
            txt_temp.setText(String.format("%.2f", Double.parseDouble(weatherData.getMdl_main().getStr_temp())) + "F" + " / " + String.format("%.2f", Double.parseDouble(CommonUtilities.convertFtoC(weatherData.getMdl_main().getStr_temp()))) + "C");
            txt_humid.setText(String.format("%.2f", Double.parseDouble(weatherData.getMdl_main().getStr_humidity())));
            txt_desc.setText(weatherData.getMdlist_weather().get(0).getStr_description());
            txt_latlong.setText(String.format("%.3f", Double.parseDouble(weatherData.getMdl_coord().getStr_lat())) + " , " + String.format("%.3f", Double.parseDouble(weatherData.getMdl_coord().getStr_lon())));
            txt_sunrise.setText(CommonUtilities.covertMsToDate(Long.parseLong(weatherData.getMdl_sys().getStr_sunrise())));
            txt_sunset.setText(CommonUtilities.covertMsToDate(Long.parseLong(weatherData.getMdl_sys().getStr_sunset())));
            dest = new LatLng(Double.parseDouble(weatherData.getMdl_coord().getStr_lat()), Double.parseDouble(weatherData.getMdl_coord().getStr_lon()));

/*
            if (weatherData.getMdl_coord().getStr_lat() != null && weatherData.getMdl_coord().getStr_lon() != null) {
                btn_map.setVisibility(View.VISIBLE);
            } else {
                btn_map.setVisibility(View.GONE);
            }
*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMapToolbarEnabled(false);

        googleMap.addMarker(new MarkerOptions()
                .position(dest)
                .title(weatherData.getStr_name())
        );

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dest, 14));

    }

}
