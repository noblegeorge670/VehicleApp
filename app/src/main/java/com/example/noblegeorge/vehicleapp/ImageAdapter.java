package com.example.noblegeorge.vehicleapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by noblegeorge on 2018-12-06.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<Images> imageList;
    private Context context;

    public ImageAdapter(Context context,List<Images> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Picasso.with(context).load(imageList.get(position).getImageUrl()).fit().centerCrop()
                .placeholder(R.drawable.vehicle)
                .into(holder.vehicleImage);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView vehicleImage;

        ImageViewHolder(View itemView) {
            super(itemView);
            vehicleImage = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
