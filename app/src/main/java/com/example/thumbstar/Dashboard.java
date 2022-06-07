package com.example.thumbstar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ImageView reservasi = findViewById(R.id.reservasi);
        final String nama = getIntent().getStringExtra("nama") ;
        ImageView daftarterapis = findViewById(R.id.terapis);
        ImageView cs = findViewById(R.id.cs);
        TextView name = findViewById(R.id.nameview);
        name.setText(nama);
        daftarterapis.setOnClickListener(view -> startActivity(new Intent(Dashboard.this, DaftarTerapis.class)));
        reservasi.setOnClickListener(view -> startActivity(new Intent(Dashboard.this,Reservasi.class)));
        cs.setOnClickListener(view -> startActivity(new Intent(Dashboard.this,Customerservice.class)));
    }
}