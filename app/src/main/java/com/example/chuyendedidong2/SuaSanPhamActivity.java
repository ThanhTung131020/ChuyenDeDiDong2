package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Fragment.TrangChuCuaHangFragment;
import com.example.chuyendedidong2.Model.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SuaSanPhamActivity extends AppCompatActivity {

    EditText ten, gia, chitiet;
    ImageView hinh;
    Button btnSua, btnXoa;
    FirebaseDatabase database;
    ProductModel product;
    DiaLogLoanding diaLogLoanding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham);
        database = FirebaseDatabase.getInstance();
        diaLogLoanding = new DiaLogLoanding(this);
        setControl();
        setEvent();
    }

    private void setControl() {
        ten = findViewById(R.id.edtSuaTenSP);
        gia = findViewById(R.id.edtSuaGiaSP);
        chitiet = findViewById(R.id.edtSuaChiTietSP);
        btnSua = findViewById(R.id.btnSuaSP);
        btnXoa = findViewById(R.id.btnXoaSP);
        hinh = findViewById(R.id.ivSuaHinhSP);
    }
    private void setEvent() {
        product = new ProductModel();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("suasanpham");
        String name = bundle.getString("sname");
        String id = bundle.getString("sid");
        String image = bundle.getString("simage");
        int price = bundle.getInt("sprice");
        float rating = bundle.getFloat("srating");
        String des = bundle.getString("sdes");
        String nameShop = bundle.getString("snameShop");
        int sl = bundle.getInt("ssl");
        Glide.with(getApplicationContext()).load(image).into(hinh);
        ten.setText(name);
        gia.setText(String.valueOf(price));
        chitiet.setText(des);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang cập nhật...");
                DatabaseReference root = database.getReference("product");
                product.setName(ten.getText().toString().trim());
                product.setPrice(Integer.parseInt(gia.getText().toString().trim()));
                product.setDesciption(chitiet.getText().toString().trim());
                root.child(id).updateChildren(product.toMap(),new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        diaLogLoanding.HideDialog();
                        Toast.makeText(SuaSanPhamActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SuaSanPhamActivity.this,HomePageCuaHangActivity.class));
                    }
                });
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang xóa sản phẩn...");
                DatabaseReference root = database.getReference("product");
                root.child(id).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        diaLogLoanding.HideDialog();
                        Toast.makeText(SuaSanPhamActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SuaSanPhamActivity.this,HomePageCuaHangActivity.class));
                    }
                });
            }
        });
    }
}