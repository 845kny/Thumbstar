package com.example.thumbstar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Pemesanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        String nama = getIntent().getStringExtra("nama");
        String asal = getIntent().getStringExtra("asal");
        String spes = getIntent().getStringExtra("spes");

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Pemesanan.this,R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_pemesanan_drawer, findViewById(R.id.pemesanandrawer));

        TextView vnama = bottomSheetView.findViewById(R.id.vnama);
        TextView vspes = bottomSheetView.findViewById(R.id.vspes);
        TextView vasal = bottomSheetView.findViewById(R.id.vasal);
        vnama.setText(nama);
        vspes.setText(spes);
        vasal.setText(asal);
        bottomSheetView.findViewById(R.id.btnkonfirmasi).setOnClickListener(view1 -> {
            Toast toast = Toast.makeText(getApplicationContext(),"Anda Berhasil Memesan "+nama,Toast.LENGTH_SHORT);
            toast.show();
        });
        bottomSheetView.findViewById(R.id.btnbatal).setOnClickListener(view -> startActivity(new Intent(Pemesanan.this,Reservasi.class)));
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}