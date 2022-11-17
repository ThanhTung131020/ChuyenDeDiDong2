package com.example.chuyendedidong2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Activity_Gio_hang;
import com.example.chuyendedidong2.Activity_ThongTin_DonHang;
import com.example.chuyendedidong2.Model.CartModel;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Adapter_GioHang extends RecyclerView.Adapter<Adapter_GioHang.GioHangViewHolder> {
    private Context mContext;
    private List<CartModel> mListGioHang = new ArrayList<>();
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    public String _tensp = "";
    public int _GiaSP = 0;
    public String _tenCH = "";
    public int _soLuong = 0;


    public Adapter_GioHang(Context mContext, List<CartModel> mListGioHang) {
        this.mContext = mContext;
        this.mListGioHang = mListGioHang;
    }

    public Adapter_GioHang(Context mContext) {
        this.mContext = mContext;
    }

    public void setDaTa(List<CartModel> list) {
        this.mListGioHang = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_giohang, parent, false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CartModel gioHang = mListGioHang.get(position);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        if (gioHang == null) {
            return;
        }
        Glide.with(mContext).load(gioHang.getProduct_imgurl()).into(holder.img_SPgiohang);
        holder.tv_tenSP.setText("tên sản phẩm: " + gioHang.getProduct_name());
        holder.tv_giaSP.setText("giá sản phẩm: " + String.valueOf(gioHang.getProduct_price()) + "VND");
        holder.tv_tenCH.setText("Tên cửa hàng: " + gioHang.getShop_id());
        holder.tv_soLuong.setText(String.valueOf(gioHang.getProduct_quality()));
        int SoLuong = Integer.parseInt(holder.tv_soLuong.getText().toString());
        if (SoLuong > 10) {
            holder.btn_tangSL.setVisibility(View.INVISIBLE);
            holder.btn_giamSL.setVisibility(View.VISIBLE);

        } else if (SoLuong <= 1) {
            holder.btn_giamSL.setVisibility(View.INVISIBLE);
        } else if (SoLuong >= 1) {
            holder.btn_tangSL.setVisibility(View.VISIBLE);
            holder.btn_giamSL.setVisibility(View.VISIBLE);
        }

        holder.btn_tangSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slMoiNhat = Integer.parseInt(holder.tv_soLuong.getText().toString()) + 1;


                int slHt = mListGioHang.get(position).getProduct_quality();
                int giaHT = mListGioHang.get(position).getProduct_price();
                mListGioHang.get(position).setProduct_quality(slMoiNhat);
                int giaMoiNhat = (giaHT * slMoiNhat) / slHt;
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                mListGioHang.get(position).setProduct_price(giaMoiNhat);
                holder.tv_soLuong.setText(decimalFormat.format(giaMoiNhat));

                notifyDataSetChanged();
                if (slMoiNhat > 9) {
                    holder.btn_tangSL.setVisibility(View.INVISIBLE);
                    holder.btn_giamSL.setVisibility(View.VISIBLE);
                    holder.tv_soLuong.setText(String.valueOf(slMoiNhat));
                } else {
                    holder.btn_tangSL.setVisibility(View.VISIBLE);
                    holder.btn_giamSL.setVisibility(View.VISIBLE);
                    holder.tv_soLuong.setText(String.valueOf(slMoiNhat));
                }
            }
        });
        holder.btn_giamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slMoiNhat = Integer.parseInt(holder.tv_soLuong.getText().toString()) - 1;


                int slHt = mListGioHang.get(position).getProduct_quality();
                int giaHT = mListGioHang.get(position).getProduct_price();
                mListGioHang.get(position).setProduct_quality(slMoiNhat);
                int giaMoiNhat = (giaHT * slMoiNhat) / slHt;
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                mListGioHang.get(position).setProduct_price(giaMoiNhat);
                holder.tv_soLuong.setText(decimalFormat.format(giaMoiNhat));

                notifyDataSetChanged();
                if (slMoiNhat > 9) {
                    holder.btn_tangSL.setVisibility(View.INVISIBLE);
                    holder.btn_giamSL.setVisibility(View.VISIBLE);
                    holder.tv_soLuong.setText(String.valueOf(slMoiNhat));
                } else {
                    holder.btn_tangSL.setVisibility(View.VISIBLE);
                    holder.btn_giamSL.setVisibility(View.VISIBLE);
                    holder.tv_soLuong.setText(String.valueOf(slMoiNhat));
                }

            }
        });

        holder.btn_xoaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference root = database.getReference("");
                notifyDataSetChanged();
            }
        });
        String strTenCH = holder.tv_tenCH.getText().toString();
        String strtenSP = holder.tv_tenSP.getText().toString();
        int iSoLuong = Integer.parseInt(holder.tv_soLuong.getText().toString());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        _tenCH = strTenCH;
        _tensp = strtenSP;
        _soLuong = iSoLuong;

//        Intent intent = new Intent(mContext , Activity_ThongTin_DonHang.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("object_products" , gioHang);
//        intent.putExtras(bundle);
//        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (mListGioHang != null) {
            return mListGioHang.size();
        }
        return 0;
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_SPgiohang;
        private TextView tv_giaSP, tv_tenSP, tv_tenCH;
        private ImageButton btn_tangSL, btn_giamSL, btn_xoaItem;
        private TextView tv_soLuong ;


        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            img_SPgiohang = itemView.findViewById(R.id.img_SPGioHang);
            tv_giaSP = itemView.findViewById(R.id.tv_giaSP);
            tv_tenSP = itemView.findViewById(R.id.tv_tenSP);
            tv_tenCH = itemView.findViewById(R.id.tv_tenCH);
            btn_tangSL = itemView.findViewById(R.id.btn_tangSL);
            btn_giamSL = itemView.findViewById(R.id.btn_giamSL);
            tv_soLuong = itemView.findViewById(R.id.tv_soLuong);
            btn_xoaItem = itemView.findViewById(R.id.btn_xoaItem);
        }
    }


    public int get_GiaSP() {
        return _GiaSP;
    }

    public String get_tensp() {
        return _tensp;
    }

    public String get_tenCH() {
        return _tenCH;
    }

    public int get_soLuong() {
        return _soLuong;
    }
}
