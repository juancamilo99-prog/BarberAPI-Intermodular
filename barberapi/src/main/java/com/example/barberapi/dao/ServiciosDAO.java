package com.example.barberapi.dao;

import com.example.barberapi.database.DBConection;
import com.example.barberapi.database.SchemDB;
import com.example.barberapi.database.SchemDBServices;
import com.example.barberapi.model.Clientes;
import com.example.barberapi.model.Servicios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiciosDAO {

    /*Clase de Acceso a Datos -> Ejecuta las queries SQL*/

    //Operación Lectura
    public List<Servicios> obtenerTodosServicios(){

        List<Servicios> listaServicios = new ArrayList<>();
        String sql = String.format("SELECT * FROM  %s", SchemDBServices.TABLE_SERVICIOS); // -> Devuelve todos los elementos de la tabla servicios

        try(Connection connection = DBConection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){

                Servicios servicio = new Servicios(); // Instanciamos un nuevo servicio

                //Atacamos con la instancia nueva instancia de servicio a cada columna de la base de datos cuya finalidad
                //sería que nos devuelva la información de la tabla servicios
                servicio.setIdServicio(resultSet.getInt("id_servicio"));
                servicio.setNombreServicio(resultSet.getString("nombre_servicio"));
                servicio.setDescripcion(resultSet.getString("descripcion"));
                servicio.setPrecio(resultSet.getDouble("precio"));
                servicio.setDuracionMinutos(resultSet.getInt("duracion_minutos"));

                //Los elementos que hemos atacado lo agregamos a la lista
                listaServicios.add(servicio);
            }

        }catch (SQLException e){
            System.out.println("Error: No se pudo obtener la tabla servicios: " + e.getMessage());
        }


        return listaServicios;
    }

}
