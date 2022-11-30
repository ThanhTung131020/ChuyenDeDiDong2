package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.chuyendedidong2.Adapter.ProductsAdapter;
import com.example.chuyendedidong2.Model.ProductModel;
import com.example.chuyendedidong2.Model.Shop;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChiTietDuyetSanPhamAdminActivity extends AppCompatActivity {

    private ImageView imageView, pic1, pic2, pic3;
    private Toolbar toolbar;
    private TextView tvTenSP,tvGiaSP,tvTenCuaHang,tvMoTa, tvIdSP, tvIdShop;
    private Button btnDuyet, btnXoa;
    private DialogOkActivity dialogOk;
    private DiaLogLoanding diaLogLoanding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ProductModel product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_duyet_san_pham_admin);
        setControl();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        diaLogLoanding = new DiaLogLoanding(this);
        dialogOk = new DialogOkActivity(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("sanpham_admin");
        String id = bundle.getString("id");
        String shop_id = bundle.getString("shop_id");
        String name = bundle.getString("name");
        String image = bundle.getString("image");
        String pic1 = bundle.getString("pic1");
        String pic2 = bundle.getString("pic2");
        String pic3 = bundle.getString("pic3");
        int price = bundle.getInt("price");
        String des = bundle.getString("des");
        String nameShop = bundle.getString("nameShop");

        Glide.with(getApplicationContext()).load(image).into(imageView);
        Glide.with(getApplicationContext()).load(pic1).into(this.pic1);
        Glide.with(getApplicationContext()).load(pic2).into(this.pic2);
        Glide.with(getApplicationContext()).load(pic3).into(this.pic3);
        tvTenSP.setText(name);
        tvGiaSP.setText(String.valueOf(price));
        tvMoTa.setText(des);
        tvTenCuaHang.setText(nameShop);
        tvIdSP.setText(id);
        tvIdShop.setText(shop_id);
        btnDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đag duyệt...");
                product = new ProductModel(id,image,0,name,price,des,shop_id,nameShop,pic1,pic2,pic3,false);
                DatabaseReference root = database.getReference("product");
                root.child(id).setValue(product, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        diaLogLoanding.HideDialog();
                        Toast.makeText(ChiTietDuyetSanPhamAdminActivity.this, "Duyệt thành công!", Toast.LENGTH_SHORT).show();
                        DatabaseReference sp = database.getReference("product_register");
                        sp.child(id).removeValue();
                        startActivity(new Intent(ChiTietDuyetSanPhamAdminActivity.this,TrangChuAdminActivity.class));
                        finishAffinity();
                    }
                });
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                androidx.appcompat.app.AlertDialog.Builder al = new AlertDialog.Builder(ChiTietDuyetSanPhamAdminActivity.this);
                al.setTitle("thông báo");
                al.setMessage("bạn có muốn xóa sản phẩm này không??");
                al.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference sp = database.getReference("product_register");
                        sp.child(id).removeValue();
                        startActivity(new Intent(ChiTietDuyetSanPhamAdminActivity.this,TrangChuAdminActivity.class));
                        finishAffinity();

                    }
                });
                al.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                al.show();

            }
        });


    }

    private void setControl() {
        toolbar = findViewById(R.id.toolbar);
        btnDuyet = findViewById(R.id.btnDuyetSP);
        btnXoa = findViewById(R.id.btnXoaSP);
        imageView = findViewById(R.id.ivProduct);
        pic1 = findViewById(R.id.ivPic1);
        pic2 = findViewById(R.id.ivPic2);
        pic3 = findViewById(R.id.ivPic3);
        tvTenSP = findViewById(R.id.tvTenSanPham);
        tvGiaSP = findViewById(R.id.tvGia);
        tvMoTa = findViewById(R.id.tvMoTa);
        tvTenCuaHang = findViewById(R.id.tvTenCuaHang);
        tvIdSP = findViewById(R.id.tvIdSanPham);
        tvIdShop = findViewById(R.id.tvIdCuaHang);
    }
}