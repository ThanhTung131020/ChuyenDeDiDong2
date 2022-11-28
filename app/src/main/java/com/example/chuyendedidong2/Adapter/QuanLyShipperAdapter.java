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
import com.example.chuyendedidong2.DonHangShipperAdminActivity;
import com.example.chuyendedidong2.Model.Personal;
import com.example.chuyendedidong2.Model.Shipper;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class QuanLyShipperAdapter extends RecyclerView.Adapter<QuanLyShipperAdapter.QLSPViewHolder> {

    Context context;
    ArrayList<Shipper> list;

    public QuanLyShipperAdapter(Context context, ArrayList<Shipper> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public QLSPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quanly_shipper_admin,parent,false);
        return new QLSPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLSPViewHolder holder, int position) {
        Shipper shipper = list.get(position);
        holder.id.setText(shipper.getId());
        holder.ten.setText(shipper.getName());
        holder.sdt.setText(shipper.getSdt());
        holder.diachi.setText(shipper.getDiachi());
        holder.qlysp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DonHangShipperAdminActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",shipper.getId());
                intent.putExtra("thongtin_shipper",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class QLSPViewHolder extends RecyclerView.ViewHolder{
        TextView id, ten, sdt, diachi, sldonhang;
        Button qlysp;

        public QLSPViewHolder(@NonNull View itemView) {
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
