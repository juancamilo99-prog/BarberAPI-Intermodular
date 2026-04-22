package com.example.barberapi.dao;

import com.example.barberapi.database.DBConection;
import com.example.barberapi.database.SchemDB;
import com.example.barberapi.model.Reservas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservasDAO {

    //Creación de una nueva Reserva
    public boolean crearReserva(Reservas reserva){
        // Query de creación de reservas -> ? nos permite acceder a los valores de la tabla reservas.
        String sql = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                SchemDB.TAB_RESERVAS,
                SchemDB.COL_FECHA_HORA, SchemDB.COL_ESTADO, SchemDB.COL_ID_CLIENTE, SchemDB.COL_ID_SERVICO
                );

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
        String sql = String.format("SELECT * FROM %s", SchemDB.TAB_RESERVAS);

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
        String sql = String.format("UPDATE %s SET %s = ? WHERE %s = ?", SchemDB.TAB_RESERVAS, SchemDB.COL_ESTADO, SchemDB.COL_ID_RESERVA
        );

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
        String sql = String.format("DELETE FROM %s WHERE %s", SchemDB.TAB_RESERVAS,  SchemDB.COL_ID_RESERVA);

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
