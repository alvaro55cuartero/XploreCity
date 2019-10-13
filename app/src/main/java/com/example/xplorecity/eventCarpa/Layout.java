package com.example.xplorecity.eventCarpa;

public class Layout {

    private int img;
    private String texto;
    private String hint;
    private String respuesta;

    public Layout(int img, String texto, String hint, String respuesta) {
        this.img = img;
        this.texto = texto;
        this.hint = hint;
        this.respuesta = respuesta;
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
}
