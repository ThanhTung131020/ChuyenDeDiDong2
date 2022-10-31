package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.chuyendedidong2.Adapter.CategoryAdapter;
import com.example.chuyendedidong2.Adapter.ImageSliderAdapter;
import com.example.chuyendedidong2.Model.Category;
import com.example.chuyendedidong2.Model.ImageSilder;
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
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private ImageSliderAdapter imageSliderAdapter;
    private BottomNavigationView bottomNavigationView;
    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> categoryList;
    private RecyclerView rvCategory;
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
        //recycler view category
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

                        }
                    }
                });
    }

    private List<ImageSilder> getListImageSlider() {
        List<ImageSilder> imageSilders = new ArrayList<>();
        imageSilders.add(new ImageSilder(R.drawable.img));
        imageSilders.add(new ImageSilder(R.drawable.img_1));
        return imageSilders;
    }

    private void setControl() {
        rvCategory = findViewById(R.id.rvCat);
        bottomNavigationView = findViewById(R.id.botNavKhachHang);
        viewPager = findViewById(R.id.viewPager);
        circleIndicator = findViewById(R.id.circle_indicate);
    }
}