package com.example.chuyendedidong2.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chuyendedidong2.DiaLogLoanding;
import com.example.chuyendedidong2.DialogOkActivity;
import com.example.chuyendedidong2.HomePageCuaHangActivity;
import com.example.chuyendedidong2.LoginActivity;
import com.example.chuyendedidong2.Model.Personal;
import com.example.chuyendedidong2.Model.Shipper;
import com.example.chuyendedidong2.Model.Shop;
import com.example.chuyendedidong2.R;
import com.example.chuyendedidong2.TrangChuShipperActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThongTinShipperFragment extends Fragment {

    FirebaseDatabase database;
    FirebaseAuth auth;
    TextView ten, sdt, diachi, donhang, id;
    Button btnSua, btnDangXuat;
    DiaLogLoanding diaLogLoanding;
    DialogOkActivity dialogOkActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_tin_shipper, container, false);
        ten = view.findViewById(R.id.edtTen_Shipper);
        sdt = view.findViewById(R.id.edtSDT_Shipper);
        diachi = view.findViewById(R.id.edtDiaChi_Shipper);
        donhang = view.findViewById(R.id.edtDonHang_Shipper);
        id = view.findViewById(R.id.edtUID_Shipper);
        btnSua = view.findViewById(R.id.btnSua_shipper);
        btnDangXuat = view.findViewById(R.id.btnDangXuat_shipper);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        diaLogLoanding = new DiaLogLoanding(getContext());
        dialogOkActivity = new DialogOkActivity(getContext());
        getDataBase();
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
                diaLogLoanding.ShowDiaLog("Đang đăng xuất...");
                DangXuat();
            }
        });
        return view;
    }

    private void DangXuat() {
        auth.signOut();
        ((TrangChuShipperActivity)getActivity()).logout();
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    private void getDataBase() {
        DatabaseReference root = database.getReference("shipper");
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(auth.getUid())){
                    Shipper shipper = snapshot.child(auth.getUid()).getValue(Shipper.class);
                    ten.setText(shipper.getName());
                    sdt.setText(shipper.getSdt());
                    diachi.setText(shipper.getDiachi());
                    id.setText(auth.getUid());
                    id.setEnabled(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void SuaThongTinDataBase() {
        Shipper shipper = new Shipper();
        String tenKH = ten.getText().toString().trim();
        String sdtKH = sdt.getText().toString().trim();
        String diachiKH = diachi.getText().toString().trim();
        if (TextUtils.isEmpty(tenKH)){
            diaLogLoanding.HideDialog();
            ten.setError("Nhập tên!");
            return;
        }else if (TextUtils.isEmpty(sdtKH)){
            diaLogLoanding.HideDialog();
            sdt.setError("Nhập SDT!");
            return;
        }else if (TextUtils.isEmpty(diachiKH)){
            diaLogLoanding.HideDialog();
            diachi.setError("Nhập địa chỉ!");
            return;
        }
        shipper.setName(tenKH);
        shipper.setSdt(sdtKH);
        shipper.setDiachi(diachiKH);
        DatabaseReference root = database.getReference("shipper");
        root.child(auth.getUid()).updateChildren(shipper.toMap(), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                diaLogLoanding.HideDialog();
                dialogOkActivity.ShowDiaLog("Sửa thành công!");
            }
        });
    }

}