package com.example.imageapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imageapi.response.HitsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    List<HitsItem> images;

    public ImageAdapter(List<HitsItem> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,
            parent, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HitsItem image = images.get(position);

        Picasso.get()
                .load(image.getPreviewURL())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        if (images == null) {
            return 0;
        }

        return images.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.attachment);
        }
    }
}
