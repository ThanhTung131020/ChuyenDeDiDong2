package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Model.DonHang;
import com.example.chuyendedidong2.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DonHangCuaHangAdapter extends RecyclerView.Adapter<DonHangCuaHangAdapter.DHCHViewHolder> {
    Context context;
    ArrayList<DonHang> list;

    public DonHangCuaHangAdapter(Context context, ArrayList<DonHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DHCHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trangchu_donhang_cuahang,parent,false);
        return new DHCHViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DHCHViewHolder holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        DonHang donHangCuaHang = list.get(position);
        if (donHangCuaHang.getTrangThaiDH() == 0){
            holder.trangthai_sp.setText("Chờ xác nhận");
            holder.trahang.setEnabled(false);
        }else if (donHangCuaHang.getTrangThaiDH() == 1){
            holder.trangthai_sp.setText("Đang giao hàng");
            holder.trahang.setEnabled(true);
            holder.xn_shipper.setEnabled(false);
            holder.xn_hang.setEnabled(false);
        }else if (donHangCuaHang.getTrangThaiDH() == 3){
            holder.trangthai_sp.setText("Giao thành công");
            holder.trahang.setEnabled(false);
            holder.xn_shipper.setEnabled(false);
            holder.xn_hang.setEnabled(false);
            holder.huy.setEnabled(false);
            holder.cv_dh.setBackgroundResource(R.drawable.set_bg_donhangthanhcong);
        }
        Glide.with(context).load(donHangCuaHang.getHinhSP()).into(holder.img_sp);
        holder.ten_sp.setText(donHangCuaHang.getTenSP());
        holder.gia_sp.setText(decimalFormat.format(donHangCuaHang.getGiaSP())+" vnđ");
        holder.sl_sp.setText("Số lượng: " + String.valueOf(donHangCuaHang.getSoLuongSP()));
        holder.ten_kh.setText(donHangCuaHang.getTenKhachHang());
        holder.sdt_kh.setText(donHangCuaHang.getSdtKhachHang());
        holder.diachi_kh.setText(donHangCuaHang.getDiaChiKhachHang());
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class DHCHViewHolder extends RecyclerView.ViewHolder{
        CardView cv_dh;
        ImageView img_sp;
        TextView ten_sp, trangthai_sp, gia_sp, sl_sp;
        TextView ten_kh, sdt_kh, diachi_kh;
        Spinner spShipper;
        Button xn_shipper, trahang, huy, xn_hang;
        public DHCHViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_dh = itemView.findViewById(R.id.cv_dhch);
            img_sp = itemView.findViewById(R.id.ivDonHangCuaHang);
            ten_sp = itemView.findViewById(R.id.tvTenSanPhamDHCH);
            trangthai_sp = itemView.findViewById(R.id.tvTrangThaiDHCH);
            gia_sp = itemView.findViewById(R.id.tvGiaSPDHCH);
            sl_sp = itemView.findViewById(R.id.tvSoLuongDHCH);
            ten_kh = itemView.findViewById(R.id.tvTenKhachHangDHCH);
            sdt_kh = itemView.findViewById(R.id.tvSDTKHDHCH);
            diachi_kh = itemView.findViewById(R.id.tvDiaChiKHDHCH);
            spShipper = itemView.findViewById(R.id.spTenShipperDHCH);
            xn_shipper = itemView.findViewById(R.id.btnXNShipperDHCH);
            trahang = itemView.findViewById(R.id.btnTraHangDHCH);
            huy = itemView.findViewById(R.id.btnHuyDHCH);
            xn_hang = itemView.findViewById(R.id.btnXacNhanDHCH);
        }
    }
}
