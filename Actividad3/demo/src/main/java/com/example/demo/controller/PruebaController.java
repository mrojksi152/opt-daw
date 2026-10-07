package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PruebaController {

    @GetMapping("/inicio")
    public String inicio(@RequestParam(name = "idioma", required = false) String idioma) {

        if (idioma.equals("english")) {

            return "redirect:/english.html";

        } else if (idioma.equals("espanol")) {

            return "redirect:/espanol.html";

        } else if (idioma.equals("german")) {

            return "redirect:/german.html";

        } else if (idioma.equals("frances")) {

            return "redirect:/frances.html";

        } else {

            return "redirect:/english.html";
        }
    }
}
