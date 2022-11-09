package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class SanPhamCuaHangAdapter extends RecyclerView.Adapter<SanPhamCuaHangAdapter.SPCHViewHolder> {
    Context context;
    ArrayList<ProductModel> list;

    public SanPhamCuaHangAdapter(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SPCHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham_cuahang_layout,parent,false);
        return new SPCHViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPCHViewHolder holder, int position) {
        ProductModel product = list.get(position);
        Glide.with(context).load(product.getImg_url()).into(holder.ivSP);
        holder.tvNameSP.setText(product.getName());
        holder.tvSLSP.setText("Số lượng: " + product.getSoLuong());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SPCHViewHolder extends RecyclerView.ViewHolder{
        ImageView ivSP;
        TextView tvNameSP, tvSLSP;
        CheckBox checkBox;
        public SPCHViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSP = itemView.findViewById(R.id.ivSanPham_Cuahang);
            tvNameSP = itemView.findViewById(R.id.tvTenSanPham_CuaHang);
            tvSLSP = itemView.findViewById(R.id.tvSoLuongSPCH);
            checkBox = itemView.findViewById(R.id.check_box_sanpham_cuahang);
        }
    }
}
