package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

//import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Adapter.CategoryAdapter;
import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Adapter.ProductsAdapter;
//import com.example.chuyendedidong2.Model.ImageSilder;
import com.example.chuyendedidong2.Model.CategoryModel;
import com.example.chuyendedidong2.Model.ImageSlider;
import com.example.chuyendedidong2.Model.ProductModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {
    //img slider
    ArrayList<ImageSlider> imageSliders;
    private RecyclerView rv_imgSlider;
    private ImageSlider imageSlider;
    private ImageSliderAdapter imageSliderAdapter;
    //bottom navigation
    private BottomNavigationView bottomNavigationView;
    //spinner
    private Spinner spinner;
    //new product
    ArrayList<ProductModel> productModelList;
    private RecyclerView rvNewProduct;
    private ProductModel productModel;
    private ProductsAdapter newProductsAdapter;
    //category
    ArrayList<CategoryModel> categoryModelList;
    private CategoryModel categoryModel;
    private RecyclerView rvCat;
    private CategoryAdapter categoryAdapter;
    //firebase
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        database = FirebaseDatabase.getInstance();
        setControl();
        setEvent();

    }
    private void setEvent() {
        //image slider
        creatImgSliderList();
        rv_imgSlider.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        imageSliderAdapter = new ImageSliderAdapter(this,imageSliders);
        rv_imgSlider.setAdapter(imageSliderAdapter);
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
        //category
        categoryModelList = new ArrayList<>();
        getCategoryFromDataBase();
        rvCat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        categoryAdapter = new CategoryAdapter(this,categoryModelList);
        rvCat.setAdapter(categoryAdapter);
        //product
        productModelList = new ArrayList<>();
        getProductFromDataBase();
        rvNewProduct.setLayoutManager(new GridLayoutManager(this,3));
        productModel = new ProductModel();
        newProductsAdapter = new ProductsAdapter(this, productModelList);
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

    private void setControl() {
        spinner = findViewById(R.id.spTinKiem);
        rvNewProduct = findViewById(R.id.rvProducts);
        rvCat = findViewById(R.id.rv_category);
        bottomNavigationView = findViewById(R.id.botNavKhachHang);
        rv_imgSlider = findViewById(R.id.rv_viewPager);
    }
    public void getCategoryFromDataBase(){
        DatabaseReference root = database.getReference("category");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (categoryModelList != null){
                    categoryModelList.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    CategoryModel category = dataSnapshot.getValue(CategoryModel.class);
                    categoryModelList.add(category);
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getProductFromDataBase() {
        DatabaseReference root = database.getReference("product");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (productModelList != null){
                    productModelList.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ProductModel product = dataSnapshot.getValue(ProductModel.class);
                    productModelList.add(product);
                }
                newProductsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void creatImgSliderList(){
        imageSliders = new ArrayList<>();
        imageSliders.add(new ImageSlider("is1","https://th.bing.com/th/id/OIP.Vd1yGBbY7iX3REHs1jOCAgHaD6?pid=ImgDet&rs=1"));
        imageSliders.add(new ImageSlider("is2","https://th.bing.com/th/id/OIP.k5zEoNlXYKit6Frf84pvwwHaFL?w=271&h=190&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
        imageSliders.add(new ImageSlider("is3","https://th.bing.com/th/id/OIP.bfwU6t0zn2j7_eX91M-6IQHaET?w=286&h=180&c=7&r=0&o=5&dpr=1.7&pid=1.7"));
    }

}