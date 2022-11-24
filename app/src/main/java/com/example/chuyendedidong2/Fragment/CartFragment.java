package com.example.chuyendedidong2.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.chuyendedidong2.Activity_Gio_hang;
import com.example.chuyendedidong2.Activity_ThongTin_DonHang;
import com.example.chuyendedidong2.Adapter.Adapter_GioHang;
import com.example.chuyendedidong2.Model.CartModel;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private static final int MY_REQUES_CODE = 10;
    private static List<CartModel> list = new ArrayList<>();
    private CartModel productModel = new CartModel();
    private RecyclerView rcv_GioHang;
    private Adapter_GioHang adapter_gioHang;
    private TextView tv_tongTien;
    private Button btn_DatHang;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    public CartFragment() {
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        rcv_GioHang = view.findViewById(R.id.rvGioHang);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        getDataBase();
        adapterGioHang();
        //getSupportActionBar().setTitle("gio hang");
        return view;
    }

    private void getDataBase() {
        DatabaseReference root = database.getReference("cart").child(auth.getUid());
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (list == null){
                    return;
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    CartModel cart = dataSnapshot.getValue(CartModel.class);
                    list.add(cart);
                }
                adapter_gioHang.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // set adapter giỏ hàng
    private void adapterGioHang() {
        adapter_gioHang = new Adapter_GioHang(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_GioHang.setLayoutManager(linearLayoutManager);
        adapter_gioHang.setDaTa(list);
        rcv_GioHang.setAdapter(adapter_gioHang);
    }

}