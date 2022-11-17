package com.example.chuyendedidong2.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.MainActivity;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_thongtin_donhang extends RecyclerView.Adapter<Adapter_thongtin_donhang.ThongTinViewholder> {
    private Context mContext;
    private List<ProductModel> mListGioHang = new ArrayList<>();
    private Adapter_GioHang adapter_gioHang;
    private MainActivity mainActivity;
    private Button btn_ok , btn_cancel;


    public Adapter_thongtin_donhang(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<ProductModel> list){
        this.mListGioHang = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ThongTinViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongtin_donhang, parent, false);
        return new ThongTinViewholder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull ThongTinViewholder holder, @SuppressLint("RecyclerView") int position) {
        ProductModel gioHang = mListGioHang.get(position);
            holder.tv_tenSP.setText("tên sản phẩm: "+ gioHang.getName());
            holder.tv_giaSP.setText("giá sản phẩm: "+String.valueOf( gioHang.getPrice()));
            holder.tv_soLuong.setText("X"+String.valueOf( gioHang.getSoLuong()));
            holder.tv_ttDH.setText("đang giao");
            if(holder.tv_ttDH.getText() == "chờ xác nhận"){
                holder.btn_daNhanhang.setEnabled(true);
            }
            else if(holder.tv_ttDH.getText() == "đã giao"){
            holder.btn_daNhanhang.setVisibility(View.VISIBLE);
            }


            holder.tv_tenCH.setText("tên cửa hàng"+gioHang.getNameShop());
            Glide.with(mContext).load(gioHang.getImg_url()).into(holder.img_SPGioHang);
            holder.btn_huy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   openhuy(Gravity.CENTER);


                }
            });

            holder.btn_daNhanhang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openRating(Gravity.CENTER);
                    mListGioHang.remove(position);
                    notifyDataSetChanged();
                }
            });


    }




    @Override
    public int getItemCount() {
        return mListGioHang.size();
    }

    public class ThongTinViewholder extends RecyclerView.ViewHolder {
        private ImageView img_SPGioHang;
        private TextView tv_giaSP, tv_tenSP, tv_tenCH , tv_soLuong , tv_ttDH;
        private Button btn_huy , btn_daNhanhang;
        private ImageButton imgbtn_remove;



        public ThongTinViewholder(@NonNull View itemView) {
            super(itemView);
            imgbtn_remove = itemView.findViewById(R.id.imgBt_back_to_home);
            img_SPGioHang = itemView.findViewById(R.id.img_SPGioHang);
            tv_giaSP = itemView.findViewById(R.id.tv_giaSP);
            tv_tenSP = itemView.findViewById(R.id.tv_tenSP);
            tv_tenCH = itemView.findViewById(R.id.tv_tenCH);
            btn_huy = itemView.findViewById(R.id.btn_huy);
            tv_soLuong = itemView.findViewById(R.id.tv_soLuong);
            btn_daNhanhang = itemView.findViewById(R.id.btn_DaNhanHang);
            tv_ttDH = itemView.findViewById(R.id.tv_trangThaiDH);

        }
    }
        private void openhuy(int gravity) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_huydon);
        Window window = dialog.getWindow();
        if (window == null) {
                return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowatribute = window.getAttributes();
        windowatribute.gravity = gravity;
        window.setAttributes(windowatribute);
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(false);
        }
        else {
            dialog.setCancelable(true);
        }
        dialog.show();
        btn_ok = dialog.findViewById(R.id.btn_dialig_huyDon_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mListGioHang.remove(0);
                    notifyDataSetChanged();
                    dialog.dismiss();

            }
        });
        btn_cancel = dialog.findViewById(R.id.btn_dialig_huyDon_no);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });




    }
    private void openRating(int gravity) {

        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_rating_sanpham);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowatribute = window.getAttributes();
        windowatribute.gravity = gravity;
        window.setAttributes(windowatribute);
        RatingBar ratingBar = dialog.findViewById(R.id.rtb_rating);
        ratingBar.setNumStars(5);
       ratingBar.setRating(4);
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(false);
        }
        else {
            dialog.setCancelable(true);
        }
        dialog.show();
    }
}


