package com.example.xplorecity.eventCarpa;

import android.content.Context;
import android.widget.Toast;

import com.example.xplorecity.logIn.ErrorResponse;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;

class CurrentLayoutResponseHandler extends AsyncHttpResponseHandler {
    private Gson gson;
    private Context context;
    private GameActivity gameActivity;

    public CurrentLayoutResponseHandler(Gson gson, Context context, GameActivity gameActivity) {
        this.context = context;
        this.gson = gson;
        this.gameActivity = gameActivity;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        if (statusCode == 200){
            CurrentLayoutResponse currentLayoutResponse = this.gson.fromJson(new String(responseBody, StandardCharsets.UTF_8), CurrentLayoutResponse.class);

            String result = gson.toJson(currentLayoutResponse);
            Toast.makeText(this.context, result, Toast.LENGTH_LONG).show();

            gameActivity.setCurrentLayout(currentLayoutResponse.getMsg());

        }
    }

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
