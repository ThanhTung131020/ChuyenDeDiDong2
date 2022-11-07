package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.QuanLySanPhamAdapter;
import com.example.chuyendedidong2.Model.Sanpham;

import java.util.ArrayList;

public class activity_allproduct extends AppCompatActivity {

    private ArrayList<Sanpham> listSP;
    private QuanLySanPhamAdapter quanLySanPhamAdapter;
    private RecyclerView rvTTSP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allproduct);
        setControl();
        setEvent();

    }

    private void setControl() {
        rvTTSP = findViewById(R.id.rcv_TTSP);
    }

    private void setEvent() {
        taoDanhSachSP();
        quanLySanPhamAdapter = new QuanLySanPhamAdapter(activity_allproduct.this, listSP);
        rvTTSP.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        rvTTSP.setAdapter(quanLySanPhamAdapter);

    }
    public void taoDanhSachSP(){
        listSP = new ArrayList<>();
        listSP.add(new Sanpham("https://imgs.search.brave.com/MmZIvEXmbt0M23wOJZKq9bjAwYfJFhhiXKtCqzdSAS8/rs:fit:1000:648:1/g:ce/aHR0cHM6Ly9jZG4u/dmluYXRhaS5tb2Jp/LzIwMjAvMDEvMTU0/NTk4MzM1NjE2OV8y/ODYwNTU4LmpwZw",3000000,6));
        listSP.add(new Sanpham("https://imgs.search.brave.com/MmZIvEXmbt0M23wOJZKq9bjAwYfJFhhiXKtCqzdSAS8/rs:fit:1000:648:1/g:ce/aHR0cHM6Ly9jZG4u/dmluYXRhaS5tb2Jp/LzIwMjAvMDEvMTU0/NTk4MzM1NjE2OV8y/ODYwNTU4LmpwZw",3000000,6));

    }
}