package com.example.xplorecity.eventManager;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.xplorecity.R;

import java.util.ArrayList;

public class Event {

    private ArrayList<Integer> layouts = new ArrayList<Integer>();


    private int iconImg;            //imagen que representa al evento.
    private String name;            //nombre del evento.
    private boolean inMyEvents;     //true si ya esta añadida la lista de mis eventos.



    private int currentActivity;
    private int numActivity;

    private Activity activity;

    public Event(String name, int iconImg) {
        this.name = name;
        this.iconImg = iconImg;
    }

    public void nextActivity() {
        currentActivity++;
        activity = new Activity(){
            @Override
            protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            }
        };
    }

    public int getIconImg() {
        return iconImg;
    }

    public String getName() {
        return name;
    }

    public boolean isInMyEvents() {
        return inMyEvents;
    }

    public void setInMyEvents(boolean inMyEvents) {
        this.inMyEvents = inMyEvents;
    }
}
