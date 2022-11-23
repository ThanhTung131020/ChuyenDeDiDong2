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
import com.example.chuyendedidong2.Model.CuaHang;
import com.example.chuyendedidong2.R;
import java.util.List;

public class ShoppAdapter extends RecyclerView.Adapter<ShoppAdapter.DNViewHolder> {
    Context context;
    List<CuaHang> list;

    public ShoppAdapter(Context context, List<CuaHang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShoppAdapter.DNViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doanhnghiep,parent,false);
        return new ShoppAdapter.DNViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppAdapter.DNViewHolder holder, int position) {
        CuaHang cuaHang = list.get(position);
        holder.tvTenCuaHang_DN.setText(cuaHang.getTen());
        holder.tvDiaChi_DN.setText(cuaHang.getDiaChi());
        holder.tvSDT_DN.setText(cuaHang.getSdt());
        holder.tvDaBan_DN.setText(cuaHang.getDaBan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminQlydoanhnghiepActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("dname",cuaHang.getTen());
                bundle.putString("ddiachi",cuaHang.getDiaChi());
                bundle.putString("dsdt",cuaHang.getSdt());
                bundle.putInt("daban", cuaHang.getDaBan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 1;
    }

    public void setDaTa(List<CuaHang> list) {
        this.list=list;
        notifyDataSetChanged();
    }


    public class DNViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenCuaHang_DN, tvDiaChi_DN, tvSDT_DN, tvDaBan_DN;
        Button btnKhoa_DN, btnXoa_DN;
        public DNViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenCuaHang_DN = itemView.findViewById(R.id.tvTenCuaHang_DN);
            tvDiaChi_DN = itemView.findViewById(R.id.tvDiaChi_DN);
            tvSDT_DN = itemView.findViewById(R.id.tvSDT_DN);
            tvDaBan_DN = itemView.findViewById(R.id.tvDaBan_DN);

        }
    }
}
