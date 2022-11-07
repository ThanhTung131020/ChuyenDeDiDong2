package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Model.Sanpham;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class QuanLySanPhamAdapter extends RecyclerView.Adapter<QuanLySanPhamAdapter.QLSPViewHolder> {
    Context context;
    ArrayList<Sanpham> list;


    public QuanLySanPhamAdapter(Context context, ArrayList<Sanpham> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QLSPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_products, parent, false);
        return new QLSPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLSPViewHolder holder, int position) {
        Sanpham sanpham = list.get(position);
        Glide.with(context).load(sanpham.getImvsanPham()).into(holder.imvsanPham);
        holder.txtGia.setText(String.valueOf(sanpham.getGia()));
        holder.txtSPDB.setText("Da ban: " + String.valueOf(sanpham.getSoLuong()));
    }

    @Override
    public int getItemCount() {
        if(list != null){
            list.size();
        }
        return 0;
    }

    public class QLSPViewHolder extends RecyclerView.ViewHolder{
        ImageView imvsanPham;
        TextView txtGia;
        TextView txtSPDB;
        public QLSPViewHolder(@NonNull View itemView) {
            super(itemView);
            imvsanPham = itemView.findViewById(R.id.imvsanpham);
            txtGia = itemView.findViewById(R.id.tvgiasp);
            txtSPDB = itemView.findViewById(R.id.tvspDaBan);

        }
    }











}
