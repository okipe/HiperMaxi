package com.hipermaxi.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name="tb_cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 8, nullable = false, unique = true)
    private String dni;

    @Column(length = 60, nullable = false)
    private String nombres;

    @Column(length = 200, nullable = false)
    private String apePaterno;

    @Column(length = 200, nullable = false)
    private String apeMaterno;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(length = 200, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false,
                foreignKey = @ForeignKey(name = "FK_CLIENTE_USUARIO"))
    private Usuario usuario;
}
