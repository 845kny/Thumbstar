package com.example.thumbstar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText telp = findViewById(R.id.EDTTelp);
        EditText password = findViewById(R.id.EDTPassword);

        Button registrasi = findViewById(R.id.btnregistrasi);
        Button login = findViewById(R.id.btnlogin);

        registrasi.setOnClickListener(view -> regis());

        login.setOnClickListener(view ->{
            if (telp.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                Toast toasti = Toast.makeText(getApplicationContext(),"Harus diisi terlebih dahulu",Toast.LENGTH_SHORT);
                toasti.show();
            }else{
                cekdata(telp.getText().toString(),password.getText().toString());
            }
        });
    }

    private void cekdata(String t,String p){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(t).child("password");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String dbpass =snapshot.getValue(String.class);
                if (p.equals(dbpass)){
                    kirimbiodata(t);
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(),"password salah",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(getApplicationContext(),"Database Error "+error,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void kirimbiodata(String t){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(t).child("nama");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String nama =snapshot.getValue(String.class);
                Intent intent = new Intent(Login.this,Dashboard.class);
                intent.putExtra("nama",nama);
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(),"Berhasil Login",Toast.LENGTH_SHORT);
                toast.show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(getApplicationContext(),"Database Error "+error,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void regis(){
        Intent intent = new Intent(Login.this,Registrasi.class);
        startActivity(intent);
    }
}