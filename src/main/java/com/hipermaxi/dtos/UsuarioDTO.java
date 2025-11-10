package com.hipermaxi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Integer id;
    private String username;
    // NO incluimos password por seguridad
}