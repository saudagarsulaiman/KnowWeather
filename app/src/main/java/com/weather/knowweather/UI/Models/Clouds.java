package com.weather.knowweather.UI.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Clouds implements Parcelable {

    @SerializedName("all")
    String str_all;

    protected Clouds(Parcel in) {
        str_all = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str_all);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Clouds> CREATOR = new Creator<Clouds>() {
        @Override
        public Clouds createFromParcel(Parcel in) {
            return new Clouds(in);
        }

        @Override
        public Clouds[] newArray(int size) {
            return new Clouds[size];
        }
    };

    public String getStr_all() {
        return str_all;
    }

    public void setStr_all(String str_all) {
        this.str_all = str_all;
    }
}
