package com.hipermaxi.controller;

import com.hipermaxi.dtos.ClienteCreateDTO;
import com.hipermaxi.dtos.ClienteDTO;
import com.hipermaxi.dtos.ClienteUpdateDTO;
import com.hipermaxi.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    // GET - Listar todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return new ResponseEntity<>(clienteService.listarClientes(), HttpStatus.OK);
    }

    // GET - Obtener cliente por ID
    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable("clienteId") Integer clienteId) {
        ClienteDTO cliente = clienteService.obtenerClientePorId(clienteId);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET - Obtener cliente por DNI
    @GetMapping("/dni/{dni}")
    public ResponseEntity<ClienteDTO> obtenerClientePorDni(@PathVariable("dni") String dni) {
        ClienteDTO cliente = clienteService.obtenerClientePorDni(dni);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST - Registrar nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> registrarCliente(@RequestBody ClienteCreateDTO clienteCreateDTO) {
        ClienteDTO nuevoCliente = clienteService.registrarCliente(clienteCreateDTO);
        if (nuevoCliente != null) {
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // PUT - Actualizar cliente
    @PutMapping
    public ResponseEntity<ClienteDTO> actualizarCliente(@RequestBody ClienteUpdateDTO clienteUpdateDTO) {
        ClienteDTO clienteActualizado = clienteService.actualizarCliente(clienteUpdateDTO);
        if (clienteActualizado != null) {
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Eliminar cliente
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<String> eliminarCliente(@PathVariable("clienteId") Integer clienteId) {
        String resultado = clienteService.eliminarCliente(clienteId);
        if (resultado.contains("éxito")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}