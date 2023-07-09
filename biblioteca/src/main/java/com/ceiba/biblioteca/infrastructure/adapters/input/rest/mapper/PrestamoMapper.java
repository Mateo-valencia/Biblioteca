package com.ceiba.biblioteca.infrastructure.adapters.input.rest.mapper;

import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto.PrestamoDTO;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto.RespuestaConsultarPrestamoDTO;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto.RespuestaPrestamoDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PrestamoMapper {

    private PrestamoMapper() {
    }

    public static Prestamo crearPrestamo(PrestamoDTO prestamoDTO){
        return Prestamo.builder()
                .isbn(prestamoDTO.getIsbn())
                .identificacionUsuario(prestamoDTO.getIdentificacionUsuario())
                .tipoUsuario(prestamoDTO.getTipoUsuario())
                .build();
    }

    public static RespuestaPrestamoDTO crearRespuestaCreacion(Prestamo prestamo){
        RespuestaPrestamoDTO respuestaPrestamoDTO = new RespuestaPrestamoDTO();

        respuestaPrestamoDTO.setId(prestamo.getId());
        respuestaPrestamoDTO.setFechaMaximaDevolucion(prestamo.getFechaMaximaDevolucion());

        return respuestaPrestamoDTO;
    }

    public static RespuestaConsultarPrestamoDTO crearRespuestaConsulta(Prestamo prestamo){
        RespuestaConsultarPrestamoDTO respuestaConsultarPrestamoDTO = new RespuestaConsultarPrestamoDTO();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        respuestaConsultarPrestamoDTO.setId(prestamo.getId());
        respuestaConsultarPrestamoDTO.setIsbn(prestamo.getIsbn());
        respuestaConsultarPrestamoDTO.setIdentificacionUsuario(prestamo.getIdentificacionUsuario());
        respuestaConsultarPrestamoDTO.setTipoUsuario(prestamo.getTipoUsuario());
        respuestaConsultarPrestamoDTO.setFechaMaximaDevolucion(dateFormat.format(prestamo.getFechaMaximaDevolucion()));

        return respuestaConsultarPrestamoDTO;
    }
}
