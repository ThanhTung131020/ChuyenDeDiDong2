package com.example.chuyendedidong2.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.MainActivity;
import com.example.chuyendedidong2.Model.DonHang;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.Model.Star;
import com.example.chuyendedidong2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Adapter_thongtin_donhang extends RecyclerView.Adapter<Adapter_thongtin_donhang.ThongTinViewholder> {
    private Context mContext;
    private List<DonHang> mListGioHang;
    private Button btn_ok , btn_cancel;
    private EditText edthuy;
    private DonHang donHang;
    FirebaseDatabase database;

    public Adapter_thongtin_donhang(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<DonHang> list){
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
        donHang = mListGioHang.get(position);
        database = FirebaseDatabase.getInstance();
            holder.tv_tenSP.setText("Tên sản phẩm: "+ donHang.getTenSP());
            holder.tv_giaSP.setText("Giá sản phẩm: "+String.valueOf(donHang.getGiaSP()));
            holder.tv_soLuong.setText("X"+String.valueOf( donHang.getSoLuongSP()));
            holder.tv_ttDH.setText("Đang giao hàng");
            if(donHang.getTrangThaiDH() == 0){
                holder.btn_daNhanhang.setEnabled(false);
                holder.btn_daNhanhang.setBackgroundResource(R.color.offgiaothanhcong);
                holder.tv_ttDH.setText("Chờ xác nhận");
            }
            else if(donHang.getTrangThaiDH() == 1 || donHang.getTrangThaiDH() == 2){
                holder.tv_ttDH.setText("Đang giao");
                holder.btn_daNhanhang.setEnabled(false);
                holder.btn_huy.setEnabled(false);
                holder.btn_huy.setBackgroundResource(R.color.offgiaothanhcong);
                holder.btn_daNhanhang.setBackgroundResource(R.color.offgiaothanhcong);
            }else if(donHang.getTrangThaiDH() == 3){
                holder.tv_ttDH.setText("Xác nhận hàng");
            }
            else if(donHang.getTrangThaiDH() == 4 || donHang.getTrangThaiDH() == 5){
                holder.tv_ttDH.setText("Đã nhận");
                holder.btn_huy.setEnabled(false);
                holder.btn_daNhanhang.setEnabled(false);
                holder.item.setBackgroundResource(R.color.btn_blue);
                holder.btn_huy.setBackgroundResource(R.color.offgiaothanhcong);
                holder.btn_daNhanhang.setBackgroundResource(R.color.offgiaothanhcong);
            }
            else if(donHang.getTrangThaiDH() == 6){
                holder.item.setBackgroundResource(R.color.donhahuy);
                holder.tv_ttDH.setText("Đã hủy");
                holder.btn_huy.setEnabled(false);
                holder.btn_daNhanhang.setEnabled(false);
                holder.btn_huy.setBackgroundResource(R.color.offgiaothanhcong);
                holder.btn_daNhanhang.setBackgroundResource(R.color.offgiaothanhcong);
            }


            holder.tv_tenCH.setText("Tên cửa hàng"+donHang.getTenCuaHang());
            Glide.with(mContext).load(donHang.getHinhSP()).into(holder.img_SPGioHang);
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
                    DatabaseReference root = database.getReference("bill").child(donHang.getIdDonHang()).child("trangThaiDH");
                    root.setValue(4);
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
        private CardView item;




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
            item = itemView.findViewById(R.id.card_item);

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
        edthuy = dialog.findViewById(R.id.edt_huyDon);
        btn_ok = dialog.findViewById(R.id.btn_dialig_huyDon_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference root = database.getReference("bill").child(donHang.getIdDonHang()).child("trangThaiDH");
                root.setValue(6);
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
        ratingBar.setRating(5);
        Button ok = dialog.findViewById(R.id.btn_rating_ok);
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(false);
        }
        else {
            dialog.setCancelable(true);
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRating(ratingBar.getRating());
               dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void getRating(float rating) {
        ArrayList<Star> listStars = new ArrayList<>();
        Star star = new Star(rating);
        DatabaseReference root = database.getReference("stars");
        root.child(donHang.getIdKhachhang()).setValue(star);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Star starn = dataSnapshot.getValue(Star.class);
                    listStars.add(starn);
                    for (int i = 0; i < listStars.size(); i++){
                        float tbc = listStars.get(i).getNumStar();
                        float size = listStars.size();
                        float tb = tbc / size;
                        DatabaseReference sanpham = database.getReference("product").child(donHang.getIdSanPham());
                        sanpham.child("numStar").setValue(tb);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}


