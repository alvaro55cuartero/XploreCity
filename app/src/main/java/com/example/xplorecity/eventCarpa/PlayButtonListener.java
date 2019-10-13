package com.example.xplorecity.eventCarpa;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class PlayButtonListener implements View.OnClickListener {

    private Activity activity;

    public PlayButtonListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(activity, GameActivity.class);
        activity.startActivity(intent);
    }
}
