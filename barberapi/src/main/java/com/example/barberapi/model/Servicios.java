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

    private String nombre_servicio,descripcion;
    private double precio;
    private int duracion_minutos;
}
