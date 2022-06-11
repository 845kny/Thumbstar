package com.example.thumbstar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.util.List;

public class Pemesanan extends AppCompatActivity {
    private Location location;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int PERMISSION_REQUEST_CODE=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getcurrent();

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

    private void getcurrent(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Pemesanan.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null){
                Address address = null;
                Geocoder geocoder = new Geocoder(Pemesanan.this);
                try {
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    address = addresses.get(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast toast = Toast.makeText(getApplicationContext()," "+address.getLatitude()+" "+address.getLongitude() +" "+address.getCountryName()+" "+address.getAddressLine(0)+" "+address.getLocality() ,Toast.LENGTH_LONG);
                toast.show();
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
                mapFragment.getMapAsync((OnMapReadyCallback) Pemesanan.this);
            }
        });
    }
}