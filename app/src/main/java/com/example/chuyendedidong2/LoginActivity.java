package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button btnDangNhap;
    Button btnDangKy;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        setControl();
        setEvent();
    }

    private void setControl() {
        email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
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
            Toast.makeText(this,"Nhập email!",Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Nhập mật khẩu!",Toast.LENGTH_SHORT).show();
            return;
        }else if(password.length() < 6){
            Toast.makeText(this,"Mật khẩu lớn hơn 6 ký tự!",Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this,"Đăng nhập thất bại"+task.getException(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}