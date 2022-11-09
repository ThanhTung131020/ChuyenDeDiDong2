package com.example.chuyendedidong2.Fragment;

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
import android.widget.Toast;

import com.example.chuyendedidong2.ActivityQuanLySanPham;
import com.example.chuyendedidong2.Adapter.SanPhamCuaHangAdapter;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;
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
    Button btnThem, btnXoa;
    FirebaseDatabase database;
    FirebaseAuth auth;
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
    }

    private void getProductFromDatabase() {
        DatabaseReference root = database.getReference("product");
//        listProduct.add(new ProductModel("https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",5000000,4,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123"));
//        listProduct.add(new ProductModel("https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",5000000,4,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123"));
//        root.setValue(listProduct);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (listProduct != null){
                    listProduct.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id = dataSnapshot.child("idShop").getValue().toString();
                    if (id.equals(auth.getUid())){
                        ProductModel product = dataSnapshot.getValue(ProductModel.class);
                        listProduct.add(product);
                    }
                }
                sanPhamCuaHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}