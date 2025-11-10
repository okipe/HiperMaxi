package com.hipermaxi.service;

import com.hipermaxi.dtos.ClienteCreateDTO;
import com.hipermaxi.dtos.ClienteDTO;
import com.hipermaxi.dtos.ClienteUpdateDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> listarClientes();

    ClienteDTO obtenerClientePorId(Integer id);

    ClienteDTO obtenerClientePorDni(String dni);

    ClienteDTO registrarCliente(ClienteCreateDTO clienteCreateDTO);

    ClienteDTO actualizarCliente(ClienteUpdateDTO clienteUpdateDTO);

    String eliminarCliente(Integer id);
}