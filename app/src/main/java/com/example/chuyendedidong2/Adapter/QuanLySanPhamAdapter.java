package com.example.chuyendedidong2.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Activity_Gio_hang;
import com.example.chuyendedidong2.Activity_allproduct;
import com.example.chuyendedidong2.Model.Sanpham;
import com.example.chuyendedidong2.ProductActivity;
import com.example.chuyendedidong2.R;
import java.util.ArrayList;

public class QuanLySanPhamAdapter extends RecyclerView.Adapter<QuanLySanPhamAdapter.QLSPViewHolder> {
    private Context context;
    private ArrayList<Sanpham> list;
    private int resource;
    private Activity_allproduct activityAllproduct;


    public QuanLySanPhamAdapter(Context context, int resource, ArrayList<Sanpham> list) {
        this.context = context;
        this.list = list;
        this.resource = resource;

    }

    @NonNull
    @Override
    public QLSPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_products, parent, false);
        return new QLSPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLSPViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Sanpham sanpham = list.get(position);
        Glide.with(context).load(sanpham.getImvsanPham()).into(holder.imvsanPham);
        holder.txtGia.setText(String.valueOf(sanpham.getGia()));
        holder.txtSPDB.setText("Đã bán: " + String.valueOf(sanpham.getSoLuong()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("price",String.valueOf(sanpham.getGia()));
                intent.putExtra("so luong", sanpham.getSoLuong());
                context.startActivity(intent);
            }
        });
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            list.size();
        }
        return 0;
    }

    public class QLSPViewHolder extends RecyclerView.ViewHolder{
        public Button btnXoa;
        ImageView imvsanPham;
        TextView txtGia;
        TextView txtSPDB;
        public QLSPViewHolder(@NonNull View itemView) {
            super(itemView);
            imvsanPham = itemView.findViewById(R.id.imvsanpham);
            txtGia = itemView.findViewById(R.id.tvgiasp);
            txtSPDB = itemView.findViewById(R.id.tvspDaBan);
            btnXoa = itemView.findViewById(R.id.btnXoa);

        }
    }
    private void dialogXoa(int gravity) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_xoasp);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
    }

}
