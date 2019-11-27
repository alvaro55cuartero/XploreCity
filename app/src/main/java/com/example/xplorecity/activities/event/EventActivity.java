package com.example.xplorecity.activities.event;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;
import com.example.xplorecity.activities.game.GameActivity;
import com.example.xplorecity.activities.loading.LoadingActivity;
import com.example.xplorecity.activities.mainScreen.MainScreenActivity;
import com.example.xplorecity.eventManager.EventInfo;
import com.example.xplorecity.eventManager.Layout;
import com.example.xplorecity.requests.Petition;
import com.example.xplorecity.requests.Response;
import com.example.xplorecity.tools.FileIO;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{

    private EventInfo eventInfo;
    private Gson gson = new Gson();
    private Layout[] layouts;
    private boolean download = false;
    private boolean wait = true;
    private boolean enable = false;
    private int count = 0;
    private Button b;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Bundle bundle = getIntent().getExtras();
        eventInfo = MainScreenActivity.eventInfoList[bundle.getInt("id")];

        b = findViewById(R.id.button);
        b.setOnClickListener(this);
        b.setText("Not yet wait till " + eventInfo.getFecha());

        TextView text = findViewById(R.id.text);
        text.setText(eventInfo.getTexto());

        ImageView img = findViewById(R.id.img);
        img.setImageBitmap(FileIO.loadIMG(this, "", eventInfo.getImg()));

        checkDate();


    }

    public void checkDate(){

        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = parser.parse(eventInfo.getFecha());
            Date currentTime = Calendar.getInstance().getTime();
            if(currentTime.after(date)) {
                peticionIsUserEvents();
                b.setText("Download");
            }
        } catch(Exception e) {

        }
    }

    public void onResume() {
        super.onResume();
        peticionIsUsed();

    }

    public void onClick(View view) {
        if(enable) {
            if (!download) {
                peticionGetEventJson();
                download = true;
                wait = true;
                b.setText("WAIT DOWLOADING...");

            } else if (!wait) {
                peticionSetDate();
                Intent intent = new Intent(this, GameActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Layouts", layouts);
                bundle.putSerializable("EventInfo", eventInfo);

                intent.putExtras(bundle);
                this.startActivity(intent);

            }
        }
    }

    public void peticionSetDate() {
        RequestParams params = new RequestParams();
        params.put("title", eventInfo.getTitle());
        params.put("imei", LoadingActivity.imei);


        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if(statusCode == 400) {
                    statusCode = 200;
                }
            }
        };

        Petition.petition(this, "http://92.222.89.84/xplore/setDate.php", responseHandler, params);
    }

    public void peticionGetEventJson() {
        RequestParams params = new RequestParams();
        params.put("imei", LoadingActivity.imei);
        params.put("title", eventInfo.getJson());

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    String text = new String(responseBody);
                    layouts = gson.fromJson(text, Layout[].class);
                    downloads();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };

        Petition.petition(this, "http://92.222.89.84/xplore/getEventsJson.php", responseHandler, params);
    }

    public void postDowload(File file, String url) {
        String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(url);
        String name = URLUtil.guessFileName(url, null, fileExtenstion);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        FileIO.saveIMG(this, bitmap, "", name);

        count++;
        if (count >= layouts.length) {
            FileIO.test(this, "");
            wait = false;
            b.setText("PLAY");
        } else {
            downloads();
        }
    }

    public void downloads() {
        FileAsyncHttpResponseHandler file = new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Log.e("error", "" + statusCode);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                postDowload(file, this.getRequestURI().toString());

            }
        };

        Layout l = layouts[count];
        String text = "http://92.222.89.84/xplore/uploads/img/"+ eventInfo.getTitle() + "/" + l.getImg();
        text = text.replace(" ", "%20");
        Petition.petition(this, text, file, new RequestParams());
    }

    public void peticionIsUserEvents() {
        RequestParams params = new RequestParams();
        params.put("imei", LoadingActivity.imei);
        params.put("title", eventInfo.getTitle());

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    Response r = gson.fromJson(new String(responseBody), Response.class);

                    if(r.getErr().matches("false")) {
                        peticionIsUsed();
                    } else {
                        peticionNewUserEvent();
                    }

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };

        Petition.petition(this, "http://92.222.89.84/xplore/isUserEvent.php", responseHandler, params);
    }

    public void peticionIsUsed() {
        RequestParams params = new RequestParams();
        params.put("imei", LoadingActivity.imei);
        params.put("title", eventInfo.getTitle());

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    Response r = gson.fromJson(new String(responseBody), Response.class);
                    if(r.getErr().matches("false")) {
                        b.setText("Ya as jugado");
                    } else {
                        enable = true;
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };

        Petition.petition(this,"http://92.222.89.84/xplore/getUsed.php", responseHandler, params);
    }

    public void peticionNewUserEvent() {
        RequestParams params = new RequestParams();
        params.put("imei", LoadingActivity.imei);
        params.put("title", eventInfo.getTitle());

        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    Response r = gson.fromJson(new String(responseBody), Response.class);
                    if(r.getErr().matches("false")) {
                       peticionIsUsed();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };

        Petition.petition(this,"http://92.222.89.84/xplore/newUserEvent.php", responseHandler, params);
    }
}
