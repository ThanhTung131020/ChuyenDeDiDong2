package com.example.chuyendedidong2.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

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
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private static final int MY_REQUES_CODE = 10;
    private static List<ProductModel> list = new ArrayList<>();
    private ProductModel productModel = new ProductModel();
    private RecyclerView rcv_GioHang;
    private Adapter_GioHang adapter_gioHang;
    private TextView tv_tongTien;
    private Button btn_DatHang;
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
        btn_DatHang = view.findViewById(R.id.btnDatHang);
        rcv_GioHang = view.findViewById(R.id.rvGioHang);
        tv_tongTien = view.findViewById(R.id.tvTongTien);
        adapterGioHang();
        //getSupportActionBar().setTitle("gio hang");
        btn_DatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext()  , Activity_ThongTin_DonHang.class);
                startActivity(intent);
            }
        });
        return view;
    }
    // set adapter giỏ hàng
    private void adapterGioHang() {


        adapter_gioHang = new Adapter_GioHang(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_GioHang.setLayoutManager(linearLayoutManager);
        adapter_gioHang.setDaTa(productModel.createProductCart());
        rcv_GioHang.setAdapter(adapter_gioHang);
        tongTien();


    }

    public void tongTien() {

        int tong = 0;

        for (int i = 0; i < list.size(); i++) {
            tong += list.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv_tongTien.setText("Tổng tiền: " + decimalFormat.format(tong) + "vnđ");
    }
}