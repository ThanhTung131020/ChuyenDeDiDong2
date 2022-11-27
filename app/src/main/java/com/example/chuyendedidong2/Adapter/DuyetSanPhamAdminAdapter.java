package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.ChiTietDuyetSanPhamAdminActivity;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.ProductsLoginActivity;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class DuyetSanPhamAdminAdapter extends RecyclerView.Adapter<DuyetSanPhamAdminAdapter.DSPADViewHolder> {
    Context context;
    ArrayList<ProductModel> list;

    public DuyetSanPhamAdminAdapter(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DSPADViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_duyet_sanpham_admin,parent,false);
        return new DSPADViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DSPADViewHolder holder, int position) {
        ProductModel product = list.get(position);
        Glide.with(context).load(product.getImg_url()).into(holder.hinh);
        holder.id_sp.setText(product.getProduct_id());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChiTietDuyetSanPhamAdminActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",product.getProduct_id());
                bundle.putString("shop_id",product.getIdShop());
                bundle.putString("image",product.getImg_url());
                bundle.putString("pic1",product.getPic1());
                bundle.putString("pic2",product.getPic2());
                bundle.putString("pic3",product.getPic3());
                bundle.putString("name",product.getName());
                bundle.putInt("price", product.getPrice());
                bundle.putFloat("rating", product.getNumStar());
                bundle.putString("des",product.getDesciption());
                bundle.putString("nameShop",product.getNameShop());
                bundle.putInt("sl", product.getSoLuong());
                intent.putExtra("sanpham_admin",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DSPADViewHolder extends RecyclerView.ViewHolder{
        ImageView hinh;
        TextView id_sp;
        public DSPADViewHolder(@NonNull View itemView) {
            super(itemView);
            hinh = itemView.findViewById(R.id.iv_pic_sanpham_admin);
            id_sp = itemView.findViewById(R.id.tv_id_sanpham_admin);
        }
    }
}
