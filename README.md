# Sistema de Gestión de Inventario y Ventas

## 📋 Descripción del Proyecto

Sistema integral de gestión de inventario, ventas y cotizaciones desarrollado con **Spring Boot 3.5.4** y **Java 21**. Este sistema permite administrar productos, clientes, ventas, movimientos de stock y usuarios con roles diferenciados.

## 🏗️ Arquitectura

El proyecto implementa una **Arquitectura Hexagonal (Puertos y Adaptadores)** con separación de responsabilidades siguiendo los principios de Clean Architecture.

### Capas del Sistema

```
├── domain/              # Lógica de negocio pura
│   ├── model/          # Entidades de dominio
│   ├── repository/     # Interfaces de repositorios (puertos)
│   └── exception/      # Excepciones de dominio
│
├── application/        # Casos de uso
│   ├── dto/           # Data Transfer Objects
│   ├── mapper/        # Mappers entre entidades y DTOs
│   └── service/       # Servicios de aplicación
│
└── infrastructure/     # Adaptadores e implementaciones
    ├── controller/    # Controladores REST
    ├── entity/        # Entidades JPA
    ├── repository/    # Implementaciones de repositorios
    ├── security/      # Configuración de seguridad
    └── config/        # Configuraciones generales
```

## 🚀 Tecnologías Utilizadas

### Backend
- **Java 21** - Lenguaje de programación
- **Spring Boot 3.5.4** - Framework principal
- **Spring Security** - Autenticación y autorización
- **Spring Data JPA** - Persistencia de datos
- **JWT (JSON Web Tokens)** - Gestión de sesiones
- **PostgreSQL** - Base de datos principal
- **H2** - Base de datos en memoria para desarrollo/testing
- **Flyway** - Migración de base de datos
- **Lombok** - Reducción de código boilerplate
- **Gradle** - Herramienta de construcción

### Librerías Adicionales
- **JJWT 0.11.5** - Implementación JWT
- **Spring Boot Actuator** - Monitoreo y métricas
- **Spring Boot Validation** - Validación de datos
- **Apache Commons Text** - Utilidades de texto
- **OWASP Encoder** - Seguridad y codificación

## 📦 Requisitos del Sistema

- **Java Development Kit (JDK) 21** o superior
- **PostgreSQL 12** o superior
- **Gradle 7.6** o superior
- **4 GB RAM** mínimo (8 GB recomendado)
- **Puerto 8080** disponible para la aplicación
- **Puerto 5432** disponible para PostgreSQL

## ⚙️ Configuración

### 1. Base de Datos

#### Crear la base de datos PostgreSQL:

```sql
CREATE DATABASE sistema_inventario_db;
```

#### Configurar credenciales en `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_inventario_db
spring.datasource.username=postgres
spring.datasource.password=toor
```

### 2. Variables de Entorno (Opcional)

Para producción, se recomienda usar variables de entorno:

```bash
DB_URL=jdbc:postgresql://localhost:5432/sistema_inventario_db
DB_USERNAME=postgres
DB_PASSWORD=your_password
JWT_SECRET=your_jwt_secret_key
```

### 3. Configuración JWT

En `application.properties`:

```properties
security.jwt.secret=MiSuperSecretoMuyLargo1234567890123456!
security.jwt.issuer=gestion-inventario
security.jwt.access-ttl=9h
security.jwt.refresh-ttl=18h
```

### 4. CORS Configuration

```properties
app.cors.allowed-origins=http://localhost:4200
```

## 🛠️ Instalación y Ejecución

### Clonar el repositorio

```bash
git clone <repository-url>
cd gestion-inventario-ventas
```

### Compilar el proyecto

```bash
# Windows
gradlew.bat clean build

# Linux/Mac
./gradlew clean build
```

### Ejecutar la aplicación

```bash
# Windows
gradlew.bat bootRun

# Linux/Mac
./gradlew bootRun
```

### Ejecutar tests

```bash
# Windows
gradlew.bat test

# Linux/Mac
./gradlew test
```

La aplicación estará disponible en: `http://localhost:8080`

## 🗄️ Migraciones de Base de Datos (Flyway)

Las migraciones se ejecutan automáticamente al iniciar la aplicación. Se encuentran en:

