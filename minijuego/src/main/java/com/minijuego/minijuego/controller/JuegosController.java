package com.minijuego.minijuego.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/juegos")
public class JuegosController {


    @GetMapping("/dados")
    public String tirarDados(
            @RequestParam(name = "cantidad", required = true, defaultValue = "2") Integer cantidad,
            @RequestParam(name = "caras", required = true, defaultValue = "6") Integer caras
    ) {

        cantidad = Integer.parseInt(String.valueOf(cantidad));
        caras = Integer.parseInt(String.valueOf(caras));


        List<Integer> carasArray  = new ArrayList<>(List.of(4,6,8,10,12));

        if((cantidad< 1 || cantidad > 10)) {

            return "La cantidad debe estar entre 1 y 10";
        }

        if (!carasArray.contains(caras)) {
            return "Las caras deben ser 4, 6, 8, 10, 12 o 20";
        }

        List<Integer> dados = new ArrayList<>();

        Random rand = new Random();

        for(int i=0; i<cantidad; i++) {
            dados.add(rand.nextInt(caras)+1);
        }

        int num = dados.get(0);

        boolean esDoble = true;
        int suma = 0;

        for(Integer dado:dados) {
            if(dado!=num) {
                esDoble = false;
            }
            suma += dado;
        }

        if(esDoble) {
            return "Tiradas: " + dados + " Suma: " + suma  + " Doble!";
        } else {
            return "Tiradas: " + dados + " Suma: " + suma;
        }
    }
}
