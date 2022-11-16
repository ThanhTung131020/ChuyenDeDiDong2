package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chuyendedidong2.Adapter.Adapter_thongtin_donhang;
import com.example.chuyendedidong2.Model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class Activity_ThongTin_DonHang extends AppCompatActivity {
    private static List<ProductModel> list = new ArrayList<>();
    private ProductModel productModel = new ProductModel();
    private RecyclerView rcv_TTDH;
    private Adapter_thongtin_donhang adapter_thongtin_donhang;
    private ProductModel product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_don_hang);
        adapterTTDH();
    }

    private void adapterTTDH() {
        productModel = new ProductModel();
        rcv_TTDH = findViewById(R.id.rcv_TTDH);
        adapter_thongtin_donhang = new Adapter_thongtin_donhang(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_TTDH.setLayoutManager(linearLayoutManager);
        adapter_thongtin_donhang.setData(getListGH());
        rcv_TTDH.setAdapter(adapter_thongtin_donhang);
    }



    private List<ProductModel> getListGH() {
        Bundle bundle = getIntent().getExtras();
        ProductModel prd = (ProductModel) bundle.get("object_products");
        list.add(new ProductModel("sp01","https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",500000,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123","","",""));
        list.add(new ProductModel("sp02","https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",5000000,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123","","",""));
        list.add(new ProductModel("sp03","https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",5000000,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123","","",""));
        list.add(new ProductModel("sp04","https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",5000000,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123","","",""));
        list.add(new ProductModel("sp05","https://th.bing.com/th/id/R.96e8ffe8f607a9f0dc2b92f3b6171e02?rik=2%2fV2Qy1ABcP2bQ&riu=http%3a%2f%2f2.bp.blogspot.com%2f_riY6CYFfwgY%2fTK6shtzHj4I%2fAAAAAAAAAFw%2fgVMDAljK2wU%2fs1600%2fMay-tinh-de-ban-01.jpg&ehk=P9B1e30r9k%2bdVX%2boCe8q3ZkHXapQuavB627ihBoXPpM%3d&risl=&pid=ImgRaw&r=0",0,"máy tính",5000000,"desc1233","Bx4MGBrJrpYa0nF9pPE5SiMPs7C2","shop123","","",""));
        return list;
    }

}