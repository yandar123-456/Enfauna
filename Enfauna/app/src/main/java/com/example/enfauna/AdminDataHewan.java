package com.example.enfauna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class AdminDataHewan extends AppCompatActivity {
    private DatabaseReference DBKoneksi;
    private ListView listViewContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data_hewan);
        DBKoneksi = FirebaseDatabase.getInstance().getReference("Hewan");
        listViewContact = findViewById(R.id.listViewContact);



        readData();



        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String idContact = ((TextView)view.findViewById(R.id.isi1)).getTag().toString();

                Intent AdminEditHewan = new Intent(AdminDataHewan.this, AdminEditHewan.class);

                AdminEditHewan.putExtra("id", idContact);

                startActivity(AdminEditHewan);


            }
        });

    }

    private void readData() {
        final ArrayList<String> id = new ArrayList<>();
        final ArrayList<String> nama = new ArrayList<>();
        final ArrayList<String> asal= new ArrayList<>();
        final ArrayList<String> penjelasan = new ArrayList<>();
        final ArrayList<String> status = new ArrayList<>();
        final ArrayList<String> foto = new ArrayList<>();

        DBKoneksi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterable.iterator();

                while (iterator.hasNext()){

                    Model1 value = iterator.next().getValue(Model1.class);

                    assert value != null;
                    id.add(value.getId());
                    nama.add(value.getNama());
                    asal.add(value.getAsal());
                    penjelasan.add(value.getPenjelasan());
                    status.add(value.getStatus());
                    foto.add(value.getFoto());

                    ((listViewAdaptar)listViewContact.getAdapter()).notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewContact.setAdapter(new listViewAdaptar(id, nama, asal,foto,this));
    }

    @Override
    public void onResume(){
        super.onResume();
        readData();
    }

    public void tambah(View view) {
        Intent intent = new Intent(AdminDataHewan.this, AdminTambahHewan.class);
        startActivity(intent);
    }

}