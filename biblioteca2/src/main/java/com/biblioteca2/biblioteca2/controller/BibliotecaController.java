package com.biblioteca2.biblioteca2.controller;

import com.biblioteca2.biblioteca2.dto.LibroRequestDto;
import com.biblioteca2.biblioteca2.dto.LibroResponseDto;
import com.biblioteca2.biblioteca2.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BibliotecaController {

    @Autowired
    private BibliotecaService biliotecaService;

    @PostMapping("/libros")
    public ResponseEntity<?> crearLibro(@RequestBody LibroRequestDto libro) {

        return biliotecaService.crearLibro(libro);
    }

    @GetMapping("/libros")
    public ResponseEntity<?> mostrarLibros() {

        return biliotecaService.devolverLista();
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<?> mostrarLibro(@PathVariable int id) {

        return biliotecaService.buscarLibro(id);
    }

    @PostMapping("/libros/{id}/prestamo")
    public ResponseEntity<?> prestarLibro(@PathVariable int id) {

        return biliotecaService.prestarLibro(id);
    }

    @PostMapping("/libros/{id}/devolucion")
    public ResponseEntity<?> devolverLibro(@PathVariable int id) {

        return biliotecaService.devolverLibro(id);
    }
}
