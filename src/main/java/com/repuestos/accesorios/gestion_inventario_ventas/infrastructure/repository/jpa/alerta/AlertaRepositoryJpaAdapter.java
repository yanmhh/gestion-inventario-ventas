package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.alerta;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.alerta.Alerta;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.alerta.AlertaRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.alerta.JpaAlertaEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.alerta.AlertaMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador unificado JPA para el repositorio de Alerta.
 * Consolida las responsabilidades de AlertaReadRepositoryJpaAdapter y AlertaWriteRepositoryJpaAdapter
 * eliminando la duplicación de código mientras mantiene la arquitectura hexagonal.
 */
@Repository
public class AlertaRepositoryJpaAdapter extends BaseRepositoryAdapter<Alerta, JpaAlertaEntity, Integer>
        implements AlertaRepository {

    private final AlertaMapperJpa alertaMapperJpa;

    public AlertaRepositoryJpaAdapter(SpringDataAlertaRepository springDataAlertaRepository,
                                      AlertaMapperJpa alertaMapperJpa) {
        super(springDataAlertaRepository);
        this.alertaMapperJpa = alertaMapperJpa;
    }

    @Override
    protected Alerta toDomain(JpaAlertaEntity entity) {
        return alertaMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaAlertaEntity toEntity(Alerta domain) {
        return alertaMapperJpa.toEntity(domain);
    }

    // ==================== Métodos de Búsqueda ====================
    // findById() heredado de BaseRepositoryAdapter

    // ==================== Métodos de Lectura ====================

    @Override
    public List<Alerta> findByUsuarioId(Integer usuarioId) {
        return ((SpringDataAlertaRepository) repository).findByUsuarioId(usuarioId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Alerta> findByUsuarioIdAndLeido(Integer usuarioId, boolean leido) {
        return ((SpringDataAlertaRepository) repository).findByUsuarioIdAndLeido(usuarioId, leido)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Alerta> findAllNoLeidas() {
        return ((SpringDataAlertaRepository) repository).findByLeido(false)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    // ==================== Métodos de Escritura ====================

    @Override
    public Alerta save(Alerta alerta) {
        JpaAlertaEntity entity = toEntity(alerta);
        JpaAlertaEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }
}
