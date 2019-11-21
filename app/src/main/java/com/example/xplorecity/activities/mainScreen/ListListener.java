package com.example.xplorecity.activities.mainScreen;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.xplorecity.activities.event.EventActivity;
import com.example.xplorecity.eventManager.EventInfo;

public class ListListener implements AdapterView.OnItemClickListener {

    private EventInfo[] eventList;
    private Activity activity;

    public ListListener(Activity activity, EventInfo[] eventList) {
        this.eventList = eventList;
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(activity, EventActivity.class);
        intent.putExtra("id", i);
        activity.startActivity(intent);
    }
}
