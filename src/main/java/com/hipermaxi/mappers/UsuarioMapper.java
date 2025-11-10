package com.hipermaxi.mappers;

import com.hipermaxi.dtos.UsuarioCreateDTO;
import com.hipermaxi.dtos.UsuarioDTO;
import com.hipermaxi.dtos.UsuarioUpdateDTO;
import com.hipermaxi.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper instancia = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO usuarioAUsuarioDTO(Usuario usuario);
    Usuario usuarioDTOAUsuario(UsuarioDTO usuarioDTO);
    Usuario usuarioCreateDTOAUsuario(UsuarioCreateDTO usuarioCreateDTO);
    Usuario usuarioUpdateDTOAUsuario(UsuarioUpdateDTO usuarioUpdateDTO);

    List<UsuarioDTO> listaUsuarioAListaUsuarioDTO(List<Usuario> listaUsuario);
}