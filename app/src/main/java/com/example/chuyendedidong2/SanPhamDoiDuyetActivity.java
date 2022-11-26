package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.SanPhamDoiDuyetAdapter;
import com.example.chuyendedidong2.Model.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;

public class SanPhamDoiDuyetActivity extends AppCompatActivity {

    ArrayList<ProductModel> list;
    SanPhamDoiDuyetAdapter adapter;
    RecyclerView rv;
    FirebaseDatabase database;
    FirebaseAuth auth;
    DiaLogLoanding diaLogLoanding;
    DialogOkActivity dialogOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham_doi_duyet);
        rv = findViewById(R.id.rv_shop_sanpham_doiduyet);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        diaLogLoanding = new DiaLogLoanding(this);
        dialogOk = new DialogOkActivity(this);
        setEvent();
        adapter = new SanPhamDoiDuyetAdapter(this,list);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(adapter);
    }

    private void setEvent() {
        list = new ArrayList<>();
        getDataBase();
    }

    private void getDataBase() {
        DatabaseReference root = database.getReference("product_register");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id_shop = dataSnapshot.child("idShop").getValue().toString();
                    if (id_shop.equals(auth.getUid())){
                        ProductModel product = dataSnapshot.getValue(ProductModel.class);
                        list.add(product);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}