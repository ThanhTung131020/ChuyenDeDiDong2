package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.chuyendedidong2.Adapter.ShoppAdapter;
import com.example.chuyendedidong2.Model.Shop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminQlydoanhnghiepActivity extends AppCompatActivity {
    private ArrayList<Shop> listCuaHang;
    private RecyclerView rcv_qlydoanhnghiep;
    private ShoppAdapter shoppAdapter;
    private ImageButton imgBt_back_to_home;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_qlydoanhnghiep);
        firebaseDatabase = FirebaseDatabase.getInstance();
        setControl();
        imgBt_back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminQlydoanhnghiepActivity.this  , AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void setControl() {
        imgBt_back_to_home = findViewById(R.id.imgBt_back_to_home);
        rcv_qlydoanhnghiep = findViewById(R.id.rcv_qlydoanhnghiep);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_qlydoanhnghiep.setLayoutManager(linearLayoutManager);
        getDataBase();
        shoppAdapter = new ShoppAdapter(this, listCuaHang);
        rcv_qlydoanhnghiep.setAdapter(shoppAdapter);
        rcv_qlydoanhnghiep.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }
    private void getDataBase(){
            listCuaHang = new ArrayList<>();
            DatabaseReference databaseReference = firebaseDatabase.getReference("shop");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listCuaHang.clear();
                    for(DataSnapshot snap : snapshot.getChildren()) {
                        Shop shop = snap.getValue(Shop.class);
                        listCuaHang.add(shop);
                    }
                    shoppAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }

