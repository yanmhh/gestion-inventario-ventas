# 🎨 Integración Frontend - Ejemplos de UI

## Componentes de Ejemplo para Angular

### 1. Badge de Alertas en el Header

```typescript
// alertas-badge.component.ts
import { Component, OnInit, OnDestroy } from '@angular/core';
import { AlertaWebSocketService } from '../../services/alerta-websocket.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-alertas-badge',
  template: `
    <div class="alertas-container">
      <button class="btn-alertas" [class.tiene-alertas]="alertasNoLeidas > 0" 
              (click)="toggleDropdown()">
        <i class="fas fa-bell"></i>
        <span class="badge" *ngIf="alertasNoLeidas > 0">{{ alertasNoLeidas }}</span>
      </button>

      <div class="alertas-dropdown" *ngIf="mostrarDropdown" [@slideDown]>
        <div class="dropdown-header">
          <h3>Alertas de Stock</h3>
          <button (click)="marcarTodasLeidas()">Marcar todas como leídas</button>
        </div>

        <div class="alertas-lista">
          <div class="alerta-item" *ngFor="let alerta of alertas" 
               [class.no-leida]="!alerta.leido"
               (click)="verDetalle(alerta)">
            <div class="alerta-icon">⚠️</div>
            <div class="alerta-contenido">
              <h4>{{ alerta.productoNombre }}</h4>
              <p>{{ alerta.mensaje }}</p>
              <small>{{ alerta.creadoEn | date:'short' }}</small>
            </div>
            <button class="btn-marcar" *ngIf="!alerta.leido" 
                    (click)="marcarLeida($event, alerta.id)">
              <i class="fas fa-check"></i>
            </button>
          </div>

          <div class="sin-alertas" *ngIf="alertas.length === 0">
            <i class="fas fa-check-circle"></i>
            <p>No hay alertas pendientes</p>
          </div>
        </div>

        <div class="dropdown-footer">
          <a routerLink="/alertas">Ver todas las alertas</a>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .alertas-container {
      position: relative;
    }

    .btn-alertas {
      position: relative;
      background: transparent;
      border: none;
      font-size: 1.5rem;
      cursor: pointer;
      color: #666;
      transition: color 0.3s;
    }

    .btn-alertas:hover {
      color: #333;
    }

    .btn-alertas.tiene-alertas {
      color: #ff9800;
      animation: shake 0.5s ease-in-out;
    }

    .badge {
      position: absolute;
      top: -5px;
      right: -5px;
      background: #f44336;
      color: white;
      border-radius: 50%;
      padding: 2px 6px;
      font-size: 0.7rem;
      font-weight: bold;
    }

    .alertas-dropdown {
      position: absolute;
      top: 100%;
      right: 0;
      width: 400px;
      max-height: 600px;
      background: white;
      border-radius: 8px;
      box-shadow: 0 4px 20px rgba(0,0,0,0.15);
      overflow: hidden;
      z-index: 1000;
      margin-top: 10px;
    }

    .dropdown-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 15px;
      border-bottom: 1px solid #eee;
      background: #f8f9fa;
    }

    .dropdown-header h3 {
      margin: 0;
      font-size: 1.1rem;
    }

    .alertas-lista {
      max-height: 400px;
      overflow-y: auto;
    }

    .alerta-item {
      display: flex;
      gap: 15px;
      padding: 15px;
      border-bottom: 1px solid #eee;
      cursor: pointer;
      transition: background 0.2s;
    }

    .alerta-item:hover {
      background: #f8f9fa;
    }

    .alerta-item.no-leida {
      background: #fff3cd;
    }

    .alerta-icon {
      font-size: 2rem;
    }

    .alerta-contenido {
      flex: 1;
    }

    .alerta-contenido h4 {
      margin: 0 0 5px 0;
      font-size: 0.95rem;
    }

    .alerta-contenido p {
      margin: 0 0 5px 0;
      font-size: 0.85rem;
      color: #666;
    }

    .sin-alertas {
      text-align: center;
      padding: 40px 20px;
      color: #999;
    }

    .sin-alertas i {
      font-size: 3rem;
      margin-bottom: 10px;
    }

    @keyframes shake {
      0%, 100% { transform: rotate(0deg); }
      10%, 30%, 50%, 70%, 90% { transform: rotate(-10deg); }
      20%, 40%, 60%, 80% { transform: rotate(10deg); }
    }

    @keyframes slideDown {
      from {
        opacity: 0;
        transform: translateY(-10px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }
  `],
  animations: [
    trigger('slideDown', [
      transition(':enter', [
        style({ opacity: 0, transform: 'translateY(-10px)' }),
        animate('200ms ease-out', style({ opacity: 1, transform: 'translateY(0)' }))
      ])
    ])
  ]
})
export class AlertasBadgeComponent implements OnInit, OnDestroy {
  alertas: any[] = [];
  alertasNoLeidas = 0;
  mostrarDropdown = false;
  private subscription?: Subscription;

  constructor(
    private alertaWS: AlertaWebSocketService,
    private alertaService: AlertaService
  ) {}

  ngOnInit(): void {
    this.cargarAlertas();
    this.escucharWebSocket();
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }

  cargarAlertas(): void {
    this.alertaService.obtenerAlertasNoLeidas().subscribe(alertas => {
      this.alertas = alertas;
      this.alertasNoLeidas = alertas.length;
    });
  }

  escucharWebSocket(): void {
    this.subscription = this.alertaWS.alertas$.subscribe(alerta => {
      if (alerta) {
        this.alertas.unshift(alerta);
        this.alertasNoLeidas++;
        this.reproducirSonido();
      }
    });
  }

  toggleDropdown(): void {
    this.mostrarDropdown = !this.mostrarDropdown;
  }

  marcarLeida(event: Event, alertaId: number): void {
    event.stopPropagation();
    this.alertaService.marcarComoLeida(alertaId).subscribe(() => {
      const alerta = this.alertas.find(a => a.id === alertaId);
      if (alerta) {
        alerta.leido = true;
        this.alertasNoLeidas--;
      }
    });
  }

  marcarTodasLeidas(): void {
    // Implementar según necesidad
  }

  verDetalle(alerta: any): void {
    // Navegar a detalle del producto o modal
  }

  private reproducirSonido(): void {
    const audio = new Audio('/assets/sounds/notification.mp3');
    audio.play().catch(e => console.log('No se pudo reproducir el sonido'));
  }
}
```

---

### 2. Toast/Snackbar de Notificación

```typescript
// alerta-toast.component.ts
import { Component, Input, OnInit } from '@angular/core';
import { trigger, transition, style, animate } from '@angular/animations';

@Component({
  selector: 'app-alerta-toast',
  template: `
    <div class="toast-container" [@slideIn]>
      <div class="toast toast-warning">
        <div class="toast-icon">
          <i class="fas fa-exclamation-triangle"></i>
        </div>
        <div class="toast-content">
          <h4>{{ titulo }}</h4>
          <p>{{ mensaje }}</p>
        </div>
        <button class="toast-close" (click)="cerrar()">
          <i class="fas fa-times"></i>
        </button>
      </div>
    </div>
  `,
  styles: [`
    .toast-container {
      position: fixed;
      top: 80px;
      right: 20px;
      z-index: 9999;
    }

    .toast {
      display: flex;
      gap: 15px;
      background: white;
      border-radius: 8px;
      padding: 15px 20px;
      box-shadow: 0 4px 20px rgba(0,0,0,0.15);
      min-width: 350px;
      max-width: 500px;
    }

    .toast-warning {
      border-left: 4px solid #ff9800;
    }

    .toast-icon {
      font-size: 2rem;
      color: #ff9800;
    }

    .toast-content h4 {
      margin: 0 0 5px 0;
      font-size: 1rem;
    }

    .toast-content p {
      margin: 0;
      font-size: 0.9rem;
      color: #666;
    }

    .toast-close {
      background: transparent;
      border: none;
      cursor: pointer;
      color: #999;
      font-size: 1.2rem;
    }
  `],
  animations: [
    trigger('slideIn', [
      transition(':enter', [
        style({ transform: 'translateX(400px)', opacity: 0 }),
        animate('300ms ease-out', style({ transform: 'translateX(0)', opacity: 1 }))
      ]),
      transition(':leave', [
        animate('300ms ease-in', style({ transform: 'translateX(400px)', opacity: 0 }))
      ])
    ])
  ]
})
export class AlertaToastComponent implements OnInit {
  @Input() titulo = 'Stock Bajo';
  @Input() mensaje = '';
  @Input() duracion = 5000;

  ngOnInit(): void {
    if (this.duracion > 0) {
      setTimeout(() => this.cerrar(), this.duracion);
    }
  }

  cerrar(): void {
    // Emitir evento o usar servicio para cerrar
  }
}
```

---

### 3. Página de Listado de Alertas

```typescript
// alertas-listado.component.ts
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-alertas-listado',
  template: `
    <div class="alertas-page">
      <header class="page-header">
        <h1>Alertas de Stock</h1>
        <div class="filtros">
          <button [class.active]="filtro === 'todas'" (click)="filtrar('todas')">
            Todas
          </button>
          <button [class.active]="filtro === 'no-leidas'" (click)="filtrar('no-leidas')">
            No leídas ({{ contadorNoLeidas }})
          </button>
          <button [class.active]="filtro === 'leidas'" (click)="filtrar('leidas')">
            Leídas
          </button>
        </div>
      </header>

      <div class="alertas-grid">
        <div class="alerta-card" *ngFor="let alerta of alertasFiltradas" 
             [class.no-leida]="!alerta.leido">
          <div class="card-header">
            <span class="badge badge-warning">⚠️ Stock Bajo</span>
            <small>{{ alerta.creadoEn | date:'short' }}</small>
          </div>

          <div class="card-body">
            <h3>{{ alerta.productoNombre }}</h3>
            <p class="codigo">Código: {{ alerta.productoCodigo }}</p>
            <p class="mensaje">{{ alerta.mensaje }}</p>
          </div>

          <div class="card-footer">
            <button class="btn btn-primary" [routerLink]="['/productos', alerta.productoId]">
              Ver Producto
            </button>
            <button class="btn btn-secondary" *ngIf="!alerta.leido" 
                    (click)="marcarLeida(alerta.id)">
              Marcar como leída
            </button>
          </div>
        </div>

        <div class="sin-alertas" *ngIf="alertasFiltradas.length === 0">
          <i class="fas fa-check-circle"></i>
          <h3>No hay alertas {{ filtro }}</h3>
          <p>Todos los productos tienen stock suficiente</p>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .alertas-page {
      padding: 20px;
    }

    .page-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 30px;
    }

    .filtros {
      display: flex;
      gap: 10px;
    }

    .filtros button {
      padding: 8px 16px;
      border: 1px solid #ddd;
      background: white;
      border-radius: 4px;
      cursor: pointer;
    }

    .filtros button.active {
      background: #007bff;
      color: white;
      border-color: #007bff;
    }

    .alertas-grid {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
      gap: 20px;
    }

    .alerta-card {
      background: white;
      border-radius: 8px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.1);
      overflow: hidden;
      transition: transform 0.2s;
    }

    .alerta-card:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    }

    .alerta-card.no-leida {
      border-left: 4px solid #ff9800;
    }

    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 15px;
      background: #f8f9fa;
      border-bottom: 1px solid #eee;
    }

    .card-body {
      padding: 20px;
    }

    .card-body h3 {
      margin: 0 0 10px 0;
    }

    .card-footer {
      display: flex;
      gap: 10px;
      padding: 15px;
      background: #f8f9fa;
      border-top: 1px solid #eee;
    }

    .sin-alertas {
      grid-column: 1 / -1;
      text-align: center;
      padding: 60px 20px;
      color: #999;
    }

    .sin-alertas i {
      font-size: 4rem;
      color: #28a745;
      margin-bottom: 20px;
    }
  `]
})
export class AlertasListadoComponent implements OnInit {
  alertas: any[] = [];
  alertasFiltradas: any[] = [];
  filtro: 'todas' | 'no-leidas' | 'leidas' = 'no-leidas';
  contadorNoLeidas = 0;

  constructor(private alertaService: AlertaService) {}

  ngOnInit(): void {
    this.cargarAlertas();
  }

  cargarAlertas(): void {
    this.alertaService.obtenerMisAlertas().subscribe(alertas => {
      this.alertas = alertas;
      this.contadorNoLeidas = alertas.filter(a => !a.leido).length;
      this.filtrar(this.filtro);
    });
  }

  filtrar(tipo: 'todas' | 'no-leidas' | 'leidas'): void {
    this.filtro = tipo;
    switch (tipo) {
      case 'todas':
        this.alertasFiltradas = this.alertas;
        break;
      case 'no-leidas':
        this.alertasFiltradas = this.alertas.filter(a => !a.leido);
        break;
      case 'leidas':
        this.alertasFiltradas = this.alertas.filter(a => a.leido);
        break;
    }
  }

  marcarLeida(alertaId: number): void {
    this.alertaService.marcarComoLeida(alertaId).subscribe(() => {
      this.cargarAlertas();
    });
  }
}
```

