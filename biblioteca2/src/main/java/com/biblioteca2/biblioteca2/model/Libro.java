package com.biblioteca2.biblioteca2.model;

public class Libro {

    public static int incrementId = 1;
    public int id;
    public String titulo;
    public String autor;
    public String isbn;
    public int anioPublicacion;
    public boolean prestado;
    public String notasInternas;

    public Libro(String titulo, String autor, String isbn, int anioPublicacion) {
        this.id = incrementId;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        prestado = false;
        incrementId++;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public void setNotasInternas(String notasInternas) {
        this.notasInternas = notasInternas;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public String getNotasInternas() {
        return notasInternas;
    }
}
