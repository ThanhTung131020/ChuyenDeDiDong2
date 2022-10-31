package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Model.ImageSilder;
import com.example.chuyendedidong2.R;


import java.util.List;

public class ImageSliderAdapter extends PagerAdapter {
    private Context context;
    private List<ImageSilder> imageSilders;

    public ImageSliderAdapter(Context context, List<ImageSilder> imageSilders) {
        this.context = context;
        this.imageSilders = imageSilders;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_image_silder, container, false);
        ImageView imageView = view.findViewById(R.id.ivSilder);

        ImageSilder imageSilder = imageSilders.get(position);
        if (imageSilder != null){
            Glide.with(context).load(imageSilder.getResourceID()).into(imageView);
        }
        //Add view to view group
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(imageSilders != null){
            return imageSilders.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //Remove view
        container.removeView((View) object);
    }
}
