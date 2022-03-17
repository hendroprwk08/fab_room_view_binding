package com.example.fab_room_view_binding;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.fab_room_view_binding.databinding.FormInputLayoutBinding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.fab_room_view_binding.databinding.ActivityMainBinding;

import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    static MyDatabase db;

    List<Kontak> kontaks;
    RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        //bentuk (build) database
        db = Room.databaseBuilder(
                getApplicationContext(),
                MyDatabase.class,
                "db-kontak")
                .allowMainThreadQueries()
                .build();

        viewRecyclerView();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormInputLayoutBinding fbinding = FormInputLayoutBinding
                        .inflate(LayoutInflater.from(getApplicationContext()));

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(fbinding.getRoot());
                builder.setCancelable(true);

                builder.setPositiveButton("SIMPAN",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String nama, telepon, tipe;

                                nama = fbinding.etNama.getText().toString();
                                telepon = fbinding.etTelepon.getText().toString();
                                tipe = fbinding.spTipe.getSelectedItem().toString();

                                db.kontakDAO().insertAll(new Kontak(nama, telepon, tipe));

                                viewRecyclerView();

                                Toast.makeText(getApplicationContext(),
                                        "Data terisimpan",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.setNegativeButton("BATAL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.show();
            }
        });
    }

    private void viewRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        binding.contentMain.rvContainer.setLayoutManager(llm);

        kontaks = db.kontakDAO().getAll(); //ambil semua data

        adapter = new RvAdapter(kontaks, this);
        binding.contentMain.rvContainer.setAdapter(adapter);
    }

    //tambah resume untuk reload
    @Override
    protected void onResume() {
        super.onResume();

        viewRecyclerView();
    }
}