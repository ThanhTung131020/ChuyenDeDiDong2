package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.QuanLyKHAdapter;
import com.example.chuyendedidong2.Adapter.QuanLyShipperAdapter;
import com.example.chuyendedidong2.Model.Personal;
import com.example.chuyendedidong2.Model.Shipper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuanLyShipperAdminActivity extends AppCompatActivity {

    ArrayList<Shipper> list;
    QuanLyShipperAdapter adapter;
    RecyclerView rv_qly;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_shipper_admin);
        rv_qly = findViewById(R.id.rv_qly_shipper_admin);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        setEvent();
    }

    private void setEvent() {
        list = new ArrayList<>();
        getDataBase();
        adapter = new QuanLyShipperAdapter(this,list);
        rv_qly.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_qly.setAdapter(adapter);
    }

    private void getDataBase() {
        DatabaseReference root = database.getReference("shipper");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Shipper shop = dataSnapshot.getValue(Shipper.class);
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