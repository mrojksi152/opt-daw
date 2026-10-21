package com.taquilla.taquilla.controllers;

import com.taquilla.taquilla.enums.Estado;
import com.taquilla.taquilla.model.Pelicula;
import com.taquilla.taquilla.model.Reserva;
import com.taquilla.taquilla.model.Respuesta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaquillaController {

    Pelicula[] peliculas = {
            new Pelicula(101, "La Casa de Bernarda Alba", 25.0, 10),
            new Pelicula(102, "Yerma", 32.0, 5),
            new Pelicula(103, "El Tragaluz", 18.0, 20)
    };

    @GetMapping("/reserva")
    public String formularioReserva() {
        return "formulario";
    }

    @GetMapping("/cartelera")
    @ResponseBody
    public List<Pelicula> consultarCartelera(
            @RequestParam(name="precioMax", required = false) Double precioMax,
            @RequestParam(name="soloDisponibles", required = false) Boolean soloDisponibles) {

        List<Pelicula> peliculasCartelera = new ArrayList<>();

        if(precioMax != null && soloDisponibles != null) {

            for(Pelicula pelicula: peliculas) {
                if(pelicula.getPrecio() <= precioMax && pelicula.getAsientos()>0) {
                    peliculasCartelera.add(pelicula);
                }
            }

        } else if (precioMax != null) {

            for(Pelicula pelicula: peliculas) {
                if(pelicula.getPrecio() <= precioMax) {
                    peliculasCartelera.add(pelicula);
                }
            }

        } else if (soloDisponibles != null) {

            for(Pelicula pelicula: peliculas) {
                if(pelicula.getAsientos()>0) {
                    peliculasCartelera.add(pelicula);
                }
            }

        } else {

            for(Pelicula pelicula: peliculas) {

                peliculasCartelera.add(pelicula);
            }
        }

        return peliculasCartelera;
    }

    @PostMapping("/reserva")
    @ResponseBody
    public Respuesta validarReserva(@ModelAttribute Reserva reserva) {

        Pelicula pelicula = null;

        for(Pelicula pelicula1:peliculas) {
            if(pelicula1.getId() == reserva.getEventoId()) {
                pelicula = pelicula1;
            }
        }

        if(pelicula == null) {
            return new Respuesta("ID de pelicula incorrecto", Estado.RECHAZADO);
        }

        if(reserva.getCantidad() > pelicula.getAsientos()) {
            return new Respuesta("Aforo insuficiente. Asientos disponibles restantes: " + pelicula.getAsientos(), Estado.RECHAZADO);
        }

        double precioUnitario = reserva.getTipoEntrada().equals("vip") ? pelicula.getPrecio()*1.2 : pelicula.getPrecio();
        double importe = precioUnitario * reserva.getCantidad();
        double descuento = 0;
        double importeTemp = importe;

        if(reserva.getCantidad() >= 4) {
            descuento = importeTemp*0.1;
            importeTemp -= descuento;
        }

        if(reserva.getCodigoCupon().equals("DESCUENTO15")) {
            descuento += importeTemp*0.15;
            importeTemp -= descuento;
        }

        return new Respuesta(
                reserva.getNombreCliente(),
                reserva.getCantidad(),
                precioUnitario,
                importe,
                descuento,
                importe-descuento,
                Estado.CONFIRMADO
        );
    }
}
