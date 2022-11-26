package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogOkActivity extends AppCompatActivity {

    Context context;
    Dialog dialog;

    public DialogOkActivity(Context context) {
        this.context = context;
    }
    public void ShowDiaLog(String title){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_dialog_ok);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView titleTV = dialog.findViewById(R.id.tvDiaLogOK);
        Button button = dialog.findViewById(R.id.btnDialogOK);

        titleTV.setText(title);
        dialog.create();
        dialog.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
