package com.weather.knowweather.UI.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Weather implements Parcelable {

    @SerializedName("id")
    String str_id;
    @SerializedName("main")
    String str_main;
    @SerializedName("description")
    String str_description;
    @SerializedName("icon")
    String str_icon;

    protected Weather(Parcel in) {
        str_id = in.readString();
        str_main = in.readString();
        str_description = in.readString();
        str_icon = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str_id);
        dest.writeString(str_main);
        dest.writeString(str_description);
        dest.writeString(str_icon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public String getStr_id() {
        return str_id;
    }

    public void setStr_id(String str_id) {
        this.str_id = str_id;
    }

    public String getStr_main() {
        return str_main;
    }

    public void setStr_main(String str_main) {
        this.str_main = str_main;
    }

    public String getStr_description() {
        return str_description;
    }

    public void setStr_description(String str_description) {
        this.str_description = str_description;
    }

    public String getStr_icon() {
        return str_icon;
    }

    public void setStr_icon(String str_icon) {
        this.str_icon = str_icon;
    }
}
