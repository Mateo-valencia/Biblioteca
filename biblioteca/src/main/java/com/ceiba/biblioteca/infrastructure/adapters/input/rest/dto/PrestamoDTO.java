package com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PrestamoDTO {

    @Size(max = 10, message = "El campo isbn No puede contener mas de 10 Digitos.")
    String isbn;
    @Size(max = 10, message = "El campo identificacionUsuario No puede contener mas de 10 Digitos.")
    String identificacionUsuario;
    Integer tipoUsuario;
}
