package com.weather.knowweather.UI.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Wind implements Parcelable {

    @SerializedName("speed")
    String str_speed;
    @SerializedName("deg")
    String str_deg;

    protected Wind(Parcel in) {
        str_speed = in.readString();
        str_deg = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str_speed);
        dest.writeString(str_deg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    public String getStr_speed() {
        return str_speed;
    }

    public void setStr_speed(String str_speed) {
        this.str_speed = str_speed;
    }

    public String getStr_deg() {
        return str_deg;
    }

    public void setStr_deg(String str_deg) {
        this.str_deg = str_deg;
    }
}
