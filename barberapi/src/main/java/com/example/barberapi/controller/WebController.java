package com.example.barberapi.controller;

import com.example.barberapi.dao.ClientesDAO;
import com.example.barberapi.dao.ReservasDAO;
import com.example.barberapi.dao.ServiciosDAO;
import com.example.barberapi.model.Clientes;
import com.example.barberapi.model.Reservas;
import com.example.barberapi.model.Servicios;
import org.springframework.expression.spel.ast.Literal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Anotación @Controller nos permite interactuar con el controlador web de nuestra interfaz
@Controller
public class WebController {

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        //Instanciamos DAO y obtenemos los datos reales de MySQL

        //Datos de servicios de la DB
        ServiciosDAO serviciosDAO = new ServiciosDAO();
        List<Servicios> listaServicios = serviciosDAO.obtenerTodosServicios();

        //Obtenemos lista de Reservas DB
        ReservasDAO reservasDAO = new ReservasDAO();
        List<Reservas> listaReservas =  reservasDAO.obtenerTodasReservas();

        //Creamos una lista solo con las fechas y horas ya ocupadas en formato JAVASCRIPT
        List<String> reservasOcupadas = new ArrayList<>();
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        //Iteramos la listaReservas
        for (Reservas reserva : listaReservas){
            reservasOcupadas.add(reserva.getFechaYHora().format(formatter));
        }

        //Inyectamos la lista en el modelo para el HTML pueda leerlo
        model.addAttribute("servicios", listaServicios);
        model.addAttribute("reservasOcupadas", reservasOcupadas);

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
            RedirectAttributes redirectAttributes) { // -> Guarda el mensaje por un segundo y luego lo elimina para que no vuelva a salir

        ServiciosDAO serviciosDAO = new ServiciosDAO();


        try {
            System.out.println("entrando al POST /crear-reserva");
            Clientes clientes = new Clientes();
            clientes.setNombre(nombre);
            clientes.setApellido(apellido);
            clientes.setCorreo(correo);
            clientes.setTelefono(telefono);
            System.out.println("Cliente creado: " + clientes.getNombre());

            ClientesController clientesController = new ClientesController();
            long idClienteGenerado = clientesController.addClientes(clientes);

            System.out.println("Cliente insertado correctamente: " + idClienteGenerado);

            Reservas reservas = new Reservas();
            reservas.setFechaYHora(LocalDateTime.parse(fechaYHora));
            reservas.setEstado("Pendiente");
            reservas.setIdCliente(idClienteGenerado);
            reservas.setIdServicio(idServicio);

            ReservasController reservasController = new ReservasController();
            reservasController.addReserva(reservas, correo, telefono);

            //Lógica para la ventana emergente confirmación reserva
            String nombreServicioReservado = "";

            //Iteramos en la lista de todos los Servicios
            for (Servicios servicio : serviciosDAO.obtenerTodosServicios()){
                if (servicio.getIdServicio() == idServicio){
                    nombreServicioReservado = servicio.getNombreServicio();
                    break;
                }
            }

            //Formateamos la fecha para que se vea Bien en el Ticket Emergente (25/05/2026 a la 13h)
            DateTimeFormatter formatterTicket = DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' HH:mm");
            String fechaBonita = LocalDateTime.parse(fechaYHora).format(formatterTicket);


            //Enviamos los datos del Ticket recogidos
            redirectAttributes.addFlashAttribute("reservaExitosa", true);
            redirectAttributes.addFlashAttribute("ticketNombre", nombre + " " + apellido);
            redirectAttributes.addFlashAttribute("ticketServicio", nombreServicioReservado);
            redirectAttributes.addFlashAttribute("ticketFecha", fechaBonita);

            // Redirigimos a la raíz y anclamos en la sección reservas
            //No necesitamos pasar la lista de los servicios, ya que redirect: pasará primero por @GetMapping("/"), que se encargará de buscar los servicios y enviarlos al HTML
            return "redirect:/#reservas";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("Error", e.getMessage());
            return "redirect:/#reservas";
        }
    }
}
