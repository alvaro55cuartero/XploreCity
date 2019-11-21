package com.example.xplorecity.activities.loading;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.xplorecity.R;
import com.example.xplorecity.activities.logIn.LogInActivity;
import com.example.xplorecity.activities.mainScreen.MainScreenActivity;
import com.example.xplorecity.requests.Petition;
import com.example.xplorecity.requests.Response;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;

public class LoadingActivity extends AppCompatActivity {

    public static String imei;
    public static Gson gson = new Gson();
    public static TelephonyManager telephonyManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        askforPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    imei = telephonyManager.getImei();
                    peticionImei(this);
                }
        }
    }


    public void askforPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 2);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    imei = telephonyManager.getImei();
                }
                peticionImei(this);
            }
        }
    }


    public void peticionImei(Context context) {
        RequestParams params = new RequestParams();
        params.put("imei", LoadingActivity.imei);

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    Response response = gson.fromJson(new String(responseBody, StandardCharsets.UTF_8), Response.class);
                    if(response.getMsg().matches("fail")) {
                        registerImei();
                    } else {
                        nextActivity();
                    }
                }
            }

            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("Error", "peticion imei fallida: " + new String(responseBody));
            }
        };
        Petition.petition(context, "http://92.222.89.84/xplore/isImei.php",responseHandler, params);
    }

    public void registerImei() {
        RequestParams params = new RequestParams();
        params.put("imei", imei);
        params.put("email", "");
        params.put("username", "");

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    Response response = gson.fromJson(new String(responseBody, StandardCharsets.UTF_8), Response.class);
                    if(response.getMsg().matches("success")) {
                        nextActivity();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };

        Petition.petition(this, "http://92.222.89.84/xplore/addUser.php", responseHandler, params);
    }

    public void nextActivity() {
        Intent intent = new Intent(this, MainScreenActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
