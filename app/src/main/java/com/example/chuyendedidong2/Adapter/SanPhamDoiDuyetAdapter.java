package com.example.chuyendedidong2.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.DiaLogLoanding;
import com.example.chuyendedidong2.DialogOkActivity;
import com.example.chuyendedidong2.HomePageCuaHangActivity;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;
import com.example.chuyendedidong2.SuaSanPhamActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SanPhamDoiDuyetAdapter extends RecyclerView.Adapter<SanPhamDoiDuyetAdapter.SPDDViewHolder> {

    Context context;
    ArrayList<ProductModel> list;
    DiaLogLoanding diaLogLoanding;
    DialogOkActivity dialogOk;
    FirebaseDatabase database;

    public SanPhamDoiDuyetAdapter(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SPDDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_sanpham_doiduyet,parent,false);
        return new SPDDViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPDDViewHolder holder, int position) {
        database = FirebaseDatabase.getInstance();
        diaLogLoanding = new DiaLogLoanding(context);
        dialogOk = new DialogOkActivity(context);
        ProductModel product = list.get(position);
        Glide.with(context).load(product.getImg_url()).into(holder.hinh);
        holder.ten.setText(product.getName());
        holder.gia.setText(String.valueOf(product.getPrice()));
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Thông báo xóa sản phẩm!");
                alert.setIcon(R.mipmap.ic_launcher);
                alert.setMessage("Bạn có muốn xóa sản phẩm đã chọn?");
                alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference root = database.getReference("product_register").child(product.getProduct_id());
                        root.removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                dialogOk.ShowDiaLog("Xóa thành công");
                            }
                        });
                    }
                });
                alert.setNegativeButton("không",null);
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SPDDViewHolder extends RecyclerView.ViewHolder{
        ImageView hinh;
        TextView ten, gia;
        Button xoa;
        public SPDDViewHolder(@NonNull View itemView) {
            super(itemView);
            hinh = itemView.findViewById(R.id.iv_hinh_sp_doiduyet);
            ten = itemView.findViewById(R.id.tv_ten_sp_doiduyet);
            gia = itemView.findViewById(R.id.tv_gia_sp_doiduyet);
            xoa = itemView.findViewById(R.id.btnXoaSPD);
        }
    }
}
