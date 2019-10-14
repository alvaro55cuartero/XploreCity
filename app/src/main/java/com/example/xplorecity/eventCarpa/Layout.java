package com.example.xplorecity.eventCarpa;

public class Layout {

    private int img;
    private String texto;
    private String hint;
    private String respuesta;
    private String botontext;



    public Layout(int img, String texto, String hint, String respuesta, String botontext) {
        this.img = img;
        this.texto = texto;
        this.hint = hint;
        this.respuesta = respuesta;
        this.botontext = botontext;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getBotontext() {
        return botontext;
    }

    public void setBotontext(String botontext) {
        this.botontext = botontext;
    }
}
