package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.chuyendedidong2.Adapter.KhachHangAdapter;
import com.example.chuyendedidong2.Model.KhachHang;
import java.util.ArrayList;

public class AdminQlykhachhangActivity extends AppCompatActivity {
    private ImageButton imgBt_back_to_home;
    private ArrayList<KhachHang> listpersonal;
    private RecyclerView rcv_qlykhachhang;
    private KhachHangAdapter khachHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_qlykhachhang);
        setControl();
        imgBt_back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminQlykhachhangActivity.this  , AdminActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setControl() {


        imgBt_back_to_home = findViewById(R.id.imgBt_back_to_home);
        rcv_qlykhachhang = findViewById(R.id.rcv_qlyCaNhan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_qlykhachhang.setLayoutManager(linearLayoutManager);
        khachHangAdapter = new KhachHangAdapter(AdminQlykhachhangActivity.this, createList());
        rcv_qlykhachhang.setAdapter(khachHangAdapter);
        rcv_qlykhachhang.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


    }
    public ArrayList<KhachHang> createList(){
        listpersonal = new ArrayList<KhachHang>();
        listpersonal.add(new KhachHang("Shoe", "123456789","Tp. Hồ Chí Minh",11, 11));
        listpersonal.add(new KhachHang("Shoe", "123456789","Tp. Hồ Chí Minh",11, 11));
        listpersonal.add(new KhachHang("Shoe", "123456789","Tp. Hồ Chí Minh",11, 11));
        return listpersonal;
    }
}