package com.example.enfauna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePilihanAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pilihan_admin);
    }
    public void hewan(View view) {
        Intent intent = new Intent(HomePilihanAdmin.this, AdminDataHewan.class);
        startActivity(intent);
    }

    public void fakta(View view) {
        Intent intent = new Intent(HomePilihanAdmin.this, AdminDataFakta.class);
        startActivity(intent);
    }
}