```
src/main/resources/db/migration/
├── V1__create_persona_table.sql
├── V2__create_roles_usuarios.sql
├── V3__create_cliente_tipoCliente_table.sql
├── V4__create_producto_table.sql
├── V5__create_MovimientoStock_table.sql
├── V6__create_venta_detalle_table.sql
└── V7__create_alertas_table.sql
```

## 🔐 Seguridad y Autenticación

### Sistema de Roles

El sistema implementa control de acceso basado en roles (RBAC):

- **ADMIN** - Acceso completo al sistema
- **VENDEDOR** - Gestión de ventas, productos y clientes
- **USUARIO** - Acceso limitado a consultas

### Autenticación JWT

Todos los endpoints (excepto `/api/auth/login` y `/api/auth/register`) requieren autenticación mediante token JWT en el header:

```
Authorization: Bearer <token>
```

## 📚 Documentación de API

### Base URL

```
http://localhost:8080/api
```

---

## 🔑 Autenticación (Auth)

### 1. Registrar Usuario

**Endpoint:** `POST /api/auth/register`

**Descripción:** Registra un nuevo usuario en el sistema.

**Permisos:** Público

**Body:**
```json
{
  "persona": {
    "nombre": "Juan",
    "apellido_paterno": "Pérez",
    "apellido_materno": "García",
    "correo": "juan.perez@email.com",
    "telefono": "987654321"
  },
  "contrasenia": "Password123!",
  "rolId": 2,
  "estado": "ACTIVO"
}
```

**Respuesta exitosa (200):**
```json
{
  "id": 1,
  "persona": {
    "id": 1,
    "nombre": "Juan",
    "apellidoPaterno": "Pérez",
    "apellidoMaterno": "García",
    "correo": "juan.perez@email.com",
    "telefono": "987654321"
  },
  "rol": "VENDEDOR",
  "estado": "ACTIVO"
}
```

---

### 2. Iniciar Sesión

**Endpoint:** `POST /api/auth/login`

**Descripción:** Autentica un usuario y devuelve tokens de acceso y renovación.

**Permisos:** Público

**Body:**
```json
{
  "correo": "juan.perez@email.com",
  "contrasenia": "Password123!"
}
```

**Respuesta exitosa (200):**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 32400
}
```

---

### 3. Renovar Token

**Endpoint:** `POST /api/auth/refresh`

**Descripción:** Renueva el token de acceso usando el refresh token.

**Headers:**
```
Authorization: Bearer <refresh_token>
```

**Respuesta exitosa (200):**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 32400
}
```

---

## 📦 Productos

### 1. Crear Producto

**Endpoint:** `POST /api/productos`

**Descripción:** Registra un nuevo producto en el sistema.

**Permisos:** ADMIN, VENDEDOR

**Headers:**
```
Authorization: Bearer <token>
```

**Body:**
```json
{
  "nombre": "Llanta Michelin 205/55 R16",
  "descripcion": "Llanta de alto rendimiento para sedán",
  "precio_venta": 450.00,
  "costo_compra": 320.00,
  "stock": 50,
  "stock_minimo": 10,
  "codigo": "LLT-MICH-205-55-R16",
  "imagen_url": "https://ejemplo.com/imagen.jpg",
  "marca_id": 1,
  "categoria_id": 1
}
```

