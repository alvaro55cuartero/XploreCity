package com.example.xplorecity.mainScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.xplorecity.R;
import com.example.xplorecity.eventCarpa.EventCarpa;

public class MainScreenActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView c = findViewById(R.id.event1);
        c.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, EventCarpa.class);
        startActivity(i);
    }
}
