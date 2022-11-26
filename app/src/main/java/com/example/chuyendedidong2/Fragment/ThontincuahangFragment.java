package com.example.chuyendedidong2.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chuyendedidong2.Adapter.SanPhamCuaHangAdapter;
import com.example.chuyendedidong2.DiaLogLoanding;
import com.example.chuyendedidong2.DialogOkActivity;
import com.example.chuyendedidong2.HomePageCuaHangActivity;
import com.example.chuyendedidong2.HomePageLoginActivity;
import com.example.chuyendedidong2.LoginActivity;
import com.example.chuyendedidong2.Model.Personal;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.Model.Shop;
import com.example.chuyendedidong2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ThontincuahangFragment extends Fragment {

    DiaLogLoanding diaLogLoanding;
    DialogOkActivity dialogOkActivity;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ArrayList<ProductModel> products;
    SanPhamCuaHangAdapter sanPhamCuaHangAdapter;
    EditText ten, diachi, spdaban, spdangco, masothue;
    Button btnSua, btnDangXuat;
    public ThontincuahangFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thontincuahang, container, false);
        ten = view.findViewById(R.id.edtTen_PF);
        diachi = view.findViewById(R.id.edtDiaChi_PF);
        spdaban = view.findViewById(R.id.edtSPDABAN_PF);
        spdangco = view.findViewById(R.id.edtSPDANGCO_PF);
        masothue = view.findViewById(R.id.edtMASOTHUE_PF);
        btnSua = view.findViewById(R.id.btnSua_store);
        btnDangXuat = view.findViewById(R.id.btnDangXuat_store);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        diaLogLoanding = new DiaLogLoanding(getContext());
        dialogOkActivity = new DialogOkActivity(getContext());
        sanPhamCuaHangAdapter = new SanPhamCuaHangAdapter(getContext(),products);
        getDatabase();
        sanPhamDaCo();
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang sửa...");
                SuaThongTinDataBase();
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang đăng xuât...");
                logout();
            }
        });
        return view;
    }

    private void logout() {
        auth.signOut();
        ((HomePageCuaHangActivity)getActivity()).LogOut();
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    private void sanPhamDaCo() {
        products = new ArrayList<>();
        DatabaseReference root = database.getReference("product");
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                products.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String id = dataSnapshot.child("idShop").getValue().toString();
                    if (id.equals(auth.getUid())){
                        ProductModel product = dataSnapshot.getValue(ProductModel.class);
                        if (product != null){
                            products.add(product);
                            sanPhamCuaHangAdapter.notifyDataSetChanged();
                        }
                    }
                    spdangco.setText(String.valueOf(products.size()));
                    spdangco.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getDatabase() {
        DatabaseReference root = database.getReference("shop");
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(auth.getUid())){
                    Shop shop = snapshot.child(auth.getUid()).getValue(Shop.class);
                    ten.setText(shop.getName());
                    diachi.setText(shop.getDiachi());
                    //spdaban.setText(shop.getSpDaBan());
                    //spdangco.setText(shop.getSpDangCo());
                    //masothue.setText(shop.getMaSoThue());
                    masothue.setEnabled(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void SuaThongTinDataBase() {
        Shop shop = new Shop();
        String tenKH = ten.getText().toString().trim();
        String diachiKH = diachi.getText().toString().trim();
        if (TextUtils.isEmpty(tenKH)){
            diaLogLoanding.HideDialog();
            ten.setError("Nhập tên!");
            return;
        }else if (TextUtils.isEmpty(diachiKH)){
            diaLogLoanding.HideDialog();
            diachi.setError("Nhập địa chỉ!");
            return;
        }
        shop.setName(tenKH);
        shop.setDiachi(diachiKH);
        DatabaseReference root = database.getReference("shop");
        root.child(auth.getUid()).updateChildren(shop.toMap(), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                diaLogLoanding.HideDialog();
                dialogOkActivity.ShowDiaLog("Sửa thành công!");
            }
        });
    }

}