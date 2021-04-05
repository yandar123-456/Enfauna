package com.example.enfauna;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class listViewAdaptar extends BaseAdapter {

    private ArrayList<String> id;
    private ArrayList<String> nama;
    private ArrayList<String> telpn;
    private ArrayList<String> foto;
    private AppCompatActivity activity;



    public listViewAdaptar(ArrayList<String> id, ArrayList<String> nama, ArrayList<String> telpn, ArrayList<String> foto, AppCompatActivity activity) {
        this.id = id;
        this.nama = nama;
        this.telpn = telpn;
        this.foto = foto;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int position) {
        return id.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.row, parent, false);

        convertView.findViewById(R.id.isi1).setTag(String.valueOf(id.get(position)));

        ((TextView)convertView.findViewById(R.id.isi1)).setText(String.valueOf(nama.get(position)));

        ((TextView)convertView.findViewById(R.id.des1)).setText(String.valueOf(telpn.get(position)));

        String fotoUrl = foto.get(position);
        if (fotoUrl != ""){


            Picasso.get().load(fotoUrl).into((ImageView)convertView.findViewById(R.id.gambar1));

        }else {
            ((ImageView)convertView.findViewById(R.id.gambar1)).setImageResource(R.mipmap.ic_launcher);

        }




        return convertView;
    }
}
