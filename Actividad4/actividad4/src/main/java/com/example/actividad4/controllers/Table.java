package com.example.actividad4.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Table {

    @GetMapping("/tabla")
    public String table(
            @RequestParam(name="filas", required = false) String filas,
            @RequestParam(name="columnas", required = false) String columnas)
    {

        Integer numFilas = 1;
        Integer numColumnas = 1;

        try {
            numColumnas = Integer.parseInt(columnas);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        try {
            numFilas = Integer.parseInt(filas);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        if(numColumnas<1  || numColumnas>20) {
            numColumnas = 1;
        }

        if(numFilas<1  || numFilas>20) {
            numFilas = 1;
        }

        String tabla = "";

        tabla += "<table border=1>";
        tabla += "<tr>";

        for(int i=1; i<=numColumnas; i++) {
            tabla += "<th>Columna " + i + "</th>";
        }

        tabla += "</tr>";

        for(int i=1; i<=numFilas; i++) {

            System.out.println("<tr>");
            for(int j=1; j<=numColumnas; j++) {
                tabla += "<td>Fila " + i + " Columna " + j + "</td>";
            }
            tabla += "</tr>";
        }

        tabla += "</table>";

        return tabla;
    }
}
