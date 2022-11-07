package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Model.ImageSlider;
import com.example.chuyendedidong2.R;
import com.google.firebase.auth.internal.RecaptchaActivity;

import java.util.ArrayList;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImgSliderViewHolder> {
    Context context;
    ArrayList<ImageSlider> list;

    public ImageSliderAdapter(Context context, ArrayList<ImageSlider> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ImgSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_silder,parent,false);
        return new ImgSliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgSliderViewHolder holder, int position) {
        ImageSlider imageSlider = list.get(position);
        Glide.with(context).load(imageSlider.getImg_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImgSliderViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ImgSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivSilder);
        }
    }
}
