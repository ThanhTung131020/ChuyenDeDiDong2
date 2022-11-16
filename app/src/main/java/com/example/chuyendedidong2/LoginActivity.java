package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button btnDangNhap;
    Button btnDangKy;
    RadioButton rdbCaNhan, rdbCuaHang, rdbShipper;
    RadioGroup rdo;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DiaLogLoanding diaLogLoanding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        diaLogLoanding = new DiaLogLoanding(this);
        setControl();
        setEvent();
    }

    private void setControl() {
        email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        rdbCaNhan = findViewById(R.id.rdbCaNhanLogin);
        rdbCuaHang = findViewById(R.id.rdbCuaHangLogin);
        rdbShipper = findViewById(R.id.rdbShipperLogin);
        rdo = findViewById(R.id.radioGroupLogin);
    }
    private void setEvent() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(view);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang đăng nhập... ");
                signIn(view);
            }
        });

    }

    public void signUp(View view){
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }
    public void signIn(View view){
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        if (TextUtils.isEmpty(email)){
            diaLogLoanding.HideDialog();
            this.email.setError("Nhập email!");
            return;
        }else if(TextUtils.isEmpty(password)){
            diaLogLoanding.HideDialog();
            this.password.setError("Nhập mật khẩu!");
            return;
        }else if(password.length() < 6){
            diaLogLoanding.HideDialog();
            this.password.setError("Mật khẩu lớn hơn 6 ký tự!");
            return;
        }
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if (rdbCaNhan.isChecked()){
                        DatabaseReference rootCaNhan = database.getReference("personal");
                        rootCaNhan.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(auth.getUid())){
                                    diaLogLoanding.HideDialog();
                                    Intent intent = new Intent(LoginActivity.this,HomePageLoginActivity.class);
                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }else {
                                    diaLogLoanding.HideDialog();
                                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }else if (rdbCuaHang.isChecked()){
                        DatabaseReference rootCaNhan = database.getReference("shop");
                        rootCaNhan.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(auth.getUid())){
                                    diaLogLoanding.HideDialog();
                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this,HomePageCuaHangActivity.class));
                                }else {
                                    diaLogLoanding.HideDialog();
                                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else if (rdbShipper.isChecked()) {
                        DatabaseReference rootCaNhan = database.getReference("shipper");
                        rootCaNhan.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(auth.getUid())) {
                                    diaLogLoanding.HideDialog();
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, TrangChuShipperActivity.class));
                                } else {
                                    diaLogLoanding.HideDialog();
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
                else{
                    diaLogLoanding.HideDialog();
                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại"+task.getException(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}