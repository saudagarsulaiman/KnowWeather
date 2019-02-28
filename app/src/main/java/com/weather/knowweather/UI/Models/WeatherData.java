package com.weather.knowweather.UI.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherData implements Parcelable {

    @SerializedName("coord")
    Coord mdl_coord;
    @SerializedName("weather")
    List<Weather> mdlist_weather;
    @SerializedName("base")
    String str_base;
    @SerializedName("main")
    Main mdl_main;
    @SerializedName("visibility")
    String str_visibility;
    @SerializedName("wind")
    Wind mdl_wind;
    @SerializedName("clouds")
    Clouds mdl_clouds;
    @SerializedName("dt")
    String str_dt;
    @SerializedName("sys")
    Sys mdl_sys;
    @SerializedName("id")
    String str_id;
    @SerializedName("name")
    String str_name;
    @SerializedName("cod")
    String str_cod;

    protected WeatherData(Parcel in) {
        mdl_coord = in.readParcelable(Coord.class.getClassLoader());
        mdlist_weather = in.createTypedArrayList(Weather.CREATOR);
        str_base = in.readString();
        mdl_main = in.readParcelable(Main.class.getClassLoader());
        str_visibility = in.readString();
        mdl_wind = in.readParcelable(Wind.class.getClassLoader());
        mdl_clouds = in.readParcelable(Clouds.class.getClassLoader());
        str_dt = in.readString();
        mdl_sys = in.readParcelable(Sys.class.getClassLoader());
        str_id = in.readString();
        str_name = in.readString();
        str_cod = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mdl_coord, flags);
        dest.writeTypedList(mdlist_weather);
        dest.writeString(str_base);
        dest.writeParcelable(mdl_main, flags);
        dest.writeString(str_visibility);
        dest.writeParcelable(mdl_wind, flags);
        dest.writeParcelable(mdl_clouds, flags);
        dest.writeString(str_dt);
        dest.writeParcelable(mdl_sys, flags);
        dest.writeString(str_id);
        dest.writeString(str_name);
        dest.writeString(str_cod);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };

    public Coord getMdl_coord() {
        return mdl_coord;
    }

    public void setMdl_coord(Coord mdl_coord) {
        this.mdl_coord = mdl_coord;
    }

    public List<Weather> getMdlist_weather() {
        return mdlist_weather;
    }

    public void setMdlist_weather(List<Weather> mdlist_weather) {
        this.mdlist_weather = mdlist_weather;
    }

    public String getStr_base() {
        return str_base;
    }

    public void setStr_base(String str_base) {
        this.str_base = str_base;
    }

    public Main getMdl_main() {
        return mdl_main;
    }

    public void setMdl_main(Main mdl_main) {
        this.mdl_main = mdl_main;
    }

    public String getStr_visibility() {
        return str_visibility;
    }

    public void setStr_visibility(String str_visibility) {
        this.str_visibility = str_visibility;
    }

    public Wind getMdl_wind() {
        return mdl_wind;
    }

    public void setMdl_wind(Wind mdl_wind) {
        this.mdl_wind = mdl_wind;
    }

    public Clouds getMdl_clouds() {
        return mdl_clouds;
    }

    public void setMdl_clouds(Clouds mdl_clouds) {
        this.mdl_clouds = mdl_clouds;
    }

    public String getStr_dt() {
        return str_dt;
    }

    public void setStr_dt(String str_dt) {
        this.str_dt = str_dt;
    }

    public Sys getMdl_sys() {
        return mdl_sys;
    }

    public void setMdl_sys(Sys mdl_sys) {
        this.mdl_sys = mdl_sys;
    }

    public String getStr_id() {
        return str_id;
    }

    public void setStr_id(String str_id) {
        this.str_id = str_id;
    }

    public String getStr_name() {
        return str_name;
    }

    public void setStr_name(String str_name) {
        this.str_name = str_name;
    }

    public String getStr_cod() {
        return str_cod;
    }

    public void setStr_cod(String str_cod) {
        this.str_cod = str_cod;
    }
}
