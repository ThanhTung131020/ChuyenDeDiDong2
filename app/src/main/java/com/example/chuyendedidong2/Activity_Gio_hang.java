package com.example.chuyendedidong2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.chuyendedidong2.Adapter.Adapter_GioHang;
import com.example.chuyendedidong2.Model.ProductModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Activity_Gio_hang extends AppCompatActivity {

    private static final int MY_REQUES_CODE = 10;
    private static List<ProductModel> list = new ArrayList<>();
    private ProductModel productModel = new ProductModel();
    private RecyclerView rcv_GioHang;
    private Adapter_GioHang adapter_gioHang;
    private TextView tv_tongTien;
    private Button btn_DatHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        adapterGioHang();
        //getSupportActionBar().setTitle("gio hang");
        anhxa();
        btn_DatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Gio_hang.this  , Activity_ThongTin_DonHang.class);
                startActivity(intent);
            }
        });


    }



    private void anhxa() {
        btn_DatHang = findViewById(R.id.btn_dathang);
    }

    // set adapter giỏ hàng
    private void adapterGioHang() {
        rcv_GioHang = findViewById(R.id.rcv_giohang);
        adapter_gioHang = new Adapter_GioHang(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_GioHang.setLayoutManager(linearLayoutManager);
        adapter_gioHang.setDaTa(productModel.createNewProduct());
        rcv_GioHang.setAdapter(adapter_gioHang);
        tongTien();
    }

    public void tongTien() {
        tv_tongTien = findViewById(R.id.tv_tongTien);
        int tong = 0;

        for (int i = 0; i < list.size(); i++) {
            tong += list.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv_tongTien.setText("Tổng tiền: " + decimalFormat.format(tong) + "vnđ");
    }


}