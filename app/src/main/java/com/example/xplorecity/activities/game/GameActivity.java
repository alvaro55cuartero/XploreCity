package com.example.xplorecity.activities.game;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;
import com.example.xplorecity.activities.loading.LoadingActivity;
import com.example.xplorecity.eventManager.EventInfo;
import com.example.xplorecity.eventManager.Layout;
import com.example.xplorecity.requests.Petition;
import com.example.xplorecity.requests.Response;
import com.example.xplorecity.tools.FileIO;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class GameActivity extends AppCompatActivity implements android.widget.AdapterView.OnItemSelectedListener, View.OnClickListener{

    private Layout[] layouts;
    private EventInfo eventInfo;
    private int currentLayoutCount = 0;
    private Gson gson = new Gson();
    private Layout currentLayout;
    private TextView text;
    private TextView text2;

    private Button b;
    private Spinner spinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_template);
        layouts = (Layout[])getIntent().getExtras().get("Layouts");
        eventInfo = (EventInfo)getIntent().getExtras().get("EventInfo");
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        text = findViewById(R.id.text);
        text2 = findViewById(R.id.text2);
        b = findViewById(R.id.button);
        b.setOnClickListener(this);
        peticionGetLevel();
    }

    public void onClick(View view) {
        if(currentLayoutCount > layouts.length) {
            currentLayoutCount++;
            peticionIncrementLevel();
        } else {
            peticionSetUsed();

        }
    }


    public void makeLayout() {
        currentLayout = layouts[currentLayoutCount];

        b.setVisibility(View.INVISIBLE);

        ImageView img = findViewById(R.id.img);
        img.setImageBitmap(FileIO.loadIMG(this,"",currentLayout.getImg()));

        text.setText(currentLayout.getContexto());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currentLayout.getOptions());
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selected = adapterView.getItemAtPosition(i).toString();

        if(selected.matches(currentLayout.getRespuesta())) {
            b.setVisibility(View.VISIBLE);
        }

        String a  =currentLayout.getTexts()[i];
        text2.setText(a);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void peticionGetLevel() {
        RequestParams params = new RequestParams();
        params.put("imei", LoadingActivity.imei);
        params.put("title", eventInfo.getTitle());

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    Response r = gson.fromJson(new String(responseBody), Response.class);
                    if(r.getErr().matches("false")) {
                        currentLayoutCount = Integer.parseInt(r.getMsg());
                        makeLayout();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };

        Petition.petition(this, "http://92.222.89.84/xplore/getLevel.php", responseHandler, params);
    }

    public void peticionIncrementLevel() {
        RequestParams params = new RequestParams();
        params.put("imei", LoadingActivity.imei);
        params.put("title", eventInfo.getTitle());

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    makeLayout();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        };

        Petition.petition(this, "http://92.222.89.84/xplore/incrementLevel.php", responseHandler, params);
    }

    public void peticionSetUsed() {
        RequestParams params = new RequestParams();
        params.put("imei", LoadingActivity.imei);
        params.put("title", eventInfo.getTitle());
        params.put("used", "1");

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode ==200) {
                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        };

        Petition.petition(this, "http://92.222.89.84/xplore/setUsed.php", responseHandler, params);
    }

}
