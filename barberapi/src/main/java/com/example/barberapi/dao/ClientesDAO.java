package com.example.barberapi.dao;

import com.example.barberapi.database.DBConection;
import com.example.barberapi.database.SchemDBClientes;
import com.example.barberapi.model.Clientes;

import java.sql.*;

public class ClientesDAO {

    private Connection conexion;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ClientesDAO(){
        conexion = DBConection.getConnection();
    }

    //Operación Crear
    public long insertarClientes(Clientes cliente) throws SQLException{
        long idGeneradoCliente = 0;
        String sql = String.format("INSERT INTO %S (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                SchemDBClientes.TABLE_CLIENTE,
                SchemDBClientes.COL_NOMBRE, SchemDBClientes.COL_APELLIDO, SchemDBClientes.COL_CORREO, SchemDBClientes.COL_TELEFONO);

            //Reemplazamos los "?" por los valores de la DB
            preparedStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getCorreo());
            preparedStatement.setString(4, cliente.getTelefono());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                idGeneradoCliente = resultSet.getLong(1);
            }
            return idGeneradoCliente;
    }

    public Clientes buscarCorreo(String correo, String telefono) throws SQLException{

        String sql = String.format("SELECT * FROM %S WHERE %s = ? OR %s = ?", SchemDBClientes.TABLE_CLIENTE,
                SchemDBClientes.COL_CORREO, SchemDBClientes.COL_TELEFONO);

        preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, correo);
        preparedStatement.setString(2, telefono);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Clientes clientes = new Clientes();
            clientes.setIdCliente(resultSet.getLong(SchemDBClientes.COL_ID_CLIENTE));
            clientes.setNombre(resultSet.getString(SchemDBClientes.COL_NOMBRE));
            clientes.setApellido(resultSet.getString(SchemDBClientes.COL_APELLIDO));
            clientes.setCorreo(resultSet.getString(SchemDBClientes.COL_CORREO));
            clientes.setTelefono(resultSet.getString(SchemDBClientes.COL_TELEFONO));
            return clientes;
        }
        return null;
    }
}
