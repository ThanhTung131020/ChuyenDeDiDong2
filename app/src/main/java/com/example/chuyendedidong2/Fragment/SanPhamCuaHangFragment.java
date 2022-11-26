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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chuyendedidong2.ActivityQuanLySanPham;
import com.example.chuyendedidong2.Adapter.SanPhamCuaHangAdapter;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;
import com.example.chuyendedidong2.SanPhamDoiDuyetActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SanPhamCuaHangFragment extends Fragment {
    ArrayList<ProductModel> listProduct;
    ProductModel productModel;
    SanPhamCuaHangAdapter sanPhamCuaHangAdapter;
    RecyclerView rv_sp_ch;
    Button btnThem, btnXoa, btnSPDangBan;
    FirebaseDatabase database;
    FirebaseAuth auth;
    TextView tvTongSP;
    int tongSanPham;
    public SanPhamCuaHangFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham_cua_hang, container, false);
        rv_sp_ch = view.findViewById(R.id.rv_sanpham_cuahang);
        btnThem = view.findViewById(R.id.btnThemSanPhamCuaHang);
        btnSPDangBan = view.findViewById(R.id.btnSanPhamDoiDuyet);
        tvTongSP = view.findViewById(R.id.tvTongSanPham);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        setEvent();
        return view;
    }

    private void setEvent() {
        //lay san pham tu database
        listProduct = new ArrayList<>();
        getProductFromDatabase();
        productModel = new ProductModel();
        rv_sp_ch.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        sanPhamCuaHangAdapter = new SanPhamCuaHangAdapter(getContext(),listProduct);
        rv_sp_ch.setAdapter(sanPhamCuaHangAdapter);
        //them san pham
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ActivityQuanLySanPham.class));
            }
        });

        btnSPDangBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SanPhamDoiDuyetActivity.class));
            }
        });
    }



    private void getProductFromDatabase() {
        DatabaseReference root = database.getReference("product");
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String id = snapshot.child("idShop").getValue().toString();
                    if (id.equals(auth.getUid())){
                        ProductModel product = snapshot.getValue(ProductModel.class);
                        if (product != null){
                            listProduct.add(product);
                            sanPhamCuaHangAdapter.notifyDataSetChanged();
                        }
                    }
                    tvTongSP.setText("Tổng sản phẩm: " + (listProduct.size()));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ProductModel product = snapshot.getValue(ProductModel.class);
                if (product == null || listProduct == null || listProduct.isEmpty()){
                    return;
                }
                for (int i = 0; i < listProduct.size(); i++){
                    if (product.getProduct_id() == listProduct.get(i).getProduct_id()){
                        listProduct.set(i, product);
                    }
                }
                sanPhamCuaHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}