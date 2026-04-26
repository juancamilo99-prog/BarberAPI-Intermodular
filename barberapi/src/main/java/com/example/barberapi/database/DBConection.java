package com.example.barberapi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {

    //Conexión a DB
    //Patrón Singleton
    private static Connection connection;

    public static Connection getConnection(){

        // Si es nula O si alguien la cerró (isClosed), la volvemos a crear
        try {
            if (connection == null || connection.isClosed()) {
                createConnection();
            }
        } catch (SQLException e) {
            System.out.println("Error al comprobar estado de conexión DB: " + e.getMessage());
        }

        return connection;
    }

    private static void createConnection(){
        try {
            //Parámetros de Conexión
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASSWORD");
            String url = System.getenv("DB_URL");


            //Creamos Conexión
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Error: No se pudo establecer conexión con DB");
            e.printStackTrace();
            throw new RuntimeException("Error conectando la BD: " + e.getMessage(), e);
        }

    }
}
