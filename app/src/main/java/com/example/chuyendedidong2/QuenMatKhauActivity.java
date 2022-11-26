package com.example.chuyendedidong2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class QuenMatKhauActivity extends AppCompatActivity {

    EditText quenmk;
    Button gui;
    FirebaseAuth auth;
    DialogOkActivity dialogOk;
    DiaLogLoanding diaLogLoanding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        quenmk = findViewById(R.id.edtEmail);
        gui = findViewById(R.id.btnGui);
        auth = FirebaseAuth.getInstance();
        dialogOk = new DialogOkActivity(this);
        diaLogLoanding = new DiaLogLoanding(this);
        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLogLoanding.ShowDiaLog("Đang gửi...");
                quenmk();
            }
        });
    }

    private void quenmk() {
        String email = quenmk.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            quenmk.setError("Nhập email!");
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                diaLogLoanding.HideDialog();
                dialogOk.ShowDiaLog("Gửi thành công! Vui lòng check email");
                quenmk.setText(null);
            }
        });
    }
}