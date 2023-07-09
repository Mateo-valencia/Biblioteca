package com.ceiba.biblioteca.infrastructure.adapters.config;

import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.domain.service.PrestamoService;
import com.ceiba.biblioteca.infrastructure.adapters.output.persistence.PrestamoPersistenceAdapter;
import com.ceiba.biblioteca.infrastructure.adapters.output.persistence.entity.PrestamoEntity;
import com.ceiba.biblioteca.infrastructure.adapters.output.persistence.mapper.PrestamoPersistenceMapper;
import com.ceiba.biblioteca.infrastructure.adapters.output.persistence.repository.PrestamoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PrestamoPersistenceMapper prestamoPersistenceMapper(){
        return new PrestamoPersistenceMapper() {
            @Override
            public PrestamoEntity toPrestamoEntity(Prestamo prestamo) {
                PrestamoEntity prestamoData = new PrestamoEntity();
                prestamoData.setId(prestamo.getId());
                prestamoData.setIsbn(prestamo.getIsbn());
                prestamoData.setIdentificacionUsuario(prestamo.getIdentificacionUsuario());
                prestamoData.setTipoUsuario(prestamo.getTipoUsuario());
                prestamoData.setFechaMaximaDevolucion(prestamo.getFechaMaximaDevolucion());

                return prestamoData;
            }

            @Override
            public Prestamo toPrestamo(PrestamoEntity prestamoEntity) {
                return Prestamo.builder()
                        .id(prestamoEntity.getId())
                        .isbn(prestamoEntity.getIsbn())
                        .identificacionUsuario(prestamoEntity.getIdentificacionUsuario())
                        .tipoUsuario(prestamoEntity.getTipoUsuario())
                        .fechaMaximaDevolucion(prestamoEntity.getFechaMaximaDevolucion())
                        .build();
            }
        };
    }

    @Bean
    public PrestamoPersistenceAdapter prestamoPersistenceAdapter(
            PrestamoRepository prestamoRepository,
            PrestamoPersistenceMapper prestamoPersistenceMapper
    ){
        return new PrestamoPersistenceAdapter(prestamoRepository, prestamoPersistenceMapper);
    }

    @Bean
    public PrestamoService prestamoService(
            PrestamoPersistenceAdapter prestamoPersistenceAdapter
    ){
        return new PrestamoService(prestamoPersistenceAdapter);
    }
}
