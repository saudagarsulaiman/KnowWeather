package com.weather.knowweather.UI.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Main implements Parcelable {

    @SerializedName("temp")
    String str_temp;
    @SerializedName("pressure")
    String str_pressure;
    @SerializedName("humidity")
    String str_humidity;
    @SerializedName("temp_min")
    String str_temp_min;
    @SerializedName("temp_max")
    String str_temp_max;
    @SerializedName("sea_level")
    String str_sea_level;
    @SerializedName("grnd_level")
    String str_grnd_level;

    protected Main(Parcel in) {
        str_temp = in.readString();
        str_pressure = in.readString();
        str_humidity = in.readString();
        str_temp_min = in.readString();
        str_temp_max = in.readString();
        str_sea_level = in.readString();
        str_grnd_level = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str_temp);
        dest.writeString(str_pressure);
        dest.writeString(str_humidity);
        dest.writeString(str_temp_min);
        dest.writeString(str_temp_max);
        dest.writeString(str_sea_level);
        dest.writeString(str_grnd_level);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    public String getStr_temp() {
        return str_temp;
    }

    public void setStr_temp(String str_temp) {
        this.str_temp = str_temp;
    }

    public String getStr_pressure() {
        return str_pressure;
    }

    public void setStr_pressure(String str_pressure) {
        this.str_pressure = str_pressure;
    }

    public String getStr_humidity() {
        return str_humidity;
    }

    public void setStr_humidity(String str_humidity) {
        this.str_humidity = str_humidity;
    }

    public String getStr_temp_min() {
        return str_temp_min;
    }

    public void setStr_temp_min(String str_temp_min) {
        this.str_temp_min = str_temp_min;
    }

    public String getStr_temp_max() {
        return str_temp_max;
    }

    public void setStr_temp_max(String str_temp_max) {
        this.str_temp_max = str_temp_max;
    }

    public String getStr_sea_level() {
        return str_sea_level;
    }

    public void setStr_sea_level(String str_sea_level) {
        this.str_sea_level = str_sea_level;
    }

    public String getStr_grnd_level() {
        return str_grnd_level;
    }

    public void setStr_grnd_level(String str_grnd_level) {
        this.str_grnd_level = str_grnd_level;
    }
}
