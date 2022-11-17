package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuyendedidong2.Model.DonHangShipper;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class DonHangShipperAdapter extends RecyclerView.Adapter<DonHangShipperAdapter.DHSHipperViewHolder> {
    Context context;
    ArrayList<DonHangShipper> list;

    public DonHangShipperAdapter(Context context, ArrayList<DonHangShipper> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DHSHipperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donhang_shipper, parent, false);
        return new DHSHipperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DHSHipperViewHolder holder, int position) {
        DonHangShipper donHangShipper = list.get(position);
        holder.tench.setText(donHangShipper.getTen_ch());
        holder.trangthai.setText(donHangShipper.getTrang_thai());
        holder.tensp.setText(donHangShipper.getTen_sp());
        holder.gia.setText(String.valueOf(donHangShipper.getGia()));
        holder.dckh.setText(String.valueOf(donHangShipper.getDia_chi_kh()));
        holder.dcch.setText(String.valueOf(donHangShipper.getDia_chi_ch()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class DHSHipperViewHolder extends RecyclerView.ViewHolder{
        TextView tench, trangthai, tensp, gia, dcch, dckh;
        Button nhanhang, giaothanhcong, huy;
        public DHSHipperViewHolder(@NonNull View itemView) {
            super(itemView);
            tench = itemView.findViewById(R.id.tv_shipper_tencuahang);
            trangthai = itemView.findViewById(R.id.tv_shipper_trangthai);
            tensp = itemView.findViewById(R.id.tv_shipper_tensanpham);
            gia = itemView.findViewById(R.id.tv_shipper_giasanpham);
            dcch = itemView.findViewById(R.id.tv_shipper_dccuahang);
            dckh = itemView.findViewById(R.id.tv_shipper_dckh);
            nhanhang = itemView.findViewById(R.id.btn_shipper_nhanhang);
            giaothanhcong = itemView.findViewById(R.id.btn_shipper_giaothanhcong);
            huy = itemView.findViewById(R.id.btn_shipper_huy);
        }
    }
}
