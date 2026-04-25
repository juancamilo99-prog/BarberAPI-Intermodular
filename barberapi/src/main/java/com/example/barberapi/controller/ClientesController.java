package com.example.barberapi.controller;

import com.example.barberapi.dao.ClientesDAO;
import com.example.barberapi.model.Clientes;

import java.sql.SQLException;
import java.util.List;

public class ClientesController {

    private ClientesDAO clientesDAO;
    private List<Clientes> listaClientes;

    public ClientesController() {
        clientesDAO = new ClientesDAO();
    }

    public long addClientes(Clientes clientes) throws Exception{
        //agregamos un usuario
        if (clientes.getNombre() == null || clientes.getNombre().trim().isEmpty()){
            throw new Exception("El nombre del cliente es obligatorio");
        }
        if (clientes.getApellido() == null || clientes.getApellido().trim().isEmpty()){
            throw new Exception("El apellido del cliente es obligatorio");
        }
        if (clientes.getTelefono() == null || clientes.getTelefono().trim().isEmpty()){
            throw new Exception("El telefono del cliente es obligatorio");
        }
        if (clientes.getCorreo() == null || clientes.getCorreo().trim().isEmpty()){
            throw new Exception("El correo del cliente es obligatorio");
        }
        try {
            Clientes clienteExistente = clientesDAO.buscarCorreo(clientes.getCorreo(), clientes.getTelefono());
            if (clienteExistente != null){
                return clienteExistente.getIdCliente();
            }
            if (!clientes.getCorreo().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
                System.out.println("correo no valido");
                throw new Exception("El correo no tiene un formato valido.");
            }

            return clientesDAO.insertarClientes(clientes);

        } catch (SQLException e) {
            throw new Exception("Error al guardar el cliente en la BD");
        }

    }
}