**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "nombre": "Llanta Michelin 205/55 R16",
  "descripcion": "Llanta de alto rendimiento para sedán",
  "precioVenta": 450.00,
  "costoCompra": 320.00,
  "stock": 50,
  "stockMinimo": 10,
  "codigo": "LLT-MICH-205-55-R16",
  "imagenUrl": "https://ejemplo.com/imagen.jpg",
  "marca": {
    "id": 1,
    "nombre": "Michelin"
  },
  "categoria": {
    "id": 1,
    "nombre": "Llantas"
  }
}
```

---

### 2. Listar Productos (Paginado)

**Endpoint:** `GET /api/productos?page=0&size=10&sort=nombre,asc`

**Descripción:** Lista todos los productos con paginación.

**Permisos:** Autenticado

**Query Parameters:**
- `page` (opcional): Número de página (default: 0)
- `size` (opcional): Tamaño de página (default: 20)
- `sort` (opcional): Campo y dirección de ordenamiento

**Respuesta exitosa (200):**
```json
{
  "content": [
    {
      "id": 1,
      "nombre": "Llanta Michelin 205/55 R16",
      "descripcion": "Llanta de alto rendimiento para sedán",
      "precioVenta": 450.00,
      "costoCompra": 320.00,
      "stock": 50,
      "stockMinimo": 10,
      "codigo": "LLT-MICH-205-55-R16",
      "marca": {
        "id": 1,
        "nombre": "Michelin"
      },
      "categoria": {
        "id": 1,
        "nombre": "Llantas"
      }
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 1,
  "totalPages": 1
}
```

---

### 3. Obtener Producto por ID

**Endpoint:** `GET /api/productos/{id}`

**Descripción:** Obtiene los detalles de un producto específico.

**Permisos:** Autenticado

**Respuesta exitosa (200):**
```json
{
  "id": 1,
  "nombre": "Llanta Michelin 205/55 R16",
  "descripcion": "Llanta de alto rendimiento para sedán",
  "precioVenta": 450.00,
  "costoCompra": 320.00,
  "stock": 50,
  "stockMinimo": 10,
  "codigo": "LLT-MICH-205-55-R16",
  "imagenUrl": "https://ejemplo.com/imagen.jpg",
  "marca": {
    "id": 1,
    "nombre": "Michelin"
  },
  "categoria": {
    "id": 1,
    "nombre": "Llantas"
  }
}
```

---

### 4. Buscar Productos con Filtros

**Endpoint:** `POST /api/productos/filtrar?page=0&size=10`

**Descripción:** Busca productos aplicando filtros dinámicos.

**Permisos:** Autenticado

**Body:**
```json
{
  "nombre": "Llanta",
  "categoriaId": 1,
  "marcaId": 1,
  "precioMin": 100.00,
  "precioMax": 500.00,
  "stockMin": 10
}
```

**Respuesta:** Similar a listar productos (paginado)

---

### 5. Actualizar Producto

**Endpoint:** `PUT /api/productos/{id}`

**Descripción:** Actualiza los datos de un producto existente.

**Permisos:** ADMIN, VENDEDOR

**Body:**
```json
{
  "nombre": "Llanta Michelin 205/55 R16 - Nueva versión",
  "descripcion": "Llanta de alto rendimiento actualizada",
  "precio_venta": 480.00,
  "costo_compra": 340.00,
  "stock": 45,
  "stock_minimo": 10,
  "codigo": "LLT-MICH-205-55-R16",
  "imagen_url": "https://ejemplo.com/nueva-imagen.jpg",
  "marca_id": 1,
  "categoria_id": 1
}
```

**Respuesta exitosa (200):** Similar a crear producto

---

### 6. Eliminar Producto

**Endpoint:** `DELETE /api/productos/{id}`

**Descripción:** Elimina un producto del sistema.

**Permisos:** ADMIN, VENDEDOR

**Respuesta exitosa (204):** Sin contenido

---

## 🛒 Ventas

### 1. Registrar Venta

**Endpoint:** `POST /api/ventas/command`

**Descripción:** Registra una nueva venta con sus detalles.

**Permisos:** ADMIN, VENDEDOR

**Body:**
```json
{
  "cliente_id": 1,
  "fecha": "2025-01-13T10:30:00",
  "estado": "COMPLETADA",
  "tipo_documento": "BOLETA",
  "total": 1350.00,
  "observaciones": "Venta al contado",
  "detalles": [
    {
      "productoId": 1,
      "cantidad": 3,
      "precioUnitario": 450.00
    }
  ]
}
```

**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nombre": "María López",
    "documentoIdentidad": "12345678"
  },
  "usuario": {
    "id": 1,
    "nombre": "Juan Pérez"
  },
  "fecha": "2025-01-13T10:30:00",
  "estado": "COMPLETADA",
  "tipoDocumento": "BOLETA",
  "total": 1350.00,
  "observaciones": "Venta al contado",
  "detalles": [
    {
      "id": 1,
      "producto": {
        "id": 1,
        "nombre": "Llanta Michelin 205/55 R16"
      },
      "cantidad": 3,
      "precioUnitario": 450.00,
      "subtotal": 1350.00
    }
  ]
}
```

---

### 2. Listar Ventas

**Endpoint:** `GET /api/ventas/query`

**Descripción:** Lista todas las ventas registradas.

**Permisos:** ADMIN, VENDEDOR

**Respuesta exitosa (200):**
```json
[
  {
    "id": 1,
    "cliente": {
      "id": 1,
      "nombre": "María López"
    },
    "usuario": {
      "id": 1,
      "nombre": "Juan Pérez"
    },
    "fecha": "2025-01-13T10:30:00",
    "estado": "COMPLETADA",
    "tipoDocumento": "BOLETA",
    "total": 1350.00,
    "observaciones": "Venta al contado"
  }
]
```

---

### 3. Obtener Venta por ID

**Endpoint:** `GET /api/ventas/query/{id}`

**Descripción:** Obtiene los detalles completos de una venta.

**Permisos:** ADMIN, VENDEDOR

**Respuesta exitosa (200):** Similar a registrar venta

---

## 👥 Clientes

### 1. Crear Cliente

**Endpoint:** `POST /api/clientes/command`

**Descripción:** Registra un nuevo cliente.

**Permisos:** Autenticado

**Body:**
```json
{
  "persona": {
    "nombre": "María",
    "apellido_paterno": "López",
    "apellido_materno": "Ramírez",
    "correo": "maria.lopez@email.com",
    "telefono": "998877665"
  },
  "tipo_cliente": "NATURAL",
  "razon_social": null,
  "documento_identidad": "12345678",
  "ruc_empresa": null
}
```

**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "persona": {
    "id": 2,
    "nombre": "María",
    "apellidoPaterno": "López",
    "apellidoMaterno": "Ramírez",
    "correo": "maria.lopez@email.com",
    "telefono": "998877665"
  },
  "tipoCliente": "NATURAL",
  "razonSocial": null,
  "documentoIdentidad": "12345678",
  "rucEmpresa": null
}
```

---

### 2. Listar Clientes

**Endpoint:** `GET /api/clientes/query`

**Descripción:** Lista todos los clientes.

**Permisos:** Autenticado

**Respuesta exitosa (200):**
```json
[
  {
    "id": 1,
    "persona": {
      "id": 2,
      "nombre": "María",
      "apellidoPaterno": "López",
      "apellidoMaterno": "Ramírez",
      "correo": "maria.lopez@email.com",
      "telefono": "998877665"
    },
    "tipoCliente": "NATURAL",
    "documentoIdentidad": "12345678"
  }
]
```

---

### 3. Obtener Cliente por ID

**Endpoint:** `GET /api/clientes/query/{id}`

**Permisos:** Autenticado

---

### 4. Buscar Cliente por Documento

**Endpoint:** `GET /api/clientes/query/documento?documentoIdentidad=12345678`

**Permisos:** Autenticado

---

### 5. Buscar Cliente por RUC

**Endpoint:** `GET /api/clientes/query/ruc?rucEmpresa=20123456789`

**Permisos:** Autenticado

---

### 6. Buscar Clientes por Tipo

**Endpoint:** `GET /api/clientes/query/tipo?tipoCliente=NATURAL`

**Descripción:** Busca clientes por tipo (NATURAL o JURIDICO).

**Permisos:** Autenticado

---

### 7. Actualizar Cliente

**Endpoint:** `PUT /api/clientes/command/{id}`

**Permisos:** Autenticado

**Body:** Similar a crear cliente

---

### 8. Eliminar Cliente

**Endpoint:** `DELETE /api/clientes/command/{id}`

**Permisos:** Autenticado

**Respuesta exitosa (204):** Sin contenido

---

## 🏷️ Categorías

### 1. Crear Categoría

**Endpoint:** `POST /api/categorias/command`

**Descripción:** Crea una nueva categoría de productos.

**Permisos:** ADMIN, VENDEDOR

**Body:**
```json
{
  "nombre": "Llantas"
}
```

**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "nombre": "Llantas"
}
```

---

### 2. Listar Categorías

**Endpoint:** `GET /api/categorias/query`

**Permisos:** Autenticado

---

### 3. Obtener Categoría por ID

**Endpoint:** `GET /api/categorias/query/{id}`

**Permisos:** Autenticado

---

### 4. Actualizar Categoría

**Endpoint:** `PUT /api/categorias/command/{id}`

**Permisos:** ADMIN, VENDEDOR

**Body:**
```json
{
  "nombre": "Llantas y Neumáticos"
}
```

---

### 5. Eliminar Categoría

**Endpoint:** `DELETE /api/categorias/command/{id}`

**Permisos:** ADMIN, VENDEDOR

**Respuesta exitosa (204):** Sin contenido

---

## 🔖 Marcas

### 1. Crear Marca

**Endpoint:** `POST /api/marcas/command`

**Descripción:** Crea una nueva marca de productos.

**Permisos:** ADMIN, VENDEDOR

**Body:**
```json
{
  "nombre": "Michelin"
}
```

**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "nombre": "Michelin"
}
```

---

### 2. Listar Marcas

**Endpoint:** `GET /api/marcas/query`

**Permisos:** Autenticado

---

### 3. Obtener Marca por ID

**Endpoint:** `GET /api/marcas/query/{id}`

**Permisos:** Autenticado

---

### 4. Actualizar Marca

**Endpoint:** `PUT /api/marcas/command/{id}`

**Permisos:** ADMIN, VENDEDOR

**Body:**
```json
{
  "nombre": "Michelin Pro"
}
```

---

### 5. Eliminar Marca

**Endpoint:** `DELETE /api/marcas/command/{id}`

**Permisos:** ADMIN, VENDEDOR

**Respuesta exitosa (204):** Sin contenido

---

## 📊 Movimientos de Stock

### 1. Registrar Movimiento

**Endpoint:** `POST /api/movimientos-stock/command`

**Descripción:** Registra un movimiento de stock (entrada o salida).

**Permisos:** ADMIN

**Body:**
```json
{
  "producto_id": 1,
  "tipo_movimiento": "ENTRADA",
  "cantidad": 20,
  "fecha": "2025-01-13T14:00:00",
  "referencia": "Compra a proveedor XYZ"
}
```

**Tipos de Movimiento:**
- `ENTRADA`: Ingreso de productos al inventario
- `SALIDA`: Salida de productos del inventario
- `AJUSTE`: Ajuste por inventario físico
- `DEVOLUCION`: Devolución de productos

**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "producto": {
    "id": 1,
    "nombre": "Llanta Michelin 205/55 R16"
  },
  "tipoMovimiento": "ENTRADA",
  "cantidad": 20,
  "fecha": "2025-01-13T14:00:00",
  "referencia": "Compra a proveedor XYZ",
  "usuario": {
    "id": 1,
    "nombre": "Juan Pérez"
  }
}
```

