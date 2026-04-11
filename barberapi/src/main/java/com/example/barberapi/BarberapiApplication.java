package com.example.barberapi;


import com.example.barberapi.dao.ServiciosDAO;
import com.example.barberapi.database.DBConection;
import com.example.barberapi.model.Servicios;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class BarberapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarberapiApplication.class, args);

		//Probamos Conexión a Base de datos
		Connection connection = DBConection.getConnection();


        try {
            System.out.println("Se ha realizado la conexión a la DB: " + connection.getCatalog());
        } catch (SQLException e) {
			System.out.println(e.getMessage());
        }


        //Instanciamos DAO - PRUEBAS
        ServiciosDAO serviciosDAO  = new ServiciosDAO();

        List<Servicios> lista = serviciosDAO.obtenerTodosServicios();

        System.out.println(" LISTADO DE SERVICIOS DESDE LA BASE DE DATOS ");

        if (lista.isEmpty()){
            System.out.println("No se encontro servicios en la base de datos");
        }else {
            for (Servicios servicio:lista){
                System.out.println("ID: " + servicio.getIdServicio() +
                        " | Nombre: " + servicio.getNombreServicio() +
                        " | Precio: " + servicio.getPrecio() + "€");
            }
        }

        System.out.println();
    }

}
