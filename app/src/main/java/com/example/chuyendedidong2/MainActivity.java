package com.example.chuyendedidong2;

import static android.hardware.Camera.open;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


import com.example.chuyendedidong2.Data.GioHang;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
Button btnopen ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnopen = findViewById(R.id.btnopen);
        btnopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Activity_Gio_hang.class);
                startActivity(intent);
            }
        });

        }
        //button hủy đơn
//        Button open = findViewById(R.id.open);
//        open.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openhuy(Gravity.CENTER);
//            }
//        });

//    custum dialog hủy đơn
//
    public void openhuy(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_huydon);
        Window window = dialog.getWindow();
        if (window == null) {
                return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowatribute = window.getAttributes();
        windowatribute.gravity = gravity;
        window.setAttributes(windowatribute);
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(false);
        }
        else {
            dialog.setCancelable(true);
        }
        dialog.show();
    }



}