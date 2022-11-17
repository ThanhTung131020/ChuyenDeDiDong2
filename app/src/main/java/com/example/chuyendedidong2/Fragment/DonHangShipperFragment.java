package com.example.chuyendedidong2.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chuyendedidong2.Adapter.DonHangShipperAdapter;
import com.example.chuyendedidong2.Model.DonHangShipper;
import com.example.chuyendedidong2.R;

public class DonHangShipperFragment extends Fragment {

    DonHangShipper donHangShipper;
    DonHangShipperAdapter donHangShipperAdapter;
    RecyclerView rv_dinhang_shipper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_hang_shipper, container, false);
        rv_dinhang_shipper = view.findViewById(R.id.rv_donhang_shipper);
        donHangShipper = new DonHangShipper();
        donHangShipperAdapter = new DonHangShipperAdapter(getContext(),donHangShipper.createList());
        rv_dinhang_shipper.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_dinhang_shipper.setAdapter(donHangShipperAdapter);
        return view;
    }
}