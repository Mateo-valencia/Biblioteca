package com.ceiba.biblioteca.infrastructure.adapters.input.rest;

import com.ceiba.biblioteca.application.ports.input.ConsultarPrestamoUseCase;
import com.ceiba.biblioteca.application.ports.input.CrearPrestamoUseCase;
import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto.PrestamoDTO;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto.RespuestaConsultarPrestamoDTO;
import com.ceiba.biblioteca.infrastructure.adapters.input.rest.dto.RespuestaPrestamoDTO;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrestamoRestAdapterTest {

    @InjectMocks
    private PrestamoRestAdapter restAdapter;

    @Mock
    private CrearPrestamoUseCase crearPrestamoUseCase;

    @Mock
    private ConsultarPrestamoUseCase consultarPrestamoUseCase;

    private PrestamoDTO prestamoDTO;
    private Prestamo prestamo;

    @Before
    public void setUp(){
        prestamoDTO = new PrestamoDTO();

        prestamoDTO.setIsbn("asdf2312");
        prestamoDTO.setIdentificacionUsuario("1036683385");
        prestamoDTO.setTipoUsuario(1);

        prestamo = Prestamo.builder()
                .id(1)
                .isbn("1e1232")
                .identificacionUsuario("1036683385")
                .tipoUsuario(1)
                .fechaMaximaDevolucion(new LocalDate(2022,9,22).toDate())
                .build();
    }

    @Test
    public void deberiaCrearPrestamo(){
        when(crearPrestamoUseCase.crearPrestamo(any()))
                .thenReturn(prestamo);

        RespuestaPrestamoDTO respuestaPrestamoDTO = restAdapter.crearPrestamo(prestamoDTO);

        Assertions.assertNotNull(respuestaPrestamoDTO);
        Assertions.assertNotNull(respuestaPrestamoDTO.getId());
        Assertions.assertNotNull(respuestaPrestamoDTO.getFechaMaximaDevolucion());
    }

    @Test
    public void deberiaConsultarPrestamo(){
        when(consultarPrestamoUseCase.consultarPrestamo(anyInt()))
                .thenReturn(prestamo);

        RespuestaConsultarPrestamoDTO respuestaConsultarPrestamoDTO = restAdapter.consultarPrestamo(1);

        Assertions.assertNotNull(respuestaConsultarPrestamoDTO);
        Assertions.assertNotNull(respuestaConsultarPrestamoDTO.getId());
        Assertions.assertNotNull(respuestaConsultarPrestamoDTO.getIsbn());
        Assertions.assertNotNull(respuestaConsultarPrestamoDTO.getIdentificacionUsuario());
        Assertions.assertNotNull(respuestaConsultarPrestamoDTO.getTipoUsuario());
        Assertions.assertNotNull(respuestaConsultarPrestamoDTO.getFechaMaximaDevolucion());
    }
}