package com.taquilla.taquilla.models;

public class Respuesta {

    public enum Estado {CONFIRMADA, RECHAZADA};
    public Estado estadoReserva;
    public String motivo;
    public String cliente;
    public Integer entradas;
    public Double precioUnitario;
    public Double subtotal;
    public Double descuentoTotal;
    public Double totalPagar;

    public Respuesta(String motivo, Estado estadoReserva) {
        this.motivo = motivo;
        this.estadoReserva = estadoReserva;
    }

    public Respuesta(String cliente, Estado estadoReserva, Integer entradas, Double precioUnitario, Double subtotal, Double descuentoTotal, Double totalPagar) {
        this.cliente = cliente;
        this.estadoReserva = estadoReserva;
        this.entradas = entradas;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.descuentoTotal = descuentoTotal;
        this.totalPagar = totalPagar;
    }

    public Estado getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(Estado estadoReserva) {
        this.estadoReserva = estadoReserva;
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
