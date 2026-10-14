package com.actividad5.Actividad5.controllers;

import com.actividad5.Actividad5.Cliente;
import com.actividad5.Actividad5.Pedido;
import com.actividad5.Actividad5.Producto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    @PostMapping("/pedido")
    public String cliente(
            @RequestBody Pedido pedido
    ) {

        Producto[] productos = pedido.getProductos();
        Cliente cliente = pedido.getCliente();

        String nombre = cliente.getNombre();

        Integer totalProductos = 0;
        Double totalPrecio = 0.0;

        for(Producto producto : productos) {

            totalProductos += producto.getCantidad();
            totalPrecio += producto.getCantidad()*producto.getPrecioUnitario();
        }

        return """
                {
                  "cliente": "%s",
                  "totalProductos": %d,
                  "precioTotal": %.2f,
                  "mensaje": "Pedido recibido correctamente"
                }
                """.formatted(nombre, totalProductos, totalPrecio);
    }
}
