package com.example.xplorecity.mainScreen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.xplorecity.R;
import com.example.xplorecity.eventCarpa.EventCarpa;
import com.example.xplorecity.eventManager.Event;
import com.example.xplorecity.logIn.LogInActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.entity.StringEntity;


public class MainScreenActivity extends AppCompatActivity implements View.OnClickListener, Tab1.OnFragmentInteractionListener, Tab2.OnFragmentInteractionListener {

    TabLayout tabLayout;

    public static ArrayList<Event> events = new ArrayList<Event>();
    public static ArrayList<Event> myEvents = new ArrayList<Event>();

    private String imei;
    private TelephonyManager telephonyManager;
    private Gson gson;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if(isPermissionGranted()){
            //do your specific task after read phone state
            imei = telephonyManager.getImei();
        }

        //Creamos el json
        gson = new Gson();
        ImeiRequest imeiRequest = new ImeiRequest(imei);
        String requestJson = gson.toJson(imeiRequest);

        //Mandamos la peticion
        AsyncHttpClient client = new AsyncHttpClient();
        try {
            client.post(
                    this,
                    "http://92.222.89.84/xplore/getLevel.php",
                    new StringEntity(requestJson),
                    "application/json",
                    new ImeiResponseHandler(gson, this, this)
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //En la primera version solo añadimos un evento si la lista está vacía para
        //evitar el bug de que se añada un evento si salimos de la app y volvemos a entrar
        //habrá que cambiarlo para futuras versiones
        if(events.isEmpty()){
            events.add(new Event("carpa", R.drawable.logo_carpa));
        }

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Eventos dispobibles"));
        tabLayout.addTab(tabLayout.newTab().setText("Tus Eventos"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, EventCarpa.class);
        startActivity(i);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE)
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

    public void startLogInActivity() {
        finish();
        Intent i = new Intent(this, LogInActivity.class);
        startActivity(i);
    }

}
