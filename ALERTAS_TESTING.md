# Pruebas del Sistema de Alertas

## 📋 Guía de Pruebas Paso a Paso

### Prerrequisitos
1. Base de datos PostgreSQL corriendo
2. Configuración SMTP en `application.properties`
3. Aplicación Spring Boot ejecutándose

---

## 🧪 Escenarios de Prueba

### Escenario 1: Alerta por Stock Bajo (Salida)

#### Datos de Prueba:
- **Producto**: "Filtro de Aceite"
- **Stock Actual**: 15 unidades
- **Stock Mínimo**: 10 unidades
- **Movimiento**: Salida de 7 unidades

#### Pasos:

1. **Crear/Verificar Producto con Stock:**

```http
POST /api/productos
Authorization: Bearer {token}
Content-Type: application/json

{
  "nombre": "Filtro de Aceite Premium",
  "descripcion": "Filtro de alta calidad",
  "precioVenta": 45.50,
  "costoCompra": 30.00,
  "stock": 15,
  "stockMinimo": 10,
  "codigo": "FLT001",
  "marcaId": 1,
  "categoriaId": 1
}
```

2. **Registrar Movimiento de Salida:**

```http
POST /api/movimientos-stock
Authorization: Bearer {token}
Content-Type: application/json

{
  "productoId": 1,
  "tipo": "SALIDA",
  "cantidad": 7,
  "motivo": "VENTA",
  "notas": "Venta al cliente ABC"
}
```

**Resultado Esperado:**
- Stock resultante: 8 unidades (menor que stock mínimo de 10)
- Se genera alerta automáticamente
- Se envía notificación por WebSocket
- Se envía email al administrador

3. **Verificar Alerta Creada:**

```http
GET /api/alertas/mis-alertas/no-leidas
Authorization: Bearer {token}
```

**Respuesta Esperada:**
```json
[
  {
    "id": 1,
    "productoId": 1,
    "productoNombre": "Filtro de Aceite Premium",
    "productoCodigo": "FLT001",
    "usuarioId": 2,
    "usuarioNombre": "Juan Pérez",
    "mensaje": "⚠️ ALERTA: El producto 'Filtro de Aceite Premium' (Código: FLT001) tiene stock bajo. Stock actual: 8, Stock mínimo: 10",
    "leido": false,
    "creadoEn": "2025-11-27 10:30:45"
  }
]
```

4. **Verificar Email:**
- Revisar bandeja de entrada del email configurado
- Debe llegar un correo con el asunto: "⚠️ ALERTA: Stock Bajo - Filtro de Aceite Premium"

---

### Escenario 2: Marcar Alerta como Leída

```http
PUT /api/alertas/1/marcar-leida
Authorization: Bearer {token}
```

**Respuesta Esperada:**
```json
{
  "id": 1,
  "productoId": 1,
  "productoNombre": "Filtro de Aceite Premium",
  "productoCodigo": "FLT001",
  "usuarioId": 2,
  "usuarioNombre": "Juan Pérez",
  "mensaje": "⚠️ ALERTA: El producto 'Filtro de Aceite Premium' (Código: FLT001) tiene stock bajo. Stock actual: 8, Stock mínimo: 10",
  "leido": true,
  "creadoEn": "2025-11-27 10:30:45"
}
```

---

### Escenario 3: Múltiples Alertas

#### Probar con varios productos:

```http
POST /api/movimientos-stock
Authorization: Bearer {token}
Content-Type: application/json

{
  "productoId": 2,
  "tipo": "SALIDA",
  "cantidad": 20,
  "motivo": "VENTA",
  "notas": "Venta mayorista"
}
```

```http
POST /api/movimientos-stock
Authorization: Bearer {token}
Content-Type: application/json

{
  "productoId": 3,
  "tipo": "SALIDA",
  "cantidad": 15,
  "motivo": "VENTA",
  "notas": "Venta retail"
}
```

