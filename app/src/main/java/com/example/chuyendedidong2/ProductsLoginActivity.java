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
import com.google.firebase.auth.FirebaseAuth;
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
    private ImageView imageView;
    private Toolbar toolbar;
    private RatingBar ratingBar;
    private TextView tvTenSP,tvGiaSP,tvTenCuaHang,tvMoTa;
    private Button btnAddCart;
    private FirebaseDatabase database;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_login);
        setControl();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Sản phẩm");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("sanpham");
        String id = bundle.getString("id");
        String shop_id = bundle.getString("shop_id");
        String name = bundle.getString("name");
        String image = bundle.getString("image");
        int price = bundle.getInt("price");
        float rating = bundle.getFloat("rating");
        String des = bundle.getString("des");
        String nameShop = bundle.getString("nameShop");
        int soluong = bundle.getInt("sl");

        //
        Glide.with(getApplicationContext()).load(image).into(imageView);
        tvTenSP.setText(name);
        tvGiaSP.setText(String.valueOf(price));
        tvMoTa.setText(des);
        tvTenCuaHang.setText(nameShop);
        ratingBar.setRating(rating);
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference root = database.getReference("cart");
                CartModel cart = new CartModel(auth.getUid(),id,shop_id,image,name,price,soluong);
                root.child(auth.getUid()).child(id).setValue(cart, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(ProductsLoginActivity.this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    }
                });
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
    }
}