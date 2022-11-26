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

import com.example.chuyendedidong2.Adapter.ShipperAdapter;
import com.example.chuyendedidong2.Adapter.ShoppAdapter;
import com.example.chuyendedidong2.Model.Shipper;
import com.example.chuyendedidong2.Model.Shop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminQlyshipperActivity extends AppCompatActivity {
    private ImageButton imgBt_back_to_home;
    FirebaseDatabase firebaseDatabase;
    private ArrayList<Shipper> listShipper;
    private RecyclerView rcv_qlyShipper;
    private ShipperAdapter shipperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_qlyshipper);
        firebaseDatabase = FirebaseDatabase.getInstance();
        setControl();
        imgBt_back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminQlyshipperActivity.this  , AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void setControl() {
        imgBt_back_to_home = findViewById(R.id.imgBt_back_to_home);
        rcv_qlyShipper = findViewById(R.id.rcv_qlyShipper);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_qlyShipper.setLayoutManager(linearLayoutManager);
        getDataBase();
        shipperAdapter = new ShipperAdapter(this, listShipper);
        rcv_qlyShipper.setAdapter(shipperAdapter);
        rcv_qlyShipper.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }
    private void getDataBase(){
        listShipper = new ArrayList<>();
        DatabaseReference databaseReference = firebaseDatabase.getReference("shipper");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listShipper.clear();
                for(DataSnapshot snap : snapshot.getChildren()) {
                    Shipper shipper = snap.getValue(Shipper.class);
                    listShipper.add(shipper);
                }
                shipperAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
