package com.joenjogu.gadsleaderboard.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String URL = "https://gadsapi.herokuapp.com/";

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder okhttp =
            new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttp.build());

    private static Retrofit retrofit = retrofitBuilder.build();

    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }
}
