package com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto;

import lombok.Data;

@Data
public class RespuestaConsultarPrestamoDTO {

    Integer id;
    String isbn;
    String identificacionUsuario;
    Integer tipoUsuario;
    String fechaMaximaDevolucion;
}
