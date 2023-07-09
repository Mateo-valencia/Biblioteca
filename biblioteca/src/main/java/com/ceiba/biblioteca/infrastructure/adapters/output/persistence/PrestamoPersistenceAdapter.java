package com.ceiba.biblioteca.infrastructure.adapters.output.persistence;

import com.ceiba.biblioteca.application.ports.output.PrestamoOutputPort;
import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.infrastructure.adapters.output.persistence.entity.PrestamoEntity;
import com.ceiba.biblioteca.infrastructure.adapters.output.persistence.mapper.PrestamoPersistenceMapper;
import com.ceiba.biblioteca.infrastructure.adapters.output.persistence.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PrestamoPersistenceAdapter implements PrestamoOutputPort {

    private final PrestamoRepository prestamoRepository;

    private final PrestamoPersistenceMapper prestamoPersistenceMapper;

    @Override
    public Optional<Prestamo> buscarPorUsuario(String identificacionUsuario) {
        Optional<PrestamoEntity> prestamoEntity = prestamoRepository.findByIdentificacionUsuario(identificacionUsuario);

        if(!prestamoEntity.isPresent()) {
            return Optional.empty();
        }

        Prestamo prestamo = prestamoPersistenceMapper.toPrestamo(prestamoEntity.get());
        return Optional.of(prestamo);
    }

    @Override
    public Optional<Prestamo> buscarPorId(Integer id) {
        Optional<PrestamoEntity> prestamoEntity = prestamoRepository.findById(id);

        if(!prestamoEntity.isPresent()) {
            return Optional.empty();
        }

        Prestamo prestamo = prestamoPersistenceMapper.toPrestamo(prestamoEntity.get());
        return Optional.of(prestamo);
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) {
        PrestamoEntity prestamoEntity = prestamoPersistenceMapper.toPrestamoEntity(prestamo);
        prestamoEntity = prestamoRepository.save(prestamoEntity);
        return prestamoPersistenceMapper.toPrestamo(prestamoEntity);
    }
}
