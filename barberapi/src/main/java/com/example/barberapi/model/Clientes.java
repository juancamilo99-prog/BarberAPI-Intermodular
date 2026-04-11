package com.example.barberapi.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Clientes {
    private String nombre, apellido, correo, telefono;
}
