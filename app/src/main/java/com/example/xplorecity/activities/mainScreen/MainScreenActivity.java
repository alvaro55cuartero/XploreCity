package com.example.xplorecity.activities.mainScreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;
import com.example.xplorecity.eventCarpa.EventCarpa;
import com.example.xplorecity.eventManager.EventInfo;
import com.example.xplorecity.requests.Petition;
import com.example.xplorecity.tools.FileIO;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;


public class MainScreenActivity extends AppCompatActivity implements View.OnClickListener{

    public static Gson gson = new Gson();
    private String eventInfoFile;
    public static EventInfo[] eventInfoList;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadEventsInfoFile();
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, EventCarpa.class);
        startActivity(i);
    }

    public void loadEventsInfoFile() {
        String txt = FileIO.loadFile(this,"json","eventsInfo.json");
        if(txt != null) {
            eventInfoFile = txt;
            postPeticion();
        } else {
            peticion();
        }
    }

    public void peticion() {
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200) {
                    eventInfoFile = new String(responseBody, StandardCharsets.UTF_8);
                    FileIO.saveToFile(getApplicationContext(),"json", "eventsInfo.json", eventInfoFile);
                    postPeticion();
                }
            }

            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };

        Petition.petition(this,"http://92.222.89.84/xplore/getEventsInfo.php",responseHandler ,new RequestParams());
    }

    public void postPeticion() {
        loadEvents();
        downloads();
    }

    public void postDowload(File file, String url) {
        String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(url);
        String name = URLUtil.guessFileName(url, null, fileExtenstion);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        FileIO.saveIMG(this, bitmap, "", name);

        count++;
        if (count >= eventInfoList.length) {
            fillList();
        }
    }

    public void fillList() {
        ListView listView = this.findViewById(R.id.list1);
        listView.setAdapter(new ListAdapter(eventInfoList));
        listView.setOnItemClickListener(new ListListener(this));
    }

    private void loadEvents() {
        eventInfoList = gson.fromJson(eventInfoFile, EventInfo[].class);
    }

    private void downloads() {
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

        for(EventInfo e : eventInfoList) {
            String text = "http://92.222.89.84/xplore/uploads/img/" + e.getTitle() + "/" + e.getImg();
            text = text.replace(" ", "%20");
            Petition.petition(this, text, file, new RequestParams());
        }
    }
}
