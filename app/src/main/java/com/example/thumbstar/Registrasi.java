package com.example.thumbstar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registrasi extends AppCompatActivity {

    final Button btnsudahpunyaakun = findViewById(R.id.btnregistrasi);
    final Button btnregis = findViewById(R.id.button_register);
    final EditText edtnama =findViewById(R.id.edittextnama);
    final EditText edtnotelp =findViewById(R.id.edittextnotelp);
    final EditText edtalamat =findViewById(R.id.edittextalamat);
    final EditText edtemail =findViewById(R.id.edittextemail);
    final EditText edtpassword = findViewById(R.id.editextpassword);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        btnsudahpunyaakun.setOnClickListener(view -> startActivity(new Intent(Registrasi.this, Login.class)));
        btnregis.setOnClickListener(view -> {
            if (edtnama.getText().toString().isEmpty() || edtnotelp.getText().toString().isEmpty()|| edtalamat.getText().toString().isEmpty() || edtemail.getText().toString().isEmpty() || edtpassword.getText().toString().isEmpty()){
                Toast toast = Toast.makeText(getApplicationContext(),"Mohon Diisi Terlebih Dahulu",Toast.LENGTH_SHORT);
                toast.show();
            }else{
                cekEmail();
            }
        });
    }

    private void cekEmail(){
        if (!edtemail.getText().toString().contains("@gmail.com")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Email Hanya Menggunakan Gmail", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            cekPassword();
        }
    }

    private void cekPassword(){
        if (edtpassword.getText().toString().length()>=8){
            Intent intent = new Intent(Registrasi.this, SyaratKetentuan.class);
            intent.putExtra("nama",edtnama.getText().toString());
            intent.putExtra("notelp",edtnotelp.getText().toString());
            intent.putExtra("alamat",edtalamat.getText().toString());
            intent.putExtra("email",edtemail.getText().toString());
            intent.putExtra("password",edtpassword.getText().toString());
            startActivity(intent);
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Password Minimal 8 Karakter", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}