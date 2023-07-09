package com.ceiba.biblioteca.domain.model;

import org.joda.time.LocalDate;

import java.time.DayOfWeek;
import java.util.Date;

public class PrestamoFactory {

    private PrestamoFactory() {
    }

    public static Prestamo setFechaMaximaDevolucion(Prestamo prestamo,int dias){
        return prestamo.toBuilder()
                .fechaMaximaDevolucion(calcularFechaMaximaDevolucion(dias))
                .build();
    }

    public static Date calcularFechaMaximaDevolucion(int dias){
        int contadorDias = 0;
        LocalDate fechaCalculada = LocalDate.now();

        while (contadorDias < dias){
            fechaCalculada = fechaCalculada.plusDays(1);
            if (!(DayOfWeek.SATURDAY.getValue() == fechaCalculada.getDayOfWeek() || DayOfWeek.SUNDAY.getValue() == fechaCalculada.getDayOfWeek())){
                contadorDias++;
            }
        }
        return fechaCalculada.toDate();
    }
}
