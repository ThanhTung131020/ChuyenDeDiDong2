package com.example.chuyendedidong2.Adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chuyendedidong2.AdminQlydoanhnghiepActivity;
import com.example.chuyendedidong2.Model.Shipper;
import com.example.chuyendedidong2.R;
import java.util.ArrayList;

public class ShipperAdapter extends RecyclerView.Adapter<ShipperAdapter.SViewHolder> {
    Context context;
    ArrayList<Shipper> listShipper;


    public ShipperAdapter(Context context, ArrayList<Shipper> listShipper) {
        this.context = context;
        this.listShipper = listShipper;
    }

    @NonNull
    @Override
    public ShipperAdapter.SViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_tkshipper,parent,false);
        return new SViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShipperAdapter.SViewHolder holder, int position) {
        Shipper shipper = listShipper.get(position);
        holder.tvTen_shiper.setText(shipper.getName());
        holder.tvDiaChi_Sh.setText(shipper.getDiachi());
        holder.tvSDT_Sh.setText(shipper.getSdt());
        holder.tvDangGiao_Sh.setText(String.valueOf(shipper.getHangDangGiao()));
        holder.tvDaGiao_Sh.setText(String.valueOf(shipper.getHangDaGiao()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminQlydoanhnghiepActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Cname",shipper.getName());
                bundle.putString("Cdiachi",shipper.getDiachi());
                bundle.putString("Csdt",shipper.getSdt());
                bundle.putInt("daban", shipper.getHangDangGiao());
                bundle.putInt("daban", shipper.getHangDaGiao());
                intent.putExtra("qlyshipper", bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listShipper.size();
    }

    public void setDaTa(ArrayList<Shipper> listShipper) {
        this.listShipper=listShipper;
        notifyDataSetChanged();
    }

    public class SViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen_shiper, tvDiaChi_Sh, tvSDT_Sh,tvDangGiao_Sh, tvDaGiao_Sh;
        Button btnKhoa_Shipper, btnXoa_Shipper;
        public SViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen_shiper = itemView.findViewById(R.id.tvTen_shiper);
            tvDiaChi_Sh = itemView.findViewById(R.id.tvDiaChi_Sh);
            tvSDT_Sh = itemView.findViewById(R.id.tvSDT_Sh);
            tvDangGiao_Sh = itemView.findViewById(R.id.tvDangGiao_Sh);
            tvDaGiao_Sh = itemView.findViewById(R.id.tvDaGiao_Sh);
            btnKhoa_Shipper = itemView.findViewById(R.id.btnKhoa_Shipper);
            btnXoa_Shipper = itemView.findViewById(R.id.btnXoa_Shipper);
        }
    }
}
