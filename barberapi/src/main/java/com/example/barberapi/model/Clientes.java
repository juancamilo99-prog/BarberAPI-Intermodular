package com.example.barberapi.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Clientes {
    private int idCliente;
    private String nombre, apellido, correo, telefono;

    @Override
    public String toString() {
        return "Clientes{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
