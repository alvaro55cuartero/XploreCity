package com.example.xplorecity.mainScreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.example.xplorecity.R;
import com.example.xplorecity.eventCarpa.EventCarpa;
import com.google.android.material.tabs.TabLayout;

public class MainScreenActivity extends AppCompatActivity implements View.OnClickListener,Tab1.OnFragmentInteractionListener,Tab2.OnFragmentInteractionListener{

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Eventos dispobibles"));
        tabLayout.addTab(tabLayout.newTab().setText("Tus Eventos"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
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
