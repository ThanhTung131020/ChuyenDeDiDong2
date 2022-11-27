package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.DangKyShipperAdapter;
import com.example.chuyendedidong2.Adapter.DangKyShopAdapter;
import com.example.chuyendedidong2.Model.Shipper;
import com.example.chuyendedidong2.Model.Shop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DangKyShipperActivity extends AppCompatActivity {

    ArrayList<Shipper> list;
    DangKyShipperAdapter adapter;
    RecyclerView rv_dk_shop;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_shipper);
        rv_dk_shop = findViewById(R.id.rv_dangky_shipper);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        list = new ArrayList<>();
        getDataBase();
        adapter = new DangKyShipperAdapter(this,list);
        rv_dk_shop.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_dk_shop.setAdapter(adapter);
    }

    private void getDataBase() {
        DatabaseReference root = database.getReference("shipper_register");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Shipper shipper = dataSnapshot.getValue(Shipper.class);
                    list.add(shipper);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}