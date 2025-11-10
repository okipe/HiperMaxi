package com.hipermaxi.dtos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO {
    private Integer id;
    private String dni;
    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private LocalDate fechaNacimiento;
    private String email;
    private UsuarioDTO usuario;
}