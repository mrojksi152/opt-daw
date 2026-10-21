package com.taquilla.taquilla.model;

import com.taquilla.taquilla.enums.Estado;

public class Respuesta {

    public Estado estado;
    public String motivo;
    public String cliente;
    public Integer entradas;
    public Double precioUnitario;
    public Double subtotal;
    public Double descuentoTotal;
    public Double totalPagar;

    public Respuesta() {
    }

    public Respuesta(String motivo, Estado estado) {
        this.motivo = motivo;
        this.estado = estado;
    }

    public Respuesta(String cliente, Integer entradas, Double precioUnitario, Double subtotal, Double descuentoTotal, Double totalPagar, Estado estado) {
        this.cliente = cliente;
        this.entradas = entradas;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.descuentoTotal = descuentoTotal;
        this.totalPagar = totalPagar;
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getEntradas() {
        return entradas;
    }

    public void setEntradas(Integer entradas) {
        this.entradas = entradas;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(Double descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }
}
