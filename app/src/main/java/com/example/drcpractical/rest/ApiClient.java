package com.example.drcpractical.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getClient(String baeUrl) {
        return new Retrofit.Builder()
            .baseUrl(baeUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}
