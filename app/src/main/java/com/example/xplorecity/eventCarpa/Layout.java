package com.example.xplorecity.eventCarpa;

public class Layout {

    private int[] img;
    private String[] texto;
    private String hint;
    private String[] respuesta;
    private String botontext;
    private String [] opciones;
    private int id;

    public Layout(int[] img, String[] texto, String hint, String[] respuesta, String botontext, String [] opciones) {
        this.img = img;
        this.texto = texto;
        this.hint = hint;
        this.respuesta = respuesta;
        this.botontext = botontext;
        this.opciones = opciones;
        if(respuesta != null) {
            this.id = (int) (Math.random() * respuesta.length);
        }
    }


    public int getImg() {
        return img[id];
    }

    public String getTexto() {
        if(texto != null) {
            if (texto.length == 1) {
                return texto[0];
            }
            return texto[id];
        }
        return null;
    }

    public String getHint() {
        return hint;
    }

    public String getRespuesta() {
        return respuesta[id];
    }

    public String getBotontext() {
        return botontext;
    }

    public String[] getOpciones() { return opciones; }
}
