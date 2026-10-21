package com.taquilla.taquilla.controllers;

import com.taquilla.taquilla.models.Pelicula;
import com.taquilla.taquilla.models.Reserva;
import com.taquilla.taquilla.models.Respuesta;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservaController {

    Pelicula[] peliculas = {
            new Pelicula(101, "La Casa de Bernarda Alba", 25.0, 10),
            new Pelicula(102, "Yerma", 32., 5),
            new Pelicula(103, "El Tragaluz", 18., 20),
    };

    @GetMapping("/reserva")
    public String reservarPelicula() {
        return "formulario";
    }

    @PostMapping("/reserva")
    @ResponseBody
    public Respuesta verificarReserva(@ModelAttribute Reserva reserva) {

        Pelicula peli = null;

        for(Pelicula pelicula:peliculas) {
            if(pelicula.getId() == reserva.getEventoId()) {
                peli = pelicula;
                break;
            }
        }

        if(peli == null) {
            return new Respuesta( "Codigo de pelicula inexistente", Respuesta.Estado.RECHAZADA);
        }

        if(reserva.getCantidad() > peli.getAsientosLibre()) {
            return new Respuesta( "Aforo insuficiente. Asientos disponibles restantes: " + peli.getAsientosLibre(), Respuesta.Estado.RECHAZADA);
        }

        peli.setAsientosLibre(peli.getAsientosLibre()-reserva.getCantidad());

        double precioUnitario = reserva.getTipoEntrada().equals("vip") ? peli.getPrecioBase()*1.2 : peli.getPrecioBase();
        double importe = reserva.getCantidad() * precioUnitario;
        double descuentoTotal = 0;
        double importeTemp = importe;

        System.out.println(precioUnitario);

        if(reserva.getCantidad() >= 4) {
            descuentoTotal += importeTemp*0.1;
            importeTemp -= descuentoTotal;
        }

        if(reserva.getCodigoCupon().equals("DESCUENTO15")) {
            descuentoTotal += importeTemp*0.15;
            importeTemp -= descuentoTotal;
        }

        return new Respuesta(reserva.getNombreCliente(), Respuesta.Estado.CONFIRMADA, reserva.getCantidad(), precioUnitario, importe, descuentoTotal, importe-descuentoTotal);
    }

    @GetMapping("/cartelera")
    @ResponseBody
    public List<Pelicula> buscarCartelera(
            @RequestParam(name="precioMax", required = true) Double precioMax,
            @RequestParam(name="soloDisponibles", required = true) Boolean soloDisponibles) {

        List<Pelicula> peliculasEncontradas = new ArrayList<>();

        for(Pelicula pelicula:peliculas) {

            if(pelicula.getPrecioBase()<=precioMax) {

                if(soloDisponibles) {

                    if(pelicula.getAsientosLibre()>0) {
                        peliculasEncontradas.add(pelicula);
                    }

                } else {
                    peliculasEncontradas.add(pelicula);
                }
            }
        }

        return peliculasEncontradas;
    }
}
