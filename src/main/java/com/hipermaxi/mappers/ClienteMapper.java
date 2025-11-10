package com.hipermaxi.mappers;

import com.hipermaxi.dtos.ClienteCreateDTO;
import com.hipermaxi.dtos.ClienteDTO;
import com.hipermaxi.dtos.ClienteUpdateDTO;
import com.hipermaxi.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface ClienteMapper {
    ClienteMapper instancia = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO clienteAClienteDTO(Cliente cliente);

    @Mapping(target = "usuario", ignore = true)
    Cliente clienteCreateDTOACliente(ClienteCreateDTO clienteCreateDTO);

    @Mapping(target = "usuario", ignore = true)
    Cliente clienteUpdateDTOACliente(ClienteUpdateDTO clienteUpdateDTO);

    List<ClienteDTO> listaClienteAListaClienteDTO(List<Cliente> listaCliente);
}