---

### 2. Listar Movimientos

**Endpoint:** `GET /api/movimientos-stock/query`

**Permisos:** ADMIN, VENDEDOR

---

### 3. Obtener Movimiento por ID

**Endpoint:** `GET /api/movimientos-stock/query/{id}`

**Permisos:** ADMIN, VENDEDOR

---

## 👤 Personas

### 1. Registrar Persona

**Endpoint:** `POST /api/personas`

**Descripción:** Registra una nueva persona en el sistema.

**Permisos:** Autenticado

**Body:**
```json
{
  "nombre": "Carlos",
  "apellido_paterno": "Gonzales",
  "apellido_materno": "Morales",
  "correo": "carlos.gonzales@email.com",
  "telefono": "987654321"
}
```

**Respuesta exitosa (201):**
```json
{
  "id": 3,
  "nombre": "Carlos",
  "apellidoPaterno": "Gonzales",
  "apellidoMaterno": "Morales",
  "correo": "carlos.gonzales@email.com",
  "telefono": "987654321"
}
```

---

### 2. Listar Personas

**Endpoint:** `GET /api/personas`

**Permisos:** Autenticado

---

### 3. Obtener Persona por ID

**Endpoint:** `GET /api/personas/{id}`

**Permisos:** Autenticado

---

### 4. Actualizar Persona

