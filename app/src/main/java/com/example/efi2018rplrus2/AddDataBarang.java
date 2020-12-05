package com.example.efi2018rplrus2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class AddDataBarang extends AppCompatActivity {
    Button btnUpdate;
    EditText edKodesepeda, edMerk, edJenis, edWarna, edHargasewa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_barang);
        edKodesepeda = findViewById(R.id.edEmail1);
        edMerk = findViewById(R.id.edNmaLengkap1);
        edJenis = findViewById(R.id.edNoKtp1);
        edWarna = findViewById(R.id.edNoHp1);
        edHargasewa = findViewById(R.id.edAlamat1);
        btnUpdate = findViewById(R.id.btnUpdate);

        Bundle extras = getIntent().getExtras();

        final String id = extras.getString("id");
        final String email = extras.getString("kodesepeda");
        final String nama = extras.getString("merk");
        final String noktp = extras.getString("warna");
        final String nohp = extras.getString("jenis");
        final String alamat = extras.getString("hargasewa");

        edKodesepeda.setText(email);
        edMerk.setText(nama);
        edWarna.setText(noktp);
        edJenis.setText(nohp);
        edHargasewa.setText(alamat);


        Toolbar toolbar = findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Data Customer");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post("http://192.168.6.114/Rental_Sepeda/Edit.php")
                        .addBodyParameter("id", id)
                        .addBodyParameter("kodesepeda", edKodesepeda.getText().toString())
                        .addBodyParameter("merk", edMerk.getText().toString())
                        .addBodyParameter("warna", edWarna.getText().toString())
                        .addBodyParameter("jenis", edJenis.getText().toString())
                        .addBodyParameter("hargasewa", edHargasewa.getText().toString())
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("hasil", response.toString());
                                try {
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    boolean sukses = hasil.getBoolean("respon");
                                    if (sukses) {
                                        Intent returnIntent = new Intent(AddDataBarang.this, ListData.class);
                                        returnIntent.putExtra("refresh", "refresh");
                                        setResult(23, returnIntent);
                                        finish();
                                        Toast.makeText(AddDataBarang.this, "Edit Suskses", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(AddDataBarang.this, "Edit gagal", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    System.out.println("DEA" + e.getMessage());
                                    Toast.makeText(AddDataBarang.this, "Edit gagal", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                Toast.makeText(AddDataBarang.this, anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}