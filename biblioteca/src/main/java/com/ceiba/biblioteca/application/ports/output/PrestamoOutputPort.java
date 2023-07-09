package com.ceiba.biblioteca.application.ports.output;

import com.ceiba.biblioteca.domain.model.Prestamo;

import java.util.Optional;

public interface PrestamoOutputPort {

    Optional<Prestamo> buscarPorUsuario(String identificacionUsuario);
    Optional<Prestamo> buscarPorId(Integer id);

    Prestamo guardarPrestamo(Prestamo prestamo);
}
