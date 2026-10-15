package com.actividad6.Actividad6.controllers;

import com.actividad6.Actividad6.model.Login;
import com.actividad6.Actividad6.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class Registro {

    Map<String, String> map = new HashMap<>();

    @GetMapping("/registro")
    public String mostrarFormulario(){
        return "formulario";
    }

    @PostMapping("/registro")
    @ResponseBody
    public String crearUsuario(@ModelAttribute Usuario usuario){

        if(usuario.getNombre().isEmpty()) {
            return "Nombre required";
        }

        if(usuario.getApellidos().isEmpty()) {
            usuario.setApellidos("");
        }

        if(usuario.getCorreo().isEmpty()) {
            return "Correo required";
        }

        if(usuario.getContrasena().isEmpty()) {
            Random rand = new Random();
            usuario.setContrasena(String.valueOf(rand.nextInt(1000000)));
        }

        String contrasena = usuario.getContrasena();

        usuario.setContrasena(String.format("%06d", Integer.parseInt(contrasena)));

        map.put(usuario.getCorreo(), usuario.getContrasena());


        return "Correo: " + usuario.getCorreo() + " Contraseña: " + usuario.getContrasena();
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String realizarLogin(@ModelAttribute Login login) {

        if(!map.containsKey(login.getUsuario())) {
            return "Usuario no existente";
        }else {
            if(map.get(login.getUsuario()).equals(login.getContrasena())) {
                return "Login exitoso";
            } else {
                return "Contraseña incorrecta";
            }
        }
    }
}
