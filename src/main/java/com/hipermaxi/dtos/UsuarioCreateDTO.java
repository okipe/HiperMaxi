package com.hipermaxi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDTO {
    private String username;
    private String password; // Este será encriptado en el Service
}