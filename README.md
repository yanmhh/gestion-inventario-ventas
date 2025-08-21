# 📦 Sistema Integral para Gestión de Inventario, Ventas y Cotizaciones

Este proyecto implementa un Sistema Integral para la gestión de inventario, ventas y cotizaciones en una empresa de repuestos automotrices.  
El desarrollo sigue los principios de **Arquitectura Hexagonal (Ports & Adapters)** y **Domain-Driven Design (DDD)**, garantizando un código modular, mantenible y escalable.  

## 🚀 Tecnologías utilizadas

- Java 21  
- Spring Boot 3.2.x  
- Spring Web  
- Spring Data JPA  
- Spring Security con JWT  
- PostgreSQL 15+  
- Flyway (migraciones de base de datos)  
- Gradle (Groovy DSL)  
- Lombok  
- Springdoc OpenAPI (Swagger UI)  
- Arquitectura Hexagonal + DDD  

## 📂 Estructura de Carpetas

El proyecto sigue una estructura basada en **DDD + Hexagonal**:

src/main/java/com/empresa/repuestos/
├─ application/ # Casos de uso y lógica de aplicación
│ ├─ dto/ # Objetos de transferencia de datos
│ ├─ service/ # Servicios de aplicación
│ └─ mapper/ # Conversión entre entidades y DTOs
│
├─ domain/ # Lógica de negocio (núcleo del sistema)
│ ├─ model/ # Entidades y Value Objects
│ ├─ exception/ # Excepciones de domain
│ └─ repository/ # Interfaces de repositorios (puertos)
│
├─ infrastructure/ # Implementaciones técnicas
│ ├─ config/ # Configuración (Spring, seguridad, JWT)
│ ├─ controller/ # Controladores REST (entradas al sistema)
│ ├─ exception/ # Excepciones de domain
│ ├─ repository/ # Implementación de repositorios
│ │ ├─ Jpa/ # Adaptadores de persistencia (JPA)
│ └─ security/ # JWT, filtros, autenticación/autorización
│
└─ shared/ # Excepciones y utilidades compartidas
