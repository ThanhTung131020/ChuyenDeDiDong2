package com.example.chuyendedidong2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    TextView tvLink;
    Personal personal;
    DiaLogLoanding diaLogLoanding;
    DialogOkActivity dialogOk;
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
        dialogOk = new DialogOkActivity(this);

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
        tvLink = findViewById(R.id.tvLinkShopandShipper);
    }

    private void setEvent() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("??ang t???o t??i kho???n...");
                signUp(view);
            }
        });
        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,RegisterShopAndShipperActivity.class));
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
            diaLogLoanding.HideDialog();
            this.email.setError("Nh???p email!");
            return;
        }else if(TextUtils.isEmpty(password)){
            diaLogLoanding.HideDialog();
            this.password.setError("Nh???p password!");
            return;
        }else if(TextUtils.isEmpty(cpassword)){
            diaLogLoanding.HideDialog();
            this.cpassword.setError("Nh???p l???i password!");
            return;
        }else if(password.length() < 6){
            diaLogLoanding.HideDialog();
            this.password.setError("Password kh??ng d?????i 6 k?? t???!");
            return;
        }else if(!password.equals(cpassword)){
            diaLogLoanding.HideDialog();
            this.cpassword.setError("Password kh??ng gi???ng nhau!");
            return;
        }else if(TextUtils.isEmpty(sdt)){
            diaLogLoanding.HideDialog();
            this.sdt.setError("Nh???p S??T!");
            return;
        }else if(TextUtils.isEmpty(hoten)){
            diaLogLoanding.HideDialog();
            this.hoten.setError("Nh???p H??? T??n!");
            return;
        }else if(TextUtils.isEmpty(diachi)){
            diaLogLoanding.HideDialog();
            this.diachi.setError("Nh???p ?????a ch???!");
            return;
        }

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    personal = new Personal(auth.getUid(),hoten,sdt,diachi,email);
                    addPersonalOnDataBase(personal);
                }else {
                    diaLogLoanding.HideDialog();
                    dialogOk.ShowDiaLog("????ng k?? th???t b???i");
                }
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
                Toast.makeText(RegistrationActivity.this,"????ng k?? th??nh c??ng",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
    public void signIn(View view){
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }
}