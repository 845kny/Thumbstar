package com.example.thumbstar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registrasi extends AppCompatActivity {
    String roles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        Button btnsudahpunyaakun = findViewById(R.id.btnregistrasi);
        Button btnregis = findViewById(R.id.button_register);
        final EditText edtnama =findViewById(R.id.edittextnama);
        final EditText edtnotelp =findViewById(R.id.edittextnotelp);
        final EditText edtalamat =findViewById(R.id.edittextalamat);
        final EditText edtpassword = findViewById(R.id.editextpassword);
        final EditText edtemail =findViewById(R.id.edittextemail);
        final CheckBox roleuser = findViewById(R.id.roleuser);
        final CheckBox roleterapis = findViewById(R.id.roleterapis);
        roleuser.setChecked(false);
        roleterapis.setChecked(false);
        btnsudahpunyaakun.setOnClickListener(view -> startActivity(new Intent(Registrasi.this, Login.class)));
        btnregis.setOnClickListener(view -> {
            if (edtnama.getText().toString().isEmpty() || edtnotelp.getText().toString().isEmpty()|| edtalamat.getText().toString().isEmpty() || edtemail.getText().toString().isEmpty() || edtpassword.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Mohon Diisi Terlebih Dahulu",Toast.LENGTH_SHORT).show();
            }else if (!roleuser.isChecked() && roleterapis.isChecked()){
                cekEmail();
            }else if (roleuser.isChecked() && !roleterapis.isChecked()){
                cekEmail();
            }else {
                Toast.makeText(getApplicationContext(),"Mohon, Pilih Role",Toast.LENGTH_SHORT).show();
            }
        });
        roleuser.setOnClickListener(view -> {
            if (roleterapis.isChecked()){
                roleterapis.setChecked(false);
            }
            roles="user";
        });
        roleterapis.setOnClickListener(view -> {
            if (roleuser.isChecked()){
                roleuser.setChecked(false);
            }
            roles="terapis";
        });
    }

    private void cekEmail(){
        final EditText edtemail =findViewById(R.id.edittextemail);
        if (!edtemail.getText().toString().contains("@gmail.com")){
            Toast toast = Toast.makeText(getApplicationContext(), "Email Hanya Menggunakan Gmail", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            cekNomor();
        }
    }

    private void cekNomor(){
        final EditText edtnotelp =findViewById(R.id.edittextnotelp);
        if (edtnotelp.getText().toString().length()>=10 && edtnotelp.getText().toString().length()<=14){
            cekValid();
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Nomor Minimal 10 Karakter", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void cekValid(){
        final EditText edtnotelp =findViewById(R.id.edittextnotelp);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(roles);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (snapshot.hasChild(edtnotelp.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Maaf, Nomor Telah Terdaftar",Toast.LENGTH_SHORT).show();
                    }else {
                        cekPassword();
                    }
                }catch (Exception ignore){
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(getApplicationContext(),"Database Error "+error,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void cekPassword(){
        final EditText edtemail =findViewById(R.id.edittextemail);
        final EditText edtnama =findViewById(R.id.edittextnama);
        final EditText edtnotelp =findViewById(R.id.edittextnotelp);
        final EditText edtalamat =findViewById(R.id.edittextalamat);
        final EditText edtpassword = findViewById(R.id.editextpassword);
        if (edtpassword.getText().toString().length()>=8){
            Intent intent = new Intent(Registrasi.this, SyaratKetentuan.class);
            intent.putExtra("nama",edtnama.getText().toString());
            intent.putExtra("notelp",edtnotelp.getText().toString());
            intent.putExtra("alamat",edtalamat.getText().toString());
            intent.putExtra("email",edtemail.getText().toString());
            intent.putExtra("password",edtpassword.getText().toString());
            intent.putExtra("roles",roles);
            startActivity(intent);
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Password Minimal 8 Karakter", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}