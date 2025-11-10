package com.hipermaxi.service;

import com.hipermaxi.dtos.ClienteCreateDTO;
import com.hipermaxi.dtos.ClienteDTO;
import com.hipermaxi.dtos.ClienteUpdateDTO;
import com.hipermaxi.mappers.ClienteMapper;
import com.hipermaxi.model.Cliente;
import com.hipermaxi.model.Usuario;
import com.hipermaxi.repository.ClienteRepository;
import com.hipermaxi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository; // 🔗 Para buscar el Usuario

    public ClienteServiceImpl(ClienteRepository clienteRepository,
                              UsuarioRepository usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<ClienteDTO> listarClientes() {
        return ClienteMapper.instancia.listaClienteAListaClienteDTO(
                clienteRepository.findAll()
        );
    }

    @Override
    public ClienteDTO obtenerClientePorId(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ClienteMapper.instancia::clienteAClienteDTO).orElse(null);
    }

    @Override
    public ClienteDTO obtenerClientePorDni(String dni) {
        Optional<Cliente> cliente = clienteRepository.findByDNI(dni);
        return cliente.map(ClienteMapper.instancia::clienteAClienteDTO).orElse(null);
    }

    @Override
    public ClienteDTO registrarCliente(ClienteCreateDTO clienteCreateDTO) {
        Cliente cliente = ClienteMapper.instancia.clienteCreateDTOACliente(clienteCreateDTO);
        Optional<Usuario> usuario = usuarioRepository.findById(clienteCreateDTO.getIdUsuario());

        if (usuario.isPresent()) {
            cliente.setUsuario(usuario.get()); // Asignar la relación
            Cliente respuestaEntity = clienteRepository.save(cliente);

            return ClienteMapper.instancia.clienteAClienteDTO(respuestaEntity);
        } else {
            return null;
        }
    }

    @Override
    public ClienteDTO actualizarCliente(ClienteUpdateDTO clienteUpdateDTO) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(clienteUpdateDTO.getId());

        if (clienteExistente.isPresent()) {
            Cliente cliente = ClienteMapper.instancia.clienteUpdateDTOACliente(clienteUpdateDTO);
            Optional<Usuario> usuario = usuarioRepository.findById(clienteUpdateDTO.getIdUsuario());

            if (usuario.isPresent()) {
                cliente.setUsuario(usuario.get());
                Cliente respuestaEntity = clienteRepository.save(cliente);

                return ClienteMapper.instancia.clienteAClienteDTO(respuestaEntity);
            } else {
                return null;
            }
        }

        return null;
    }

    @Override
    public String eliminarCliente(Integer id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            clienteRepository.delete(clienteOptional.get());
            return "Cliente eliminado con éxito";
        } else {
            return "Cliente no encontrado";
        }
    }
}