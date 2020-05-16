package com.example.imageapi.api;

import com.example.imageapi.response.ImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageApi {
    @GET("/api")
    Call<ImageResponse> getImages(@Query("key") String apiKey);

    @GET("/api")
    Call<ImageResponse> getImagesByType(
            @Query("key") String apiKey,
            @Query("image_type") String imageType,
            @Query("q") String query);
}
