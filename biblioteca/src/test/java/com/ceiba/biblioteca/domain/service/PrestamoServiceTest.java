package com.ceiba.biblioteca.domain.service;

import com.ceiba.biblioteca.application.ports.output.PrestamoOutputPort;
import com.ceiba.biblioteca.domain.exception.PrestamoException;
import com.ceiba.biblioteca.domain.model.Prestamo;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrestamoServiceTest {


    @InjectMocks
    private PrestamoService prestamoService;

    @Mock
    private PrestamoOutputPort prestamoOutputPort;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Prestamo prestamo;

    @Before
    public void setUp(){
        prestamo = Prestamo.builder()
                .id(1)
                .isbn("1e1232")
                .identificacionUsuario("1036683385")
                .tipoUsuario(1)
                .fechaMaximaDevolucion(new LocalDate(2022,9,22).toDate())
                .build();

        when(prestamoOutputPort.guardarPrestamo(any()))
                .thenReturn(prestamo);
    }

    @Test
    public void deberiaPrestarUsuarioAfiliado(){
        Prestamo prestamoRespuesta = prestamoService.crearPrestamo(prestamo);

        Assertions.assertNotNull(prestamoRespuesta);
        Assertions.assertNotNull(prestamoRespuesta.getId());
        Assertions.assertNotNull(prestamoRespuesta.getFechaMaximaDevolucion());
    }

    @Test
    public void deberiaPrestarUsuarioEmpleado(){
        Prestamo prestamoEmpleado = prestamo.toBuilder().tipoUsuario(2).build();
        Prestamo prestamoRespuesta = prestamoService.crearPrestamo(prestamoEmpleado);

        Assertions.assertNotNull(prestamoRespuesta);
        Assertions.assertNotNull(prestamoRespuesta.getId());
        Assertions.assertNotNull(prestamoRespuesta.getFechaMaximaDevolucion());
    }

    @Test
    public void deberiaPrestarUsuarioInvitado(){
        when(prestamoOutputPort.buscarPorUsuario(anyString()))
                .thenReturn(Optional.empty());

        Prestamo prestamoEmpleado = prestamo.toBuilder().tipoUsuario(3).build();
        Prestamo prestamoRespuesta = prestamoService.crearPrestamo(prestamoEmpleado);

        Assertions.assertNotNull(prestamoRespuesta);
        Assertions.assertNotNull(prestamoRespuesta.getId());
        Assertions.assertNotNull(prestamoRespuesta.getFechaMaximaDevolucion());
    }

    @Test
    public void deberiaPrestarUsuarioInvitadoException(){
        when(prestamoOutputPort.buscarPorUsuario(anyString()))
                .thenReturn(Optional.of(prestamo));

        Prestamo prestamoEmpleado = prestamo.toBuilder().tipoUsuario(3).build();

        thrown.expect(PrestamoException.class);
        thrown.expectMessage("El usuario con identificación 1036683385 ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");

        Prestamo prestamoRespuesta = prestamoService.crearPrestamo(prestamoEmpleado);
    }

    @Test
    public void deberiaPrestarUsuarioException(){

        Prestamo prestamoEmpleado = prestamo.toBuilder().tipoUsuario(60).build();

        thrown.expect(PrestamoException.class);
        thrown.expectMessage("Tipo de usuario no permitido en la biblioteca");

        Prestamo prestamoRespuesta = prestamoService.crearPrestamo(prestamoEmpleado);
    }

    @Test
    public void deberiaConsultarPrestamo(){
        when(prestamoOutputPort.buscarPorId(anyInt()))
                .thenReturn(Optional.of(prestamo));

        Prestamo prestamoRespuesta = prestamoService.consultarPrestamo(1);

        Assertions.assertNotNull(prestamoRespuesta);
        Assertions.assertNotNull(prestamoRespuesta.getId());
        Assertions.assertNotNull(prestamoRespuesta.getIsbn());
        Assertions.assertNotNull(prestamoRespuesta.getIdentificacionUsuario());
        Assertions.assertNotNull(prestamoRespuesta.getTipoUsuario());
        Assertions.assertNotNull(prestamoRespuesta.getFechaMaximaDevolucion());
    }

    @Test
    public void deberiaConsultarPrestamoException(){
        when(prestamoOutputPort.buscarPorId(anyInt()))
                .thenReturn(Optional.empty());

        thrown.expect(PrestamoException.class);
        thrown.expectMessage("Prestamo No encontrado");

        Prestamo prestamoRespuesta = prestamoService.consultarPrestamo(1);
    }
}