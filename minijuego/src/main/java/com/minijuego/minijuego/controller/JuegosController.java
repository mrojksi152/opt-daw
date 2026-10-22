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

    @GetMapping("/moneda")
    public String caraCruz(@RequestParam(name = "lanzamientos") Integer lanzamientos) {

        lanzamientos = Integer.parseInt(String.valueOf(lanzamientos));

        if(lanzamientos < 1 || lanzamientos > 100) {
            return "Los lanzamientos tienen que estar entre 1 y 100";
        }

        System.out.println("-----------------------------------------");
        Random rand = new Random();
        int cara = 0;
        int cruz = 0;
        int racha = 1;
        int moneda = rand.nextInt(2);
        System.out.println(moneda);
        int maxRacha = 1;
        int temp = moneda;

        if(moneda == 0) {
            cara++;
        } else {
            cruz++;
        }

        for(int i=0; i<lanzamientos-1; i++) {

            temp = moneda;
            moneda = rand.nextInt(2);
            System.out.println(moneda);
            if(moneda == 0) {
                cara++;
            } else {
                cruz++;
            }

            if(temp != moneda) {
                racha = 0;
                racha++;
            } else {
                racha++;
            }

            if(racha > maxRacha) {
                System.out.println("Racha: " + racha);
                maxRacha = racha;
            }
        }

        return "Caras: " + cara + " | Cruz: " + cruz + " | Mayor racha: " + maxRacha;
    }
}
