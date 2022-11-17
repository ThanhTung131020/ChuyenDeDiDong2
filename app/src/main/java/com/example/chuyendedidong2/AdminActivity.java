package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
private Button btnTKDN, btnTKCN, btnTKNGH;
private Button btnDangXuat;
private TextView tvAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        setControl();
        btnTKDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this  , AdminQlydoanhnghiepActivity.class);
                startActivity(intent);
            }
        });
        btnTKCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this  , AdminQlykhachhangActivity.class);
                startActivity(intent);
            }
        });
        btnTKNGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this  , AdminQlyshipperActivity.class);
                startActivity(intent);
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        btnTKDN = findViewById(R.id.btnTKDN);
        btnTKCN = findViewById(R.id.btnTKCN);
        btnTKNGH = findViewById(R.id.btnTKNGiaoHang);
        btnDangXuat = findViewById(R.id.btnDangXuat_Admin);
    }

}