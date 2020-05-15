package com.example.imageapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.imageapi.api.ImageApi;
import com.example.imageapi.response.HitsItem;
import com.example.imageapi.response.ImageResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<HitsItem> images;
    private RecyclerView recyclerView;
    Callback<ImageResponse> callback;
    private Button photoFetcher;
    private Button illustrationFetcher;
    private Button vectorFetcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new ArrayList<>();
        ImageAdapter adapter = new ImageAdapter(images);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.list_of_images);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        callback = new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.body() != null) {
                    images.addAll(response.body().getHits());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred while loading", Toast.LENGTH_SHORT).show();
            }
        };


        photoFetcher = findViewById(R.id.photoButton);

        photoFetcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchPhotos();
            }
        });

        vectorFetcher = findViewById(R.id.vectorButton);

        vectorFetcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchVector();
            }
        });

        illustrationFetcher = findViewById(R.id.illustraionButton);

        illustrationFetcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchIllustrations();
            }
        });
    }

    public void fetchPhotos() {
        images.clear();
        String apiKey = ImageApplication.apiKey;
        ImageApplication.getApi().getImagesByType(apiKey, "photo").enqueue(this.callback);
    }

    public void fetchIllustrations() {
        images.clear();
        String apiKey = ImageApplication.apiKey;
        ImageApplication.getApi().getImagesByType(apiKey, "illustration").enqueue(this.callback);
    }

    public void fetchVector() {
        images.clear();
        String apiKey = ImageApplication.apiKey;
        ImageApplication.getApi().getImagesByType(apiKey, "vector").enqueue(this.callback);
    }

}
