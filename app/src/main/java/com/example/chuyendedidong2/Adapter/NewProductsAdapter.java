package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Model.NewProductModel;
import com.example.chuyendedidong2.ProductActivity;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;


public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NewProductModel> list;
    String img;
    public NewProductsAdapter(Context context, ArrayList<NewProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_product_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewProductModel newProductModel = list.get(position);
        Glide.with(context).load(newProductModel.getImg_url()).into(holder.imageView);
        holder.newName.setText(newProductModel.getName());
        holder.newRating.setRating(newProductModel.getNumStar());
        holder.newPrice.setText(String.valueOf(newProductModel.getPrice()));

//        holder.itemView.setOnClickListener(view -> {
//            Intent intent = new Intent(context,ProductActivity.class);
//
//            intent.putExtra("image",newProductModel.getImg_url());
//            intent.putExtra("name",newProductModel.getName());
//            intent.putExtra("price",String.valueOf(newProductModel.getPrice()));
//            intent.putExtra("rating",String.valueOf(newProductModel.getNumStar()));
//            intent.putExtra("des",newProductModel.getDesciption());
//            intent.putExtra("nameShop",newProductModel.getNameShop());
//            context.startActivity(intent);
//        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductActivity.class);
                intent.putExtra("image",newProductModel.getImg_url());
                intent.putExtra("name",newProductModel.getName());
                intent.putExtra("price",String.valueOf(newProductModel.getPrice()));
                intent.putExtra("rating",String.valueOf(newProductModel.getNumStar()));
                intent.putExtra("des",newProductModel.getDesciption());
                intent.putExtra("nameShop",newProductModel.getNameShop());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView newName, newPrice;
        RatingBar newRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivNewProduct);
            newName = itemView.findViewById(R.id.tvNPName);
            newPrice = itemView.findViewById(R.id.tvNPPrice);
            newRating = itemView.findViewById(R.id.ratingBarNP);
        }
    }
}
