package com.example.barberapi.dao;

import com.example.barberapi.database.DBConection;
import com.example.barberapi.database.SchemDBClientes;
import com.example.barberapi.model.Clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientesDAO {

    //Operación Crear
    public boolean insertarClientes(Clientes cliente){
        String sql = String.format("INSERT INTO %S (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                SchemDBClientes.TABLE_CLIENTE,
                SchemDBClientes.COL_NOMBRE, SchemDBClientes.COL_APELLIDO, SchemDBClientes.COL_CORREO, SchemDBClientes.COL_TELEFONO);

        try(Connection connection = DBConection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            //Reemplazamos los "?" por los valores de la DB
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getCorreo());
            preparedStatement.setString(4, cliente.getTelefono());

            //Variable para controlar las filas actualizadas
            int filasAfectadas = preparedStatement.executeUpdate();

            //Si es mayor de 0, se inserto correctamente
            return filasAfectadas > 0;

        }catch (SQLException e){
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }
}
