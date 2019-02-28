package com.weather.knowweather.UI.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.weather.knowweather.R;
import com.weather.knowweather.Utilities.CONSTANTS;
import com.weather.knowweather.Utilities.CommonUtilities;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.img_english)
    ImageView img_english;
    @BindView(R.id.img_arabic)
    ImageView img_arabic;
    @BindView(R.id.img_chinese)
    ImageView img_chinese;
    @BindView(R.id.img_russian)
    ImageView img_russian;
    @BindView(R.id.img_malay)
    ImageView img_malay;
    @BindView(R.id.img_sel_lang)
    ImageView img_sel_lang;

    /*
    @BindView(R.id.img_arabic)
    ImageView img_arabic;
*/

    @BindView(R.id.text_splash_title)
    TextView text_splash_title;
    @BindView(R.id.btn_continue)
    Button btn_continue;


    private Typeface typeface;
    private Window mWindow;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        prefs = getSharedPreferences(CONSTANTS.appName, Activity.MODE_PRIVATE);
        editor = prefs.edit();


        mWindow = getWindow();
        mWindow.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        String strLocale = CommonUtilities.loadLocale(this);

        if (strLocale.equalsIgnoreCase("en")) {
            img_sel_lang.setImageDrawable(getResources().getDrawable(R.drawable.english));
            img_english.setVisibility(View.GONE);
            img_arabic.setVisibility(View.VISIBLE);
            img_russian.setVisibility(View.VISIBLE);
            img_chinese.setVisibility(View.VISIBLE);
            img_malay.setVisibility(View.VISIBLE);
        } else if (strLocale.equalsIgnoreCase("ar")) {
            img_sel_lang.setImageDrawable(getResources().getDrawable(R.drawable.arab));
            img_english.setVisibility(View.VISIBLE);
            img_arabic.setVisibility(View.GONE);
            img_russian.setVisibility(View.VISIBLE);
            img_chinese.setVisibility(View.VISIBLE);
            img_malay.setVisibility(View.VISIBLE);
        } else if (strLocale.equalsIgnoreCase("zh")) {
            img_sel_lang.setImageDrawable(getResources().getDrawable(R.drawable.china));
            img_english.setVisibility(View.VISIBLE);
            img_arabic.setVisibility(View.VISIBLE);
            img_russian.setVisibility(View.VISIBLE);
            img_chinese.setVisibility(View.GONE);
            img_malay.setVisibility(View.VISIBLE);
        } else if (strLocale.equalsIgnoreCase("ru")) {
            img_sel_lang.setImageDrawable(getResources().getDrawable(R.drawable.russia));
            img_english.setVisibility(View.VISIBLE);
            img_arabic.setVisibility(View.VISIBLE);
            img_russian.setVisibility(View.GONE);
            img_chinese.setVisibility(View.VISIBLE);
            img_malay.setVisibility(View.VISIBLE);
        } else if (strLocale.equalsIgnoreCase("ms")) {
            img_sel_lang.setImageDrawable(getResources().getDrawable(R.drawable.malaysia));
            img_english.setVisibility(View.VISIBLE);
            img_arabic.setVisibility(View.VISIBLE);
            img_russian.setVisibility(View.VISIBLE);
            img_chinese.setVisibility(View.VISIBLE);
            img_malay.setVisibility(View.GONE);
        }

        img_arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtilities.changeLang(SplashScreenActivity.this, "ar");
                recreate();
            }
        });
        img_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtilities.changeLang(SplashScreenActivity.this, "en");
                recreate();
            }
        });
        img_chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtilities.changeLang(SplashScreenActivity.this, "zh");
                recreate();
            }
        });
        img_russian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtilities.changeLang(SplashScreenActivity.this, "ru");
                recreate();
            }
        });
        img_malay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtilities.changeLang(SplashScreenActivity.this, "ms");
                recreate();
            }
        });

//        text_splash_sub_title.setTextColor(getResources().getColor(R.color.white));

        typeface = Typeface.createFromAsset(getAssets(), "fonts/Fabulous_PERSONAL_USE.ttf");
        text_splash_title.setTypeface(typeface);

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    boolean app_pin = prefs.getBoolean(CONSTANTS.is_app_pin, false);
//
//                    if (app_pin) {
//                        Intent intent = new Intent(SplashScreenActivity.this, AppPinActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                } catch (NullPointerException e) {
//                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                    e.printStackTrace();
//                }
//            }
//        }, 2000);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean app_pin = prefs.getBoolean(CONSTANTS.is_app_pin, false);
                if (app_pin) {
                    Intent intent = new Intent(SplashScreenActivity.this, AppPinActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }


}