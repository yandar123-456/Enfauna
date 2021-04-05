package com.example.enfauna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AdminTambahFakta extends AppCompatActivity {
    private DatabaseReference DBKoneksi;
    private StorageReference STKoneksi;
    private String contactId, storageUrl;

    ImageView FotoContact;
    EditText TextNama, TextFakta, TextPenjelasan;
    Button Tsave, Tcancel;
    ProgressBar progressBar;
    private Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tambah_fakta);

        FotoContact = findViewById(R.id.imageview);
        TextNama = findViewById(R.id.textView);
        TextFakta = findViewById(R.id.textView2);
        TextPenjelasan = findViewById(R.id.textView3);

        Tsave = findViewById(R.id.save);
        Tcancel = findViewById(R.id.cancel);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        DBKoneksi = FirebaseDatabase.getInstance().getReference( "FaktaUnik");
        STKoneksi = FirebaseStorage.getInstance().getReference();
        contactId = DBKoneksi.push().getKey();

        FotoContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGallery();
            }
        });

        Tsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFoto();
            }
        });


    }
    private void uploadFoto() {

        if(filePath != null) {
            String imagename = TextNama.getText().toString();

            final StorageReference ref = STKoneksi.child("fakta/"+imagename);

            UploadTask uploadTask = ref.putFile(filePath);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }


                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUrl = task.getResult();

                        storageUrl = downloadUrl.toString();

                        simpanData();

                        Toast.makeText(getApplicationContext(),"data telah berhasil", Toast.LENGTH_SHORT).show();
                        progressBar.setProgress(0);
                        progressBar.setVisibility(View.INVISIBLE);
                        finish();
                    }


                }
            });

            uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setVisibility(View.VISIBLE);
                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                    progressBar.setProgress((int)progress);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"gagal menyimmpan data" + e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

        }

    }

    private void simpanData() {

        String imagename = TextNama.getText().toString();
        Model contact = new Model(imagename,
                TextNama.getText().toString(),
                TextFakta.getText().toString(),
                TextPenjelasan.getText().toString(),

                storageUrl);

        DBKoneksi.child(imagename).setValue(contact);

    }

    private void showGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "pilih foto"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){

            filePath = data.getData();
            Picasso.get().load(filePath).fit().into(FotoContact);

        }else{
            Toast.makeText(getApplicationContext(), "text tidak ada file" ,Toast.LENGTH_SHORT).show();
        }
    }
    public void kembali(View view) {
        finish();
    }


}