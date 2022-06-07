package com.example.thumbstar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Reservasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasi);

        TextView v_nama1 = findViewById(R.id.nama1);
        TextView v_nama2 = findViewById(R.id.nama2);
        TextView v_nama3 = findViewById(R.id.nama3);
        TextView v_asal1 = findViewById(R.id.asal1);
        TextView v_asal2 = findViewById(R.id.asal2);
        TextView v_asal3 = findViewById(R.id.asal3);
        TextView v_spes1 = findViewById(R.id.spesialis1);
        TextView v_spes2 = findViewById(R.id.spesialis2);
        TextView v_spes3 = findViewById(R.id.spesialis3);

        String nama1 = v_nama1.getText().toString();
        String nama2 = v_nama2.getText().toString();
        String nama3 = v_nama3.getText().toString();
        String asal1 = v_asal1.getText().toString();
        String asal2 = v_asal2.getText().toString();
        String asal3 = v_asal3.getText().toString();
        String spes1 = v_spes1.getText().toString();
        String spes2 = v_spes2.getText().toString();
        String spes3 = v_spes3.getText().toString();

        v_nama1.setOnClickListener(view -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Reservasi.this,R.style.BottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_reservasi_drawer,findViewById(R.id.reservasidrawer));
            TextView vnama = bottomSheetView.findViewById(R.id.vnama);
            TextView vspes = bottomSheetView.findViewById(R.id.vspes);
            TextView vasal = bottomSheetView.findViewById(R.id.vasal);
            vnama.setText(nama1);
            vspes.setText(spes1);
            vasal.setText(asal1);
            bottomSheetView.findViewById(R.id.btnpesan).setOnClickListener(view1 -> kirimdata(nama1,asal1,spes1));
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
        v_nama2.setOnClickListener(view -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Reservasi.this,R.style.BottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_reservasi_drawer, findViewById(R.id.reservasidrawer));

            TextView vnama = bottomSheetView.findViewById(R.id.vnama);
            TextView vspes = bottomSheetView.findViewById(R.id.vspes);
            TextView vasal = bottomSheetView.findViewById(R.id.vasal);
            vnama.setText(nama2);
            vspes.setText(spes2);
            vasal.setText(asal2);

            bottomSheetView.findViewById(R.id.btnpesan).setOnClickListener(view1 -> kirimdata(nama2,asal2,spes2));
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
        v_nama3.setOnClickListener(view -> {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Reservasi.this,R.style.BottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_reservasi_drawer, findViewById(R.id.reservasidrawer));

            TextView vnama = bottomSheetView.findViewById(R.id.vnama);
            TextView vspes = bottomSheetView.findViewById(R.id.vspes);
            TextView vasal = bottomSheetView.findViewById(R.id.vasal);
            vnama.setText(nama3);
            vspes.setText(spes3);
            vasal.setText(asal3);

            bottomSheetView.findViewById(R.id.btnpesan).setOnClickListener(view1 -> kirimdata(nama3,asal3,spes3));
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
    }

    private void kirimdata(String n, String a, String s){
        Intent intent = new Intent(Reservasi.this,Pemesanan.class);
        intent.putExtra("nama",n);
        intent.putExtra("spes",s);
        intent.putExtra("asal",a);
        startActivity(intent);
    }
}