package com.hipermaxi.service;

import com.hipermaxi.dtos.UsuarioCreateDTO;
import com.hipermaxi.dtos.UsuarioDTO;
import com.hipermaxi.dtos.UsuarioUpdateDTO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO obtenerUsuarioPorId(Integer id);

    UsuarioDTO obtenerUsuarioPorUsername(String username);

    UsuarioDTO registrarUsuario(UsuarioCreateDTO usuarioCreateDTO);

    UsuarioDTO actualizarUsuario(UsuarioUpdateDTO usuarioUpdateDTO);

    String eliminarUsuario(Integer id);
}