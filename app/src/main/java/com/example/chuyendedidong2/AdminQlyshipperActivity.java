package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AdminQlyshipperActivity extends AppCompatActivity {
private ImageButton imgBt_back_to_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_qlyshipper);
        setControl();
        imgBt_back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminQlyshipperActivity.this  , AdminActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setControl() {
        imgBt_back_to_home = findViewById(R.id.imgBt_back_to_home);
    }
}