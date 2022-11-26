package com.example.chuyendedidong2.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

    FirebaseDatabase database;
    FirebaseAuth auth;
    EditText ten, diachi, sdt, email, matkhau;
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

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        getDatabase();
        setEvent();
        return view;
    }

    private void setEvent() {
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