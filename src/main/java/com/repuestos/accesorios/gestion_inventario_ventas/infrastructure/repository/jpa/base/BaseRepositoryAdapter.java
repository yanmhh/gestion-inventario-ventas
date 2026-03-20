package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Adapter base genérico que implementa operaciones comunes de repositorio.
 * Elimina duplicación de código en los adapters de repositorio.
 * Proporciona conversión automática entre entidades JPA y modelos de dominio.
 * 
 * @param <DOMAIN> Tipo de la entidad del dominio
 * @param <ENTITY> Tipo de la entidad JPA
 * @param <ID> Tipo del identificador
 */
public abstract class BaseRepositoryAdapter<DOMAIN, ENTITY, ID> {
    
    protected final JpaRepository<ENTITY, ID> repository;
    
    protected BaseRepositoryAdapter(JpaRepository<ENTITY, ID> repository) {
        this.repository = repository;
    }
    
    /**
     * Convierte una entidad JPA al modelo de dominio.
     * Debe ser implementado por cada adapter específico.
     */
    protected abstract DOMAIN toDomain(ENTITY entity);
    
    /**
     * Convierte un modelo de dominio a entidad JPA.
     * Debe ser implementado por cada adapter específico.
     */
    protected abstract ENTITY toEntity(DOMAIN domain);
    
    /**
     * Busca una entidad por su ID.
     * Implementación común para evitar duplicación.
     */
    public Optional<DOMAIN> findById(ID id) {
        return repository.findById(id)
                .map(this::toDomain);
    }
    
    /**
     * Verifica si existe una entidad con el ID dado.
     */
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
}
