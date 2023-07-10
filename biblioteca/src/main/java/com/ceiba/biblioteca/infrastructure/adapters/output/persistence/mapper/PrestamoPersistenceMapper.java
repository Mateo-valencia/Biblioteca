package com.ceiba.biblioteca.infrastructure.adapters.output.persistence.mapper;

import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.infrastructure.adapters.output.persistence.entity.PrestamoEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PrestamoPersistenceMapper {

    public PrestamoEntity toPrestamoEntity(Prestamo prestamo) {
        PrestamoEntity prestamoData = new PrestamoEntity();
        prestamoData.setId(prestamo.getId());
        prestamoData.setIsbn(prestamo.getIsbn());
        prestamoData.setIdentificacionUsuario(prestamo.getIdentificacionUsuario());
        prestamoData.setTipoUsuario(prestamo.getTipoUsuario());
        prestamoData.setFechaMaximaDevolucion(prestamo.getFechaMaximaDevolucion());

        return prestamoData;
    }

    public Prestamo toPrestamo(PrestamoEntity prestamoEntity) {
        return Prestamo.builder()
                .id(prestamoEntity.getId())
                .isbn(prestamoEntity.getIsbn())
                .identificacionUsuario(prestamoEntity.getIdentificacionUsuario())
                .tipoUsuario(prestamoEntity.getTipoUsuario())
                .fechaMaximaDevolucion(prestamoEntity.getFechaMaximaDevolucion())
                .build();
    }
}
