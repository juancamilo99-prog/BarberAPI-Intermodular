package com.example.barberapi;

import com.example.barberapi.database.DBConecction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class BarberapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarberapiApplication.class, args);

		//Probamos Conexión a Base de datos
		Connection connection = DBConecction.getConnection();

        try {
            System.out.println("Se ha realizado la conexión a la DB: " + connection.getCatalog());
        } catch (SQLException e) {
			System.out.println(e.getMessage());
        }
    }

}
