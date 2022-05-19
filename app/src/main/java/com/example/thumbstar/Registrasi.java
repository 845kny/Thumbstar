package com.example.thumbstar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registrasi extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        Button btnsudahpunyaakun = findViewById(R.id.btnregistrasi);
        Button btnregis = findViewById(R.id.button_register);
        final EditText edtnama =findViewById(R.id.edittextnama);
        final EditText edtnotelp =findViewById(R.id.edittextnotelp);
        final EditText edtalamat =findViewById(R.id.edittextalamat);
        final EditText edtemail =findViewById(R.id.edittextemail);
        final EditText edtpassword = findViewById(R.id.editextpassword);
        btnsudahpunyaakun.setOnClickListener(view -> startActivity(new Intent(Registrasi.this, Login.class)));
        btnregis.setOnClickListener(view -> {
            if (edtnama.getText().toString().isEmpty() || edtnotelp.getText().toString().isEmpty()|| edtalamat.getText().toString().isEmpty() || edtemail.getText().toString().isEmpty() || edtpassword.getText().toString().isEmpty()){
                Toast toast = Toast.makeText(getApplicationContext(),"Mohon Diisi Terlebih Dahulu",Toast.LENGTH_SHORT);
                toast.show();
            }else if (!edtemail.getText().toString().contains("@gmail.com")){
                Toast toast = Toast.makeText(getApplicationContext(),"Email Hanya Menggunakan Gmail",Toast.LENGTH_SHORT);
                toast.show();
            }else{
                Intent intent = new Intent(Registrasi.this, SyaratKetentuan.class);
                intent.putExtra("nama",edtnama.getText().toString());
                intent.putExtra("notelp",edtnotelp.getText().toString());
                intent.putExtra("alamat",edtalamat.getText().toString());
                intent.putExtra("email",edtemail.getText().toString());
                intent.putExtra("password",edtpassword.getText().toString());
                startActivity(intent);
            }
        });
    }
}