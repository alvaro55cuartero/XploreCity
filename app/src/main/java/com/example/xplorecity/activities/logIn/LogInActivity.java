package com.example.xplorecity.activities.logIn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;
import com.example.xplorecity.activities.loading.LoadingActivity;
import com.example.xplorecity.activities.mainScreen.MainScreenActivity;
import com.example.xplorecity.requests.Petition;
import com.example.xplorecity.requests.Response;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;

public class LogInActivity extends AppCompatActivity {

    private String email = "";
    private String username = "";

    private EditText emailText;
    private EditText nickNameText;

    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailText = findViewById(R.id.email);
        nickNameText = findViewById(R.id.nickName);

        findViewById(R.id.boton).setOnClickListener(new AceptarButtonListener());
    }



    private void addUser(Context context, String email, String username, String imei) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("username", username);
        params.put("imei", imei);

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    Response response = gson.fromJson(new String(responseBody, StandardCharsets.UTF_8), Response.class);
                    if(response.getMsg().matches("success")) {
                        startMainScreenActivity();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("imei", "" + statusCode);
            }
        };

        Petition.petition(context,"http://92.222.89.84/xplore/addUser.php",responseHandler, params);

    }

    public void startMainScreenActivity(){
        finish();
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }

    private class AceptarButtonListener implements View.OnClickListener {

        public void onClick(View v) {
            if (emailText.getText().toString().isEmpty() || nickNameText.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Por favor, introduzca la información requerida", Toast.LENGTH_SHORT).show();
            } else {

                username = nickNameText.getText().toString();
                email = emailText.getText().toString();

                addUser(getApplicationContext(), email, username, LoadingActivity.imei);
            }
        }
    }
}
