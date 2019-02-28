package com.weather.knowweather.Utilities;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

//import com.google.android.gms.plus.Plus;


/*
 * Created by Sulaiman on 28/2/2019.
 */

public class MyApplication extends Application {

    public static MyApplication myApplication;

    public AppCompatActivity activity;
    SharedPreferences sharedPreferences;

    public Boolean getScreenShot() {
        return isScreenShot;
    }

    public void setScreenShot(Boolean screenShot) {
        isScreenShot = screenShot;
    }

    public Boolean getAppPin() {
        return isAppPin;
    }

    public void setAppPin(Boolean appPin) {
        isAppPin = appPin;
    }

    Boolean isScreenShot;
    Boolean isAppPin;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        sharedPreferences = getSharedPreferences(CONSTANTS.appName, Activity.MODE_PRIVATE);
        isScreenShot = sharedPreferences.getBoolean(CONSTANTS.screenshot, false);
        isAppPin = sharedPreferences.getBoolean(CONSTANTS.is_app_pin, false);

    }

    public void disableScreenCapture(Activity context) {
        if (isScreenShot) {
            context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        } else {
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
    }

}
