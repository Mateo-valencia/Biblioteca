package com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto;

import lombok.Data;

@Data
public class PrestamoDTO {

    String isbn;
    String identificacionUsuario;
    Integer tipoUsuario;
}
