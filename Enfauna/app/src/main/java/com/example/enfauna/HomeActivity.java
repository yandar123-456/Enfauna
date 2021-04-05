package com.example.enfauna;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void admin(View view) {
        Intent intent = new Intent(HomeActivity.this, HomeAdmin.class);
        startActivity(intent);
    }

    public void hewan1(View view) {
        Intent intent = new Intent(HomeActivity.this, HomeDataHewan.class);
        startActivity(intent);
    }
    public void fakta(View view) {
        Intent intent = new Intent(HomeActivity.this, HomeDataFakta.class);
        startActivity(intent);
    }

    public void quis(View view) {
        Intent intent = new Intent(HomeActivity.this, HomeQuis.class);
        startActivity(intent);
    }

    public void info(View view) {
        Intent intent = new Intent(HomeActivity.this, HomeInfo.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Apa Kamu Ingin Keluar?");
        builder.setCancelable(true);
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                startActivity(new Intent(
                        HomeActivity. this, Splashfinish.class));
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void keluar(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Apa Kamu Ingin Keluar?");
        builder.setCancelable(true);
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                startActivity(new Intent(HomeActivity. this, Splashfinish.class));
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}