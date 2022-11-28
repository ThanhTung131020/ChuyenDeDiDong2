package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chuyendedidong2.Adapter.SanPhamShopAdminAdapter;
import com.example.chuyendedidong2.Model.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChiTietShopAdminActivity extends AppCompatActivity {

    TextView id,ten, sdt, diachi;
    RecyclerView rv_shop;
    ArrayList<ProductModel> list;
    SanPhamShopAdminAdapter adapter;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_shop_admin);
        setControl();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("thongtin_shop");
        String id_shop = bundle.getString("id");
        String name_shop = bundle.getString("name");
        String sdt_shop = bundle.getString("sdt");
        String diachi_shop = bundle.getString("diachi");
        id.setText("ID Cửa Hàng: "+id_shop);
        ten.setText("Tên Cửa Hàng: "+name_shop);
        sdt.setText("SDT Cửa Hàng: "+sdt_shop);
        diachi.setText("Địa Chỉ Cửa Hàng: "+diachi_shop);
        list = new ArrayList<>();
        DatabaseReference root = database.getReference("product");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id = dataSnapshot.child("idShop").getValue().toString();
                    if (id.equals(id_shop)){
                        ProductModel product = dataSnapshot.getValue(ProductModel.class);
                        list.add(product);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adapter = new SanPhamShopAdminAdapter(this,list);
        rv_shop.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_shop.setAdapter(adapter);
    }

    private void setControl() {
        id = findViewById(R.id.tv_id_cuahang_admin);
        ten = findViewById(R.id.tv_ten_cuahang_admin);
        sdt = findViewById(R.id.tv_sdt_cuahang_admin);
        diachi = findViewById(R.id.tv_diachi_cuahang_admin);
        rv_shop = findViewById(R.id.rv_sanpham_cuahang_admin);
    }
}