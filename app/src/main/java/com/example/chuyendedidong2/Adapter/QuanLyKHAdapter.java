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

import com.example.chuyendedidong2.DonHangKhachHangActivity;
import com.example.chuyendedidong2.DonHangShopAdminActivity;
import com.example.chuyendedidong2.Model.Personal;
import com.example.chuyendedidong2.Model.Shop;
import com.example.chuyendedidong2.R;
import com.example.chuyendedidong2.SanPhamShopAdminActivity;

import java.util.ArrayList;

public class QuanLyKHAdapter extends RecyclerView.Adapter<QuanLyKHAdapter.QLKHViewHolder> {
    Context context;
    ArrayList<Personal> list;

    public QuanLyKHAdapter(Context context, ArrayList<Personal> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public QLKHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quanly_kh_admin,parent,false);
        return new QLKHViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLKHViewHolder holder, int position) {
        Personal personal = list.get(position);
        holder.id.setText(personal.getId());
        holder.ten.setText(personal.getName());
        holder.sdt.setText(personal.getSdt());
        holder.diachi.setText(personal.getDiachi());
        holder.qlysp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DonHangKhachHangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",personal.getId());
                intent.putExtra("thongtin_kh",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QLKHViewHolder extends RecyclerView.ViewHolder{
        TextView id, ten, sdt, diachi, sldonhang;
        Button khoa, qlysp;

        public QLKHViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvIdCuaHang_DN);
            ten = itemView.findViewById(R.id.tvTenCuaHang_DN);
            sdt = itemView.findViewById(R.id.tvSDT_DN);
            diachi = itemView.findViewById(R.id.tvDiaChi_DN);
            sldonhang = itemView.findViewById(R.id.tvDaBan_DN);
            qlysp = itemView.findViewById(R.id.btnSP_DN);
        }
    }
}
