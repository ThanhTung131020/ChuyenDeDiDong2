package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chuyendedidong2.Adapter.QuanLySanPhamAdapter;
import com.example.chuyendedidong2.Model.Sanpham;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Activity_allproduct extends AppCompatActivity {

    private ArrayList<Sanpham> listSP;
    private QuanLySanPhamAdapter quanLySanPhamAdapter;
    private RecyclerView rvThonTinSanPham;
    private ImageView imvSanPham;
    private TextView tvgiasp, tvspdaban;
    private CheckBox chbsanPham;
    private EditText edtTongSP;
    private Button btnXoa, btnQlySP;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allproduct);
        setControl();
        setEvent();

    }

    private void setControl() {

        rvThonTinSanPham = findViewById(R.id.rcv_ThongTinSanPham);
        tvgiasp = findViewById(R.id.tvgiasp);
        tvspdaban = findViewById(R.id.tvspDaBan);
        edtTongSP = findViewById(R.id.edtTongSP);
        btnXoa = findViewById(R.id.btnXoa);
        btnQlySP = findViewById(R.id.btnQLSP);


    }

    private void setEvent() {
        taoDanhSachSP();
        quanLySanPhamAdapter = new QuanLySanPhamAdapter(Activity_allproduct.this, R.layout.item_products, listSP);
        rvThonTinSanPham.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        rvThonTinSanPham.setAdapter(quanLySanPhamAdapter);
        quanLySanPhamAdapter = new QuanLySanPhamAdapter(this, R.layout.item_products,listSP);

        //btn xoa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSP.remove(imvSanPham);
                quanLySanPhamAdapter.notifyDataSetChanged();
            }
        });


    }
    public void taoDanhSachSP(){
        listSP = new ArrayList<>();
        listSP.add(new Sanpham("https://imgs.search.brave.com/MmZIvEXmbt0M23wOJZKq9bjAwYfJFhhiXKtCqzdSAS8/rs:fit:1000:648:1/g:ce/aHR0cHM6Ly9jZG4u/dmluYXRhaS5tb2Jp/LzIwMjAvMDEvMTU0/NTk4MzM1NjE2OV8y/ODYwNTU4LmpwZw",3000000,6));
        listSP.add(new Sanpham("https://imgs.search.brave.com/MmZIvEXmbt0M23wOJZKq9bjAwYfJFhhiXKtCqzdSAS8/rs:fit:1000:648:1/g:ce/aHR0cHM6Ly9jZG4u/dmluYXRhaS5tb2Jp/LzIwMjAvMDEvMTU0/NTk4MzM1NjE2OV8y/ODYwNTU4LmpwZw",3000000,6));

    }
    public void tongSanPham() {
        edtTongSP = findViewById(R.id.edtTongSP);
        int tong = 0;
        for (int i = 0; i < listSP.size(); i++) {
            tong += listSP.get(i).getSoLuong();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###");
        edtTongSP.setText("Tổng sản phẩm: " + decimalFormat.format(tong) + "sản phẩm");
    }
}