package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangChuAdminActivity extends AppCompatActivity {

    private Button dangkycuahang, cuahang, duyetsanpham, dangkyshipper , dangxuat;
    private DialogOkActivity dialogOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_admin);
        dangkycuahang = findViewById(R.id.btnDKShop);
        cuahang = findViewById(R.id.btnTKDN);
        duyetsanpham = findViewById(R.id.btnDuyetSP);
        dangkyshipper = findViewById(R.id.btnDKSHipper);
        dialogOk = new DialogOkActivity(this);
        dangxuat = findViewById(R.id.btnDangXuat_Admin);
        setEvent();
    }

    private void setEvent() {
        dangkycuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuAdminActivity.this,DangKyCuaHangActivity.class));
            }
        });
        cuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuAdminActivity.this,QLyShopAdminActivity.class));
            }
        });
        duyetsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuAdminActivity.this, SanPhamShopAdminActivity.class));
            }
        });
        dangkyshipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuAdminActivity.this, DangKyShipperActivity.class));
            }
        });
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void showOKDKShop(){
        dialogOk.ShowDiaLog("Duyệt thành công!");
    }
}