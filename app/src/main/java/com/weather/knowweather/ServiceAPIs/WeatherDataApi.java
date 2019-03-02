package com.weather.knowweather.ServiceAPIs;

/*
 * Created by Sulaiman on 26/10/2018.
 */

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherDataApi {

    @GET("/data/2.5/weather?")
    Call<ResponseBody> getDataByName(@Query("q") String cityName, @Query("APPID") String APPID, @Query("units") String units);

    @GET("/data/2.5/weather?")
    Call<ResponseBody> getDataById(@Query("id") String cityID, @Query("APPID") String APPID, @Query("units") String units);

    @GET("/data/2.5/weather?")
    Call<ResponseBody> getDataByZipCode(@Query("zip") String cityID, @Query("APPID") String APPID, @Query("units") String units);

}
