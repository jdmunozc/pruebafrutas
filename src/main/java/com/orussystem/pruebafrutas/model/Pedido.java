package com.orussystem.pruebafrutas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Pedido {

    @Id
    @GeneratedValue
    private final String id;

    @NotNull
    private final int cantidad;

    @NotNull
    private final String frutaId;

    private double precio;

    public Pedido(@JsonProperty("id") String id,
                  @JsonProperty("cantidad") int cantidad,
                  @JsonProperty("frutaId") String frutaId,
                  double precio) {
        this.id = id;
        this.cantidad = cantidad;
        this.frutaId = frutaId;
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getFrutaId() {
        return frutaId;
    }

}
