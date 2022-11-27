package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.ProductsLoginActivity;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsLoginAdapter extends RecyclerView.Adapter<ProductsLoginAdapter.ViewHolder> implements Filterable {
    private Context context;
    private ArrayList<ProductModel> list;
    private ArrayList<ProductModel> listOld;
    String img;
    public ProductsLoginAdapter(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
        this.listOld = list;
    }

    @NonNull
    @Override
    public ProductsLoginAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsLoginAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsLoginAdapter.ViewHolder holder, int position) {
        ProductModel productModel = list.get(position);
        Glide.with(context).load(productModel.getImg_url()).into(holder.imageView);
        holder.newName.setText(productModel.getName());
        holder.newRating.setRating(productModel.getNumStar());
        holder.newPrice.setText(String.valueOf(productModel.getPrice()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsLoginActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",productModel.getProduct_id());
                bundle.putString("shop_id",productModel.getIdShop());
                bundle.putString("image",productModel.getImg_url());
                bundle.putString("pic1",productModel.getPic1());
                bundle.putString("pic2",productModel.getPic2());
                bundle.putString("pic3",productModel.getPic3());
                bundle.putString("name",productModel.getName());
                bundle.putInt("price", productModel.getPrice());
                bundle.putFloat("rating", productModel.getNumStar());
                bundle.putString("des",productModel.getDesciption());
                bundle.putString("nameShop",productModel.getNameShop());
                bundle.putInt("sl", productModel.getSoLuong());
                intent.putExtra("sanpham",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String str = charSequence.toString();
                if (str.isEmpty()) {
                    list =  listOld;
                } else {
                    List<ProductModel> pro = new ArrayList<>();
                    for (ProductModel item : listOld) {
                        if (item.getName().contains(str)) {
                            pro.add(item);
                        }

                    }
                    list = (ArrayList<ProductModel>) pro;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = list;


                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<ProductModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
