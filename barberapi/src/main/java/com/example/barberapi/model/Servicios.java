package com.example.barberapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servicios {

    private long idServicio;
    private String nombreServicio,descripcion;
    private double precio;
    private int duracionMinutos;

    @Override
    public String toString() {
        return "Servicios{" +
                "idServicio=" + idServicio +
                ", nombreServicio='" + nombreServicio + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", duracionMinutos=" + duracionMinutos +
                '}';
    }
}
