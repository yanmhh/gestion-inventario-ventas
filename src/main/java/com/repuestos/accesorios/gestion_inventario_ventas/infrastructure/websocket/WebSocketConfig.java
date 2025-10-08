package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final AlertaWebSocketHandler alertaWebSocketHandler;
    private final InventarioWebSocketHandler inventarioWebSocketHandler;
    private final VentasWebSocketHandler ventasWebSocketHandler;
    private final CotizacionesWebSocketHandler cotizacionesWebSocketHandler;

    public WebSocketConfig(AlertaWebSocketHandler alertaWebSocketHandler,
                           InventarioWebSocketHandler inventarioWebSocketHandler,
                           VentasWebSocketHandler ventasWebSocketHandler,
                           CotizacionesWebSocketHandler cotizacionesWebSocketHandler) {
        this.alertaWebSocketHandler = alertaWebSocketHandler;
        this.inventarioWebSocketHandler = inventarioWebSocketHandler;
        this.ventasWebSocketHandler = ventasWebSocketHandler;
        this.cotizacionesWebSocketHandler = cotizacionesWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // endpoints separados por responsabilidad
        registry.addHandler(alertaWebSocketHandler, "/ws/alerts").setAllowedOrigins("*");
        registry.addHandler(inventarioWebSocketHandler, "/ws/inventario").setAllowedOrigins("*");
        registry.addHandler(ventasWebSocketHandler, "/ws/ventas").setAllowedOrigins("*");
        registry.addHandler(cotizacionesWebSocketHandler, "/ws/cotizaciones").setAllowedOrigins("*");

        // si deseas soporte SockJS (fallback), podrías añadir .withSockJS()
        // registry.addHandler(...).setAllowedOrigins("*").withSockJS();
    }
}
