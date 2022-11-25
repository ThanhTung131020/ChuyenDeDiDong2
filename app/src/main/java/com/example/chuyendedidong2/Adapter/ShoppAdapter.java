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
import com.example.chuyendedidong2.Model.Shop;
import com.example.chuyendedidong2.R;
import java.util.ArrayList;

public class ShoppAdapter extends RecyclerView.Adapter<ShoppAdapter.DNViewHolder> {
    Context context;
    ArrayList<Shop> list;
    public ShoppAdapter(Context context, ArrayList<Shop> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ShoppAdapter.DNViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_tkdoanhnghiep,parent,false);
        return new DNViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DNViewHolder holder, int position) {
        Shop shop = list.get(position);
        holder.tvTen_DN.setText(shop.getName());
        holder.tvDiaChi_DN.setText(shop.getDiachi());
        holder.tvSDT_DN.setText(shop.getSdt());
        holder.tvDaBan_DN.setText(String.valueOf(shop.getSpDaBan()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminQlydoanhnghiepActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Cname",shop.getName());
                bundle.putString("Cdiachi",shop.getDiachi());
                bundle.putString("Csdt",shop.getSdt());
                bundle.putInt("daban", shop.getSpDaBan());
                intent.putExtra("qlydoanhnghiep", bundle);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setDaTa(ArrayList<Shop> list) {
        this.list=list;
        notifyDataSetChanged();
    }


    public class DNViewHolder extends RecyclerView.ViewHolder{
        TextView tvTen_DN, tvDiaChi_DN, tvSDT_DN, tvDaBan_DN;
        Button btnXoa, btnKhoa;
        public DNViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen_DN = itemView.findViewById(R.id.tvTen_DN);
            tvDiaChi_DN = itemView.findViewById(R.id.tvDiaChi_DN);
            tvSDT_DN = itemView.findViewById(R.id.tvSDT_DN);
            tvDaBan_DN = itemView.findViewById(R.id.tvDaBan_DN);
            btnKhoa = itemView.findViewById(R.id.btnKhoa_DN);
            btnXoa = itemView.findViewById(R.id.btnXoa_DN);

        }
    }
}
