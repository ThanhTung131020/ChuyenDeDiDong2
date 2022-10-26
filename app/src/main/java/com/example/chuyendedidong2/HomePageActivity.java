package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Adapter.NewProductsAdapter;
import com.example.chuyendedidong2.Model.ImageSilder;
import com.example.chuyendedidong2.Model.NewProductModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class HomePageActivity extends AppCompatActivity {
    //img slider
    private ViewPager viewPager;
    private ImageSliderAdapter imageSliderAdapter;
    //bottom navigation
    private BottomNavigationView bottomNavigationView;

    //new product
    ArrayList<NewProductModel> newProductModelList;
    private RecyclerView rvNewProduct;
    private NewProductModel newProductModel;
    private NewProductsAdapter newProductsAdapter;
    //firebase
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        db = FirebaseFirestore.getInstance();
        setControl();
        setEvent();

    }
    private void setEvent() {
        //image slider
        imageSliderAdapter = new ImageSliderAdapter(this,getListImageSlider());
        viewPager.setAdapter(imageSliderAdapter);
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
        createNewProduct();
        newProductModel = new NewProductModel();
        newProductsAdapter = new NewProductsAdapter(this,newProductModelList);
        rvNewProduct.setAdapter(newProductsAdapter);
    }

    private List<ImageSilder> getListImageSlider() {
        List<ImageSilder> imageSilders = new ArrayList<>();
        imageSilders.add(new ImageSilder(R.drawable.img));
        imageSilders.add(new ImageSilder(R.drawable.img_1));
        return imageSilders;
    }

    private void setControl() {
        rvNewProduct = findViewById(R.id.rvProducts);
        bottomNavigationView = findViewById(R.id.botNavKhachHang);
        viewPager = findViewById(R.id.viewPager);
    }
    public void createNewProduct(){
        newProductModelList = new ArrayList<>();
        newProductModelList.add(new NewProductModel("latop",1000,5,"https://th.bing.com/th/id/OIP.IJzazGh2VeCw8let2ORy6gHaFj?pid=ImgDet&rs=1"));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
    }

}