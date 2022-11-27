package com.example.chuyendedidong2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chuyendedidong2.Model.Shipper;
import com.example.chuyendedidong2.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DangKyShipperAdapter extends RecyclerView.Adapter<DangKyShipperAdapter.DKSPViewHolder> {

    Context context;
    ArrayList<Shipper> list;

    public DangKyShipperAdapter(Context context, ArrayList<Shipper> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DKSPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_id_dangky_shipper,parent,false);
        return new DKSPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DKSPViewHolder holder, int position) {
        Shipper shipper = list.get(position);
        holder.tv.setText(shipper.getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DKSPViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public DKSPViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_id_shipper_dangky);
        }
    }
}
