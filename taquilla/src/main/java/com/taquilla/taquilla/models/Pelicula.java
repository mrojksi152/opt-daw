package com.taquilla.taquilla.models;

public class Pelicula {

    public Integer id;
    public String titulo;
    public Double precioBase;
    public Integer asientosLibre;

    public Pelicula(Integer id, String titulo, Double precioBase, Integer asientosLibre) {
        this.id = id;
        this.titulo = titulo;
        this.precioBase = precioBase;
        this.asientosLibre = asientosLibre;
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

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public Integer getAsientosLibre() {
        return asientosLibre;
    }

    public void setAsientosLibre(Integer asientosLibre) {
        this.asientosLibre = asientosLibre;
    }
}
