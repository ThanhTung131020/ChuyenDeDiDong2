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

import com.example.chuyendedidong2.Adapter.DonHangShipperAdapter;
import com.example.chuyendedidong2.Model.DonHang;
import com.example.chuyendedidong2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DonHangDaGiaoShipperFragment extends Fragment {

    RecyclerView rv_dagiao;
    ArrayList<DonHang> list;
    DonHangShipperAdapter adapter;
    FirebaseDatabase database;
    FirebaseAuth auth;
    public DonHangDaGiaoShipperFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang_da_giao_shipper, container, false);
        rv_dagiao = view.findViewById(R.id.rv_bill_dagiao_shipper);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        list = new ArrayList<>();
        getDataBaseBill();
        adapter = new DonHangShipperAdapter(getContext(),list);
        rv_dagiao.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_dagiao.setAdapter(adapter);
        return view;
    }

    private void getDataBaseBill() {
        DatabaseReference root = database.getReference("bill");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list != null){
                    list.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id = dataSnapshot.child("idNguoiGiaoHang").getValue().toString();
                    long trang_thai = (long) dataSnapshot.child("trangThaiDH").getValue();
                    if (id.equals(auth.getUid()) && trang_thai == 5){
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