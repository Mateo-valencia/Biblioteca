package com.ceiba.biblioteca.infrastructure.adapters.input.rest;

import com.ceiba.biblioteca.application.ports.input.ConsultarPrestamoUseCase;
import com.ceiba.biblioteca.application.ports.input.CrearPrestamoUseCase;
import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.mapper.PrestamoMapper;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto.PrestamoDTO;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto.RespuestaConsultarPrestamoDTO;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto.RespuestaPrestamoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestamo")
@RequiredArgsConstructor
public class PrestamoRestAdapter {

    private final CrearPrestamoUseCase crearPrestamoUseCase;
    private final ConsultarPrestamoUseCase consultarPrestamoUseCase;

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public RespuestaConsultarPrestamoDTO consultarPrestamo(@PathVariable Integer id){
        Prestamo prestamo = consultarPrestamoUseCase.consultarPrestamo(id);

        return PrestamoMapper.crearRespuestaConsulta(prestamo);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RespuestaPrestamoDTO crearPrestamo(@RequestBody PrestamoDTO prestamoDTO){
        Prestamo prestamo = crearPrestamoUseCase.crearPrestamo(PrestamoMapper.crearPrestamo(prestamoDTO));

        return PrestamoMapper.crearRespuestaCreacion(prestamo);
    }

}
