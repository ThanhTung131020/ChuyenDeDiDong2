package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuyendedidong2.ChiTietDangKyShopActivity;
import com.example.chuyendedidong2.Model.Shop;
import com.example.chuyendedidong2.ProductsLoginActivity;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class DangKyShopAdapter extends RecyclerView.Adapter<DangKyShopAdapter.DKSViewHolder> {
    Context context;
    ArrayList<Shop> list;

    public DangKyShopAdapter(Context context, ArrayList<Shop> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DKSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_id_dangky_shop,parent,false);
        return new DKSViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DKSViewHolder holder, int position) {
        Shop shop = list.get(position);
        holder.tv.setText(shop.getId());
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietDangKyShopActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",shop.getId());
                bundle.putString("email",shop.getEmail());
                bundle.putString("sdt",shop.getSdt());
                bundle.putString("diachi",shop.getDiachi());
                bundle.putString("ten",shop.getName());
                intent.putExtra("dangky_shop",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DKSViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public DKSViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_id_shop_dangky);
        }
    }
}
