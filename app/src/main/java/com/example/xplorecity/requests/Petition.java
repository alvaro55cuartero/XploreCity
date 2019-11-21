package com.example.xplorecity.requests;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Petition {

    private Gson gson = new Gson();

    public static void petition(Context context, String url, AsyncHttpResponseHandler responseHandler, RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();

        try {
            client.post(context, url, params, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
