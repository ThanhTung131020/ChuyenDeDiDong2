package com.example.chuyendedidong2.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.chuyendedidong2.Model.Personal;
import com.example.chuyendedidong2.Model.Shop;
import com.example.chuyendedidong2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ThontincuahangFragment extends Fragment {

    FirebaseDatabase database;
    FirebaseAuth auth;
    EditText ten, diachi, spdaban, spdangco, masothue;

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
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        getDatabase();

        return view;
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
}