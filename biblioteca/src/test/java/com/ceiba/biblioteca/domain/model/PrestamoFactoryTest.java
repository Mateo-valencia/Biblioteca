package com.ceiba.biblioteca.domain.model;

import org.junit.Assert;
import org.junit.Test;

public class PrestamoFactoryTest {

    @Test
    public void ValidarFechaMaxima(){
        Prestamo prestamo = Prestamo.builder()
                .id(1)
                .isbn("DASD154212")
                .identificacionUsuario("C000001")
                .tipoUsuario(1)
                .build();

        Assert.assertEquals(PrestamoFactory.setFechaMaximaDevolucion(prestamo,7).getFechaMaximaDevolucion(), PrestamoFactory.calcularFechaMaximaDevolucion(7) );
    }
}