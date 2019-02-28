package com.weather.knowweather.Utilities;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*
 * Created by Sulaiman on 28/2/2019.
 */

public class KWApiClient {
    private static Retrofit retrofit = null;

    static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build();

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(CommonUtilities.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }
}