**Verificar:**
```http
GET /api/alertas/todas/no-leidas
Authorization: Bearer {token}
```

---

## 🌐 Pruebas de WebSocket

### Usando Cliente de Consola JavaScript

Abre la consola del navegador en cualquier página y ejecuta:

```javascript
// 1. Conectar al WebSocket
const socket = new SockJS('http://localhost:8080/ws-alertas');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    console.log('Conectado: ' + frame);
    
    // 2. Suscribirse al topic de alertas generales
    stompClient.subscribe('/topic/alertas', function(message) {
        const alerta = JSON.parse(message.body);
        console.log('🔔 Nueva alerta recibida:', alerta);
        alert(`ALERTA: ${alerta.mensaje}`);
    });
    
    // 3. Suscribirse a alertas específicas del usuario (reemplaza 2 con tu usuarioId)
    stompClient.subscribe('/queue/alertas-2', function(message) {
        const alerta = JSON.parse(message.body);
        console.log('📧 Alerta personal:', alerta);
    });
});

// Para desconectar
// stompClient.disconnect();
```

### HTML de Prueba

Crea un archivo `test-websocket.html`:

```html
<!DOCTYPE html>
<html>
<head>
    <title>Test WebSocket Alertas</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@7.0.0/bundles/stomp.umd.min.js"></script>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        .alerta { 
            background: #fff3cd; 
            border: 1px solid #ffc107; 
            padding: 15px; 
            margin: 10px 0; 
            border-radius: 5px;
        }
        .alerta.nueva { animation: pulse 1s; }
        @keyframes pulse {
            0%, 100% { background: #fff3cd; }
            50% { background: #ffc107; }
        }
        button { 
            padding: 10px 20px; 
            margin: 5px;
            cursor: pointer;
        }
        #log { 
            background: #f5f5f5; 
            padding: 15px; 
            border: 1px solid #ddd;
            height: 300px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <h1>🔔 Test de WebSocket - Sistema de Alertas</h1>
    
    <div>
        <label>Usuario ID: <input type="number" id="usuarioId" value="2"></label>
        <button onclick="conectar()">Conectar</button>
        <button onclick="desconectar()">Desconectar</button>
        <span id="status">❌ Desconectado</span>
    </div>

    <h2>Alertas Recibidas:</h2>
    <div id="alertas"></div>

    <h2>Log de Conexión:</h2>
    <div id="log"></div>

    <script>
        let stompClient = null;

        function log(mensaje) {
            const logDiv = document.getElementById('log');
            const timestamp = new Date().toLocaleTimeString();
            logDiv.innerHTML += `[${timestamp}] ${mensaje}<br>`;
            logDiv.scrollTop = logDiv.scrollHeight;
        }

        function conectar() {
            const usuarioId = document.getElementById('usuarioId').value;
            const socket = new SockJS('http://localhost:8080/ws-alertas');
            stompClient = Stomp.over(socket);

            stompClient.connect({}, function(frame) {
                log('✅ Conectado exitosamente');
                document.getElementById('status').innerHTML = '✅ Conectado';
                document.getElementById('status').style.color = 'green';

                // Suscripción a alertas generales
                stompClient.subscribe('/topic/alertas', function(message) {
                    log('📢 Alerta general recibida');
                    mostrarAlerta(JSON.parse(message.body));
                });

                // Suscripción a alertas personales
                stompClient.subscribe('/queue/alertas-' + usuarioId, function(message) {
                    log('📧 Alerta personal recibida');
                    mostrarAlerta(JSON.parse(message.body));
                });

                log(`Suscrito a /topic/alertas y /queue/alertas-${usuarioId}`);
            }, function(error) {
                log('❌ Error: ' + error);
                document.getElementById('status').innerHTML = '❌ Error';
                document.getElementById('status').style.color = 'red';
            });
        }

        function desconectar() {
            if (stompClient !== null) {
                stompClient.disconnect();
                log('🔌 Desconectado');
                document.getElementById('status').innerHTML = '❌ Desconectado';
                document.getElementById('status').style.color = 'gray';
            }
        }

        function mostrarAlerta(alerta) {
            const alertasDiv = document.getElementById('alertas');
            const alertaHtml = `
                <div class="alerta nueva">
                    <strong>${alerta.productoNombre}</strong> (${alerta.productoCodigo})<br>
                    ${alerta.mensaje}<br>
                    <small>Recibido: ${alerta.creadoEn}</small>
                </div>
            `;
            alertasDiv.innerHTML = alertaHtml + alertasDiv.innerHTML;

            // Notificación del navegador
            if (Notification.permission === 'granted') {
                new Notification('Nueva Alerta de Stock', {
                    body: alerta.mensaje,
                    icon: '⚠️'
                });
            }
        }

        // Solicitar permisos de notificación
        if ('Notification' in window && Notification.permission === 'default') {
            Notification.requestPermission();
        }
    </script>
</body>
</html>
```

