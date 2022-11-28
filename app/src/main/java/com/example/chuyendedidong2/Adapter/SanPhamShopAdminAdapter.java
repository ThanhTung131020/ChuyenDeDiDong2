package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SanPhamShopAdminAdapter extends RecyclerView.Adapter<SanPhamShopAdminAdapter.SPSADViewHolder> {

    Context context;
    ArrayList<ProductModel> list;

    public SanPhamShopAdminAdapter(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SPSADViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham_cuahang_admin,parent,false);
        return new SPSADViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPSADViewHolder holder, int position) {
        ProductModel product = list.get(position);
        Glide.with(context).load(product.getImg_url()).into(holder.hinh);
        holder.ten.setText(product.getName());
        holder.gia.setText(String.valueOf(product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SPSADViewHolder extends RecyclerView.ViewHolder{
        ImageView hinh;
        TextView ten, gia;
        public SPSADViewHolder(@NonNull View itemView) {
            super(itemView);
            hinh = itemView.findViewById(R.id.iv_hinhsp_admin);
            ten = itemView.findViewById(R.id.tv_tensp_admin);
            gia = itemView.findViewById(R.id.tv_giasp_admin);
        }
    }
}
