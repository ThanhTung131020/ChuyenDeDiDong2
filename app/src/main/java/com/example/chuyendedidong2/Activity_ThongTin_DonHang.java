package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.Adapter_thongtin_donhang;
import com.example.chuyendedidong2.Model.DonHang;
import com.example.chuyendedidong2.Model.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_ThongTin_DonHang extends AppCompatActivity {
    private static List<DonHang> list = new ArrayList<>();
    private RecyclerView rcv_TTDH;
    private Adapter_thongtin_donhang adapter_thongtin_donhang;
    private FirebaseDatabase database;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_don_hang);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        adapterTTDH();
    }

    private void adapterTTDH() {
        rcv_TTDH = findViewById(R.id.rcv_TTDH);
        getBillDataBase();
        adapter_thongtin_donhang = new Adapter_thongtin_donhang(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_TTDH.setLayoutManager(linearLayoutManager);
        adapter_thongtin_donhang.setData(list);
        rcv_TTDH.setAdapter(adapter_thongtin_donhang);
    }

    private void getBillDataBase() {
        DatabaseReference bill = database.getReference("bill");
        bill.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list != null){
                    list.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id = dataSnapshot.child("idKhachhang").getValue().toString();
                    long trang_thai = (long) dataSnapshot.child("trangThaiDH").getValue();
                    if (id.equals(auth.getUid()) && trang_thai != 6){
                        DonHang donHang = dataSnapshot.getValue(DonHang.class);
                        list.add(donHang);
                    }
                }
                adapter_thongtin_donhang.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}