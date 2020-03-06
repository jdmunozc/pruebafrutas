package com.orussystem.pruebafrutas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Fruta {

    @Id
    @GeneratedValue
private final String id;

    @NotBlank
private final String tipo;

    @NotNull
private final int cantidad;

    @NotNull
private final int precio;

    public Fruta(@JsonProperty("id") String id,
                 @JsonProperty("tipo") String tipo,
                 @JsonProperty("cantidad") int cantidad,
                 @JsonProperty("precio") int precio) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getPrecio() {
        return precio;
    }
}
