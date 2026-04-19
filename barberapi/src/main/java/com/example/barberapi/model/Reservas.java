package com.example.barberapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservas {

    private int idReserva;
    private LocalDateTime fechaYHora;
    private String estado;
    private int idCliente, idServicio;

    @Override
    public String toString() {
        return "Reservas{" +
                "idReserva=" + idReserva +
                ", fechaYHora=" + fechaYHora +
                ", estado='" + estado + '\'' +
                '}';
    }
}
