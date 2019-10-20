package com.example.xplorecity.tools;

import com.example.xplorecity.eventCarpa.Layout;
import com.google.gson.Gson;

import java.util.List;

public class Event {
    public Layout[] layouts;


    public Event(String json) {
        Gson gson = new Gson();
        layouts = gson.fromJson(json, Layout[].class);
    }

    public Event(Layout[] layouts) {
        this.layouts = layouts;
    }

}
