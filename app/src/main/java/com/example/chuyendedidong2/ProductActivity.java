package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Adapter.NewProductsAdapter;
import com.example.chuyendedidong2.Model.NewProductModel;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private ImageView imageView;
    private RatingBar ratingBar;
    private TextView tvTenSP,tvGiaSP,tvTenCuaHang,tvMoTa;
    private ArrayList<NewProductModel> newProductModelArrayList;
    private NewProductsAdapter newProductsAdapter;
    private LinearLayout linearLayout;
    private Button btnAddCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setControl();
        NewProductModel newProductModel = getIntent().getParcelableExtra("chitiet");
//        tvTenSP.setText(newProductModel.getName());
//        tvGiaSP.setText(String.valueOf(newProductModel.getPrice()));
//        ratingBar.setNumStars(newProductModel.getNumStar());
//        tvMoTa.setText(newProductModel.getDesciption());
//        Glide.with(getApplicationContext()).load(newProductModel.getImg_url());
        Intent intent = getIntent();
       String name = intent.getStringExtra("name");
       String image = intent.getStringExtra("image");
       String price = intent.getStringExtra("price");
       String ratingbar = intent.getStringExtra("rating");
       String des = intent.getStringExtra("des");
       String nameShop = intent.getStringExtra("nameShop");
       //
        Glide.with(getApplicationContext()).load(image).into(imageView);
        tvTenSP.setText(name);
        tvGiaSP.setText(price);
        tvMoTa.setText(des);
        tvTenCuaHang.setText(nameShop);
        ratingBar.setRating(Float.parseFloat(ratingbar));
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductActivity.this,Activity_Gio_hang.class));
            }
        });
    }


    private void setControl() {
        btnAddCart = findViewById(R.id.btnAddCart);
        imageView = findViewById(R.id.ivProduct);
        ratingBar = findViewById(R.id.ratingBar);
        tvTenSP = findViewById(R.id.tvTenSanPham);
        tvGiaSP = findViewById(R.id.tvGia);
        tvMoTa = findViewById(R.id.tvMoTa);
        tvTenCuaHang = findViewById(R.id.tvTenCuaHang);
    }
}