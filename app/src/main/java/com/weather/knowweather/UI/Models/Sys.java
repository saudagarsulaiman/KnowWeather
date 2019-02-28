package com.weather.knowweather.UI.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Sys implements Parcelable {

    @SerializedName("type")
    String str_type;
    @SerializedName("id")
    String str_id;
    @SerializedName("message")
    String str_message;
    @SerializedName("country")
    String str_country;
    @SerializedName("sunrise")
    String str_sunrise;
    @SerializedName("sunset")
    String str_sunset;

    protected Sys(Parcel in) {
        str_type = in.readString();
        str_id = in.readString();
        str_message = in.readString();
        str_country = in.readString();
        str_sunrise = in.readString();
        str_sunset = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str_type);
        dest.writeString(str_id);
        dest.writeString(str_message);
        dest.writeString(str_country);
        dest.writeString(str_sunrise);
        dest.writeString(str_sunset);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Sys> CREATOR = new Creator<Sys>() {
        @Override
        public Sys createFromParcel(Parcel in) {
            return new Sys(in);
        }

        @Override
        public Sys[] newArray(int size) {
            return new Sys[size];
        }
    };

    public String getStr_type() {
        return str_type;
    }

    public void setStr_type(String str_type) {
        this.str_type = str_type;
    }

    public String getStr_id() {
        return str_id;
    }

    public void setStr_id(String str_id) {
        this.str_id = str_id;
    }

    public String getStr_message() {
        return str_message;
    }

    public void setStr_message(String str_message) {
        this.str_message = str_message;
    }

    public String getStr_country() {
        return str_country;
    }

    public void setStr_country(String str_country) {
        this.str_country = str_country;
    }

    public String getStr_sunrise() {
        return str_sunrise;
    }

    public void setStr_sunrise(String str_sunrise) {
        this.str_sunrise = str_sunrise;
    }

    public String getStr_sunset() {
        return str_sunset;
    }

    public void setStr_sunset(String str_sunset) {
        this.str_sunset = str_sunset;
    }
}