**Endpoint:** `PUT /api/personas/{id}`

**Permisos:** Autenticado

**Body:** Similar a registrar persona

---

### 5. Eliminar Persona

**Endpoint:** `DELETE /api/personas/{id}`

**Permisos:** Autenticado

**Respuesta exitosa (204):** Sin contenido

---

## ❌ Códigos de Error

### Respuestas de Error Estándar

```json
{
  "timestamp": "2025-01-13T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Descripción del error",
  "path": "/api/productos"
}
```

### Códigos HTTP Utilizados

| Código | Descripción |
|--------|-------------|
| 200 | OK - Solicitud exitosa |
| 201 | Created - Recurso creado exitosamente |
| 204 | No Content - Operación exitosa sin contenido |
| 400 | Bad Request - Datos de entrada inválidos |
| 401 | Unauthorized - No autenticado |
| 403 | Forbidden - No autorizado |
| 404 | Not Found - Recurso no encontrado |
| 409 | Conflict - Conflicto con el estado actual |
| 500 | Internal Server Error - Error del servidor |

---

## 🧪 Testing

### Ejecutar todos los tests

```bash
# Windows
gradlew.bat test

# Linux/Mac
./gradlew test
```

### Ver reporte de tests

El reporte HTML se genera en:
```
build/reports/tests/test/index.html
```

