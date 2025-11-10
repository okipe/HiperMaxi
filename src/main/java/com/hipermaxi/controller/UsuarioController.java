package com.hipermaxi.controller;

import com.hipermaxi.dtos.UsuarioCreateDTO;
import com.hipermaxi.dtos.UsuarioDTO;
import com.hipermaxi.dtos.UsuarioUpdateDTO;
import com.hipermaxi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // GET - Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    // GET - Obtener usuario por ID
    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable("usuarioId") Integer usuarioId) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(usuarioId);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET - Obtener usuario por Username
    @GetMapping("/username/{username}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorUsername(@PathVariable("username") String username) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorUsername(username);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST - Registrar nuevo usuario
    @PostMapping
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioDTO nuevoUsuario = usuarioService.registrarUsuario(usuarioCreateDTO);
        if (nuevoUsuario != null) {
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // PUT - Actualizar usuario
    @PutMapping
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(usuarioUpdateDTO);
        if (usuarioActualizado != null) {
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //  DELETE - Eliminar usuario
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("usuarioId") Integer usuarioId) {
        String resultado = usuarioService.eliminarUsuario(usuarioId);
        if (resultado.contains("éxito")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}