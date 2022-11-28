package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.DonHangKHAdminAdapter;
import com.example.chuyendedidong2.Adapter.DonHangShopAdminAdapter;
import com.example.chuyendedidong2.Model.DonHang;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonHangKhachHangActivity extends AppCompatActivity {

    ArrayList<DonHang> list;
    DonHangKHAdminAdapter adapter;
    RecyclerView rv;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_khach_hang);
        rv = findViewById(R.id.rv_bill_kh_admin);
        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        getDataBase();
        adapter = new DonHangKHAdminAdapter(this,list);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(adapter);
    }

    private void getDataBase() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("thongtin_kh");
        String id_shop = bundle.getString("id");
        DatabaseReference root = database.getReference("bill");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id = dataSnapshot.child("idKhachhang").getValue().toString();
                    if (id.equals(id_shop)){
                        DonHang bill = dataSnapshot.getValue(DonHang.class);
                        list.add(bill);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}