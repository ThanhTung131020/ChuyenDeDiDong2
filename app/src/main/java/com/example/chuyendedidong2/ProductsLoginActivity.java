package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Adapter.ProductsAdapter;
import com.example.chuyendedidong2.Fragment.CartFragment;
import com.example.chuyendedidong2.Model.CartModel;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.Model.Shop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ProductsLoginActivity extends AppCompatActivity {
    private ImageView imageView,pic1,pic2,pic3;
    private Toolbar toolbar;
    private RatingBar ratingBar;
    private TextView tvTenSP,tvGiaSP,tvTenCuaHang,tvMoTa;
    private Button btnAddCart;
    private DialogOkActivity dialogOk;
    private DiaLogLoanding diaLogLoanding;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_login);
        setControl();
        dialogOk = new DialogOkActivity(this);
        diaLogLoanding = new DiaLogLoanding(this);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Sản phẩm");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("sanpham");
        String id = bundle.getString("id");
        String shop_id = bundle.getString("shop_id");
        String name = bundle.getString("name");
        String image = bundle.getString("image");
        String pic1 = bundle.getString("pic1");
        String pic2 = bundle.getString("pic2");
        String pic3 = bundle.getString("pic3");
        int price = bundle.getInt("price");
        float rating = bundle.getFloat("rating");
        String des = bundle.getString("des");
        String nameShop = bundle.getString("nameShop");
        int soluong = bundle.getInt("sl");

        //
        Glide.with(getApplicationContext()).load(image).into(imageView);
        Glide.with(getApplicationContext()).load(pic1).into(this.pic1);
        Glide.with(getApplicationContext()).load(pic2).into(this.pic2);
        Glide.with(getApplicationContext()).load(pic3).into(this.pic3);
        tvTenSP.setText(name);
        tvGiaSP.setText(String.valueOf(price));
        tvMoTa.setText(des);
        tvTenCuaHang.setText(nameShop);
        ratingBar.setRating(rating);
        ratingBar.setEnabled(false);
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang mua...");
                if (user.isEmailVerified()){
                    DatabaseReference name_shop = database.getReference("shop");
                    name_shop.child(shop_id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Shop shop = snapshot.getValue(Shop.class);
                            String nameShop = shop.getName();
                            DatabaseReference root = database.getReference("cart");
                            CartModel cart = new CartModel(auth.getUid(),id,shop_id,nameShop,image,name,price,1);
                            root.child(auth.getUid()).child(id).setValue(cart, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    diaLogLoanding.HideDialog();
                                    dialogOk.ShowDiaLog("Thêm giỏ hàng thành công!");
                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else {
                    diaLogLoanding.HideDialog();
                    dialogOk.ShowDiaLog("Bạn phải xác thực email!");
                    return;
                }

            }
        });
    }
    private void setControl() {
        toolbar = findViewById(R.id.toolbar);
        btnAddCart = findViewById(R.id.btnAddCart);
        imageView = findViewById(R.id.ivProduct);
        ratingBar = findViewById(R.id.ratingBar);
        tvTenSP = findViewById(R.id.tvTenSanPham);
        tvGiaSP = findViewById(R.id.tvGia);
        tvMoTa = findViewById(R.id.tvMoTa);
        tvTenCuaHang = findViewById(R.id.tvTenCuaHang);
        pic1 = findViewById(R.id.ivPic1);
        pic2 = findViewById(R.id.ivPic2);
        pic3 = findViewById(R.id.ivPic3);
    }
}