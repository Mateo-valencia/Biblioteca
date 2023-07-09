package com.ceiba.biblioteca.infrastructure.adapters.output.persistence.repository;

import com.ceiba.biblioteca.infrastructure.adapters.output.persistence.entity.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Integer>{

    Optional<PrestamoEntity> findByIdentificacionUsuario(String identificacionUsuario);
}
