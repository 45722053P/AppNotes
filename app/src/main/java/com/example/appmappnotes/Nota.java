package com.example.appmappnotes;


public class Nota {

    private String nota;
    private String localizacion;

    public Nota() {

    }
    public Nota(String nota, String localizacion) {
        this.nota = nota;
        this.localizacion = localizacion;
    }

    public String getlocalizacion() {

        return localizacion;

    }

    public String getnota() {

        return nota;

    }
}
