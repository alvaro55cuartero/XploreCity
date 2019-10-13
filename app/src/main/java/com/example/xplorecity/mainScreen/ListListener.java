package com.example.xplorecity.mainScreen;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.xplorecity.eventCarpa.EventCarpa;
import com.example.xplorecity.eventManager.Event;

import java.util.List;

public class ListListener implements AdapterView.OnItemClickListener {

    private List<Event> events;
    private Activity activity;

    public ListListener(Activity activity, List<Event> events) {
        this.events = events;
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(activity, EventCarpa.class);
        intent.putExtra("id", i);
        activity.startActivity(intent);
    }
}
