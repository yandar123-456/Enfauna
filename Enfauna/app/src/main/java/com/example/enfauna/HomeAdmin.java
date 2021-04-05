package com.example.enfauna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeAdmin extends AppCompatActivity {
    FirebaseDatabase DBKoneksi;
    DatabaseReference Contact;

    private EditText edtEmail, edtPassword;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        DBKoneksi = FirebaseDatabase.getInstance();
        Contact = DBKoneksi.getReference("Contact");

        edtEmail = findViewById(R.id.edt_email_register);
        edtPassword = findViewById(R.id.edt_password_register);
        btnRegister = findViewById(R.id.btn_sign_up);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn (edtEmail.getText().toString(),edtPassword.getText().toString());
            }
        });


    }

    private void signIn(final String nama, final String telpon) {
        Contact.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(nama).exists()){
                    if(!nama.isEmpty()){
                        com.example.enfauna.Contact id = dataSnapshot.child(nama).getValue(Contact.class);
                        if(id.getTelpon().equals(telpon)){
                            AlertDialog.Builder dialog = new AlertDialog.Builder(HomeAdmin.this);
                            dialog.setTitle("Login sukses");
                            dialog.setMessage("");
                            dialog.setPositiveButton("lanjut", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent s = new Intent(getApplicationContext(), HomePilihanAdmin.class);
                                    startActivity(s);
                                }
                            });
                            AlertDialog btnRegister = dialog.create();
                            btnRegister.show();

                        }
                        else{
                            Toast.makeText(HomeAdmin.this, "salah",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(HomeAdmin.this, "id tidak ada belum regis",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}