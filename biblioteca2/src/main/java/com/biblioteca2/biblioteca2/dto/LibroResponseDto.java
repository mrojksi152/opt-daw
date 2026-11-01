package com.biblioteca2.biblioteca2.dto;

public class LibroResponseDto {

    public int id;
    public String titulo;
    public String autor;
    public String isbn;
    public int anioPublicacion;
    public boolean prestado;

    public LibroResponseDto(int id, String titulo, String autor, String isbn, int anioPublicacion, boolean prestado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.prestado = prestado;
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
}
