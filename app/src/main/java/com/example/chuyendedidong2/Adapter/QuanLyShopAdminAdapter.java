package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuyendedidong2.ChiTietShopAdminActivity;
import com.example.chuyendedidong2.DonHangShopAdminActivity;
import com.example.chuyendedidong2.Model.Shop;
import com.example.chuyendedidong2.ProductActivity;
import com.example.chuyendedidong2.ProductsLoginActivity;
import com.example.chuyendedidong2.QLyShopAdminActivity;
import com.example.chuyendedidong2.R;
import com.example.chuyendedidong2.SanPhamShopAdminActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuanLyShopAdminAdapter extends RecyclerView.Adapter<QuanLyShopAdminAdapter.QLSADViewHolder> {
    Context context;
    ArrayList<Shop> list;

    public QuanLyShopAdminAdapter(Context context, ArrayList<Shop> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QLSADViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quanly_shop_admin,parent,false);
        return new QLSADViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLSADViewHolder holder, int position) {
        Shop shop = list.get(position);
        holder.id.setText(shop.getId());
        holder.ten.setText(shop.getName());
        holder.sdt.setText(shop.getSdt());
        holder.diachi.setText(shop.getDiachi());
        holder.qlysp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietShopAdminActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",shop.getId());
                bundle.putString("name",shop.getName());
                bundle.putString("sdt",shop.getSdt());
                bundle.putString("diachi",shop.getDiachi());
                bundle.putString("email",shop.getEmail());
                intent.putExtra("thongtin_shop",bundle);
                context.startActivity(intent);
            }
        });
        holder.khoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DonHangShopAdminActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",shop.getId());
                intent.putExtra("thongtin_shop",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QLSADViewHolder extends RecyclerView.ViewHolder{
        TextView id, ten, sdt, diachi, sldonhang;
        Button khoa, qlysp;
        public QLSADViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvIdCuaHang_DN);
            ten = itemView.findViewById(R.id.tvTenCuaHang_DN);
            sdt = itemView.findViewById(R.id.tvSDT_DN);
            diachi = itemView.findViewById(R.id.tvDiaChi_DN);
            sldonhang = itemView.findViewById(R.id.tvDaBan_DN);
            khoa = itemView.findViewById(R.id.btnKhoa_DN);
            qlysp = itemView.findViewById(R.id.btnSP_DN);
        }
    }
}
