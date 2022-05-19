package com.example.thumbstar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Reservasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasi);

        TextView nama1 = findViewById(R.id.nama1);
        nama1.setOnClickListener(view -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                Reservasi.this,R.style.BottomSheetDialogTheme
            );
            View bottomSheetView = LayoutInflater.from(getApplicationContext())
                    .inflate(
                            R.layout.activity_reservasi_drawer,
                            findViewById(R.id.reservasidrawer)
                    );
            bottomSheetView.findViewById(R.id.btnpesan).setOnClickListener(view1 -> {
                Toast toast = Toast.makeText(getApplicationContext(),"anda memesan ini",Toast.LENGTH_SHORT);
                toast.show();
            });
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
    }
}