package com.example.chuyendedidong2;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.chuyendedidong2.Adapter.AdapterGioHang;
import com.example.chuyendedidong2.Data.GioHang;

import java.util.ArrayList;
import java.util.List;

public class Activity_Gio_hang extends AppCompatActivity {
    private RecyclerView rcv_GioHang;
    private List<GioHang> mlistGioHang;
    private AdapterGioHang adapterGioHang;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

       rcv_GioHang = findViewById(R.id.rcv_giohang);
        mlistGioHang = new ArrayList<>();
        GioHang gioHang = new GioHang(300000, R.drawable.img, "cua hang 1");
        GioHang gioHang1 = new GioHang(300000, R.drawable.img, "cua hang 2");
        mlistGioHang.add(gioHang);
        mlistGioHang.add(gioHang1);
        adapterGioHang = new AdapterGioHang(mlistGioHang);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_GioHang.setLayoutManager(linearLayoutManager);
        rcv_GioHang.setAdapter(adapterGioHang);
    }


}