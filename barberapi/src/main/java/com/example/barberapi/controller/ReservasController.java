package com.example.barberapi.controller;

import com.example.barberapi.dao.ReservasDAO;
import com.example.barberapi.model.Reservas;

import java.sql.SQLException;
import java.time.LocalDate;

public class ReservasController {

    private ReservasDAO reservaDAO;

    public ReservasController(){
        reservaDAO = new ReservasDAO();
    }

    public void addReserva(Reservas reserva, String correo, String telefono) throws Exception{
        System.out.println("procedemos a agregar una reserva");
        try {

            LocalDate fecha = reserva.getFechaYHora().toLocalDate();
            boolean yaEstaReserva = reservaDAO.existeReservaCliente(correo,telefono, fecha);
            if (yaEstaReserva){
                System.out.println("reserva ya existe");
                throw new Exception("Ya existe una reserva con ese correo");
            }
            if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
                System.out.println("correo no valido");
                throw new Exception("El correo no tiene un formato valido.");
            }
            reservaDAO.crearReserva(reserva);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al guardar en la bd");
        }

    }
}
