# Sistema de Alertas en Tiempo Real - Guía de Configuración

## 📋 Descripción General

El sistema de alertas en tiempo real notifica automáticamente cuando el stock de un producto alcanza o está por debajo del nivel mínimo configurado. Las notificaciones se envían mediante:

- **WebSocket**: Notificaciones instantáneas al frontend
- **Email (SMTP)**: Correos electrónicos a los responsables

---

## 🏗️ Arquitectura Implementada

### Capas según DDD y Arquitectura Hexagonal:

```
domain/
├── model/alerta/Alerta.java                    # Entidad de dominio
├── event/StockBajoEvent.java                   # Evento de dominio
└── repository/alerta/                          # Puertos (interfaces)

application/
├── dto/alerta/AlertaView.java                  # DTO de vista
├── mapper/alerta/AlertaViewMapper.java         # Mapper
└── service/alerta/
    ├── AlertaQueryService.java                 # Consultas
    ├── AlertaCommandService.java               # Comandos
    └── StockAlertaService.java                 # Orquestador de alertas

infrastructure/
├── entity/alerta/JpaAlertaEntity.java          # Entidad JPA
├── mapper/alerta/AlertaMapperJpa.java          # Mapper JPA
├── repository/jpa/alerta/                      # Adaptadores de repositorio
├── config/
│   ├── WebSocketConfig.java                    # Configuración WebSocket
│   └── AsyncConfig.java                        # Configuración Async
├── notification/
│   ├── WebSocketNotificationService.java       # Servicio WebSocket
│   └── EmailNotificationService.java           # Servicio Email
├── listener/StockBajoEventListener.java        # Listener de eventos
└── controller/alerta/                          # Controladores REST
```

---

## ⚙️ Configuración

### 1. Configuración de Correo Electrónico (application.properties)

```properties
# SMTP Gmail (recomendado usar contraseña de aplicación)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=tu-email@gmail.com
spring.mail.password=tu-password-de-aplicacion
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true

# Email destino para alertas
app.alertas.email-destino=admin@inventario.com
```

#### Obtener Contraseña de Aplicación de Gmail:
1. Ve a tu cuenta de Google
2. Seguridad → Verificación en dos pasos (activar)
3. Contraseñas de aplicaciones → Generar
4. Copia la contraseña generada

### 2. Base de Datos

La tabla `alertas` ya está creada con la migración V7:

```sql
CREATE TABLE alertas (
    id BIGSERIAL PRIMARY KEY,
    producto_id INTEGER NOT NULL,
    usuario_id INTEGER NOT NULL,
    mensaje TEXT NOT NULL,
    leido BOOLEAN DEFAULT FALSE,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_alerta_producto FOREIGN KEY (producto_id)
        REFERENCES producto (producto_id) ON DELETE CASCADE,
    CONSTRAINT fk_alerta_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuario (id) ON DELETE CASCADE
);
```

---

## 🔌 Endpoints REST API

### Consultar Alertas

```http
GET /api/alertas/mis-alertas
Authorization: Bearer {token}
```
Retorna todas las alertas del usuario autenticado.

```http
GET /api/alertas/mis-alertas/no-leidas
Authorization: Bearer {token}
```
Retorna solo las alertas no leídas del usuario autenticado.

```http
GET /api/alertas/todas/no-leidas
Authorization: Bearer {token}
```
Retorna todas las alertas no leídas del sistema.

```http
GET /api/alertas/usuario/{usuarioId}
Authorization: Bearer {token}
```
Retorna las alertas de un usuario específico.

### Marcar como Leída

```http
PUT /api/alertas/{alertaId}/marcar-leida
Authorization: Bearer {token}
```

---

## 🔄 WebSocket - Conexión desde el Frontend

### Configuración Angular/TypeScript

#### 1. Instalar dependencias:

```bash
npm install sockjs-client @stomp/stompjs
npm install --save-dev @types/sockjs-client
```

#### 2. Servicio de WebSocket (alerta-websocket.service.ts):

