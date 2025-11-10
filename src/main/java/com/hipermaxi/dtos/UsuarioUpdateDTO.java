package com.hipermaxi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateDTO {
    private Integer id;
    private String username;
    private String password; // Opcional: solo si quiere cambiar contraseña
}