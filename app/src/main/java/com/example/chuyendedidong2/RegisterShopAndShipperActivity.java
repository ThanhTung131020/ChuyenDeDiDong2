package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chuyendedidong2.Model.Personal;
import com.example.chuyendedidong2.Model.Shipper;
import com.example.chuyendedidong2.Model.Shop;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterShopAndShipperActivity extends AppCompatActivity {
    EditText email, sdt, diachi, hoten;
    Button btnDangKy;
    RadioButton rdoShop, rdoShipper;
    Shop shop;
    Shipper shipper;
    DiaLogLoanding diaLogLoanding;
    DialogOkActivity dialogOk;
    private FirebaseAuth auth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shop_and_shipper);
        setControl();
        database = FirebaseDatabase.getInstance();
        dialogOk = new DialogOkActivity(this);
        diaLogLoanding = new DiaLogLoanding(this);
        setEvent();
    }

    private void setEvent() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang đăng ký!");
                dangKy();
            }
        });
    }

    private void dangKy() {
        String email = this.email.getText().toString();
        String sdt = this.sdt.getText().toString();
        String hoten = this.hoten.getText().toString();
        String diachi = this.diachi.getText().toString();

        if (TextUtils.isEmpty(email)){
            diaLogLoanding.HideDialog();
            this.email.setError("Nhập email!");
            return;
        }else if(TextUtils.isEmpty(sdt)){
            diaLogLoanding.HideDialog();
            this.sdt.setError("Nhập SĐT!");
            return;
        }else if(TextUtils.isEmpty(hoten)){
            diaLogLoanding.HideDialog();
            this.hoten.setError("Nhập Họ Tên!");
            return;
        }else if(TextUtils.isEmpty(diachi)){
            diaLogLoanding.HideDialog();
            this.diachi.setError("Nhập địa chỉ!");
            return;
        }
        if (rdoShop.isChecked()){
            DatabaseReference root = database.getReference("shop_register");
            String pathID = "SHOP"+root.push().getKey();
            shop = new Shop(pathID,hoten, sdt, diachi, email);
            root.child(pathID).setValue(shop, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    diaLogLoanding.HideDialog();
                    dialogOk.ShowDiaLog("ĐĂNG KÝ THÀNH CÔNG, VUI LÒNG ĐỢI EMAIL CỦA CHÚNG TÔI");
                    clearText();
                }
            });
        }else if(rdoShipper.isChecked()){

        }
    }

    private void registerShopDataBase(Shop shop) {

    }
    public void clearText(){
        email.setText(null);
        sdt.setText(null);
        diachi.setText(null);
        hoten.setText(null);
    }

    private void setControl() {
        email = findViewById(R.id.edtEmail);
        sdt = findViewById(R.id.edtSDT);
        hoten = findViewById(R.id.edtHoTen);
        diachi = findViewById(R.id.edtDiaChi);
        btnDangKy = findViewById(R.id.btnDangky);
        rdoShop = findViewById(R.id.rdbShop);
        rdoShipper = findViewById(R.id.rdbShipper);
    }

}