package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.DangKyShopAdapter;
import com.example.chuyendedidong2.Model.Shop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DangKyCuaHangActivity extends AppCompatActivity {

    ArrayList<Shop> list;
    DangKyShopAdapter adapter;
    RecyclerView rv_dk_shop;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_cua_hang);
        rv_dk_shop = findViewById(R.id.rv_dangky_shop);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        getDatabase();
        adapter = new DangKyShopAdapter(this,list);
        rv_dk_shop.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_dk_shop.setAdapter(adapter);
    }

    private void getDatabase() {
        list = new ArrayList<>();
        DatabaseReference root = database.getReference("shop_register");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Shop shop = dataSnapshot.getValue(Shop.class);
                    list.add(shop);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}