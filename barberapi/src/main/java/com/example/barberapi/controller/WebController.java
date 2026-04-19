package com.example.barberapi.controller;

import com.example.barberapi.dao.ServiciosDAO;
import com.example.barberapi.model.Servicios;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//Anotación @Controller nos permite interactuar con el controlador web de nuestra interfaz
@Controller
public class WebController {

    @GetMapping("/")
    public String mostrarInicio(Model model){
        //Instanciamos DAO y obtenemos los datos reales de MySQL
        ServiciosDAO serviciosDAO = new ServiciosDAO();
        List<Servicios> listaServicios = serviciosDAO.obtenerTodosServicios();

        //Inyectamos la lista en el modelo para el HTML pueda leerlo
        model.addAttribute("servicios", listaServicios);

        //Spring carga el archivo llamada index.html
        return "index";
    }
}
