package com.ceiba.biblioteca.infrastructure.adapters.output.persistence.mapper;

import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.infrastructure.adapters.output.persistence.entity.PrestamoEntity;

public interface PrestamoPersistenceMapper {

    PrestamoEntity toPrestamoEntity(Prestamo prestamo);

    Prestamo toPrestamo(PrestamoEntity prestamoEntity);
}
