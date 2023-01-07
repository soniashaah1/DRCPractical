package com.example.drcpractical.rest;

import com.example.drcpractical.modelplace.PlacesResponse;
import com.example.drcpractical.modelgif.GIFResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("json")
    Call<PlacesResponse> getPlaces(@Query("location") String location,
                                   @Query("rankby") String rankby,
                                   @Query("types") String types,
                                   @Query("sensor") String sensor,
                                   @Query("key") String key);

    @GET("search")
    Call<GIFResponse> getGIF(@Query("q") String q, @Query("apikey") String apiKey
    );
}
