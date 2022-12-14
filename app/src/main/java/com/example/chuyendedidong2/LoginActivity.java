package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    CheckBox check_save;
    String thongtinluu = "tk_mk login";
    TextView tvQuenMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        diaLogLoanding = new DiaLogLoanding(this);
        // show hide password using eye icon
        ImageView imageViewshowhidepwd = findViewById(R.id.img_show_hide_pwd);
        imageViewshowhidepwd.setImageResource(R.mipmap.hide);
        imageViewshowhidepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //
                    imageViewshowhidepwd.setImageResource(R.mipmap.hide);
                }else {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewshowhidepwd.setImageResource(R.mipmap.show);
                }
            }
        });
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
        check_save = findViewById(R.id.check_save);
        tvQuenMK = findViewById(R.id.tvQuenMatKhau);
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
                diaLogLoanding.ShowDiaLog("??ang ????ng nh???p... ");
                if (email.getText().toString().equals("admin") && password.getText().toString().equals("1234567")){
                    startActivity(new Intent(LoginActivity.this,TrangChuAdminActivity.class));
                    finish();
                }
                signIn(view);
                //l??u th??ng tin ????ng nh???p
                SharedPreferences sharedPreferences = getSharedPreferences(thongtinluu,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // l??u theo d???ng ph??n r??
                editor.putString("UserName",email.getText().toString());
                editor.putString("Password",password.getText().toString());
                editor.putBoolean("Save",check_save.isChecked());
                editor.commit();
            }
        });
        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,QuenMatKhauActivity.class));
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
            this.email.setError("Nh???p email!");
            return;
        }else if(TextUtils.isEmpty(password)){
            diaLogLoanding.HideDialog();
            this.password.setError("Nh???p m???t kh???u!");
            return;
        }else if(password.length() < 6){
            diaLogLoanding.HideDialog();
            this.password.setError("M???t kh???u l???n h??n 6 k?? t???!");
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
                                    Toast.makeText(LoginActivity.this,"????ng nh???p th??nh c??ng",Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }else {
                                    diaLogLoanding.HideDialog();

                                    Toast.makeText(LoginActivity.this,"????ng nh???p th???t b???i",Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(LoginActivity.this,"????ng nh???p th??nh c??ng",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this,HomePageCuaHangActivity.class));
                                }else {
                                    diaLogLoanding.HideDialog();
                                    Toast.makeText(LoginActivity.this,"????ng nh???p th???t b???i",Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(LoginActivity.this, "????ng nh???p th??nh c??ng", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, TrangChuShipperActivity.class));
                                } else {
                                    diaLogLoanding.HideDialog();
                                    Toast.makeText(LoginActivity.this, "????ng nh???p th???t b???i", Toast.LENGTH_SHORT).show();
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
                    if (email.equals("admin") && password.equals("1234567")){
                        Toast.makeText(LoginActivity.this, "????ng nh???p admin th??nh c??ng", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this,"????ng nh???p th???t b???i "+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // hi???n th??ng tin ???? ???????c l??u
        SharedPreferences sharedPreferences = getSharedPreferences(thongtinluu,MODE_PRIVATE);
        String username = sharedPreferences.getString("UserName","");
        String password1 = sharedPreferences.getString("Password","");
        Boolean save = sharedPreferences.getBoolean("Save",false);
        if(save==true){
            email.setText(username);
            password.setText(password1);
        }

    }
}