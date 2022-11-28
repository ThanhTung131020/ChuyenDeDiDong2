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
import android.widget.TextView;
import android.widget.Toast;

import com.example.chuyendedidong2.Model.Shipper;
import com.example.chuyendedidong2.Model.Shop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChiTietDangKyShipperActivity extends AppCompatActivity {

    TrangChuAdminActivity trangChuAdmin;
    TextView email, sdt, diachi, ten;
    EditText pass;
    Button dangky, huy;
    FirebaseDatabase database;
    FirebaseAuth auth;
    DialogOkActivity dialogOkActivity;
    DiaLogLoanding diaLogLoanding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dang_ky_shipper);
        setControl();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        dialogOkActivity = new DialogOkActivity(this);
        diaLogLoanding = new DiaLogLoanding(this);
        setEvent();
    }

    private void setEvent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("dangky_shipper");
        String id_shop = bundle.getString("id");
        String email_shop = bundle.getString("email");
        String sdt_shop = bundle.getString("sdt");
        String diachi_shop = bundle.getString("diachi");
        String ten_shop = bundle.getString("ten");

        email.setText("Email cửa hàng: " + email_shop);
        sdt.setText("SDT cửa hàng: " + sdt_shop);
        diachi.setText("Địa chỉ cửa hàng: " + diachi_shop);
        ten.setText("Tên cửa hàng: " + ten_shop);

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang duyệt...");
                String mk = pass.getText().toString().trim();
                if (TextUtils.isEmpty(mk)){
                    diaLogLoanding.HideDialog();
                    pass.setError("Nhập mật khẩu!");
                    return;
                }else if(mk.length() < 6){
                    diaLogLoanding.HideDialog();
                    pass.setError("Mật khẩu không dưới 6 ký tự!");
                    return;
                }
                auth.createUserWithEmailAndPassword(email_shop,mk).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Shipper shipper = new Shipper(auth.getUid(), ten_shop, sdt_shop, diachi_shop, email_shop);
                            DatabaseReference root = database.getReference("shipper");
                            root.child(auth.getUid()).setValue(shipper, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                    DatabaseReference old = database.getReference("shipper_register").child(id_shop);
                                    old.removeValue();
                                    Toast.makeText(ChiTietDangKyShipperActivity.this, "Duyệt thành công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ChiTietDangKyShipperActivity.this, TrangChuAdminActivity.class));
                                    finishAffinity();
                                }
                            });
                        }else {
                            dialogOkActivity.ShowDiaLog("Duyệt thất bại");
                        }
                    }
                });

            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang hủy...");
                DatabaseReference root = database.getReference("shipper_register").child(id_shop);
                root.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        diaLogLoanding.HideDialog();
                        Toast.makeText(ChiTietDangKyShipperActivity.this, "Huỷ thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ChiTietDangKyShipperActivity.this, TrangChuAdminActivity.class));
                        finishAffinity();
                    }
                });
            }
        });
    }

    private void setControl() {
        email = findViewById(R.id.tv_dangky_shop_email);
        sdt = findViewById(R.id.tv_dangky_shop_sdt);
        diachi = findViewById(R.id.tv_dangky_shop_diachi);
        ten = findViewById(R.id.tv_dangky_shop_tenhshop);
        pass = findViewById(R.id.edt_taomk_shop);
        dangky = findViewById(R.id.btn_dangky_shop);
        huy = findViewById(R.id.btn_huy_dangky_shop);
    }
}