package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Model.DonHang;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class DonHangShopAdminAdapter extends RecyclerView.Adapter<DonHangShopAdminAdapter.DHSADViewHolder> {

    Context context;
    ArrayList<DonHang> list;

    public DonHangShopAdminAdapter(Context context, ArrayList<DonHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DHSADViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill_shop_admin, parent, false);
        return new DHSADViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DHSADViewHolder holder, int position) {
        DonHang bill = list.get(position);
        Glide.with(context).load(bill.getHinhSP()).into(holder.hinh);
        if (bill.getTrangThaiDH() == 0) {
            holder.trangthai.setText("Chờ xác nhận");
        } else if (bill.getTrangThaiDH() == 1) {
            holder.trangthai.setText("Chờ shipper nhận hàng");
        } else if (bill.getTrangThaiDH() == 2) {
            holder.trangthai.setText("Shipper đã xác nhận");
        } else if (bill.getTrangThaiDH() == 3 || bill.getTrangThaiDH() == 4) {
            holder.trangthai.setText("Đang giao hàng");
        } else if (bill.getTrangThaiDH() == 5) {
            holder.trangthai.setText("Giao thành công");
            holder.bg.setBackgroundResource(R.color.donHangGiaoThanhCong);
        }else if (bill.getTrangThaiDH() == 6) {
            holder.trangthai.setText("Đơn hàng bị hủy");
            holder.bg.setBackgroundResource(R.color.bill_huy);
        }
        holder.tensp.setText(bill.getTenSP());
        holder.giasl.setText(String.valueOf(bill.getGiaSP()));
        holder.slsp.setText(String.valueOf(bill.getSoLuongSP()));
        holder.tenkh.setText(bill.getTenKhachHang());
        holder.sdtkh.setText(bill.getSdtKhachHang());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DHSADViewHolder extends RecyclerView.ViewHolder{
        LinearLayout bg;
        ImageView hinh;
        TextView trangthai, tensp, giasl, slsp, tenkh, sdtkh;
        public DHSADViewHolder(@NonNull View itemView) {
            super(itemView);
            bg = itemView.findViewById(R.id.linearBill);
            hinh = itemView.findViewById(R.id.iv_bill_shop_admin);
            trangthai = itemView.findViewById(R.id.tv_trangthai_bill_shop_admin);
            tensp = itemView.findViewById(R.id.tv_ten_bill_shop_admin);
            giasl = itemView.findViewById(R.id.tv_gia_bill_shop_admin);
            slsp = itemView.findViewById(R.id.tv_soluong_bill_shop_admin);
            tenkh = itemView.findViewById(R.id.tv_tenkh_bill_shop_admin);
            sdtkh = itemView.findViewById(R.id.tv_sdtkh_bill_shop_admin);
        }
    }
}
