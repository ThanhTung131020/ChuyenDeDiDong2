package com.example.chuyendedidong2.Fragment;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.chuyendedidong2.DiaLogLoanding;
import com.example.chuyendedidong2.DialogOkActivity;
import com.example.chuyendedidong2.HomePageLoginActivity;
import com.example.chuyendedidong2.LoginActivity;
import com.example.chuyendedidong2.Model.Personal;
import com.example.chuyendedidong2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

//    HomePageLoginActivity homePageLoginActivity;
    FirebaseDatabase database;
    DiaLogLoanding diaLogLoanding;
    DialogOkActivity dialogOkActivity;
    FirebaseAuth auth;
    EditText ten, diachi, sdt, email, matkhau;
    Button btnSua, btnDangXuat;
    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ten = view.findViewById(R.id.edtTen_PF);
        diachi = view.findViewById(R.id.edtDiaChi_PF);
        sdt = view.findViewById(R.id.edtSDT_PF);
        email = view.findViewById(R.id.edtEmail_PF);
        matkhau = view.findViewById(R.id.edtMatKhau_PF);
        btnSua = view.findViewById(R.id.btnSua_user);
        btnDangXuat = view.findViewById(R.id.btnDangXuat_user);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        diaLogLoanding = new DiaLogLoanding(getContext());
        dialogOkActivity = new DialogOkActivity(getContext());
        getDatabase();
        setEvent();
        return view;
    }

    private void setEvent() {
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang cập nhật...");
                SuaThongTinDataBase();
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đăng xuất...");
                logout();
            }
        });
    }

    private void logout() {
        auth.signOut();
        ((HomePageLoginActivity)getActivity()).logout();
        startActivity(new Intent(getContext(),LoginActivity.class));
    }

    private void SuaThongTinDataBase() {
        Personal personal = new Personal();
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
        personal.setName(tenKH);
        personal.setSdt(sdtKH);
        personal.setDiachi(diachiKH);
        DatabaseReference root = database.getReference("personal");
        root.child(auth.getUid()).updateChildren(personal.toMap(), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                diaLogLoanding.HideDialog();
                dialogOkActivity.ShowDiaLog("Sửa thành công!");
            }
        });
    }

    private void getDatabase() {
        DatabaseReference root = database.getReference("personal");
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(auth.getUid())){
                    Personal personal = snapshot.child(auth.getUid()).getValue(Personal.class);
                    ten.setText(personal.getName());
                    sdt.setText(personal.getSdt());
                    diachi.setText(personal.getDiachi());
                    email.setText(personal.getEmail());
                    matkhau.setText(auth.getUid());
                    email.setEnabled(false);
                    matkhau.setEnabled(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}