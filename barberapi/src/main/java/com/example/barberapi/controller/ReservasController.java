package com.example.barberapi.controller;

import com.example.barberapi.dao.ReservasDAO;
import com.example.barberapi.model.Reservas;

import java.sql.SQLException;

public class ReservasController {

    private ReservasDAO reservaDAO;

    public ReservasController(){
        reservaDAO = new ReservasDAO();
    }

    public void addReserva(Reservas reserva) throws Exception{
        System.out.println("procedemos a agregar una reserva");
        try {
            reservaDAO.crearReserva(reserva);
        } catch (SQLException e) {
            throw new Exception("Error al guardar en la bd");
        }

    }
}
