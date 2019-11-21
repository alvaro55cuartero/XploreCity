package com.example.xplorecity.eventManager;

import java.io.Serializable;

public class Layout implements Serializable {

    private String img;
    private String contexto;
    private String[] options;
    private String[] texts;
    private String conclusion;
    private String respuesta;


    public Layout(String img, String contexto, String[] options, String[] texts, String conclusion, String respuesta) {
        this.img = img;
        this.contexto = contexto;
        this.options = options;
        this.texts = texts;
        this.respuesta = respuesta;
        this.conclusion = conclusion;
    }

    public String getImg() {
        return img;
    }

    public String getContexto() {
        return contexto;
    }

    public String[] getOptions() {
        return options;
    }

    public String[] getTexts() {
        return texts;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public String getConclusion() {
        return conclusion;
    }
}
