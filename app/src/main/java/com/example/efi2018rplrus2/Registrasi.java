package com.example.efi2018rplrus2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Registrasi extends AppCompatActivity {

    TextView txtRegristrasi;
    EditText txtemail;
    EditText txtpassword;
    EditText txtnamalengkap;
    EditText txtnoktp;
    EditText txtnohp;
    EditText txtalamat;
    Button btnregister;
    TextView tvlogin;

    private ProgressDialog progressBar;
    SharedPreferences mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        mLogin = getSharedPreferences("login",MODE_PRIVATE);

        progressBar = new ProgressDialog(this);

        txtemail = findViewById(R.id.txtemail);
        txtpassword = findViewById(R.id.txtpassword);
        txtnamalengkap = findViewById(R.id.txtnamalengkap);
        txtnoktp = findViewById(R.id.txtnoktp);
        txtnohp = findViewById(R.id.txtnohp);
        txtalamat = findViewById(R.id.txtalamat);
        tvlogin = findViewById(R.id.tvlogin);
        btnregister = findViewById(R.id.btnregister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtemail.getText().toString();
                String password = txtpassword.getText().toString();
                String nama = txtnamalengkap.getText().toString();
                String noktp = txtnoktp.getText().toString();
                String nohp = txtnohp.getText().toString();
                String alamat = txtalamat.getText().toString().trim();
                progressBar.setTitle("Register In...");
                progressBar.show();
                AndroidNetworking.post("http://192.168.6.114/Rental_Sepeda/Register.php")
                        .addBodyParameter("username", username)
                        .addBodyParameter("password", password)
                        .addBodyParameter("nama", nama)
                        .addBodyParameter("noktp", noktp)
                        .addBodyParameter("nohp", nohp)
                        .addBodyParameter("alamat", alamat)
                        .addBodyParameter("roleuser", "1")
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("hasil", "onResponse: ");
                                try {
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    String status = hasil.getString("STATUS");
                                    String message = hasil.getString("MESSAGE");
                                    Log.d("STATUS", "onResponse: " + status);
                                    if (status.equals("SUCCESS")) {
                                        mLogin.edit().putBoolean("logged",true).apply();
                                            Intent intent = new Intent(Registrasi.this, CustomerActivity.class);
                                        startActivity(intent);
                                        finish();
                                        progressBar.dismiss();
                                    } else {
                                        Toast.makeText(Registrasi.this, message.toString(), Toast.LENGTH_SHORT).show();
                                        progressBar.dismiss();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                progressBar.dismiss();
                                Log.d("test", "onError: " + anError.getErrorDetail());
                                Log.d("test", "onError: " + anError.getErrorBody());
                                Log.d("test", "onError: " + anError.getErrorCode());

                            }
                        });
            }
        });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registrasi.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}