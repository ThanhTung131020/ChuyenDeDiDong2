package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.ProductActivity;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProductModel> list;
    public ProductsAdapter(Context context, ArrayList<ProductModel> list) {
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
        ProductModel productModel = list.get(position);
        Glide.with(context).load(productModel.getImg_url()).into(holder.imageView);
        holder.newName.setText(productModel.getName());
        holder.newRating.setRating(productModel.getNumStar());
        holder.newPrice.setText(String.valueOf(productModel.getPrice()));

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
                intent.putExtra("image", productModel.getImg_url());
                intent.putExtra("name", productModel.getName());
                intent.putExtra("price",String.valueOf(productModel.getPrice()));
                intent.putExtra("rating",String.valueOf(productModel.getNumStar()));
                intent.putExtra("des", productModel.getDesciption());
                intent.putExtra("nameShop", productModel.getNameShop());
                intent.putExtra("sl", productModel.getSoLuong());
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
