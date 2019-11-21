package com.example.xplorecity.eventCarpa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;

public class EventCarpa extends AppCompatActivity implements View.OnClickListener {

    private int id = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //if(events.get(0).isInMyEvents()) {
        //    b1.setText(R.string.remove_from_my_list);
        //    b2.setVisibility(View.VISIBLE);
        //}

    }

    @Override
    public void onClick(View view) {

        /*
        if (!events.get(id).isInMyEvents()){
            events.get(id).setInMyEvents(true);

            Button b1 = findViewById(R.id.button1);
            b1.setText(R.string.remove_from_my_list);

            Button b2 = findViewById(R.id.button2);
            b2.setVisibility(View.VISIBLE);

        } else {
            events.get(id).setInMyEvents(false);

            Button b1 = findViewById(R.id.button1);
            b1.setText(R.string.enter);

            Button b2 = findViewById(R.id.button2);
            b2.setVisibility(View.GONE);
        }*/
    }
}