---

## 📊 Verificación en Base de Datos

### Consultar Alertas Generadas:

```sql
SELECT 
    a.id,
    p.nombre AS producto,
    p.codigo,
    u.id AS usuario_id,
    per.nombre || ' ' || per.apellido_paterno AS usuario_nombre,
    a.mensaje,
    a.leido,
    a.creado_en
FROM alertas a
JOIN producto p ON a.producto_id = p.producto_id
JOIN usuario u ON a.usuario_id = u.id
JOIN persona per ON u.persona_id = per.id
ORDER BY a.creado_en DESC;
```

### Verificar Stock de Productos:

```sql
SELECT 
    producto_id,
    nombre,
    codigo,
    stock,
    stock_minimo,
    CASE 
        WHEN stock <= stock_minimo THEN '⚠️ BAJO'
        ELSE '✅ OK'
    END AS estado_stock
FROM producto
ORDER BY (stock - stock_minimo) ASC;
```

---

## 🎯 Checklist de Pruebas

- [ ] Registrar movimiento que baje stock por debajo del mínimo
- [ ] Verificar creación de alerta en base de datos
- [ ] Verificar recepción de alerta por WebSocket
- [ ] Verificar envío de email
- [ ] Probar consulta de alertas no leídas
- [ ] Probar marcar alerta como leída
- [ ] Probar múltiples usuarios conectados simultáneamente
- [ ] Probar reconexión automática del WebSocket
- [ ] Verificar notificaciones del navegador
- [ ] Probar con diferentes productos y umbrales

---

## 🐛 Solución de Problemas

### WebSocket no conecta:

1. Verificar CORS en `WebSocketConfig.java`:
```java
.setAllowedOriginPatterns("*")
```

2. Verificar que el servidor esté corriendo en el puerto correcto (8080)

3. Revisar consola del navegador para errores

### Email no se envía:

1. Verificar credenciales SMTP
2. Revisar logs del servidor:
```bash
tail -f logs/spring.log | grep -i email
```

3. Para Gmail, asegúrate de usar "Contraseña de Aplicación"

### Alerta no se genera:

1. Verificar que el stock esté realmente bajo:
```sql
SELECT stock, stock_minimo FROM producto WHERE producto_id = 1;
```

2. Revisar logs del servidor:
```bash
tail -f logs/spring.log | grep -i alerta
```

---

## 📈 Pruebas de Carga

### Simular múltiples movimientos:

```bash
#!/bin/bash
TOKEN="tu-token-aqui"

for i in {1..10}; do
  curl -X POST http://localhost:8080/api/movimientos-stock \
    -H "Authorization: Bearer $TOKEN" \
    -H "Content-Type: application/json" \
    -d "{
      \"productoId\": 1,
      \"tipo\": \"SALIDA\",
      \"cantidad\": 1,
      \"motivo\": \"VENTA\",
      \"notas\": \"Test $i\"
    }"
  sleep 2
done
```

---

¡Sistema de alertas listo para pruebas! 🚀
