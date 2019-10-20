package com.example.xplorecity.logIn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.xplorecity.mainScreen.MainScreenActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;

class ImeiResponseHandler extends AsyncHttpResponseHandler {

    private Gson gson;
    private Context context;
    private LogInActivity logInActivity;

    public ImeiResponseHandler(Gson gson, Context context, LogInActivity logInActivity) {
        this.context = context;
        this.gson = gson;
        this.logInActivity = logInActivity;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        if (statusCode == 200){
            ImeiResponse imeiResponse = this.gson.fromJson(new String(responseBody, StandardCharsets.UTF_8), ImeiResponse.class);
            //-----------------------------------------

            String result = gson.toJson(imeiResponse);
            //Toast.makeText(this.context, result, Toast.LENGTH_LONG).show();

            if (result.isEmpty()){
                logInActivity.startMainActivity();
                logInActivity.finish();
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        if (statusCode == 400){

            ErrorResponse errorResponse = this.gson.fromJson(
                    new String(responseBody, StandardCharsets.UTF_8),
                    ErrorResponse.class
            );

            Toast.makeText(this.context, errorResponse.getDescription(), Toast.LENGTH_LONG).show();

        } else {

            if (error != null){
                Toast.makeText(this.context, "Recibido statusCode " + statusCode + " con error " + error.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.context, "Recibido statusCode" + statusCode, Toast.LENGTH_LONG).show();
            }

        }
    }
}
