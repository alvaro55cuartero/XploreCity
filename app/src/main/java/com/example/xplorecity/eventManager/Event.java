package com.example.xplorecity.eventManager;


public class Event {

    private int iconImg;            //imagen que representa al evento.
    private String name;            //nombre del evento.
    private boolean inMyEvents;     //true si ya esta añadida la lista de mis eventos.
    private String jsonPath;

    public Event() {
    }

    public int getIconImg() {
        return iconImg;
    }

    public void setIconImg(int iconImg) {
        this.iconImg = iconImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInMyEvents() {
        return inMyEvents;
    }

    public void setInMyEvents(boolean inMyEvents) {
        this.inMyEvents = inMyEvents;
    }
}
