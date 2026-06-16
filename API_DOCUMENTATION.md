# 📖 Documentación Técnica de API - Sistema de Gestión de Inventario

## Índice
- [Información General](#información-general)
- [Autenticación](#autenticación)
- [Endpoints por Módulo](#endpoints-por-módulo)
- [Ejemplos de Uso con cURL](#ejemplos-de-uso-con-curl)
- [Postman Collection](#postman-collection)
- [Códigos de Estado](#códigos-de-estado)

---

## Información General

### Base URL
```
http://localhost:8080/api
```

### Formato de Respuestas
Todas las respuestas son en formato JSON con codificación UTF-8.

### Paginación
Los endpoints que retornan listas usan paginación con los siguientes parámetros:

| Parámetro | Tipo | Default | Descripción |
|-----------|------|---------|-------------|
| `page` | Integer | 0 | Número de página (inicia en 0) |
| `size` | Integer | 20 | Cantidad de elementos por página |
| `sort` | String | - | Campo y dirección de ordenamiento (ej: `nombre,asc`) |

**Ejemplo:**
```
GET /api/productos?page=0&size=10&sort=nombre,desc
```

---

## Autenticación

### Sistema de Autenticación JWT

El sistema utiliza JSON Web Tokens (JWT) para la autenticación. Cada petición autenticada debe incluir el token en el header:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Flujo de Autenticación

```mermaid
sequenceDiagram
    Usuario->>API: POST /api/auth/login
    API->>Usuario: {accessToken, refreshToken}
    Usuario->>API: GET /api/productos (con accessToken)
    API->>Usuario: Lista de productos
    Note over Usuario,API: Token expira
    Usuario->>API: POST /api/auth/refresh (con refreshToken)
    API->>Usuario: Nuevo {accessToken, refreshToken}
```

### Tiempos de Expiración

| Token | Duración |
|-------|----------|
| Access Token | 9 horas |
| Refresh Token | 18 horas |

---

## Endpoints por Módulo

## 🔐 Módulo de Autenticación

### POST /api/auth/register
Registra un nuevo usuario en el sistema.

**Request Body:**
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

**Validaciones:**
- `nombre`: Requerido, máximo 100 caracteres
- `apellido_paterno`: Requerido, máximo 100 caracteres
- `correo`: Requerido, formato email válido, único en el sistema
- `contrasenia`: Requerido, mínimo 8 caracteres
- `rolId`: Requerido (1=ADMIN, 2=VENDEDOR, 3=USUARIO)
- `estado`: Requerido (ACTIVO, INACTIVO)

**Response (200 OK):**
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

**Errores Comunes:**
- `400`: Email ya registrado, datos inválidos
- `500`: Error en el servidor

---

### POST /api/auth/login
Inicia sesión y obtiene tokens de acceso.

**Request Body:**
```json
{
  "correo": "juan.perez@email.com",
  "contrasenia": "Password123!"
}
```

**Response (200 OK):**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqdWFuLnBlcmV6QGVtYWlsLmNvbSIsInJvbGUiOiJWRU5ERURPUiIsImlhdCI6MTY0MzY2NTIwMCwiZXhwIjoxNjQzNjk3NjAwfQ.signature",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqdWFuLnBlcmV6QGVtYWlsLmNvbSIsInR5cGUiOiJyZWZyZXNoIiwiaWF0IjoxNjQzNjY1MjAwLCJleHAiOjE2NDM3MzAwMDB9.signature",
  "tokenType": "Bearer",
  "expiresIn": 32400
}
```

**Errores Comunes:**
- `401`: Credenciales inválidas
- `400`: Formato de datos incorrecto

---

### POST /api/auth/refresh
Renueva el token de acceso usando el refresh token.

**Headers:**
```http
Authorization: Bearer <refresh_token>
```

**Response (200 OK):**
```json
{
  "accessToken": "nuevo_access_token",
  "refreshToken": "nuevo_refresh_token",
  "tokenType": "Bearer",
  "expiresIn": 32400
}
```

**Errores Comunes:**
- `401`: Refresh token inválido o expirado
- `403`: Token no autorizado

---

## 📦 Módulo de Productos

### POST /api/productos
Crea un nuevo producto.

**Permisos:** ADMIN, VENDEDOR

**Headers:**
```http
Authorization: Bearer <access_token>
Content-Type: application/json
```

**Request Body:**
```json
{
  "nombre": "Llanta Michelin 205/55 R16",
  "descripcion": "Llanta de alto rendimiento para sedán, diseñada para proporcionar máxima adherencia y durabilidad",
  "precio_venta": 450.00,
  "costo_compra": 320.00,
  "stock": 50,
  "stock_minimo": 10,
  "codigo": "LLT-MICH-205-55-R16",
  "imagen_url": "https://ejemplo.com/imagenes/llanta-michelin.jpg",
  "marca_id": 1,
  "categoria_id": 1
}
```

**Validaciones:**
- `nombre`: Requerido, máximo 100 caracteres
- `descripcion`: Requerido, máximo 250 caracteres
- `precio_venta`: Requerido, mayor a 0, decimal con 2 decimales
- `costo_compra`: Requerido, mayor a 0, decimal con 2 decimales
- `stock`: Requerido, entero >= 0
- `stock_minimo`: Entero >= 0
- `codigo`: Requerido, máximo 30 caracteres, único
- `imagen_url`: Opcional, URL válida
- `marca_id`: Opcional, debe existir en la base de datos
- `categoria_id`: Opcional, debe existir en la base de datos

**Response (201 Created):**
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
  "imagenUrl": "https://ejemplo.com/imagenes/llanta-michelin.jpg",
  "marca": {
    "id": 1,
    "nombre": "Michelin"
  },
  "categoria": {
    "id": 1,
    "nombre": "Llantas"
  },
  "activo": true
}
```

**Headers de Respuesta:**
```http
Location: /api/productos/1
```

**Errores Comunes:**
- `400`: Datos inválidos, código duplicado
- `401`: No autenticado
- `403`: Sin permisos (rol no autorizado)
- `404`: Marca o categoría no encontrada

---

### GET /api/productos
Lista todos los productos con paginación.

**Permisos:** Autenticado

**Query Parameters:**
```
page=0&size=10&sort=nombre,asc
```

**Response (200 OK):**
```json
{
  "content": [
    {
      "id": 1,
      "nombre": "Llanta Michelin 205/55 R16",
      "descripcion": "Llanta de alto rendimiento",
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
      },
      "activo": true
    }
  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 5,
  "totalElements": 47,
  "last": false,
  "size": 10,
  "number": 0,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "numberOfElements": 10,
  "first": true,
  "empty": false
}
```

---

### GET /api/productos/{id}
Obtiene un producto por su ID.

**Permisos:** Autenticado

**Path Parameters:**
- `id`: Integer - ID del producto

**Response (200 OK):**
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
  },
  "activo": true
}
```

**Errores:**
- `404`: Producto no encontrado

---

### POST /api/productos/filtrar
Busca productos aplicando múltiples filtros.

**Permisos:** Autenticado

**Query Parameters:**
```
page=0&size=10&sort=nombre,asc
```

**Request Body:**
```json
{
  "nombre": "Llanta",
  "categoriaId": 1,
  "marcaId": 1,
  "precioMin": 100.00,
  "precioMax": 500.00,
  "stockMin": 10,
  "codigo": "LLT"
}
```

**Nota:** Todos los campos del filtro son opcionales. Se aplican solo los filtros proporcionados.

**Response (200 OK):**
Similar a GET /api/productos (paginado)

---

### PUT /api/productos/{id}
Actualiza un producto existente.

**Permisos:** ADMIN, VENDEDOR

**Path Parameters:**
- `id`: Integer - ID del producto

**Request Body:**
```json
{
  "nombre": "Llanta Michelin 205/55 R16 - Edición Especial",
  "descripcion": "Llanta actualizada con nueva tecnología",
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

**Response (200 OK):**
Retorna el producto actualizado (mismo formato que POST)

**Errores:**
- `404`: Producto no encontrado
- `400`: Datos inválidos

---

### DELETE /api/productos/{id}
Elimina un producto (eliminación lógica o física según implementación).

**Permisos:** ADMIN, VENDEDOR

**Path Parameters:**
- `id`: Integer - ID del producto

**Response (204 No Content):**
Sin contenido

**Errores:**
- `404`: Producto no encontrado
- `409`: No se puede eliminar (tiene ventas asociadas)

---

## 🛒 Módulo de Ventas

### POST /api/ventas/command
Registra una nueva venta con sus detalles.

**Permisos:** ADMIN, VENDEDOR

**Request Body:**
```json
{
  "cliente_id": 1,
  "fecha": "2025-01-13T10:30:00",
  "estado": "COMPLETADA",
  "tipo_documento": "BOLETA",
  "total": 1350.00,
  "observaciones": "Venta al contado con descuento del 5%",
  "detalles": [
    {
      "productoId": 1,
      "cantidad": 3,
      "precioUnitario": 450.00
    },
    {
      "productoId": 2,
      "cantidad": 2,
      "precioUnitario": 150.00
    }
  ]
}
```

**Validaciones:**
- `cliente_id`: Requerido, debe existir
- `fecha`: Opcional (se usa fecha actual si no se proporciona)
- `estado`: Valores válidos: PENDIENTE, COMPLETADA, CANCELADA
- `tipo_documento`: Requerido (BOLETA, FACTURA, TICKET)
- `total`: Se calcula automáticamente desde los detalles
- `detalles`: Requerido, al menos un detalle
  - `productoId`: Requerido, debe existir y tener stock
  - `cantidad`: Requerido, mayor a 0, no debe exceder stock disponible
  - `precioUnitario`: Requerido, mayor a 0

**Comportamiento:**
1. Valida que el cliente exista
2. Valida stock disponible para cada producto
3. Reduce el stock de cada producto
4. Calcula el total automáticamente
5. Registra el usuario que realizó la venta (desde el token JWT)
6. Crea un registro de movimiento de stock por cada detalle

**Response (201 Created):**
```json
{
  "id": 1,
  "cliente": {
    "id": 1,
    "nombre": "María López Ramírez",
    "documentoIdentidad": "12345678",
    "tipoCliente": "NATURAL"
  },
  "usuario": {
    "id": 1,
    "nombre": "Juan Pérez García",
    "rol": "VENDEDOR"
  },
  "fecha": "2025-01-13T10:30:00",
  "estado": "COMPLETADA",
  "tipoDocumento": "BOLETA",
  "total": 1350.00,
  "observaciones": "Venta al contado con descuento del 5%",
  "detalles": [
    {
      "id": 1,
      "producto": {
        "id": 1,
        "nombre": "Llanta Michelin 205/55 R16",
        "codigo": "LLT-MICH-205-55-R16"
      },
      "cantidad": 3,
      "precioUnitario": 450.00,
      "subtotal": 1350.00
    }
  ]
}
```

**Errores:**
- `400`: Datos inválidos, stock insuficiente
- `404`: Cliente o producto no encontrado

---

### GET /api/ventas/query
Lista todas las ventas.

**Permisos:** ADMIN, VENDEDOR

**Response (200 OK):**
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
    "total": 1350.00
  }
]
```

---

### GET /api/ventas/query/{id}
Obtiene una venta específica con todos sus detalles.

**Permisos:** ADMIN, VENDEDOR

**Response (200 OK):**
Retorna el mismo formato que POST /api/ventas/command

---

## 👥 Módulo de Clientes

### POST /api/clientes/command
Crea un nuevo cliente.

**Request Body (Cliente Natural):**
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

**Request Body (Cliente Jurídico):**
```json
{
  "persona": {
    "nombre": "Carlos",
    "apellido_paterno": "Méndez",
    "apellido_materno": "Torres",
    "correo": "contacto@empresa.com",
    "telefono": "987654321"
  },
  "tipo_cliente": "JURIDICO",
  "razon_social": "Empresa SAC",
  "documento_identidad": "20123456789",
  "ruc_empresa": "20123456789"
}
```

**Validaciones:**
- `tipo_cliente`: NATURAL o JURIDICO
- Para NATURAL: `documento_identidad` requerido (DNI - 8 dígitos)
- Para JURIDICO: `ruc_empresa` requerido (11 dígitos), `razon_social` requerido

**Response (201 Created):**
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

### GET /api/clientes/query
Lista todos los clientes.

**Response (200 OK):**
Array de clientes

---

### GET /api/clientes/query/{id}
Obtiene un cliente por ID.

---

### GET /api/clientes/query/documento?documentoIdentidad=12345678
Busca un cliente por número de documento.

---

### GET /api/clientes/query/ruc?rucEmpresa=20123456789
Busca un cliente por RUC.

---

### GET /api/clientes/query/tipo?tipoCliente=NATURAL
Lista clientes por tipo (NATURAL o JURIDICO).

---

### PUT /api/clientes/command/{id}
Actualiza un cliente existente.

---

### DELETE /api/clientes/command/{id}
Elimina un cliente.

**Response (204 No Content)**

---

## 📊 Módulo de Movimientos de Stock

### POST /api/movimientos-stock/command
Registra un movimiento de inventario.

**Permisos:** ADMIN

**Request Body:**
```json
{
  "producto_id": 1,
  "tipo_movimiento": "ENTRADA",
  "cantidad": 20,
  "fecha": "2025-01-13T14:00:00",
  "referencia": "Compra a proveedor ABC - Orden #12345"
}
```

**Tipos de Movimiento:**
- `ENTRADA`: Incrementa el stock (compras, devoluciones de clientes)
- `SALIDA`: Reduce el stock (ventas, pérdidas, daños)
- `AJUSTE`: Ajuste por inventario físico
- `DEVOLUCION`: Devolución a proveedor

**Validaciones:**
- `producto_id`: Requerido, debe existir
- `tipo_movimiento`: Requerido
- `cantidad`: Requerido, mayor a 0
- `referencia`: Opcional, máximo 255 caracteres

**Comportamiento:**
- ENTRADA: `stock_actual = stock_actual + cantidad`
- SALIDA: `stock_actual = stock_actual - cantidad` (valida que haya stock suficiente)
- AJUSTE: Establece el stock al valor de cantidad
- DEVOLUCION: Reduce el stock

**Response (201 Created):**
```json
{
  "id": 1,
  "producto": {
    "id": 1,
    "nombre": "Llanta Michelin 205/55 R16",
    "codigo": "LLT-MICH-205-55-R16",
    "stockAnterior": 50,
    "stockActual": 70
  },
  "tipoMovimiento": "ENTRADA",
  "cantidad": 20,
  "fecha": "2025-01-13T14:00:00",
  "referencia": "Compra a proveedor ABC - Orden #12345",
  "usuario": {
    "id": 1,
    "nombre": "Juan Pérez"
  }
}
```

---

## 🏷️ Módulo de Categorías

### POST /api/categorias/command
Crea una nueva categoría.

**Permisos:** ADMIN, VENDEDOR

**Request Body:**
```json
{
  "nombre": "Llantas"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nombre": "Llantas"
}
```

---

### GET /api/categorias/query
Lista todas las categorías.

---

### GET /api/categorias/query/{id}
Obtiene una categoría por ID.

---

### PUT /api/categorias/command/{id}
Actualiza una categoría.

---

### DELETE /api/categorias/command/{id}
Elimina una categoría.

---

## 🔖 Módulo de Marcas

### POST /api/marcas/command
Crea una nueva marca.

**Permisos:** ADMIN, VENDEDOR

**Request Body:**
```json
{
  "nombre": "Michelin"
}
```

---

### GET /api/marcas/query
Lista todas las marcas.

---

### GET /api/marcas/query/{id}
Obtiene una marca por ID.

---

### PUT /api/marcas/command/{id}
Actualiza una marca.

---

### DELETE /api/marcas/command/{id}
Elimina una marca.

---

## 👤 Módulo de Personas

### POST /api/personas
Registra una nueva persona.

**Request Body:**
```json
{
  "nombre": "Carlos",
  "apellido_paterno": "Gonzales",
  "apellido_materno": "Morales",
  "correo": "carlos.gonzales@email.com",
  "telefono": "987654321"
}
```

---

## Ejemplos de Uso con cURL

### 1. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "correo": "juan.perez@email.com",
    "contrasenia": "Password123!"
  }'
```

### 2. Crear Producto
```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_ACCESS_TOKEN" \
  -d '{
    "nombre": "Llanta Michelin 205/55 R16",
    "descripcion": "Llanta de alto rendimiento",
    "precio_venta": 450.00,
    "costo_compra": 320.00,
    "stock": 50,
    "stock_minimo": 10,
    "codigo": "LLT-MICH-001",
    "marca_id": 1,
    "categoria_id": 1
  }'
```

### 3. Listar Productos
```bash
curl -X GET "http://localhost:8080/api/productos?page=0&size=10" \
  -H "Authorization: Bearer YOUR_ACCESS_TOKEN"
```

### 4. Registrar Venta
```bash
curl -X POST http://localhost:8080/api/ventas/command \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_ACCESS_TOKEN" \
  -d '{
    "cliente_id": 1,
    "tipo_documento": "BOLETA",
    "observaciones": "Venta al contado",
    "detalles": [
      {
        "productoId": 1,
        "cantidad": 2,
        "precioUnitario": 450.00
      }
    ]
  }'
```

---

## Postman Collection

### Importar en Postman

1. Crear una nueva colección llamada "Sistema Inventario"
2. Crear variables de entorno:
   - `base_url`: `http://localhost:8080/api`
   - `access_token`: (se actualiza después del login)

3. Estructura de carpetas recomendada:
```
Sistema Inventario/
├── Auth/
│   ├── Register
│   ├── Login
│   └── Refresh Token
├── Productos/
│   ├── Crear Producto
│   ├── Listar Productos
│   ├── Obtener Producto
│   ├── Actualizar Producto
│   ├── Eliminar Producto
│   └── Filtrar Productos
├── Ventas/
│   ├── Registrar Venta
│   ├── Listar Ventas
│   └── Obtener Venta
├── Clientes/
│   ├── Crear Cliente
│   ├── Listar Clientes
│   └── Buscar Cliente
└── Configuración/
    ├── Marcas
    └── Categorías
```

### Script Pre-request para Token Automático

Agregar en la configuración de la colección:

```javascript
// Pre-request Script
pm.sendRequest({
    url: pm.environment.get("base_url") + "/auth/refresh",
    method: 'POST',
    header: {
        'Authorization': 'Bearer ' + pm.environment.get("refresh_token")
    }
}, function (err, res) {
    if (!err) {
        var jsonData = res.json();
        pm.environment.set("access_token", jsonData.accessToken);
    }
});
```

---

## Códigos de Estado HTTP

| Código | Significado | Uso |
|--------|-------------|-----|
| 200 | OK | Operación exitosa (GET, PUT) |
| 201 | Created | Recurso creado exitosamente (POST) |
| 204 | No Content | Eliminación exitosa (DELETE) |
| 400 | Bad Request | Datos inválidos o error de validación |
| 401 | Unauthorized | No autenticado (token inválido/expirado) |
| 403 | Forbidden | No autorizado (sin permisos) |
| 404 | Not Found | Recurso no encontrado |
| 409 | Conflict | Conflicto (ej: código duplicado) |
| 500 | Internal Server Error | Error del servidor |

---

## Manejo de Errores

### Estructura de Error Estándar

```json
{
  "timestamp": "2025-01-13T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "El código del producto ya existe",
  "path": "/api/productos"
}
```

### Errores de Validación

```json
{
  "timestamp": "2025-01-13T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Errores de validación",
  "errors": [
    {
      "field": "nombre",
      "message": "El nombre es requerido"
    },
    {
      "field": "precio_venta",
      "message": "El precio debe ser mayor a 0"
    }
  ],
  "path": "/api/productos"
}
```

---

## Tips de Integración

### 1. Manejo de Token Expirado

```javascript
// Ejemplo en JavaScript
async function fetchWithAuth(url, options = {}) {
  let response = await fetch(url, {
    ...options,
    headers: {
      ...options.headers,
      'Authorization': `Bearer ${getAccessToken()}`
    }
  });

  if (response.status === 401) {
    // Token expirado, renovar
    await refreshToken();
    // Reintentar la petición
    response = await fetch(url, {
      ...options,
      headers: {
        ...options.headers,
        'Authorization': `Bearer ${getAccessToken()}`
      }
    });
  }

  return response;
}
```

### 2. Paginación Eficiente

```javascript
// Cargar más resultados
async function loadMoreProducts(page = 0) {
  const response = await fetch(
    `${API_URL}/productos?page=${page}&size=20&sort=nombre,asc`,
    {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    }
  );
  
  const data = await response.json();
  
  return {
    products: data.content,
    hasMore: !data.last,
    totalPages: data.totalPages,
    currentPage: data.number
  };
}
```

### 3. Búsqueda con Debounce

```javascript
// Búsqueda de productos con retardo
let searchTimeout;

function searchProducts(query) {
  clearTimeout(searchTimeout);
  
  searchTimeout = setTimeout(async () => {
    const response = await fetch(`${API_URL}/productos/filtrar`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify({
        nombre: query
      })
    });
    
    const data = await response.json();
    displayProducts(data.content);
  }, 500); // Esperar 500ms después de que el usuario deje de escribir
}
```

---

## Seguridad

### Headers de Seguridad Recomendados

```http
Content-Security-Policy: default-src 'self'
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block
Strict-Transport-Security: max-age=31536000; includeSubDomains
```

### Best Practices

1. **Nunca almacenar tokens en localStorage** (vulnerable a XSS)
2. **Usar httpOnly cookies** para el refresh token
3. **Implementar rate limiting** en el cliente
4. **Validar y sanitizar** todos los inputs
5. **Usar HTTPS** en producción
6. **Implementar CORS** correctamente
7. **Rotar el secret JWT** periódicamente

---

**Versión:** 1.0.0  
**Última actualización:** 13 de Noviembre, 2025

