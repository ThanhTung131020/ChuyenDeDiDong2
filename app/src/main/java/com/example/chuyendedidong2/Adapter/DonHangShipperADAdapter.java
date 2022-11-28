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
import com.example.chuyendedidong2.DonHangShipperAdminActivity;
import com.example.chuyendedidong2.Model.DonHang;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;

public class DonHangShipperADAdapter extends RecyclerView.Adapter<DonHangShipperADAdapter.DHSPADViewHolder> {


    Context context;
    ArrayList<DonHang> list;

    public DonHangShipperADAdapter(Context context, ArrayList<DonHang> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public DHSPADViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill_shipper_admin, parent, false);
        return new DHSPADViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DHSPADViewHolder holder, int position) {
        DonHang bill = list.get(position);
        Glide.with(context).load(bill.getHinhSP()).into(holder.hinh);
        if (bill.getTrangThaiDH() == 1) {
            holder.trangthai.setText("Chờ xác nhận");
        } else if (bill.getTrangThaiDH() == 2) {
            holder.trangthai.setText("Đã nhận hàng");
        }
        else if (bill.getTrangThaiDH() == 3) {
            holder.trangthai.setText("Đang giao");
        } else if (bill.getTrangThaiDH() == 4 || bill.getTrangThaiDH() == 5) {
            holder.trangthai.setText("Đã giao");
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
        holder.tench.setText(bill.getTenCuaHang());
        holder.tench.setText(bill.getSdtCuaHang());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DHSPADViewHolder extends RecyclerView.ViewHolder{
        LinearLayout bg;
        ImageView hinh;
        TextView trangthai, tensp, giasl, slsp, tenkh, sdtkh, tench, sdtch;
        public DHSPADViewHolder(@NonNull View itemView) {
            super(itemView);

            bg = itemView.findViewById(R.id.linearBill);
            hinh = itemView.findViewById(R.id.iv_bill_shop_admin);
            trangthai = itemView.findViewById(R.id.tv_trangthai_bill_shop_admin);
            tensp = itemView.findViewById(R.id.tv_ten_bill_shop_admin);
            giasl = itemView.findViewById(R.id.tv_gia_bill_shop_admin);
            slsp = itemView.findViewById(R.id.tv_soluong_bill_shop_admin);
            tenkh = itemView.findViewById(R.id.tv_tenkh_bill_shop_admin);
            sdtkh = itemView.findViewById(R.id.tv_sdtkh_bill_shop_admin);
            tench = itemView.findViewById(R.id.tv_tench_bill_shop_admin);
            sdtch = itemView.findViewById(R.id.tv_sdtch_bill_shop_admin);
        }
    }
}
