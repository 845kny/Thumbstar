package com.example.thumbstar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Timer;
import java.util.TimerTask;

public class SyaratKetentuan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syaratketentuan);
        CheckBox setuju = findViewById(R.id.cbsyarat);
        Button fixSetuju = findViewById(R.id.btnmasuk);

        String nama = getIntent().getStringExtra("nama");
        String telp = getIntent().getStringExtra("notelp");
        String alamat = getIntent().getStringExtra("alamat");
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String roles = getIntent().getStringExtra("roles");
        setuju.setChecked(false);
        fixSetuju.setEnabled(false);
        setuju.setOnClickListener(view -> {
            if (setuju.isChecked()){
                fixSetuju.setEnabled(true);
            }else if (!setuju.isChecked()){
                fixSetuju.setEnabled(false);
            }
        });
        fixSetuju.setOnClickListener(view -> {
            adddata(email,password,nama,telp,alamat,roles);
        });
    }

    private void adddata(String addemail, String addpassword, String addnama, String addtelp, String addalamat, String roles){
        usermodels akun = new usermodels();
        akun.setEmail(addemail);
        akun.setPassword(addpassword);
        akun.setNama(addnama);
        akun.setNotelp(addtelp);
        akun.setAlamat(addalamat);
        akun.setRoles(roles);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(roles).child(addtelp);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(akun);
                Toast toast = Toast.makeText(getApplicationContext(),"Telah Berhasil Daftar",Toast.LENGTH_SHORT);
                toast.show();
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SyaratKetentuan.this, Login.class));
                    }
                };
                timer.schedule(timerTask,2000);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(getApplicationContext(),"Database Error",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}