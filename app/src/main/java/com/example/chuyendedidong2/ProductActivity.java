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
import com.example.chuyendedidong2.Model.ProductModel;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private ImageView imageView, pic1, pic2, pic3;
    private Toolbar toolbar;
    private RatingBar ratingBar;
    private TextView tvTenSP,tvGiaSP,tvTenCuaHang,tvMoTa;
    private ArrayList<ProductModel> productModelArrayList;
    private ProductsAdapter newProductsAdapter;
    private LinearLayout linearLayout;
    private Button btnAddCart;
    private DialogOkActivity dialogOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        dialogOk = new DialogOkActivity(this);
        setControl();

        //setSupportActionBar(toolbar);
      // getSupportActionBar().setTitle("Sản phẩm");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
       String name = intent.getStringExtra("name");
       String image = intent.getStringExtra("image");
       String price = intent.getStringExtra("price");
       String ratingbar = intent.getStringExtra("rating");
       String des = intent.getStringExtra("des");
       String nameShop = intent.getStringExtra("nameShop");
       String pic1 = intent.getStringExtra("pic1");
       String pic2 = intent.getStringExtra("pic2");
       String pic3 = intent.getStringExtra("pic3");
       //
        Glide.with(getApplicationContext()).load(image).into(imageView);
        Glide.with(getApplicationContext()).load(pic1).into(this.pic1);
        Glide.with(getApplicationContext()).load(pic2).into(this.pic2);
        Glide.with(getApplicationContext()).load(pic3).into(this.pic3);
        tvTenSP.setText(name);
        tvGiaSP.setText(price);
        tvMoTa.setText(des);
        tvTenCuaHang.setText(nameShop);
        ratingBar.setRating(Float.parseFloat(ratingbar));

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogOk.ShowDiaLog("Bạn phải đăng nhập!");
             }
        });
    }


    private void setControl() {
        toolbar = findViewById(R.id.toolbar);
        btnAddCart = findViewById(R.id.btnAddCart);
        imageView = findViewById(R.id.ivProduct);
        pic1 = findViewById(R.id.ivPic1);
        pic2 = findViewById(R.id.ivPic2);
        pic3 = findViewById(R.id.ivPic3);
        ratingBar = findViewById(R.id.ratingBar);
        tvTenSP = findViewById(R.id.tvTenSanPham);
        tvGiaSP = findViewById(R.id.tvGia);
        tvMoTa = findViewById(R.id.tvMoTa);
        tvTenCuaHang = findViewById(R.id.tvTenCuaHang);
    }
}