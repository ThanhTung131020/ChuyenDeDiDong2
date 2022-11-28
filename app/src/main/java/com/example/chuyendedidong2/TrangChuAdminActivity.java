package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangChuAdminActivity extends AppCompatActivity {

    private Button shipper,khachhang,dangkycuahang, cuahang, duyetsanpham, dangkyshipper, dangxuat;
    private DialogOkActivity dialogOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_admin);
        dangkycuahang = findViewById(R.id.btnDKShop);
        cuahang = findViewById(R.id.btnTKDN);
        duyetsanpham = findViewById(R.id.btnDuyetSP);
        dangkyshipper = findViewById(R.id.btnDKSHipper);
        khachhang = findViewById(R.id.btnTKCN);
        shipper = findViewById(R.id.btnTKNGiaoHang);
        dangxuat = findViewById(R.id.btnDangXuat_Admin);
        dialogOk = new DialogOkActivity(this);
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
        khachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuAdminActivity.this, QlyKhachHangActivity.class));
            }
        });
        shipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuAdminActivity.this, QuanLyShipperAdminActivity.class));
            }
        });
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuAdminActivity.this, LoginActivity.class));
                finishAffinity();
            }
        });
    }
    public void showOKDKShop(){
        dialogOk.ShowDiaLog("Duyệt thành công!");
    }
}