package com.ceiba.biblioteca.domain.service;

import com.ceiba.biblioteca.application.ports.input.ConsultarPrestamoUseCase;
import com.ceiba.biblioteca.application.ports.input.CrearPrestamoUseCase;
import com.ceiba.biblioteca.application.ports.output.PrestamoOutputPort;
import com.ceiba.biblioteca.domain.exception.PrestamoException;
import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.domain.model.PrestamoFactory;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class PrestamoService implements CrearPrestamoUseCase, ConsultarPrestamoUseCase {

    private final PrestamoOutputPort prestamoOutputPort;

    public static final String PRESTAMO_INVALIDO = "El usuario con identificación %s ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo";
    public static final String USUARIO_NOVALIDO = "Tipo de usuario no permitido en la biblioteca";

    @Override
    public Prestamo consultarPrestamo(Integer id) {
        return prestamoOutputPort.buscarPorId(id).orElseThrow(() -> new PrestamoException("Prestamo No encontrado"));
    }

    @Override
    public Prestamo crearPrestamo(Prestamo prestamo) {
        switch (prestamo.getTipoUsuario()){
            case 1:{
                prestamo = PrestamoFactory.setFechaMaximaDevolucion(prestamo,10);
                break;
            }
            case 2:{
                prestamo = PrestamoFactory.setFechaMaximaDevolucion(prestamo,8);
                break;
            }
            case 3:{
                Optional<Prestamo> prestamoOptional = prestamoOutputPort.buscarPorUsuario(prestamo.getIdentificacionUsuario());

                if (prestamoOptional.isPresent()){
                    throw new PrestamoException(String.format(PRESTAMO_INVALIDO,prestamo.getIdentificacionUsuario()));
                }else {
                    prestamo = PrestamoFactory.setFechaMaximaDevolucion(prestamo,7);
                }
                break;
            }
            default: {
                throw new PrestamoException(USUARIO_NOVALIDO);
            }
        }

        return prestamoOutputPort.guardarPrestamo(prestamo);
    }
}
