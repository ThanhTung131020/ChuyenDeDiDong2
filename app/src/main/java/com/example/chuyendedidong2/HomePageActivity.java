package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.chuyendedidong2.Adapter.CategoryAdapter;
import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Adapter.NewProductsAdapter;
import com.example.chuyendedidong2.Model.Category;
import com.example.chuyendedidong2.Model.ImageSilder;
import com.example.chuyendedidong2.Model.NewProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;

public class HomePageActivity extends AppCompatActivity {
    //img slider
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private ImageSliderAdapter imageSliderAdapter;
    //bottom navigation
    private BottomNavigationView bottomNavigationView;
    //category
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categoryList;
    private RecyclerView rvCategory;
    //new product
    private RecyclerView rvNewProduct;
    private ArrayList<NewProductModel> newProductModelList;
    private NewProductsAdapter newProductsAdapter;
    //firebase
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        circleIndicator.setViewPager(viewPager);
        imageSliderAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
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
        //recycler view va firebase category
        rvCategory.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this,categoryList);
        rvCategory.setAdapter(categoryAdapter);
        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category category = document.toObject(Category.class);
                                categoryList.add(category);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            //Toast.makeText(HomePageActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //recycler view va firebase new product
        rvNewProduct.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        //newProductModelList = new ArrayList<>();
        createNewProduct();
        newProductsAdapter = new NewProductsAdapter(this,newProductModelList);
        rvNewProduct.setAdapter(newProductsAdapter);
//        db.collection("NewProducts")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            for (QueryDocumentSnapshot document : task.getResult()){
//                                NewProductModel newProductModel = document.toObject(NewProductModel.class);
//                                newProductModelList.add(newProductModel);
//                                categoryAdapter.notifyDataSetChanged();
//                            }
//                        }else {
//                           Toast.makeText(HomePageActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

    }

    private List<ImageSilder> getListImageSlider() {
        List<ImageSilder> imageSilders = new ArrayList<>();
        imageSilders.add(new ImageSilder(R.drawable.img));
        imageSilders.add(new ImageSilder(R.drawable.img_1));
        return imageSilders;
    }

    private void setControl() {
        rvNewProduct = findViewById(R.id.new_product_rec);
        rvCategory = findViewById(R.id.rvCat);
        bottomNavigationView = findViewById(R.id.botNavKhachHang);
        viewPager = findViewById(R.id.viewPager);
        circleIndicator = findViewById(R.id.circle_indicate);
    }
    private void createNewProduct(){
        newProductModelList = new ArrayList<>();
        newProductModelList.add(new NewProductModel("latop",1000,5,"https://th.bing.com/th/id/OIP.IJzazGh2VeCw8let2ORy6gHaFj?pid=ImgDet&rs=1"));
        newProductModelList.add(new NewProductModel("phone",1000,5,""));
    }
}