---

## 📊 Monitoreo y Actuator

Spring Boot Actuator proporciona endpoints de monitoreo:

### Endpoints disponibles

- `/actuator/health` - Estado de salud de la aplicación
- `/actuator/info` - Información de la aplicación
- `/actuator/metrics` - Métricas de la aplicación

Acceso: `http://localhost:8080/actuator/health`

---

## 🔧 Troubleshooting

### La aplicación no inicia

1. Verificar que PostgreSQL esté en ejecución
2. Verificar credenciales de base de datos en `application.properties`
3. Asegurar que el puerto 8080 esté disponible
4. Verificar que Java 21 esté instalado: `java -version`

### Error de conexión a la base de datos

```
Caused by: org.postgresql.util.PSQLException: Connection refused
```

**Solución:**
- Verificar que PostgreSQL esté corriendo
- Verificar host y puerto en `application.properties`
- Asegurar que la base de datos exista

### Flyway migration failed

```
FlywayException: Validate failed
```

**Solución:**
```bash
# Limpiar la base de datos y volver a migrar
# ADVERTENCIA: Esto eliminará todos los datos
```

### Token JWT expirado

```json
{
  "status": 401,
  "error": "Unauthorized",
  "message": "Token has expired"
}
```

**Solución:** Usar el endpoint `/api/auth/refresh` para renovar el token

---

## 📝 Buenas Prácticas

### 1. Manejo de Tokens
- Almacenar el access token en memoria (variable de estado)
- Almacenar el refresh token de forma segura
- Renovar el token antes de que expire

### 2. Validación de Datos
- Todos los endpoints validan datos de entrada
- Usar los tipos de datos correctos en JSON
- Incluir todos los campos requeridos

### 3. Paginación
- Usar paginación para listados grandes
- Parámetros recomendados: `page=0&size=20`

### 4. Filtros de Búsqueda
- Usar endpoints de filtrado para búsquedas específicas
- Combinar múltiples criterios de búsqueda

---

## 🤝 Contribución

### Pasos para contribuir

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

### Estándares de Código

- Seguir las convenciones de Java
- Documentar métodos públicos con Javadoc
- Escribir tests para nuevas funcionalidades
- Mantener la cobertura de código > 70%

---

## 📄 Licencia

Este proyecto es de uso académico para tesis de grado.

---

## 👨‍💻 Autor

**Sistema de Gestión de Inventario y Ventas**
- Proyecto de Tesis
- Universidad: [Tu Universidad]
- Año: 2025

---

## 📞 Soporte

Para reportar problemas o solicitar características:
- Crear un issue en el repositorio
- Contactar al desarrollador principal

---

## 🔄 Changelog

### Versión 0.0.1-SNAPSHOT (Actual)
- Implementación inicial del sistema
- Módulos: Autenticación, Productos, Ventas, Clientes
- Integración con PostgreSQL
- Sistema de roles y permisos
- Migraciones con Flyway
- Documentación API completa

---

## 🚀 Roadmap

### Próximas características planificadas:

- [ ] Sistema de cotizaciones
- [ ] Reportes y estadísticas avanzadas
- [ ] Integración con sistemas de facturación electrónica
- [ ] Módulo de compras a proveedores
- [ ] Dashboard analítico
- [ ] Exportación de reportes a PDF/Excel
- [ ] Notificaciones en tiempo real (WebSocket)
- [ ] API para integración con e-commerce

---

## 📚 Referencias

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT.io](https://jwt.io/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Flyway Documentation](https://flywaydb.org/documentation/)