```typescript
import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import { Client, Message, StompSubscription } from '@stomp/stompjs';
import { BehaviorSubject, Observable } from 'rxjs';

export interface AlertaView {
  id: number;
  productoId: number;
  productoNombre: string;
  productoCodigo: string;
  usuarioId: number;
  usuarioNombre: string;
  mensaje: string;
  leido: boolean;
  creadoEn: string;
}

@Injectable({
  providedIn: 'root'
})
export class AlertaWebSocketService {
  private stompClient: Client | null = null;
  private alertasSubject = new BehaviorSubject<AlertaView | null>(null);
  public alertas$: Observable<AlertaView | null> = this.alertasSubject.asObservable();

  private readonly WS_URL = 'http://localhost:8080/ws-alertas';

  constructor() {}

  connect(usuarioId: number): void {
    const socket = new SockJS(this.WS_URL);
    
    this.stompClient = new Client({
      webSocketFactory: () => socket as any,
      debug: (str) => {
        console.log('STOMP: ' + str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000
    });

    this.stompClient.onConnect = () => {
      console.log('WebSocket conectado');

      // Suscribirse a alertas generales
      this.stompClient?.subscribe('/topic/alertas', (message: Message) => {
        const alerta: AlertaView = JSON.parse(message.body);
        console.log('Alerta general recibida:', alerta);
        this.alertasSubject.next(alerta);
        this.mostrarNotificacion(alerta);
      });

      // Suscribirse a alertas específicas del usuario
      this.stompClient?.subscribe(`/queue/alertas-${usuarioId}`, (message: Message) => {
        const alerta: AlertaView = JSON.parse(message.body);
        console.log('Alerta personal recibida:', alerta);
        this.alertasSubject.next(alerta);
        this.mostrarNotificacion(alerta);
      });
    };

    this.stompClient.onStompError = (frame) => {
      console.error('Error STOMP:', frame);
    };

    this.stompClient.activate();
  }

  disconnect(): void {
    if (this.stompClient) {
      this.stompClient.deactivate();
      console.log('WebSocket desconectado');
    }
  }

  private mostrarNotificacion(alerta: AlertaView): void {
    // Usar notificaciones del navegador
    if ('Notification' in window && Notification.permission === 'granted') {
      new Notification('Nueva Alerta de Stock', {
        body: alerta.mensaje,
        icon: '/assets/icons/warning.png',
        badge: '/assets/icons/badge.png'
      });
    }
  }

  solicitarPermisoNotificaciones(): void {
    if ('Notification' in window && Notification.permission === 'default') {
      Notification.requestPermission().then(permission => {
        console.log('Permiso de notificaciones:', permission);
      });
    }
  }
}
```

#### 3. Uso en un Componente (dashboard.component.ts):

```typescript
import { Component, OnInit, OnDestroy } from '@angular/core';
import { AlertaWebSocketService, AlertaView } from './services/alerta-websocket.service';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit, OnDestroy {
  alertas: AlertaView[] = [];
  alertasNoLeidas = 0;

  constructor(
    private alertaWS: AlertaWebSocketService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // Solicitar permisos de notificación
    this.alertaWS.solicitarPermisoNotificaciones();

    // Conectar WebSocket
    const usuarioId = this.authService.getUsuarioId();
    this.alertaWS.connect(usuarioId);

    // Escuchar nuevas alertas
    this.alertaWS.alertas$.subscribe(alerta => {
      if (alerta) {
        this.alertas.unshift(alerta);
        this.alertasNoLeidas++;
        
        // Mostrar toast o notificación en la UI
        this.mostrarToast(alerta);
      }
    });

    // Cargar alertas existentes
    this.cargarAlertasNoLeidas();
  }

  ngOnDestroy(): void {
    this.alertaWS.disconnect();
  }

  cargarAlertasNoLeidas(): void {
    // Llamar al endpoint REST para cargar alertas previas
    // this.alertaService.obtenerAlertasNoLeidas()...
  }

  mostrarToast(alerta: AlertaView): void {
    // Implementar toast/snackbar según tu framework
    console.log('Nueva alerta:', alerta);
  }
}
```

#### 4. Template (dashboard.component.html):

