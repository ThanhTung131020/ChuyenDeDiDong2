package com.example.chuyendedidong2;

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
import com.example.chuyendedidong2.Model.ProductModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ProductsLoginActivity extends AppCompatActivity {
    private ImageView imageView;
    private Toolbar toolbar;
    private RatingBar ratingBar;
    private TextView tvTenSP,tvGiaSP,tvTenCuaHang,tvMoTa;
    private ArrayList<ProductModel> productModelArrayList;
    private ProductsAdapter newProductsAdapter;
    private LinearLayout linearLayout;
    private Button btnAddCart;
    private ProductModel products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_login);

        setControl();
       // final ProductModel[] productModel = {getIntent().getParcelableExtra("chitiet")};

        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Sản phẩm");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("sanpham");
        String name = bundle.getString("name");
        String image = bundle.getString("image");
        int price = bundle.getInt("price");
        float rating = bundle.getFloat("rating");
        String des = bundle.getString("des");
        String nameShop = bundle.getString("nameShop");
        int soluong = bundle.getInt("sl");

//        Intent intent = getIntent();
//        String name = intent.getStringExtra("name");
//        String image = intent.getStringExtra("image");
//        String price = intent.getStringExtra("price");
//        String ratingbar = intent.getStringExtra("rating");
//        String des = intent.getStringExtra("des");
//        String nameShop = intent.getStringExtra("nameShop");
//        String soluong = intent.getStringExtra("sl");

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
                products = new ProductModel();
                products.createProductCart().add(new ProductModel(soluong,name,des,nameShop,price,rating,image));
                startActivity(new Intent(ProductsLoginActivity.this, HomePageLoginActivity.class));
                Toast.makeText(ProductsLoginActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
            }
        });
//        sanPham = new ProductModel(Integer.parseInt(soluong),name,des,nameShop,Integer.parseInt(price),Float.parseFloat(ratingbar),image);
//        Toast.makeText(ProductsLoginActivity.this, name , Toast.LENGTH_SHORT).show();
//        products.createProductCart().add(sanPham);
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