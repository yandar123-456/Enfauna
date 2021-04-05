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

public class AdminDataFakta extends AppCompatActivity {
    private DatabaseReference DBKoneksi;
    private ListView listViewContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_data_fakta);
        DBKoneksi = FirebaseDatabase.getInstance().getReference("FaktaUnik");
        listViewContact = findViewById(R.id.listViewContact);



        readData();



        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String idContact = ((TextView)view.findViewById(R.id.isi1)).getTag().toString();

                Intent AdminEditFakta = new Intent(AdminDataFakta.this, AdminEditFakta.class);

                AdminEditFakta.putExtra("id", idContact);

                startActivity(AdminEditFakta);


            }
        });

    }

    private void readData() {
        final ArrayList<String> id = new ArrayList<>();
        final ArrayList<String> nama = new ArrayList<>();
        final ArrayList<String> fakta= new ArrayList<>();
        final ArrayList<String> penjelasan = new ArrayList<>();
        final ArrayList<String> foto = new ArrayList<>();

        DBKoneksi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterable.iterator();

                while (iterator.hasNext()){

                    Model value = iterator.next().getValue(Model.class);

                    assert value != null;
                    id.add(value.getId());
                    nama.add(value.getNama());
                    fakta.add(value.getFakta());
                    penjelasan.add(value.getPenjelasan());
                    foto.add(value.getFoto());

                    ((listViewAdaptar)listViewContact.getAdapter()).notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listViewContact.setAdapter(new listViewAdaptar(id, nama, fakta,foto,this));
    }

    @Override
    public void onResume(){
        super.onResume();
        readData();
    }

    public void tambah(View view) {
        Intent intent = new Intent(AdminDataFakta.this, AdminTambahFakta.class);
        startActivity(intent);
    }

}