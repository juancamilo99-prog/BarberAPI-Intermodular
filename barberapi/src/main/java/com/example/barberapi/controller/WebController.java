package com.example.barberapi.controller;

import com.example.barberapi.dao.ClientesDAO;
import com.example.barberapi.dao.ReservasDAO;
import com.example.barberapi.dao.ServiciosDAO;
import com.example.barberapi.model.Clientes;
import com.example.barberapi.model.Reservas;
import com.example.barberapi.model.Servicios;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
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

    @PostMapping("/crear-reserva")
    public String formularioInicio(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("correo") String correo,
            @RequestParam("telefono") String telefono,
            @RequestParam("fechaYHora") String fechaYHora,
            @RequestParam("idServicio") long idServicio,
            Model model){

        ServiciosDAO serviciosDAO = new ServiciosDAO();


        try {
            System.out.println("entrando al POST /crear-reserva");
            Clientes clientes = new Clientes();
            clientes.setNombre(nombre);
            clientes.setApellido(apellido);
            clientes.setCorreo(correo);
            clientes.setTelefono(telefono);
            System.out.println("cliente creado: "+clientes.getNombre());

            ClientesController clientesController = new ClientesController();
            long idClienteGenerado = clientesController.addClientes(clientes);

            System.out.println("cliente insertado correctamente" + idClienteGenerado);

            Reservas reservas = new Reservas();
            reservas.setFechaYHora(LocalDateTime.parse(fechaYHora));
            reservas.setEstado("PENDIENTE");
            reservas.setIdCliente(idClienteGenerado);
            reservas.setIdServicio(idServicio);

            ReservasDAO reservasDAO = new ReservasDAO();
            reservasDAO.crearReserva(reservas);

            model.addAttribute("mensaje", "Reserva guardada correctamente");
            model.addAttribute("servicios", serviciosDAO.obtenerTodosServicios());
            return "index";
        } catch (Exception e) {
            model.addAttribute("Error", e.getMessage());
            model.addAttribute("servicios", serviciosDAO.obtenerTodosServicios());
            return "index";
        }

    }
}
