package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.chuyendedidong2.Fragment.CartFragment;
import com.example.chuyendedidong2.Fragment.DonHangDangGiaoShopFragment;
import com.example.chuyendedidong2.Fragment.DonHangGiaoThanhCongShopFragment;
import com.example.chuyendedidong2.Fragment.HomePageFragment;
import com.example.chuyendedidong2.Fragment.ProfileFragment;
import com.example.chuyendedidong2.Fragment.SanPhamCuaHangFragment;
import com.example.chuyendedidong2.Fragment.ThontincuahangFragment;
import com.example.chuyendedidong2.Fragment.TrangChuCuaHangFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePageCuaHangActivity extends AppCompatActivity {
    BottomNavigationView bot_cuahang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_cua_hang);
        setControl();
        setEvent();
    }

    private void setControl() {
        bot_cuahang = findViewById(R.id.botNavCuaHang);
    }
    private void setEvent() {
        replaceFrament(new TrangChuCuaHangFragment());
        bot_cuahang.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mnSanPhamCuaHang:
                        replaceFrament(new SanPhamCuaHangFragment());
                        break;
                    case R.id.mnThongTinCuaHang:
                        replaceFrament(new ThontincuahangFragment());
                        break;
                    case R.id.mnTrangChuCuaHang:
                        replaceFrament(new TrangChuCuaHangFragment());
                        break;
                    case R.id.mnDonHangDangGiao:
                        replaceFrament(new DonHangDangGiaoShopFragment());
                        break;
                    case R.id.mnDonHangDaGiao:
                        replaceFrament(new DonHangGiaoThanhCongShopFragment());
                        break;
                }
                return true;
            }
        });
    }
    private void replaceFrament(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentCuaHang,fragment);
        fragmentTransaction.commit();
    }
    public void LogOut(){
        finishAffinity();
    }
}
