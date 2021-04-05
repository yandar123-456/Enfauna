package com.example.enfauna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class AdminEditHewan extends AppCompatActivity {
    private DatabaseReference DBKoneksi;
    private StorageReference STKoneksi;


    ImageView FotoContact;
    TextView TextNama, TextAsal, Textpenjelasan, TextStatus;
    Button TombolEdit, TombolHapus;
    private String fotoUrl, contactId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_hewan);
        FotoContact = findViewById(R.id.imageview);
        TextNama = findViewById(R.id.textView);
        TextAsal = findViewById(R.id.textView2);
        Textpenjelasan = findViewById(R.id.textView3);
        TextStatus = findViewById(R.id.textView4);
        TombolEdit = findViewById(R.id.edit);
        TombolHapus = findViewById(R.id.hapus);

        DBKoneksi = FirebaseDatabase.getInstance().getReference("Hewan");

        contactId = getIntent().getExtras().getString("id");


        readData();

        TombolEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
                Toast.makeText(getApplicationContext(),"Data Hewan berhasil di Ubah", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        TombolHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                deleteFoto();
                Toast.makeText(getApplicationContext(),"Data Hewan berhasil di hapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void deleteFoto() {
        STKoneksi = FirebaseStorage.getInstance().getReferenceFromUrl(fotoUrl);

        STKoneksi.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });

    }

    private void deleteData() {
        Query delQuery = DBKoneksi.orderByKey().equalTo(contactId);
        delQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DBKoneksi.child(contactId).removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateData() {
        Query editQuery = DBKoneksi.orderByKey().equalTo(contactId);

        editQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot addSnapshot : dataSnapshot.getChildren()){
                    addSnapshot.getRef().child("nama").setValue(TextNama.getText().toString());
                    addSnapshot.getRef().child("asal").setValue(TextAsal.getText().toString());
                    addSnapshot.getRef().child("penjelasan").setValue(Textpenjelasan.getText().toString());
                    addSnapshot.getRef().child("status").setValue(TextStatus.getText().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void readData() {
        Query findQuery = DBKoneksi.orderByKey().equalTo(contactId);

        findQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot getSnapshot : dataSnapshot.getChildren()){

                    TextNama.setText(getSnapshot.child("nama").getValue().toString());
                    TextAsal.setText(getSnapshot.child("asal").getValue().toString());
                    Textpenjelasan.setText(getSnapshot.child("penjelasan").getValue().toString());
                    TextStatus.setText(getSnapshot.child("status").getValue().toString());
                    fotoUrl = getSnapshot.child("foto").getValue().toString();

                    Picasso.get().load(fotoUrl).fit().into(FotoContact);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}

