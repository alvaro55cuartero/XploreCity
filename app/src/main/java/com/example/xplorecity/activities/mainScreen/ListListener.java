package com.example.xplorecity.activities.mainScreen;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.xplorecity.activities.event.EventActivity;

public class ListListener implements AdapterView.OnItemClickListener {


    private Activity activity;

    public ListListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(activity, EventActivity.class);
        intent.putExtra("id", i);
        activity.startActivity(intent);
    }
}
