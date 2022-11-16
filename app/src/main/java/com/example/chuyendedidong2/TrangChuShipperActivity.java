package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.chuyendedidong2.Fragment.DonHangShipperFragment;
import com.example.chuyendedidong2.Fragment.ThongTinShipperFragment;
import com.example.chuyendedidong2.Fragment.ThontincuahangFragment;
import com.example.chuyendedidong2.Fragment.TrangChuCuaHangFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TrangChuShipperActivity extends AppCompatActivity {
    BottomNavigationView bot_shipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_shipper);
        setControl();
        setEvent();
    }

    private void setEvent() {
        replaceFrament(new ThongTinShipperFragment());
        bot_shipper.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mnThongTinShipper:
                        replaceFrament(new ThongTinShipperFragment());
                        break;
                    case R.id.mnDonHangShipper:
                        replaceFrament(new DonHangShipperFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void setControl() {
        bot_shipper = findViewById(R.id.botNavShipper);
    }
    private void replaceFrament(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentShipper,fragment);
        fragmentTransaction.commit();
    }
}