```html
<div class="alertas-widget">
  <button class="btn-alertas" (click)="abrirListaAlertas()">
    <i class="icon-bell"></i>
    <span class="badge" *ngIf="alertasNoLeidas > 0">{{ alertasNoLeidas }}</span>
  </button>

  <div class="alertas-dropdown" *ngIf="mostrarAlertas">
    <div class="alerta-item" *ngFor="let alerta of alertas" 
         [class.no-leida]="!alerta.leido">
      <div class="alerta-header">
        <strong>{{ alerta.productoNombre }}</strong>
        <span class="alerta-fecha">{{ alerta.creadoEn }}</span>
      </div>
      <p class="alerta-mensaje">{{ alerta.mensaje }}</p>
      <button (click)="marcarComoLeida(alerta.id)" 
              *ngIf="!alerta.leido">
        Marcar como leída
      </button>
    </div>
  </div>
</div>
```

---

## 🔥 Flujo de Funcionamiento

1. **Movimiento de Stock**: Cuando se registra un movimiento de stock (entrada/salida)
2. **Verificación**: El sistema verifica si `stock <= stockMinimo`
3. **Evento**: Se publica un `StockBajoEvent`
4. **Listener**: El `StockBajoEventListener` captura el evento
5. **Procesamiento**: El `StockAlertaService` procesa la alerta:
   - Crea registros en la base de datos para cada usuario responsable
   - Envía notificación por WebSocket a todos los usuarios conectados
   - Envía email al administrador
6. **Frontend**: Los clientes conectados reciben la alerta en tiempo real

---

## 🧪 Pruebas

### Probar con Postman:

1. **Registrar un movimiento de salida que baje el stock:**

```http
POST /api/movimientos-stock
Authorization: Bearer {token}
Content-Type: application/json

{
  "productoId": 1,
  "tipo": "SALIDA",
  "cantidad": 50,
  "motivo": "Venta",
  "notas": "Prueba de alerta"
}
```

2. **Verificar que llegó la alerta:**

```http
GET /api/alertas/mis-alertas/no-leidas
Authorization: Bearer {token}
```

### Probar WebSocket con herramientas:

- **Chrome Extension**: "Simple WebSocket Client"
- **Online Tool**: https://www.websocket.org/echo.html

---

## 📧 Configuración Email Alternativa

### Para otros proveedores SMTP:

#### Outlook/Hotmail:
```properties
spring.mail.host=smtp-mail.outlook.com
spring.mail.port=587
```

#### Yahoo:
```properties
spring.mail.host=smtp.mail.yahoo.com
spring.mail.port=587
```

#### SMTP Personalizado:
```properties
spring.mail.host=smtp.tu-dominio.com
spring.mail.port=465 # o 587
spring.mail.properties.mail.smtp.ssl.enable=true # para puerto 465
```

---

## 🎯 Personalización

### Filtrar usuarios que reciben alertas:

En `StockAlertaService.java`, modifica el método `obtenerUsuariosResponsables()`:

```java
private List<Usuario> obtenerUsuariosResponsables() {
    // Filtrar solo usuarios con rol ADMIN
    return usuarioReadRepository.findAll()
        .stream()
        .filter(u -> u.getRol().getNombre().equals("ADMIN"))
        .collect(Collectors.toList());
}
```

### Personalizar mensaje de email:

Modifica `EmailNotificationService.enviarAlertaStockBajo()` para incluir más información.

---

## ✅ Checklist de Implementación

- [x] Modelo de dominio Alerta
- [x] Evento de dominio StockBajoEvent
- [x] Repositorios (puertos e implementaciones)
- [x] Servicios de aplicación
- [x] Entidades JPA y mappers
- [x] Configuración WebSocket
- [x] Servicio de notificación WebSocket
- [x] Servicio de notificación Email
- [x] Event Listener
- [x] Integración con MovimientoStockCommandService
- [x] Controladores REST
- [x] Configuración de properties
- [x] Documentación

---

## 🚀 Próximos Pasos

1. **Configurar credenciales SMTP** en `application.properties`
2. **Probar el flujo completo** registrando movimientos de stock
3. **Implementar frontend** con WebSocket
4. **Personalizar usuarios responsables** según roles
5. **Agregar tests unitarios e integración**

---

## 📝 Notas Importantes

- Las notificaciones por email son **asíncronas** (@Async)
- El WebSocket se reconecta automáticamente si se pierde la conexión
- Las alertas se persisten en base de datos para historial
- Cada usuario tiene su propia cola de mensajes WebSocket
- Los eventos de dominio desacoplan la lógica de negocio de las notificaciones

---

¡Sistema de alertas implementado exitosamente! 🎉
