package com.example.fab_room_view_binding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fab_room_view_binding.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    MyDatabase db;
    String nama, telepon, tipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //tangkap bundle
        Bundle bundle = null;
        bundle = this.getIntent().getExtras();

        //letakkan isi bundle
        final int id = bundle.getInt("b_id", 0);
        binding.detEtNama.setText(bundle.getString("b_nama"));
        binding.detEtTelepon.setText(bundle.getString("b_telepon"));

        //set spinner
        int idx_spinner = setSpinner(binding.spDetTipe, bundle.getString("b_kelas"));
        binding.spDetTipe.setSelection(idx_spinner);

        getSupportActionBar().setTitle("Data Kontak"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action back

        binding.detBtUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama =  binding.detEtNama.getText().toString();
                telepon =  binding.detEtTelepon.getText().toString();
                tipe =  binding.spDetTipe.getSelectedItem().toString();//et_tipe. getText().toString();

                MainActivity.db.kontakDAO().update(new Kontak(id, nama, telepon, tipe));
                Toast.makeText(getApplicationContext(), "Data " + nama + " berhasil diubah", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        binding.detBtHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama =  binding.detEtNama.getText().toString();
                telepon =  binding.detEtTelepon.getText().toString();
                tipe =  binding.spDetTipe.getSelectedItem().toString();//et_tipe. getText().toString();

                MainActivity.db.kontakDAO().delete(new Kontak(id, nama, telepon, tipe));
                Toast.makeText(getApplicationContext(), "Data " + nama + " dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //tampilkan tombol panah back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private int setSpinner(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}