package com.biblioteca2.biblioteca2.dto;

public class ListarResponseDto {

    public int id;
    public String titulo;

    public ListarResponseDto(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
