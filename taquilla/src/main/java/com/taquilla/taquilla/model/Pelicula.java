package com.taquilla.taquilla.model;

public class Pelicula {

    public Integer id;
    public String titulo;
    public Double precio;
    public Integer asientos;

    public Pelicula() {
    }

    public Pelicula(Integer id, String titulo, Double precio, Integer asientos) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.asientos = asientos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getAsientos() {
        return asientos;
    }

    public void setAsientos(Integer asientos) {
        this.asientos = asientos;
    }
}
