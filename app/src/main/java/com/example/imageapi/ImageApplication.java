package com.example.imageapi;

import android.app.Application;

import com.example.imageapi.api.ImageApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageApplication extends Application {
    private Retrofit retrofit;
    private static ImageApi imageApi;
    private String baseURL = "https://pixabay.com/";

    public static String apiKey = "16540032-f2caf057e44153ee9e0770f79";

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        imageApi = retrofit.create(ImageApi.class);
    }

    public static ImageApi getApi() {
        return imageApi;
    }


}
