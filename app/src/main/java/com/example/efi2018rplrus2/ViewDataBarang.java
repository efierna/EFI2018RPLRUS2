package com.example.efi2018rplrus2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.VerifiedInputEvent;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewDataBarang extends AppCompatActivity {

    public List<ModelSepeda> ModelSepeda;
    public AdapterBarang adapterBarang;
    private ArrayList<ModelSepeda> DataArrayList;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_barang);

        getData();
    }

        private void getData() {
            DataArrayList = new ArrayList<>();
            Log.d("test", "onCreate: ");

            AndroidNetworking.post("http://192.168.6.12/Rental_Sepeda/GetData.php")
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray data = response.getJSONArray("result");

                                for (int i = 0; i < data.length(); i++) {

                                    ModelSepeda model = new ModelSepeda();
                                    JSONObject object = data.getJSONObject(i);
                                    model.setKodesepeda(object.getString("kodesepeda"));
                                    model.setMerk(object.getString("merk"));
                                    model.setWarna(object.getString("warna"));
                                    model.setHargasewa(object.getString("hargasewa"));
                                    DataArrayList.add(model);

                                }

                                adapterBarang = new AdapterBarang(DataArrayList);

                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewDataBarang.this);

                                recyclerView.setLayoutManager(layoutManager);

                                recyclerView.setAdapter(adapterBarang);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Log.d("test", "onResponse: " + anError.toString());
                            Log.d("test", "onResponse: " + anError.getErrorBody());
                            Log.d("test", "onResponse: " + anError.getErrorCode());
                            Log.d("test", "onResponse: " + anError.getErrorDetail());
                        }
                    });
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == 23 && data.getStringExtra("refresh") != null) {
                //refresh list
                getData();
                Toast.makeText(this, "testtest", Toast.LENGTH_SHORT).show();

            }
        }
    }