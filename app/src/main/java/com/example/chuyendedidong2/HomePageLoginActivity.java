package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chuyendedidong2.Fragment.CartFragment;
import com.example.chuyendedidong2.Fragment.HomePageFragment;
import com.example.chuyendedidong2.Fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePageLoginActivity extends AppCompatActivity {
    //bottom navigation
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_login);
        setControl();
        setEvent();
    }

    private void setControl() {
        bottomNavigationView = findViewById(R.id.botNavKhachHang);
    }
    private void setEvent() {
        replaceFrament(new HomePageFragment());
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mnHome:
                        replaceFrament(new HomePageFragment());
                        break;
                    case R.id.mnBill:
                        startActivity(new Intent(HomePageLoginActivity.this,Activity_ThongTin_DonHang.class));
                        break;
                    case R.id.mnUser:
                        replaceFrament(new ProfileFragment());
                        break;
                    case R.id.mnCart:
                        replaceFrament(new CartFragment());
                        break;
                }
                return true;
            }
        });
    }
    private void replaceFrament(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentKhachHang,fragment);
        fragmentTransaction.commit();
    }
    public void timKiem(){

    }


}