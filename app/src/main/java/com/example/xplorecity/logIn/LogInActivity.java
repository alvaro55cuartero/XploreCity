package com.example.xplorecity.logIn;

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
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.xplorecity.R;
import com.example.xplorecity.mainScreen.MainScreenActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class LogInActivity extends AppCompatActivity {

    private String email, username, imei;
    private EditText emailText, nickNameText;
    private TelephonyManager telephonyManager;

    private Gson gson;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        gson = new Gson();
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if(isPermissionGranted()){
            //do your specific task after read phone state
            imei = telephonyManager.getImei();
        }

        email = "";
        username = "";

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

                username = nickNameText.getText().toString();
                email = emailText.getText().toString();

                addUser(email, username, imei);

            }
        }
    }

    private void addUser(String email, String username, String imei) {

        //Creamos el json
        AddUserRequest addUserRequest = new AddUserRequest(imei, email, username);
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

    public void startMainScreenActivity(){
        finish();
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
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
