package com.ceiba.biblioteca.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@EqualsAndHashCode
@Builder(toBuilder = true)
@Getter
public class Prestamo {

    final Integer id;
    final String isbn;
    final String identificacionUsuario;
    final Integer tipoUsuario;
    final Date fechaMaximaDevolucion;
}
