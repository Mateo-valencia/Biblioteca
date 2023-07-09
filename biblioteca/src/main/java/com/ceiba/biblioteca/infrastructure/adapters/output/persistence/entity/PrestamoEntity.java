package com.ceiba.biblioteca.infrastructure.adapters.output.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "tb_prestamo")
public class PrestamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String isbn;

    @Column
    private String identificacionUsuario;

    @Column
    private Integer tipoUsuario;

    @Column
    private Date fechaMaximaDevolucion;
}