---

## 📱 Diseño Responsive

```css
/* Estilos móviles */
@media (max-width: 768px) {
  .alertas-dropdown {
    width: 100vw;
    max-width: 100vw;
    right: 0;
    left: 0;
    border-radius: 0;
  }

  .alertas-grid {
    grid-template-columns: 1fr;
  }
}
```

---

## 🔔 Notificaciones del Navegador

```typescript
// notification.service.ts
export class NotificationService {
  
  constructor() {
    this.solicitarPermiso();
  }

  solicitarPermiso(): void {
    if ('Notification' in window && Notification.permission === 'default') {
      Notification.requestPermission();
    }
  }

  mostrarNotificacion(titulo: string, mensaje: string, icono?: string): void {
    if ('Notification' in window && Notification.permission === 'granted') {
      const notification = new Notification(titulo, {
        body: mensaje,
        icon: icono || '/assets/icons/warning.png',
        badge: '/assets/icons/badge.png',
        tag: 'alerta-stock',
        requireInteraction: true,
        vibrate: [200, 100, 200]
      });

      notification.onclick = () => {
        window.focus();
        notification.close();
      };

      // Auto cerrar después de 5 segundos
      setTimeout(() => notification.close(), 5000);
    }
  }
}
```

---

¡Interfaz completa para el sistema de alertas! 🎨
