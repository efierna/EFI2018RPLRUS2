package com.example.efi2018rplrus2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {

    EditText txtusername;
    EditText txtpassword;
    Button btnlogin;
    Button tvregister;


    SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvregister = (Button) findViewById(R.id.btregister);
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Registrasi.class);
                startActivity(i);
            }
        });
        txtusername = (EditText)findViewById(R.id.txtusername);
        txtpassword = (EditText)findViewById(R.id.txtpassword);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.post(BaseURL.url + "loginadmin.php")
                        .addBodyParameter("email", txtusername.getText().toString())
                        .addBodyParameter("password", txtpassword.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // do anything with response
                                try {
                                    JSONObject hasil = response.getJSONObject("PAYLOAD");
                                    Log.d("RBA", "url: " + hasil.toString());
                                    Boolean respon= hasil.getBoolean("respon");
                                    if (respon){
                                        //Toast.makeText(MainActivity.this, "Sukses Login", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), MainMenu.class));
                                    }else{
                                        Toast.makeText(MainActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }


                            @Override
                            public void onError(ANError error) {
                                // handle error
                                Log.d("RBA", "onError: " + error.getErrorBody());
                                Log.d("RBA", "onError: " + error.getLocalizedMessage());
                                Log.d("RBA", "onError: " + error.getErrorDetail());
                                Log.d("RBA", "onError: " + error.getResponse());
                                Log.d("RBA", "onError: " + error.getErrorCode());
                            }
                        });
            }
        });
    }
}
