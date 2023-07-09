package com.ceiba.biblioteca.application.ports.input;

import com.ceiba.biblioteca.domain.model.Prestamo;

public interface CrearPrestamoUseCase {

    Prestamo crearPrestamo(Prestamo prestamo);
}
