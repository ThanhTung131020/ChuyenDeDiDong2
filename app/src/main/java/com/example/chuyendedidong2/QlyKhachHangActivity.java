package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.QuanLyKHAdapter;
import com.example.chuyendedidong2.Adapter.QuanLyShopAdminAdapter;
import com.example.chuyendedidong2.Model.Personal;
import com.example.chuyendedidong2.Model.Shop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QlyKhachHangActivity extends AppCompatActivity {

    ArrayList<Personal> list;
    QuanLyKHAdapter adapter;
    RecyclerView rv_qly;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qly_khach_hang);
        rv_qly = findViewById(R.id.rv_quanly_kh_admin);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        setEvent();
    }

    private void setEvent() {
        list = new ArrayList<>();
        getDataBase();
        adapter = new QuanLyKHAdapter(this,list);
        rv_qly.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_qly.setAdapter(adapter);
    }

    private void getDataBase() {
        DatabaseReference root = database.getReference("personal");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Personal shop = dataSnapshot.getValue(Personal.class);
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