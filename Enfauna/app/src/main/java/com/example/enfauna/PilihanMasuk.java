package com.example.enfauna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PilihanMasuk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan_masuk);
    }
    public void umum(View view) {
        startActivity(new Intent(PilihanMasuk.this, HomeActivity.class));
        finish();

    }

    public void admin(View view) {
        startActivity(new Intent(PilihanMasuk.this, HomeAdmin.class));


    }
}