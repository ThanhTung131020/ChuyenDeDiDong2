package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.Adapter_thongtin_donhang;
import com.example.chuyendedidong2.Model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class Activity_ThongTin_DonHang extends AppCompatActivity {
    private static List<ProductModel> list = new ArrayList<>();
    private ProductModel productModel = new ProductModel();
    private RecyclerView rcv_TTDH;
    private Adapter_thongtin_donhang adapter_thongtin_donhang;
    private ProductModel product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_don_hang);
        adapterTTDH();

    }

    private void adapterTTDH() {
        productModel = new ProductModel();
        rcv_TTDH = findViewById(R.id.rcv_TTDH);
        adapter_thongtin_donhang = new Adapter_thongtin_donhang(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_TTDH.setLayoutManager(linearLayoutManager);
        adapter_thongtin_donhang.setData(product.createNewProduct());
        rcv_TTDH.setAdapter(adapter_thongtin_donhang);
    }



//    private List<ProductModel> getListGH() {
//        Bundle bundle = getIntent().getExtras();
//        ProductModel prd = (ProductModel) bundle.get("object_products");
//        list.add(new ProductModel(prd.getImg_url() , prd.getName() , prd.getPrice() , prd.getSoLuong(),prd.getNameShop()));
//        return list;
//    }

}