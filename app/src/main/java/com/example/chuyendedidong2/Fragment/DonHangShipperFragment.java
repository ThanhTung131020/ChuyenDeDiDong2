package com.example.chuyendedidong2.Fragment;

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
import com.example.chuyendedidong2.Model.DonHangShipper;
import com.example.chuyendedidong2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonHangShipperFragment extends Fragment {

    ArrayList<DonHang> donHangShippers;
    DonHangShipperAdapter donHangShipperAdapter;
    RecyclerView rv_dinhang_shipper;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang_shipper, container, false);
        rv_dinhang_shipper = view.findViewById(R.id.rv_donhang_shipper);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        donHangShippers = new ArrayList<>();
        getDataBaseDonHang();
        donHangShipperAdapter = new DonHangShipperAdapter(getContext(),donHangShippers);
        rv_dinhang_shipper.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_dinhang_shipper.setAdapter(donHangShipperAdapter);
        return view;
    }

    private void getDataBaseDonHang() {
        DatabaseReference root = database.getReference("bill");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (donHangShippers != null){
                    donHangShippers.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id = dataSnapshot.child("idNguoiGiaoHang").getValue().toString();
                    if (id.equals(auth.getUid())){
                        DonHang bill = dataSnapshot.getValue(DonHang.class);
                        donHangShippers.add(bill);
                    }
                }
                donHangShipperAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}