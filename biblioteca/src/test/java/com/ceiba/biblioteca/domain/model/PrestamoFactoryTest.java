package com.ceiba.biblioteca.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class PrestamoFactoryTest {

    private final int dias;

    public PrestamoFactoryTest(int dias) {
        this.dias = dias;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1}, {2}, {8}, {4}, {5}
        });
    }

    @Test
    public void ValidarFechaMaxima(){
        Prestamo prestamo = Prestamo.builder()
                .id(1)
                .isbn("DASD154212")
                .identificacionUsuario("C000001")
                .tipoUsuario(1)
                .build();

        Assert.assertEquals(PrestamoFactory.setFechaMaximaDevolucion(prestamo,dias).getFechaMaximaDevolucion(), PrestamoFactory.calcularFechaMaximaDevolucion(dias) );
    }
}