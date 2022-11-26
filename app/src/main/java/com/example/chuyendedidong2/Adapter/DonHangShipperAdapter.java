package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuyendedidong2.Model.DonHang;
import com.example.chuyendedidong2.Model.DonHangShipper;
import com.example.chuyendedidong2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DonHangShipperAdapter extends RecyclerView.Adapter<DonHangShipperAdapter.DHSHipperViewHolder> {
    Context context;
    ArrayList<DonHang> list;
    FirebaseDatabase database;

    public DonHangShipperAdapter(Context context, ArrayList<DonHang> list) {
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
        DonHang donHangShipper = list.get(position);
        database = FirebaseDatabase.getInstance();
        if(donHangShipper.getTrangThaiDH() == 1){
            holder.trangthai.setText("Chờ xác nhận");
            holder.giaothanhcong.setEnabled(false);
            holder.giaothanhcong.setBackgroundResource(R.color.offgiaothanhcong);
        }else if (donHangShipper.getTrangThaiDH() == 2){
            holder.trangthai.setText("Đã nhận hàng");
            holder.giaothanhcong.setEnabled(false);
            holder.nhanhang.setEnabled(false);
            holder.nhanhang.setBackgroundResource(R.color.offgiaothanhcong);
            holder.giaothanhcong.setBackgroundResource(R.color.offgiaothanhcong);
        }else if (donHangShipper.getTrangThaiDH() == 3){
            holder.trangthai.setText("Đang giao");
            holder.giaothanhcong.setEnabled(false);
            holder.nhanhang.setEnabled(false);
            holder.giaothanhcong.setBackgroundResource(R.color.offgiaothanhcong);
            holder.nhanhang.setBackgroundResource(R.color.offgiaothanhcong);
        }else if (donHangShipper.getTrangThaiDH() == 4){
            holder.trangthai.setText("Đang giao");
            holder.nhanhang.setEnabled(false);
            holder.huy.setEnabled(false);
            holder.nhanhang.setBackgroundResource(R.color.offgiaothanhcong);
            holder.huy.setBackgroundResource(R.color.offgiaothanhcong);
        }else if (donHangShipper.getTrangThaiDH() == 5){
            holder.trangthai.setText("Đã giao");
            holder.giaothanhcong.setEnabled(false);
            holder.nhanhang.setEnabled(false);
            holder.huy.setEnabled(false);
            holder.nhanhang.setBackgroundResource(R.color.offgiaothanhcong);
            holder.huy.setBackgroundResource(R.color.offgiaothanhcong);
            holder.giaothanhcong.setBackgroundResource(R.color.offgiaothanhcong);
        }
        else if(donHangShipper.getTrangThaiDH() ==6){
            holder.trangthai.setText("đã hủy");
            holder.giaothanhcong.setEnabled(false);
            holder.nhanhang.setEnabled(false);
            holder.huy.setEnabled(false);
            holder.nhanhang.setBackgroundResource(R.color.offgiaothanhcong);
            holder.huy.setBackgroundResource(R.color.offgiaothanhcong);
            holder.giaothanhcong.setBackgroundResource(R.color.offgiaothanhcong);
        }
        holder.tench.setText("Tên CH: "+donHangShipper.getTenCuaHang());
        holder.tensp.setText("Tên SP: "+donHangShipper.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.gia.setText("Giá SP"+ decimalFormat.format(donHangShipper.getGiaSP())+" vnđ");
        holder.dckh.setText(String.valueOf("Địa chỉ KH: "+donHangShipper.getDiaChiKhachHang()));
        holder.sdtkh.setText("SDT khách hàng: "+donHangShipper.getSdtKhachHang());
        holder.dcch.setText(String.valueOf("Địa chỉ CH: "+donHangShipper.getDiaChiCuaHang()));
        holder.sdtch.setText("SDT cửa hàng: "+donHangShipper.getSdtCuaHang());
        holder.nhanhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference root = database.getReference("bill").child(donHangShipper.getIdDonHang()).child("trangThaiDH");
                root.setValue(2);
            }
        });
        holder.giaothanhcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference root = database.getReference("bill").child(donHangShipper.getIdDonHang()).child("trangThaiDH");
                root.setValue(5);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class DHSHipperViewHolder extends RecyclerView.ViewHolder{
        TextView tench, trangthai, tensp, gia, dcch, dckh ,sdtkh , sdtch;
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
            sdtch = itemView.findViewById(R.id.tv_shipper_sdtcuahang);
            sdtkh = itemView.findViewById(R.id.tv_shipper_sdtkh);
        }
    }
}
