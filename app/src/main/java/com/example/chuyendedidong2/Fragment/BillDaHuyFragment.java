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

import com.example.chuyendedidong2.Adapter.Adapter_thongtin_donhang;
import com.example.chuyendedidong2.Model.DonHang;
import com.example.chuyendedidong2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BillDaHuyFragment extends Fragment{
    FirebaseDatabase database;
    FirebaseAuth auth;
    ArrayList<DonHang> list;
    Adapter_thongtin_donhang adapter_thongtin_donhang;
    RecyclerView rv_bill;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill_da_huy, container, false);
        rv_bill = view.findViewById(R.id.rv_remove_bill_kh);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        adapter_thongtin_donhang = new Adapter_thongtin_donhang(getContext());
        list = new ArrayList<>();
        getDataBaseBill();
        rv_bill.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter_thongtin_donhang.setData(list);
        rv_bill.setAdapter(adapter_thongtin_donhang);
        return view;
    }

    private void getDataBaseBill() {
        DatabaseReference root = database.getReference("bill");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list != null) {
                    list.clear();
                }
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String id = dataSnapshot.child("idKhachhang").getValue().toString();
                    long trang_thai = (long) dataSnapshot.child("trangThaiDH").getValue();
                    if (id.equals(auth.getUid()) && trang_thai == 6){
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