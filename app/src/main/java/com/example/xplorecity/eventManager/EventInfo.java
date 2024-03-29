package com.example.xplorecity.eventManager;

import java.io.Serializable;

public class EventInfo implements Serializable {
    private String title;
    private String img;
    private String texto;
    private String personas;
    private String premio;
    private String json;
    private String fecha;

    public EventInfo(String title, String img, String texto, String personas, String premio, String json, String fecha) {
        this.title = title;
        this.img = img;
        this.texto = texto;
        this.personas = personas;
        this.premio = premio;
        this.json = json;
        this.fecha = fecha;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

    public String getTexto() {
        return texto;
    }

    public String getPersonas() {
        return personas;
    }

    public String getPremio() {
        return premio;
    }

    public String getJson() {
        return json;
    }

    public String getFecha() {
        return fecha;
    }
}
