package com.example.chuyendedidong2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chuyendedidong2.Model.Personal;
import com.example.chuyendedidong2.Model.Shipper;
import com.example.chuyendedidong2.Model.Shop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    EditText email, password, cpassword, sdt, diachi, hoten;
    Button btnDangKy;
    RadioButton rdbCaNhan, rdbCuaHang, rdbShipper;
    RadioGroup rdo;
    Personal personal;
    Shop shop;
    Shipper shipper;
    DiaLogLoanding diaLogLoanding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        diaLogLoanding = new DiaLogLoanding(this);

        //anh xa view
        setControl();
        //
        setEvent();

    }

    private void setControl() {
        email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtMatKhau);
        cpassword = findViewById(R.id.edtCMatKhau);
        sdt = findViewById(R.id.edtSDT);
        hoten = findViewById(R.id.edtHoTen);
        diachi = findViewById(R.id.edtDiaChi);
        btnDangKy = findViewById(R.id.btnDangky);
        rdbCaNhan = findViewById(R.id.rdbCaNhan);
        rdbCuaHang = findViewById(R.id.rdbCuaHang);
        rdbShipper = findViewById(R.id.rdbShipper);
        rdo = findViewById(R.id.radioGroup);
    }

    private void setEvent() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang tạo tài khoản...");
                signUp(view);
            }
        });
    }
    public void signUp(View view){

        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        String cpassword = this.cpassword.getText().toString();
        String sdt = this.sdt.getText().toString();
        String hoten = this.hoten.getText().toString();
        String diachi = this.diachi.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Nhập email!",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Nhập mật khẩu!",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(cpassword)){
            Toast.makeText(this,"Nhập mật khẩu!",Toast.LENGTH_SHORT).show();
            return;
        }else if(password.length() < 6){
            Toast.makeText(this,"Mật khẩu lớn hơn 6 ký tự!",Toast.LENGTH_SHORT).show();
            return;
        }else if(!password.equals(cpassword)){
            Toast.makeText(this,"Nhập lại mật khẩu không giống nhau!",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(sdt)){
            Toast.makeText(this,"Nhập số điện thoại!",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(hoten)){
            Toast.makeText(this,"Nhập họ tên!",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(diachi)){
            Toast.makeText(this,"Nhập địa chỉ!",Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if (rdbCaNhan.isChecked()){
                        personal = new Personal(auth.getUid(),hoten,sdt,diachi,email);
                        addPersonalOnDataBase(personal);
                    }else if(rdbCuaHang.isChecked()){
                        shop = new Shop(auth.getUid(),hoten,sdt,diachi,email);
                        addShopOnDataBase(shop);
                    }else if(rdbShipper.isChecked()){
                        shipper = new Shipper(auth.getUid(),hoten,sdt,diachi,email);
                        addShipperOnDataBase(shipper);
                    }
                }else {
                    diaLogLoanding.HideDialog();
                    Toast.makeText(RegistrationActivity.this,"Đăng ký thất bại"+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addShipperOnDataBase(Shipper shipper) {
        DatabaseReference root = database.getReference("shipper");
        String pathID = auth.getUid();
        root.child(pathID).setValue(shipper, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                diaLogLoanding.HideDialog();
                Toast.makeText(RegistrationActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                finish();
            }
        });
    }

    private void addShopOnDataBase(Shop shop) {
        DatabaseReference root = database.getReference("shop");
        String pathID = auth.getUid();
        root.child(pathID).setValue(shop, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                diaLogLoanding.HideDialog();
                Toast.makeText(RegistrationActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
    public  void addPersonalOnDataBase(Personal personal){
        DatabaseReference root = database.getReference("personal");
        String pathID = auth.getUid();
        root.child(pathID).setValue(personal, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                diaLogLoanding.HideDialog();
                Toast.makeText(RegistrationActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
    public void signIn(View view){
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }
}