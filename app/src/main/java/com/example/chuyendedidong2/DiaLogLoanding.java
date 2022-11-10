package com.example.chuyendedidong2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

public class DiaLogLoanding extends AppCompatActivity {

    Fragment fragment;
    Context context;
    Dialog dialog;

    public DiaLogLoanding(Context context) {
        this.context = context;
    }
    public void ShowDiaLog(String title){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_dia_log_loanding);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView titleTV = dialog.findViewById(R.id.tvLoading);
        titleTV.setText(title);
        dialog.create();
        dialog.show();
    }
    public void HideDialog(){
        dialog.dismiss();
    }

}