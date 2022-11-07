package com.example.chuyendedidong2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Activity_Gio_hang;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Adapter_GioHang extends RecyclerView.Adapter<Adapter_GioHang.GioHangViewHolder> {
    private Context mContext;
    private List<ProductModel> mListGioHang = new ArrayList<>();
    private Activity_Gio_hang activityGioHang;
    public int _img_SP = 0;
    public String _tensp = "";
    public int _GiaSP = 0;
    public String _tenCH = "";
    public int _soLuong = 0;



    public Adapter_GioHang(Context mContext) {
        this.mContext = mContext;
    }

    public void setDaTa(List<ProductModel> list) {
        this.mListGioHang = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_giohang, parent, false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductModel gioHang = mListGioHang.get(position);

        if (gioHang == null) {
            return;
        }
        Glide.with(mContext).load(gioHang.getImg_url()).into(holder.img_SPgiohang);
        holder.tv_tenSP.setText("tên sản phẩm: " + gioHang.getName());
        holder.tv_giaSP.setText("giá sản phẩm: " + String.valueOf(gioHang.getPrice()) + "VND");
        holder.tv_tenCH.setText("Tên cửa hàng: " + gioHang.getNameShop());
        holder.tv_soLuong.setText(String.valueOf(gioHang.getSoLuong()));
        int SoLuong = Integer.parseInt(holder.tv_soLuong.getText().toString());
        if (SoLuong > 10) {
            holder.btn_tangSL.setVisibility(View.INVISIBLE);
            holder.btn_giamSL.setVisibility(View.VISIBLE);

        } else if (SoLuong <= 1) {
            holder.btn_giamSL.setVisibility(View.INVISIBLE);
        } else if (SoLuong >= 1) {
            holder.btn_tangSL.setVisibility(View.VISIBLE);
            holder.btn_giamSL.setVisibility(View.VISIBLE);
        }
        holder.btn_tangSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int slMoiNhat = Integer.parseInt(holder.tv_soLuong.getText().toString()) + 1;


                int slHt = mListGioHang.get(position).getSoLuong();
                int giaHT = mListGioHang.get(position).getPrice();
                mListGioHang.get(position).setSoLuong(slMoiNhat);
                int giaMoiNhat = (giaHT * slMoiNhat) / slHt;
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                mListGioHang.get(position).setPrice(giaMoiNhat);
                holder.tv_soLuong.setText(decimalFormat.format(giaMoiNhat));

                notifyDataSetChanged();
                if (slMoiNhat > 9) {
                    holder.btn_tangSL.setVisibility(View.INVISIBLE);
                    holder.btn_giamSL.setVisibility(View.VISIBLE);
                    holder.tv_soLuong.setText(String.valueOf(slMoiNhat));
                } else {
                    holder.btn_tangSL.setVisibility(View.VISIBLE);
                    holder.btn_giamSL.setVisibility(View.VISIBLE);
                    holder.tv_soLuong.setText(String.valueOf(slMoiNhat));
                }
            }
        });
        holder.btn_giamSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slMoiNhat = Integer.parseInt(holder.tv_soLuong.getText().toString()) - 1;


                int slHt = mListGioHang.get(position).getSoLuong();
                int giaHT = mListGioHang.get(position).getPrice();
                mListGioHang.get(position).setSoLuong(slMoiNhat);
                int giaMoiNhat = (giaHT * slMoiNhat) / slHt;
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                mListGioHang.get(position).setPrice(giaMoiNhat);
                holder.tv_soLuong.setText(decimalFormat.format(giaMoiNhat));

                notifyDataSetChanged();
                if (slMoiNhat > 9) {
                    holder.btn_tangSL.setVisibility(View.INVISIBLE);
                    holder.btn_giamSL.setVisibility(View.VISIBLE);
                    holder.tv_soLuong.setText(String.valueOf(slMoiNhat));
                } else {
                    holder.btn_tangSL.setVisibility(View.VISIBLE);
                    holder.btn_giamSL.setVisibility(View.VISIBLE);
                    holder.tv_soLuong.setText(String.valueOf(slMoiNhat));
                }

            }
        });
        holder.btn_xoaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListGioHang.remove(position);
                notifyDataSetChanged();
            }
        });
        String strTenCH = holder.tv_tenCH.getText().toString();
        String strtenSP = holder.tv_tenSP.getText().toString();
        int iSoLuong = Integer.parseInt(holder.tv_soLuong.getText().toString());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        _tenCH = strTenCH;
        _tensp = strtenSP;
        _soLuong = iSoLuong;




    }

    @Override
    public int getItemCount() {
        if (mListGioHang != null) {
            return mListGioHang.size();
        }
        return 0;
    }

    public class GioHangViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_SPgiohang;
        private TextView tv_giaSP, tv_tenSP, tv_tenCH;
        private ImageButton btn_tangSL, btn_giamSL, btn_xoaItem;
        private TextView tv_soLuong ;


        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            img_SPgiohang = itemView.findViewById(R.id.img_SPGioHang);
            tv_giaSP = itemView.findViewById(R.id.tv_giaSP);
            tv_tenSP = itemView.findViewById(R.id.tv_tenSP);
            tv_tenCH = itemView.findViewById(R.id.tv_tenCH);
            btn_tangSL = itemView.findViewById(R.id.btn_tangSL);
            btn_giamSL = itemView.findViewById(R.id.btn_giamSL);
            tv_soLuong = itemView.findViewById(R.id.tv_soLuong);
            btn_xoaItem = itemView.findViewById(R.id.btn_xoaItem);

        }
    }


    public int get_GiaSP() {
        return _GiaSP;
    }

    public String get_tensp() {
        return _tensp;
    }

    public String get_tenCH() {
        return _tenCH;
    }

    public int get_soLuong() {
        return _soLuong;
    }
}
