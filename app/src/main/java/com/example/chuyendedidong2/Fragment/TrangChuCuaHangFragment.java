package com.example.chuyendedidong2.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chuyendedidong2.Adapter.DonHangCuaHangAdapter;
import com.example.chuyendedidong2.Model.DonHangCuaHang;
import com.example.chuyendedidong2.R;


public class TrangChuCuaHangFragment extends Fragment {

    DonHangCuaHang donHangCuaHang;
    DonHangCuaHangAdapter donHangCuaHangAdapter;
    RecyclerView rv_donhang;

    public TrangChuCuaHangFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu_cua_hang, container, false);
        rv_donhang = view.findViewById(R.id.rv_donhang_cuahang);
        setEvent();
        return view;
    }

    private void setEvent() {
        donHangCuaHang = new DonHangCuaHang();
        donHangCuaHangAdapter = new DonHangCuaHangAdapter(getContext(),donHangCuaHang.createList());
        rv_donhang.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_donhang.setAdapter(donHangCuaHangAdapter);
    }
}