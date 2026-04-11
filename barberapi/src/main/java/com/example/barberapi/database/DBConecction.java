package com.example.barberapi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConecction {

    //Conexión a DB
    //Patrón Singleton
    private static Connection connection;

    public static Connection getConnection(){

        if ( connection == null){
            createConnection();
        }

        return connection;
    }

    private static void createConnection(){

        //Parámetros de Conexión
        String user = "root";
        String pass = "root";
        String database = "barberapi";

        //Creamos Conexión

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, pass);
        } catch (SQLException e) {
            System.out.println("Error: No se pudo establecer conexión con DB");
            System.out.println(e.getMessage());
        }

    }
}
