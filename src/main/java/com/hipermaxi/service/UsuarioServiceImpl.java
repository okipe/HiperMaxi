package com.hipermaxi.service;

import com.hipermaxi.dtos.UsuarioCreateDTO;
import com.hipermaxi.dtos.UsuarioDTO;
import com.hipermaxi.dtos.UsuarioUpdateDTO;
import com.hipermaxi.mappers.UsuarioMapper;
import com.hipermaxi.model.Usuario;
import com.hipermaxi.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder; // 🔒 Para encriptar passwords

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // 🔐 Inicializamos BCrypt
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return UsuarioMapper.instancia.listaUsuarioAListaUsuarioDTO(
                usuarioRepository.findAll()
        );
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorId(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(UsuarioMapper.instancia::usuarioAUsuarioDTO).orElse(null);
    }

    @Override
    public UsuarioDTO obtenerUsuarioPorUsername(String username) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        return usuario.map(UsuarioMapper.instancia::usuarioAUsuarioDTO).orElse(null);
    }

    @Override
    public UsuarioDTO registrarUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = UsuarioMapper.instancia.usuarioCreateDTOAUsuario(usuarioCreateDTO);

        String passwordEncriptado = passwordEncoder.encode(usuarioCreateDTO.getPassword());
        usuario.setPassword(passwordEncriptado);

        Usuario respuestaEntity = usuarioRepository.save(usuario);

        return UsuarioMapper.instancia.usuarioAUsuarioDTO(respuestaEntity);
    }

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioUpdateDTO usuarioUpdateDTO) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuarioUpdateDTO.getId());

        if (usuarioExistente.isPresent()) {
            Usuario usuario = UsuarioMapper.instancia.usuarioUpdateDTOAUsuario(usuarioUpdateDTO);

            if (usuarioUpdateDTO.getPassword() != null && !usuarioUpdateDTO.getPassword().isEmpty()) {
                String passwordEncriptado = passwordEncoder.encode(usuarioUpdateDTO.getPassword());
                usuario.setPassword(passwordEncriptado);
            } else {
                usuario.setPassword(usuarioExistente.get().getPassword());
            }

            Usuario respuestaEntity = usuarioRepository.save(usuario);
            return UsuarioMapper.instancia.usuarioAUsuarioDTO(respuestaEntity);
        }

        return null; //
    }

    @Override
    public String eliminarUsuario(Integer id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
            return "Usuario eliminado con éxito";
        } else {
            return "Usuario no encontrado";
        }
    }
}