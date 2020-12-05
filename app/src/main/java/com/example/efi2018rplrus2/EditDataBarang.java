package com.example.efi2018rplrus2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class EditDataBarang extends AppCompatActivity {

    TextView tvId, tvKodesepeda, tvMerk, tvWarna, tvJenis, tvHargasewa;
    Button btnedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tvId = findViewById(R.id.tvId);
        tvKodesepeda = findViewById(R.id.tvKodesepeda);
        tvMerk = findViewById(R.id.tvMerk);
        tvWarna = findViewById(R.id.tvWarna);
        tvJenis = findViewById(R.id.tvJenis);
        tvHargasewa = findViewById(R.id.tvHargasewa);
        btnedit = findViewById(R.id.btnedit);


        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Customer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("id");
        final String kode = extras.getString("kode");
        final String merk = extras.getString("merk");
        final String warna = extras.getString("warna");
        final String jenis = extras.getString("jenis");
        final String hargasewa = extras.getString("hargasewa");

        tvId.setText("Id           : " + id);
        tvKodesepeda.setText("Kode      : " + kode);
        tvMerk.setText("Merk      : " + merk);
        tvWarna.setText("No Hp     : " + warna);
        tvJenis.setText("Alamat    : " + jenis);
        tvHargasewa.setText("No KTP    : " + hargasewa);

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(EditDataBarang.this, EditActivity.class);
                in.putExtra("id", id);
                in.putExtra("nama", kode);
                in.putExtra("username", merk);
                in.putExtra("nohp", warna);
                in.putExtra("alamat", jenis);
                in.putExtra("noktp", hargasewa);
                startActivityForResult(in, 23);
            }
        });

    }
}