package com.weather.knowweather.UI.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Coord implements Parcelable {

    @SerializedName("lon")
    String str_lon;
    @SerializedName("lat")
    String str_lat;

    protected Coord(Parcel in) {
        str_lon = in.readString();
        str_lat = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str_lon);
        dest.writeString(str_lat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Coord> CREATOR = new Creator<Coord>() {
        @Override
        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        @Override
        public Coord[] newArray(int size) {
            return new Coord[size];
        }
    };

    public String getStr_lon() {
        return str_lon;
    }

    public void setStr_lon(String str_lon) {
        this.str_lon = str_lon;
    }

    public String getStr_lat() {
        return str_lat;
    }

    public void setStr_lat(String str_lat) {
        this.str_lat = str_lat;
    }
}
