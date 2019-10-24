package com.example.xplorecity.logIn;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xplorecity.R;
import com.example.xplorecity.mainScreen.MainScreenActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class LogInActivity extends AppCompatActivity {

    private String imei, email, nickName;
    private EditText emailText, nickNameText;
    private TelephonyManager telephonyManager;

    private Gson gson;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // temporal para poder probar mas cosas mientras esto no funciona

        Intent intent = new Intent(this, MainScreenActivity.class);
        this.startActivity(intent);

        //Lo primero que hacemos es coger el imei y hacer la petición porque
        //si ya tenemos el imei saltamos al MainActivity
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if(isPermissionGranted()){
            //do your specific task after read phone state
            imei = telephonyManager.getImei();
        }

        //Creamos el json
        gson = new Gson();
        ImeiRequest imeiRequest = new ImeiRequest(imei);
        String requestJson = gson.toJson(imeiRequest);

        //Mandamos la peticion
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            client.post(
                    this,
                    "http://92.222.89.84/xplore/getLevel.php?",
                    new StringEntity(requestJson),
                    "application/json",
                    new ImeiResponseHandler(gson, this, this)
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = "";
        nickName = "";

        emailText = findViewById(R.id.email);
        nickNameText = findViewById(R.id.nickName);

        findViewById(R.id.botonAceptarLogIn).setOnClickListener(new AceptarButtonListener());

    }

    private class AceptarButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (emailText.getText().toString().isEmpty() || nickNameText.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Por favor, introduzca la información requerida", Toast.LENGTH_SHORT).show();
            } else {

                nickName = nickNameText.getText().toString();
                email = emailText.getText().toString();

                addUser(email, nickName);

            }
        }
    }

    private void addUser(String email, String nickName) {

        //Creamos el json
        gson = new Gson();
        AddUserRequest addUserRequest = new AddUserRequest(imei, email, nickName);
        String requestJson = gson.toJson(addUserRequest);

        //Mandamos la peticion
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            client.post(
                    this,
                    "http://92.222.89.84/xplore/addUser.php",
                    new StringEntity(requestJson),
                    "application/json",
                    new AddUserResponseHandler(gson, this, this)
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void startMainActivity(){
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 2: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    //do your specific task after read phone state granted
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
