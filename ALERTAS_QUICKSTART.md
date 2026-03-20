# 🚀 Inicio Rápido - Sistema de Alertas

## ⚡ Configuración en 5 Minutos

### 1. Configurar Email SMTP

Edita `src/main/resources/application.properties`:

```properties
# Gmail (recomendado)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=tu-email@gmail.com
spring.mail.password=tu-password-de-aplicacion  # Ver abajo cómo obtenerla

# Email destino
app.alertas.email-destino=admin@inventario.com
```

#### 📧 Obtener Contraseña de Aplicación de Gmail:

1. Ve a https://myaccount.google.com/security
2. Activa "Verificación en dos pasos"
3. Busca "Contraseñas de aplicaciones"
4. Genera nueva contraseña para "Correo"
5. Copia la contraseña de 16 caracteres

### 2. Iniciar la Aplicación

```bash
./gradlew bootRun
```

### 3. Probar el Sistema

#### Opción A: Con Postman

1. **Login** para obtener token:
```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "correo": "admin@test.com",
  "contrasenia": "admin123"
}
```

2. **Crear producto con stock bajo**:
```http
POST http://localhost:8080/api/productos
Authorization: Bearer {tu-token}
Content-Type: application/json

{
  "nombre": "Producto Test",
  "descripcion": "Para probar alertas",
  "precioVenta": 100.00,
  "costoCompra": 50.00,
  "stock": 12,
  "stockMinimo": 10,
  "codigo": "TEST001",
  "marcaId": 1,
  "categoriaId": 1
}
```

3. **Registrar salida que baje el stock**:
```http
POST http://localhost:8080/api/movimientos-stock
Authorization: Bearer {tu-token}
Content-Type: application/json

{
  "productoId": 1,
  "tipo": "SALIDA",
  "cantidad": 5,
  "motivo": "VENTA",
  "notas": "Probando alertas"
}
```

4. **Verificar alerta creada**:
```http
GET http://localhost:8080/api/alertas/mis-alertas/no-leidas
Authorization: Bearer {tu-token}
```

#### Opción B: Con cURL

```bash
# 1. Login
TOKEN=$(curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"correo":"admin@test.com","contrasenia":"admin123"}' \
  | jq -r '.accessToken')

# 2. Registrar movimiento
curl -X POST http://localhost:8080/api/movimientos-stock \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "productoId": 1,
    "tipo": "SALIDA",
    "cantidad": 5,
    "motivo": "VENTA",
    "notas": "Test"
  }'

# 3. Ver alertas
curl http://localhost:8080/api/alertas/mis-alertas/no-leidas \
  -H "Authorization: Bearer $TOKEN"
```

### 4. Probar WebSocket (Frontend)

Abre la consola del navegador en http://localhost:4200 y ejecuta:

```javascript
const socket = new SockJS('http://localhost:8080/ws-alertas');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    console.log('✅ Conectado');
    
    stompClient.subscribe('/topic/alertas', function(message) {
        console.log('🔔 ALERTA:', JSON.parse(message.body));
        alert('Nueva alerta: ' + JSON.parse(message.body).mensaje);
    });
});
```

Ahora registra un movimiento y verás la alerta en tiempo real.

---

## ✅ Verificación

### Deberías ver:

1. **En la consola del servidor**:
```
INFO: Procesando alerta de stock bajo para producto: Producto Test
INFO: Alerta enviada por WebSocket al usuario: 1
INFO: Email de alerta enviado para producto: Producto Test
```

2. **En tu email**: Correo con asunto "⚠️ ALERTA: Stock Bajo"

3. **En el frontend**: Notificación WebSocket con la alerta

4. **En la API**: Alerta disponible en `/api/alertas/mis-alertas/no-leidas`

---

## 🐛 Problemas Comunes

### Email no se envía

- ✅ Verifica credenciales SMTP
- ✅ Usa "Contraseña de Aplicación" de Gmail, no tu contraseña normal
- ✅ Revisa logs: `tail -f logs/spring.log | grep -i email`

### WebSocket no conecta

- ✅ Verifica CORS en WebSocketConfig
- ✅ Asegúrate de usar SockJS
- ✅ Revisa consola del navegador

### Alerta no se genera

- ✅ Verifica que `stock <= stockMinimo` después del movimiento
- ✅ Revisa logs: `tail -f logs/spring.log | grep -i alerta`
- ✅ Consulta BD: `SELECT * FROM alertas;`

---

## 📚 Documentación Completa

- **ALERTAS_RESUMEN.md** - Resumen ejecutivo
- **ALERTAS_CONFIG.md** - Configuración detallada
- **ALERTAS_TESTING.md** - Guía de pruebas

---

## 🎉 ¡Listo!

El sistema de alertas está funcionando. Cada vez que un producto llegue a su stock mínimo:
- 📱 Se enviará notificación WebSocket en tiempo real
- 📧 Se enviará email al administrador
- 💾 Se guardará en base de datos para historial

---

**Siguiente paso**: Implementar el cliente WebSocket en tu frontend Angular/React/Vue.
