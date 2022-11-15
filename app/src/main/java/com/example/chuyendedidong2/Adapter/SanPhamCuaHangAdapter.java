package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.chuyendedidong2.ProductsLoginActivity;
import com.example.chuyendedidong2.R;
import com.example.chuyendedidong2.SuaSanPhamActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        holder.tvSLSP.setText("Đã bán: ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SuaSanPhamActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("simage",product.getImg_url());
                bundle.putString("sid",product.getProduct_id());
                bundle.putString("sname",product.getName());
                bundle.putInt("sprice", product.getPrice());
                bundle.putFloat("srating", product.getNumStar());
                bundle.putString("sdes",product.getDesciption());
                bundle.putString("snameShop",product.getNameShop());
                //bundle.putInt("ssl", product.getSoLuong());
                intent.putExtra("suasanpham",bundle);
                context.startActivity(intent);
            }
        });

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
