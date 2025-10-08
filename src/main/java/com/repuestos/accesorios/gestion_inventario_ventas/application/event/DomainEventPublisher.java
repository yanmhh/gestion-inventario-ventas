package com.repuestos.accesorios.gestion_inventario_ventas.application.event;

public interface DomainEventPublisher {
    void publish(Object event);
}
