package com.example.xplorecity.activities.game;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;
import com.example.xplorecity.eventManager.Layout;
import com.example.xplorecity.requests.Petition;
import com.example.xplorecity.requests.Response;
import com.example.xplorecity.tools.FileIO;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class GameActivity extends AppCompatActivity {

    private Layout[] layouts;
    private int currentLayoutCount = 0;
    private Gson gson = new Gson();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_template);
        layouts = (Layout[])getIntent().getExtras().get("Layouts");

        peticionIsUserEvent();
        makeLayout();
    }


    public void makeLayout() {
        Layout currentLayout = layouts[currentLayoutCount];

        ImageView img = findViewById(R.id.img);
        img.setImageBitmap(FileIO.loadIMG(this,"",currentLayout.getImg()));

        TextView text = findViewById(R.id.text);
        text.setText(currentLayout.getContexto());

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currentLayout.getOptions());
        spinner.setAdapter(adapter);
    }

    public void peticionIsUserEvent() {
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    Response  r = gson.fromJson(new String(responseBody), Response.class);
                    if(r.getErr().matches("true")) {
                        peticionNewUserEvent();
                    } else {
                        peticionUsed();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };

        Petition.petition(this, "", responseHandler, new RequestParams());
    }

    public void peticionUsed() {
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };

        Petition.petition(this, "", responseHandler, new RequestParams());
    }

    public void peticionNewUserEvent() {

    }
}
