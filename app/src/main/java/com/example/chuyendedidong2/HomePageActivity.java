package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

//import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Adapter.ProductsAdapter;
//import com.example.chuyendedidong2.Model.ImageSilder;
import com.example.chuyendedidong2.Model.ProductModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {
    //img slider
    private ViewPager viewPager;
    //private ImageSliderAdapter imageSliderAdapter;
    //bottom navigation
    private BottomNavigationView bottomNavigationView;
    //spinner
    private Spinner spinner;
    //new product
    ArrayList<ProductModel> productModelList;
    private RecyclerView rvNewProduct;
    private ProductModel productModel;
    private ProductsAdapter newProductsAdapter;
    //firebase
    //private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //db = FirebaseFirestore.getInstance();
        setControl();
        setEvent();

    }
    private void setEvent() {
        //image slider
//        imageSliderAdapter = new ImageSliderAdapter(this,getListImageSlider());
//        viewPager.setAdapter(imageSliderAdapter);
        //bottom navigation view
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mnHome:
                        break;
                    case R.id.mnUser:
                        startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
                        break;
                }
                return true;
            }
        });
        //newProductModelList = new ArrayList<>();
        rvNewProduct.setLayoutManager(new GridLayoutManager(this,3));
        productModel = new ProductModel();
        newProductsAdapter = new ProductsAdapter(this, productModel.createNewProduct());
        rvNewProduct.setAdapter(newProductsAdapter);
        //spinner
        String[] spin = {"Mặc định","Theo giá cao đến thấp","Theo hãng"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,spin);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(HomePageActivity.this, spin[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

//    private List<ImageSilder> getListImageSlider() {
//        List<ImageSilder> imageSilders = new ArrayList<>();
//        imageSilders.add(new ImageSilder(R.drawable.img));
//        imageSilders.add(new ImageSilder(R.drawable.img_1));
//        return imageSilders;
//    }

    private void setControl() {
        spinner = findViewById(R.id.spTinKiem);
        rvNewProduct = findViewById(R.id.rvProducts);
        bottomNavigationView = findViewById(R.id.botNavKhachHang);
        viewPager = findViewById(R.id.viewPager);
    }


}