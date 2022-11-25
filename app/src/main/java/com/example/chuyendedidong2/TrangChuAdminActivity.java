package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangChuAdminActivity extends AppCompatActivity {

    private Button dangkycuahang;
    private DialogOkActivity dialogOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_admin);
        dangkycuahang = findViewById(R.id.btnDKShop);
        dialogOk = new DialogOkActivity(this);
        setEvent();
    }

    private void setEvent() {
        dangkycuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrangChuAdminActivity.this,DangKyCuaHangActivity.class));
            }
        });
    }
    public void showOKDKShop(){
        dialogOk.ShowDiaLog("Duyệt thành công!");
    }
}