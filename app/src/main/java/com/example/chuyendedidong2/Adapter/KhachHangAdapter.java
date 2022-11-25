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

import com.example.chuyendedidong2.AdminQlykhachhangActivity;

import com.example.chuyendedidong2.Model.KhachHang;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.KHViewHolder> {
    Context context;
    ArrayList<KhachHang> listKhachHang;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> listKhachHang) {
        this.context = context;
        this.listKhachHang = listKhachHang;
    }

    @NonNull
    @Override
    public KhachHangAdapter.KHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_tkcanhan, parent, false);
        return new KHViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangAdapter.KHViewHolder holder, int position) {
        KhachHang khachHang =  listKhachHang.get(position);
        holder.tvTen_CN.setText(khachHang.getName());
        holder.tvDiaChi_CN.setText(khachHang.getDiachi());
        holder.tvSDT_CN.setText(khachHang.getSdt());
        holder.tvDaMua_CN.setText(String.valueOf(khachHang.getDaMua()));
        holder.tvDaHuy_CN.setText(String.valueOf(khachHang.getDaHuy()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminQlykhachhangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", khachHang.getName());
                bundle.putString("diachi", khachHang.getDiachi());
                bundle.putString("sdt", khachHang.getSdt());
                bundle.putInt("damua", khachHang.getDaMua());
                bundle.putInt("dahuy", khachHang.getDaHuy());
                intent.putExtra("qlycanhan", bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listKhachHang != null){
            return listKhachHang.size();
        }
        return 0;

    }

    public class KHViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen_CN, tvDiaChi_CN, tvSDT_CN, tvDaMua_CN, tvDaHuy_CN;
        Button btnKhoa_CN, btnXoa_CN;
        public KHViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen_CN = itemView.findViewById(R.id.tvTen_CN);
            tvDiaChi_CN = itemView.findViewById(R.id.tvDiaChi_CN);
            tvSDT_CN = itemView.findViewById(R.id.tvSDT_CN);
            tvDaMua_CN = itemView.findViewById(R.id.tvDaMua_CN);
            tvDaHuy_CN = itemView.findViewById(R.id.tvDaHuy_CN);
            btnKhoa_CN = itemView.findViewById(R.id.btnKhoa_CN);
            btnXoa_CN = itemView.findViewById(R.id.btnXoa_CN);
        }
    }
}
