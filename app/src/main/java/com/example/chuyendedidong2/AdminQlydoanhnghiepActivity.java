package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chuyendedidong2.Adapter.ShoppAdapter;
import com.example.chuyendedidong2.Model.CuaHang;
import com.example.chuyendedidong2.Model.Shop;

import java.util.ArrayList;

public class AdminQlydoanhnghiepActivity extends AppCompatActivity {
    private ArrayList<CuaHang> listCuaHang;
    private RecyclerView rcv_qlydoanhnghiep;
    private ShoppAdapter shoppAdapter;
    private CuaHang cuaHang;
    private TextView tvTenCuaHang_DN, tvDiaChi_DN, tvSDT_DN, tvDaBan_DN;
    private Button btnKhoa_DN, btnXoa_DN;
    private ImageButton imgBt_back_to_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_qlydoanhnghiep);
        setControl();
        imgBt_back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminQlydoanhnghiepActivity.this  , AdminActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {

        imgBt_back_to_home = findViewById(R.id.imgBt_back_to_home);
        rcv_qlydoanhnghiep = findViewById(R.id.rcv_qlydoanhnghiep);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_qlydoanhnghiep.setLayoutManager(linearLayoutManager);
        shoppAdapter = new ShoppAdapter(AdminQlydoanhnghiepActivity.this, createList());
        rcv_qlydoanhnghiep.setAdapter(shoppAdapter);


    }
    public ArrayList<CuaHang> createList(){
        listCuaHang = new ArrayList<>();
        listCuaHang.add(new CuaHang("shop01","123","123",1));
        listCuaHang.add(new CuaHang("shop01","123","123",3));
        listCuaHang.add(new CuaHang("shop01","123","123",7));
        return listCuaHang;
    }
}
