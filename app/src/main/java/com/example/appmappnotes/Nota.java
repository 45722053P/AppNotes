package com.example.appmappnotes;


public class Nota {
    private String titulo;
    private String descripcion;
    private double longitude;
    private double latitude;

    public Nota() {

    }

    public Nota(String titulo, double latitude, double longitude, String descripcion) {
        this.titulo = titulo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
