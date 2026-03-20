# 📮 Guía de Uso - Colección Postman

## Importar la Colección

### Paso 1: Abrir Postman
1. Descarga e instala [Postman](https://www.postman.com/downloads/)
2. Abre la aplicación

### Paso 2: Importar el archivo
1. Haz clic en el botón **Import** (esquina superior izquierda)
2. Selecciona la pestaña **File**
3. Arrastra el archivo `postman_collection.json` o haz clic en **Choose Files**
4. Haz clic en **Import**

## Configurar Variables de Entorno

### Crear Entorno

1. Haz clic en el icono de **Environments** (⚙️) en la esquina superior derecha
2. Haz clic en **Create Environment** o **Add**
3. Nombra el entorno: `Inventario - Local`

### Agregar Variables

| Variable | Initial Value | Current Value |
|----------|--------------|---------------|
| `base_url` | `http://localhost:8080/api` | `http://localhost:8080/api` |
| `access_token` | (vacío) | (vacío) |
| `refresh_token` | (vacío) | (vacío) |

4. Haz clic en **Save**
5. Selecciona el entorno en el dropdown de la esquina superior derecha

### Para Producción

Crea otro entorno llamado `Inventario - Producción`:

| Variable | Value |
|----------|-------|
| `base_url` | `https://tu-dominio.com/api` |
| `access_token` | (vacío) |
| `refresh_token` | (vacío) |

## Flujo de Uso Básico

### 1. Autenticación

#### Primer Uso - Registrar Usuario

1. Navega a: `🔐 Autenticación` → `Registrar Usuario`
2. Modifica los datos en el Body si es necesario:
```json
{
  "persona": {
    "nombre": "Tu Nombre",
    "apellido_paterno": "Tu Apellido",
    "apellido_materno": "Apellido",
    "correo": "tu.email@example.com",
    "telefono": "987654321"
  },
  "contrasenia": "Password123!",
  "rolId": 2,
  "estado": "ACTIVO"
}
```
3. Haz clic en **Send**
4. Deberías recibir una respuesta `200 OK`

**Nota sobre rolId:**
- `1` = ADMIN (acceso completo)
- `2` = VENDEDOR (puede gestionar productos, ventas, clientes)
- `3` = USUARIO (acceso limitado)

#### Iniciar Sesión

1. Navega a: `🔐 Autenticación` → `Login`
2. Modifica el correo y contraseña en el Body:
```json
{
  "correo": "tu.email@example.com",
  "contrasenia": "Password123!"
}
```
3. Haz clic en **Send**
4. **¡Los tokens se guardan automáticamente!** (gracias al script de Tests)

La respuesta será algo como:
```json
{
  "accessToken": "eyJhbGc...",
  "refreshToken": "eyJhbGc...",
  "tokenType": "Bearer",
  "expiresIn": 32400
}
```

### 2. Configurar Categorías y Marcas (Opcional pero Recomendado)

#### Crear Categoría

1. Navega a: `🏷️ Categorías` → `Crear Categoría`
2. Modifica el nombre si deseas:
```json
{
  "nombre": "Llantas"
}
```
3. Haz clic en **Send**
4. Anota el `id` de la respuesta (lo necesitarás para crear productos)

#### Crear Marca

1. Navega a: `🔖 Marcas` → `Crear Marca`
2. Modifica el nombre:
```json
{
  "nombre": "Michelin"
}
```
3. Haz clic en **Send**
4. Anota el `id` de la respuesta

### 3. Crear Productos

1. Navega a: `📦 Productos` → `Crear Producto`
2. Modifica los datos, especialmente `marca_id` y `categoria_id` con los IDs que anotaste:
```json
{
  "nombre": "Llanta Michelin 205/55 R16",
  "descripcion": "Llanta de alto rendimiento para sedán",
  "precio_venta": 450.00,
  "costo_compra": 320.00,
  "stock": 50,
  "stock_minimo": 10,
  "codigo": "LLT-MICH-001",
  "imagen_url": "https://ejemplo.com/imagen.jpg",
  "marca_id": 1,
  "categoria_id": 1
}
```
3. Haz clic en **Send**
4. Respuesta exitosa: `201 Created`

### 4. Crear Clientes

1. Navega a: `👥 Clientes` → `Crear Cliente Natural`
2. Modifica los datos:
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
3. Haz clic en **Send**
4. Anota el `id` del cliente para usarlo en ventas

### 5. Registrar Ventas

1. Navega a: `🛒 Ventas` → `Registrar Venta`
2. Modifica el `cliente_id` y `productoId` con los IDs creados anteriormente:
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
3. Haz clic en **Send**
4. El sistema validará el stock y creará la venta

## Renovar Token Expirado

Si recibes un error `401 Unauthorized`:

1. Navega a: `🔐 Autenticación` → `Refresh Token`
2. Haz clic en **Send**
3. El nuevo `access_token` se guarda automáticamente
4. Reintenta tu petición anterior

## Scripts Automáticos

### Script de Login (Ya incluido)

El request de Login incluye un script que guarda automáticamente los tokens:

```javascript
if (pm.response.code === 200) {
    var jsonData = pm.response.json();
    pm.environment.set("access_token", jsonData.accessToken);
    pm.environment.set("refresh_token", jsonData.refreshToken);
}
```

### Agregar Auto-Refresh (Opcional)

Para refrescar automáticamente el token en cada request:

1. Haz clic derecho en la colección → **Edit**
2. Pestaña **Pre-request Script**
3. Agrega:

```javascript
const accessToken = pm.environment.get("access_token");

// Si no hay token, salta el refresh
if (!accessToken) {
    return;
}

// Decodificar token JWT para ver si está por expirar
function parseJwt(token) {
    try {
        return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
        return null;
    }
}

const decoded = parseJwt(accessToken);

// Si el token expira en menos de 5 minutos, refrescarlo
if (decoded && decoded.exp) {
    const now = Math.floor(Date.now() / 1000);
    const timeToExpire = decoded.exp - now;
    
    if (timeToExpire < 300) { // Menos de 5 minutos
        pm.sendRequest({
            url: pm.environment.get("base_url") + "/auth/refresh",
            method: 'POST',
            header: {
                'Authorization': 'Bearer ' + pm.environment.get("refresh_token")
            }
        }, function (err, res) {
            if (!err && res.code === 200) {
                const jsonData = res.json();
                pm.environment.set("access_token", jsonData.accessToken);
                pm.environment.set("refresh_token", jsonData.refreshToken);
                console.log("Token refreshed automatically");
            }
        });
    }
}
```

## Tests Automatizados

### Agregar Tests a Requests

Puedes agregar tests a cada request para validar respuestas:

```javascript
// Test para validar código de estado
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

// Test para validar estructura de respuesta
pm.test("Response has required fields", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('id');
    pm.expect(jsonData).to.have.property('nombre');
});

// Test para validar tiempo de respuesta
pm.test("Response time is less than 500ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(500);
});
```

## Ejecutar Colección Completa

### Runner Manual

1. Haz clic en el icono de **Runner** (▶️) en la parte superior
2. Arrastra la colección al Runner
3. Selecciona el entorno
4. Haz clic en **Run**

### Con Newman (CLI)

Instalar Newman:
```bash
npm install -g newman
```

Ejecutar colección:
```bash
newman run postman_collection.json \
  -e environment.json \
  --reporters cli,json
```

## Tips y Mejores Prácticas

### 1. Usar Variables para IDs

Después de crear un recurso, guarda su ID como variable:

```javascript
// En el Tab "Tests" del request
if (pm.response.code === 201) {
    var jsonData = pm.response.json();
    pm.environment.set("producto_id", jsonData.id);
}
```

Luego usa `{{producto_id}}` en requests posteriores.

### 2. Organizar Carpetas

- Mantén requests relacionados en la misma carpeta
- Usa nombres descriptivos
- Agrega descripciones a cada request

### 3. Duplicar Requests

Para probar variaciones:
1. Haz clic derecho en el request
2. **Duplicate**
3. Renombra (ej: "Crear Producto - Con Imagen")

### 4. Guardar Ejemplos

Después de ejecutar un request exitoso:
1. Haz clic en **Save Response**
2. Haz clic en **Save as Example**
3. Agrega un nombre descriptivo

## Troubleshooting

### Error: "Could not send request"

**Causa:** La aplicación no está corriendo

**Solución:**
```bash
cd D:\Proyecto_Tesis\gestion-inventario-ventas
gradlew.bat bootRun
```

### Error: 401 Unauthorized

**Causa:** Token inválido o expirado

**Solución:**
1. Ejecuta `Login` nuevamente, o
2. Ejecuta `Refresh Token`

### Error: 403 Forbidden

**Causa:** El usuario no tiene permisos para esa operación

**Solución:**
- Verifica el rol del usuario (debe ser ADMIN o VENDEDOR para la mayoría de operaciones)
- Registra un nuevo usuario con rol 1 (ADMIN)

### Error: 404 Not Found

**Causa:** La ruta o el recurso no existe

**Solución:**
- Verifica que la URL sea correcta
- Verifica que el ID del recurso exista

### Variables no se guardan

**Causa:** Entorno no seleccionado

**Solución:**
1. Verifica que el entorno esté seleccionado en el dropdown superior derecho
2. Haz clic en **Save** después de modificar variables

## Exportar Resultados

### Exportar Colección Actualizada

1. Haz clic derecho en la colección
2. **Export**
3. Selecciona versión (recomendado: Collection v2.1)
4. **Export**

### Exportar Entorno

1. Haz clic en el icono de entornos (⚙️)
2. Haz clic en los tres puntos (...) junto al entorno
3. **Export**

## Recursos Adicionales

- [Documentación de Postman](https://learning.postman.com/docs/)
- [Postman Learning Center](https://learning.postman.com/)
- [Newman CLI Documentation](https://learning.postman.com/docs/running-collections/using-newman-cli/)

---

## Quick Start (Resumen)

1. **Importar** `postman_collection.json`
2. **Crear entorno** con variables:
   - `base_url`: `http://localhost:8080/api`
   - `access_token`: (vacío)
   - `refresh_token`: (vacío)
3. **Seleccionar entorno** en el dropdown
4. **Ejecutar** `Registrar Usuario`
5. **Ejecutar** `Login` (los tokens se guardan automáticamente)
6. **¡Listo!** Puedes usar el resto de endpoints

---

**Versión:** 1.0.0  
**Última actualización:** 13 de Noviembre, 2025

