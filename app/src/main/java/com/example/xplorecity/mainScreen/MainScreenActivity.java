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
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MainScreenActivity extends AppCompatActivity implements View.OnClickListener, Tab1.OnFragmentInteractionListener, Tab2.OnFragmentInteractionListener {

    TabLayout tabLayout;

    public static ArrayList<Event> events = new ArrayList<Event>();
    public static ArrayList<Event> myEvents = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

}
