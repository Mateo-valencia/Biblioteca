package com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RespuestaPrestamoDTO {

    Integer id;
    Date fechaMaximaDevolucion;
}
