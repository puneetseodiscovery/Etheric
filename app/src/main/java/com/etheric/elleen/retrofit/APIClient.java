package com.etheric.elleen.retrofit;


import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;

    static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       // OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ethericindia.com/etheric/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(1000, TimeUnit.SECONDS)
//                .readTimeout(1000,TimeUnit.SECONDS).build();
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://ethericindia.com/etheric/").client(client)
//                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
        return retrofit;
    }
}
