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

src/main/java/com/empresa/repuestos/<br>
├─ application/ # Casos de uso y lógica de aplicación<br>
│ ├─ dto/ # Objetos de transferencia de datos<br>
│ ├─ service/ # Servicios de aplicación<br>
│ └─ mapper/ # Conversión entre entidades y DTOs<br>
│<br>
├─ domain/ # Lógica de negocio (núcleo del sistema)<br>
│ ├─ model/ # Entidades y Value Objects<br>
│ ├─ exception/ # Excepciones de domain<br>
│ └─ repository/ # Interfaces de repositorios (puertos)<br>
│<br>
├─ infrastructure/ # Implementaciones técnicas<br>
│ ├─ config/ # Configuración (Spring, seguridad, JWT)<br>
│ ├─ controller/ # Controladores REST (entradas al sistema)<br>
│ ├─ exception/ # Excepciones de domain<br>
│ ├─ repository/ # Implementación de repositorios<br>
│ │ ├─ Jpa/ # Adaptadores de persistencia (JPA)<br>
│ └─ security/ # JWT, filtros, autenticación/autorización<br>
│<br>
└─ shared/ # Excepciones y utilidades compartidas<br>
