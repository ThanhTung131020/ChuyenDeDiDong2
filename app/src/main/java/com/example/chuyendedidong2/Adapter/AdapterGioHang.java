package com.example.chuyendedidong2.Adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuyendedidong2.Data.GioHang;
import com.example.chuyendedidong2.R;

import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.GioHangAdapter> {
    private List<GioHang> mListGioHang;

    public AdapterGioHang(List<GioHang> mListGioHang) {
        this.mListGioHang = mListGioHang;
        notifyDataSetChanged();
    }


    @NonNull
    @Override

    public GioHangAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_giohang, parent, false);

        return new GioHangAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter holder, int position) {
        GioHang gioHang = mListGioHang.get(position);
        if (gioHang == null) {
            return;
        }
        holder.edt_GiaSP.setText(String.valueOf(gioHang.getGia()));
        holder.edt_tenCH.setText(gioHang.getTenCH());
        holder.resourceID.setImageResource(R.drawable.img);
    }

    @Override
    public int getItemCount() {
        mListGioHang.size();
        return 0;
    }

    public class GioHangAdapter extends RecyclerView.ViewHolder {
        TextView edt_GiaSP, edt_tenCH;
        ImageView resourceID;

        public GioHangAdapter(@NonNull View itemView) {
            super(itemView);
            edt_GiaSP = itemView.findViewById(R.id.edt_giaSanPham);
            edt_tenCH = itemView.findViewById(R.id.edt_tenCuaHang);
            resourceID = itemView.findViewById(R.id.img_SPGioHang);
        }
    }


}
