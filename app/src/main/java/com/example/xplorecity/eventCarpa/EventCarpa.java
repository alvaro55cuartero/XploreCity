package com.example.xplorecity.eventCarpa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.xplorecity.R;

import static com.example.xplorecity.mainScreen.MainScreenActivity.events;
import static com.example.xplorecity.mainScreen.MainScreenActivity.myEvents;

public class EventCarpa extends AppCompatActivity implements View.OnClickListener {

    private int id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_carpa);

        Button b1 = findViewById(R.id.button1);
        b1.setOnClickListener(this);
        Button b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new PlayButtonListener(this));
        Bundle bundle = getIntent().getExtras();
        try {
            this.id = bundle.getInt("id");
        } catch (Exception e){
            System.err.println(e.toString());
        }
        if(events.get(this.id).isInMyEvents()) {
            b1.setText(R.string.remove_from_my_list);
            b2.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View view) {

        if (!events.get(id).isInMyEvents()){
            myEvents.add(events.get(id));
            events.get(id).setInMyEvents(true);

            Button b1 = findViewById(R.id.button1);
            b1.setText(R.string.remove_from_my_list);

            Button b2 = findViewById(R.id.button2);
            b2.setVisibility(View.VISIBLE);

        } else {
            myEvents.remove(id);
            events.get(id).setInMyEvents(false);

            Button b1 = findViewById(R.id.button1);
            b1.setText(R.string.enter);

            Button b2 = findViewById(R.id.button2);
            b2.setVisibility(View.GONE);
        }
    }
}
