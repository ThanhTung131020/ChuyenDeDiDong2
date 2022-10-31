package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.Adapter_GioHang;
import com.example.chuyendedidong2.Adapter.Adapter_thongtin_donhang;
import com.example.chuyendedidong2.Data.GioHang;
import com.example.chuyendedidong2.Model.NewProductModel;

import java.util.ArrayList;
import java.util.List;

public class Activity_ThongTin_DonHang extends AppCompatActivity {
    private static List<NewProductModel> list = new ArrayList<>();
    private NewProductModel newProductModel;
    private RecyclerView rcv_TTDH;
    private Adapter_thongtin_donhang adapter_thongtin_donhang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_don_hang);
        adapterTTDH();

    }

    private void adapterTTDH() {
        rcv_TTDH = findViewById(R.id.rcv_TTDH);
        adapter_thongtin_donhang = new Adapter_thongtin_donhang(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_TTDH.setLayoutManager(linearLayoutManager);
        adapter_thongtin_donhang.setData(newProductModel.createNewProduct());
        rcv_TTDH.setAdapter(adapter_thongtin_donhang);


    }



//    private List<GioHang> getListGH() {
//
//
//        list.add(new GioHang(1, 1000, R.drawable.img, "ch1", "iphone 11"));
//        list.add(new GioHang(1, 1000, R.drawable.img, "ch1", "iphone 11"));
//        list.add(new GioHang(1, 1000, R.drawable.img, "ch1", "iphone 11"));
//        list.add(new GioHang(1, 1000, R.drawable.img, "ch1", "iphone 11"));
//
//        return list;
//    }

}