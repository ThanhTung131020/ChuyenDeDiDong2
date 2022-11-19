package com.example.chuyendedidong2.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chuyendedidong2.Adapter.DonHangCuaHangAdapter;
import com.example.chuyendedidong2.Model.DonHang;
import com.example.chuyendedidong2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DonHangGiaoThanhCongShopFragment extends Fragment {

    ArrayList<DonHang> list = new ArrayList<>();
    DonHangCuaHangAdapter donHangCuaHangAdapter;
    RecyclerView rv_donhang;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang_giao_thanh_cong_shop, container, false);
        rv_donhang = view.findViewById(R.id.rv_bill_dagiao_shop);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        setEvent();
        return view;
    }

    private void setEvent() {
        getDonHangDataBase();
        donHangCuaHangAdapter = new DonHangCuaHangAdapter(getContext(),list);
        rv_donhang.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_donhang.setAdapter(donHangCuaHangAdapter);
    }

    private void getDonHangDataBase() {
        DatabaseReference donHang = database.getReference("bill");
        donHang.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list != null){
                    list.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String idShop = dataSnapshot.child("idCuaHang").getValue().toString();
                    long trang_thai = (long) dataSnapshot.child("trangThaiDH").getValue();
                    if (idShop.equals(auth.getUid()) && trang_thai == 3){
                        DonHang bill = dataSnapshot.getValue(DonHang.class);
                        list.add(bill);
                    }
                    donHangCuaHangAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}