package com.biblioteca2.biblioteca2.service;

import com.biblioteca2.biblioteca2.dto.ErrorDto;
import com.biblioteca2.biblioteca2.dto.LibroRequestDto;
import com.biblioteca2.biblioteca2.dto.LibroResponseDto;
import com.biblioteca2.biblioteca2.dto.ListarResponseDto;
import com.biblioteca2.biblioteca2.model.Libro;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaService {

    List<Libro> libros = new ArrayList<>();

    public ResponseEntity<?> crearLibro(LibroRequestDto libro) {

        if(libro.getTitulo().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("El Titulo no puede etar vacio"));
        }

        if(libro.getAutor().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("El Autor no puede estar vacio"));
        }

        if(libro.getAnioPublicacion() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto("El año tiene que ser mayor que 0"));
        }

        for(Libro libroBiblioteca:libros) {

            if(libroBiblioteca.getIsbn().equals(libro.getIsbn())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("Este ISBN ya ha sido insertado"));
            }
        }

        libros.add(new Libro(libro.getTitulo(), libro.getAutor(), libro.getIsbn(), libro.getAnioPublicacion()));

        return ResponseEntity.status(HttpStatus.CREATED).body(libro);
    }

    public ResponseEntity<?> devolverLista() {

        List<ListarResponseDto> lista = new ArrayList<>();

        for(Libro libro:libros) {
            lista.add(new ListarResponseDto(libro.getId(), libro.getTitulo()));
        }

        if(lista.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("Biblioteca vacia"));
        }

        return ResponseEntity.ok(lista);
    }

    public ResponseEntity<?> buscarLibro(int id) {

        Optional<LibroResponseDto> libro = null;

        for(Libro libroBiblioteca:libros) {

            if(libroBiblioteca.getId() == id) {
                libro = Optional.of(new LibroResponseDto(
                        libroBiblioteca.getId(),
                        libroBiblioteca.getTitulo(),
                        libroBiblioteca.getAutor(),
                        libroBiblioteca.getIsbn(),
                        libroBiblioteca.getAnioPublicacion(),
                        libroBiblioteca.isPrestado()
                ));
            }
        }

        if(libro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("No hay ningun libro con ese ID"));
        }

        return ResponseEntity.ok(libro);
    }

    public ResponseEntity<?> prestarLibro(int id) {

        Optional<Libro> libro = null;

        for(Libro libroBiblioteca:libros) {

            if(libroBiblioteca.getId() == id) {
                libro = Optional.of(libroBiblioteca);
                break;
            }
        }

        if(libro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("No hay ningun libro con ese ID"));
        }

        if(libro.get().isPrestado()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("No se puede prestar un libro ya prestado"));
        }

        libro.get().setPrestado(true);

        Optional libroPrestado = Optional.of(new LibroResponseDto(
                libro.get().getId(),
                libro.get().getTitulo(),
                libro.get().getAutor(),
                libro.get().getIsbn(),
                libro.get().getAnioPublicacion(),
                libro.get().isPrestado()
        ));

        return ResponseEntity.of(libroPrestado);
    }

    public ResponseEntity<?> devolverLibro(int id) {

        Optional<Libro> libro = null;

        for(Libro libroBiblioteca:libros) {

            if(libroBiblioteca.getId() == id) {
                libro = Optional.of(libroBiblioteca);
                break;
            }
        }

        if(libro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto("No hay ningun libro con ese ID"));
        }

        if(!libro.get().isPrestado()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDto("No se puede devolver un libro ya devuelto"));
        }

        libro.get().setPrestado(false);

        Optional libroDevuelto = Optional.of(new LibroResponseDto(
                libro.get().getId(),
                libro.get().getTitulo(),
                libro.get().getAutor(),
                libro.get().getIsbn(),
                libro.get().getAnioPublicacion(),
                libro.get().isPrestado()
        ));

        return ResponseEntity.of(libroDevuelto);
    }
}
