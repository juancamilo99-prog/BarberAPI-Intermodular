package com.example.barberapi.dao;

import com.example.barberapi.database.DBConection;
import com.example.barberapi.model.Reservas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservasDAO {

    //Creación de una nueva Reserva
    public boolean crearReserva(Reservas reserva){
        // Query de creación de reservas -> ? nos permite acceder a los valores de la tabla reservas.
        String sql = "INSERT INTO reservas (fecha_y_hora, estado, id_cliente, id_servicio) VALUES (?, ?, ?, ?)";

        try(Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            //getFechaYHora devuelve un LocalDateTime de JAVA
            //Sustituimos  ? por los valores reales de la tabla reservas.
            preparedStatement.setTimestamp(1, Timestamp.valueOf(reserva.getFechaYHora()));
            preparedStatement.setString(2, reserva.getEstado());
            preparedStatement.setInt(3, reserva.getIdCliente());
            preparedStatement.setInt(4, reserva.getIdServicio());

            //Filas afectadas
            int filasAfectadas = preparedStatement.executeUpdate();
            //Si es mayor de 0, se inserto correctamente
            return filasAfectadas > 0;

        }catch (SQLException e){
            System.out.println("Error al crear la reserva: " + e.getMessage());
            return false;
        }
    }


    //Lectura de todas las reservas realizadas
    public List<Reservas> obtenerTodasReservas(){
        List<Reservas> listaReservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";

        try(Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                Reservas reserva = new Reservas();
                reserva.setIdReserva(resultSet.getInt("id_reserva"));
                //Pasamos Timestamp SQL a LocalDateTime JAVA
                reserva.setFechaYHora(resultSet.getTimestamp("fecha_y_hora").toLocalDateTime());
                reserva.setEstado(resultSet.getString("estado"));
                reserva.setIdCliente(resultSet.getInt("id_cliente"));
                reserva.setIdServicio(resultSet.getInt("id_servicio"));

                listaReservas.add(reserva);
            }

        }catch (SQLException e){
            System.out.println("Error al obtener las reservas: " + e.getMessage());
        }

        return listaReservas;
    }

    //Actualizar estado de reservas
    public boolean actualizarEstadoReservas(int idReserva, String nuevoEstado){
        String sql = "UPDATE reservas SET estado = ? WHERE id_reserva = ?";

        try(Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            //Capturamos el id de reservas y cambios el estado de esa reserva
            preparedStatement.setString(1, nuevoEstado);
            preparedStatement.setInt(2, idReserva);

            //Fila afectada
            int filaAfectada =  preparedStatement.executeUpdate();
            return filaAfectada > 0;

        }catch (SQLException e){
            System.out.println("Error al actualizar la reserva: " + e.getMessage());
            return false;
        }
    }

    //Eliminación de una reserva
    public boolean eliminarReserva(int idReserva){
        // Query eliminación de reserva
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";

        try(Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, idReserva);

            return preparedStatement.executeUpdate()>0;


        }catch (SQLException e){
            System.out.println("Error al eliminar la reserva: " + e.getMessage());
            return false;
        }
    }
}
