# 🔄 REFACTORIZACIÓN: ELIMINACIÓN DE CQRS - REPOSITORIO ÚNICO

## 📋 RESUMEN EJECUTIVO

**Objetivo:** Eliminar código duplicado eliminando CQRS, consolidando en un único repositorio por entidad.

**Estrategia:**
- ❌ Eliminar `XxxReadRepository` y `XxxWriteRepository`
- ❌ Eliminar `XxxFinder`
- ✅ Crear `XxxRepository` único
- ✅ Consolidar adapters: `XxxRepositoryJpaAdapter` único
- ✅ Actualizar servicios: Usar `XxxRepository` en lugar de Read/Write
- ✅ Mantener arquitectura hexagonal + DDD
- ✅ Reutilizar `BaseRepositoryAdapter`

---

## 🏗️ ARQUITECTURA RESULTANTE

### **Antes (CQRS - Código Duplicado)**
```
Domain Layer:
├── XxxFinder (interface)
├── XxxReadRepository extends XxxFinder
└── XxxWriteRepository extends XxxFinder

Infrastructure Layer:
├── XxxReadRepositoryJpaAdapter implements XxxReadRepository
│   ├── springDataRepository ❌ DUPLICADO
│   ├── mapperJpa ❌ DUPLICADO
│   └── findById() ❌ DUPLICADO
└── XxxWriteRepositoryJpaAdapter implements XxxWriteRepository
    ├── springDataRepository ❌ DUPLICADO
    ├── mapperJpa ❌ DUPLICADO
    └── findById() ❌ DUPLICADO

Application Layer:
├── XxxQueryService (usa XxxReadRepository)
└── XxxCommandService (usa XxxWriteRepository)
```

### **Después (Sin CQRS - Sin Duplicación)**
```
Domain Layer:
└── XxxRepository (interface única)
    ├── findById()
    ├── findAll()
    ├── save()
    └── delete()

Infrastructure Layer:
└── XxxRepositoryJpaAdapter extends BaseRepositoryAdapter
    ├── repository (heredado) ✅ ÚNICO
    ├── mapperJpa ✅ ÚNICO
    ├── toDomain() (implementado)
    ├── toEntity() (implementado)
    ├── findById() ✅ HEREDADO
    ├── métodos de lectura específicos
    └── métodos de escritura

Application Layer:
├── XxxQueryService (usa XxxRepository)
└── XxxCommandService (usa XxxRepository)
```

---

## 📐 PATRÓN DE IMPLEMENTACIÓN

### **PASO 1: Crear Interface Única en Domain**

**Archivo:** `domain/repository/xxx/XxxRepository.java`

```java
package com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.xxx;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.xxx.Xxx;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio único para Xxx.
 * Consolida operaciones de lectura y escritura.
 * Mantiene arquitectura hexagonal sin CQRS.
 */
public interface XxxRepository {
    
    // Operaciones de búsqueda
    Optional<Xxx> findById(Integer id);
    List<Xxx> findAll();
    // ... otros métodos de búsqueda específicos
    
    // Operaciones de escritura
    Xxx save(Xxx xxx);
    void delete(Xxx xxx);
}
```

---

### **PASO 2: Crear Adapter Único en Infrastructure**

**Archivo:** `infrastructure/repository/jpa/xxx/XxxRepositoryJpaAdapter.java`

```java
package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.xxx;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.xxx.Xxx;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.xxx.XxxRepository;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.entity.xxx.JpaXxxEntity;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.mapper.xxx.XxxMapperJpa;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.repository.jpa.base.BaseRepositoryAdapter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adapter único para Xxx.
 * Consolida operaciones de lectura y escritura.
 * Extiende BaseRepositoryAdapter para reutilizar métodos comunes.
 */
@Repository
public class XxxRepositoryJpaAdapter extends BaseRepositoryAdapter<Xxx, JpaXxxEntity, Integer>
        implements XxxRepository {

    private final XxxMapperJpa xxxMapperJpa;

    public XxxRepositoryJpaAdapter(SpringDataXxxRepository springDataXxxRepository,
                                   XxxMapperJpa xxxMapperJpa) {
        super(springDataXxxRepository);
        this.xxxMapperJpa = xxxMapperJpa;
    }

    @Override
    protected Xxx toDomain(JpaXxxEntity entity) {
        return xxxMapperJpa.toDomain(entity);
    }

    @Override
    protected JpaXxxEntity toEntity(Xxx domain) {
        return xxxMapperJpa.toEntity(domain);
    }

    // ========== OPERACIONES DE BÚSQUEDA ==========
    
    // findById() heredado de BaseRepositoryAdapter

    @Override
    public List<Xxx> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Xxx> findByNombre(String nombre) {
        return ((SpringDataXxxRepository) repository).findByNombre(nombre)
                .map(this::toDomain);
    }

    // ========== OPERACIONES DE ESCRITURA ==========

    @Override
    public Xxx save(Xxx xxx) {
        JpaXxxEntity entity = toEntity(xxx);
        JpaXxxEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(Xxx xxx) {
        JpaXxxEntity entity = toEntity(xxx);
        repository.delete(entity);
    }
}
```

---

### **PASO 3: Actualizar QueryService**

**Cambios en:** `application/service/xxx/XxxQueryService.java`

```java
// ❌ ANTES
import ...XxxReadRepository;

@Service
public class XxxQueryService {
    private final XxxReadRepository xxxReadRepository;
    
    public XxxQueryService(XxxReadRepository xxxReadRepository) {
        this.xxxReadRepository = xxxReadRepository;
    }
    
    public XxxView obtenerPorId(Integer id) {
        return xxxReadRepository.findById(id)...
    }
}

// ✅ DESPUÉS
import ...XxxRepository;

@Service
public class XxxQueryService {
    private final XxxRepository xxxRepository;
    
    public XxxQueryService(XxxRepository xxxRepository) {
        this.xxxRepository = xxxRepository;
    }
    
    public XxxView obtenerPorId(Integer id) {
        return xxxRepository.findById(id)...
    }
}
```

---

### **PASO 4: Actualizar CommandService**

**Cambios en:** `application/service/xxx/XxxCommandService.java`

```java
// ❌ ANTES
import ...XxxWriteRepository;

@Service
public class XxxCommandService {
    private final XxxWriteRepository xxxWriteRepository;
    
    public XxxCommandService(XxxWriteRepository xxxWriteRepository) {
        this.xxxWriteRepository = xxxWriteRepository;
    }
    
    public XxxView crear(XxxDto dto) {
        return xxxWriteRepository.save(xxx)...
    }
}

// ✅ DESPUÉS
import ...XxxRepository;

@Service
public class XxxCommandService {
    private final XxxRepository xxxRepository;
    
    public XxxCommandService(XxxRepository xxxRepository) {
        this.xxxRepository = xxxRepository;
    }
    
    public XxxView crear(XxxDto dto) {
        return xxxRepository.save(xxx)...
    }
}
```

---

### **PASO 5: Eliminar Archivos Obsoletos (OPCIONAL)**

Una vez verificado que todo compila y funciona:

```bash
# Eliminar interfaces obsoletas
rm domain/repository/xxx/XxxFinder.java
rm domain/repository/xxx/XxxReadRepository.java
rm domain/repository/xxx/XxxWriteRepository.java

# Eliminar adapters obsoletos
rm infrastructure/repository/jpa/xxx/XxxReadRepositoryJpaAdapter.java
rm infrastructure/repository/jpa/xxx/XxxWriteRepositoryJpaAdapter.java
```

---

## ✅ ENTIDADES REFACTORIZADAS

### **Completadas:**
- [x] **Producto**
  - ✅ ProductoRepository (interface única)
  - ✅ ProductoRepositoryJpaAdapter (adapter único)
  - ✅ ProductoQueryService actualizado
  - ✅ ProductoCommandService actualizado
  - ✅ Compilación exitosa

### **Pendientes:**
- [ ] Categoria
- [ ] Marca
- [ ] Alerta
- [ ] Cliente
- [ ] Persona
- [ ] MovimientoStock
- [ ] DetalleVenta
- [ ] Usuario
- [ ] Venta
- [ ] Rol

---

## 📊 MÉTRICAS DE MEJORA

### **ANTES:**
- **33+ métodos duplicados** (findById en cada Read/Write)
- **22 pares de dependencias duplicadas**
- **3 interfaces por entidad** (Finder + Read + Write)
- **2 adapters por entidad** (Read + Write)
- **~60 archivos** con lógica duplicada

### **DESPUÉS:**
- ✅ **0 métodos duplicados**
- ✅ **1 dependencia por adapter** (repository + mapper)
- ✅ **1 interface por entidad**
- ✅ **1 adapter por entidad**
- ✅ **~30 archivos menos** (simplificación 50%)

---

## 🎯 BENEFICIOS OBTENIDOS

1. **Eliminación total de duplicación**
2. **Código más simple y directo**
3. **Menos archivos que mantener**
4. **Arquitectura hexagonal intacta**
5. **DDD preservado**
6. **Servicios Query/Command pueden mantenerse o consolidarse**

---

## 🚨 CONSIDERACIONES

### **¿Mantener Query/Command Services Separados?**

**Opción A: Mantener separados** (Recomendado inicialmente)
- ✅ Menor riesgo de romper código existente
- ✅ Separación de responsabilidades clara
- ✅ Controllers siguen funcionando igual

**Opción B: Consolidar en un solo Service**
- ✅ Menos archivos
- ✅ Más directo
- ❌ Requiere cambios en controllers
- ❌ Mayor refactor inicial

**Decisión:** Mantener Query/Command separados por ahora.

---

## 📝 CHECKLIST POR ENTIDAD

Para cada entidad pendiente:

1. [ ] Crear `XxxRepository.java` en domain/repository/xxx/
2. [ ] Crear `XxxRepositoryJpaAdapter.java` en infrastructure/repository/jpa/xxx/
3. [ ] Actualizar `XxxQueryService.java` para usar `XxxRepository`
4. [ ] Actualizar `XxxCommandService.java` para usar `XxxRepository`
5. [ ] Buscar otros servicios que usen Read/Write y actualizar
6. [ ] Compilar y verificar
7. [ ] (Opcional) Eliminar archivos obsoletos

---

## 🔧 COMANDOS ÚTILES

```bash
# Compilar
./gradlew compileJava --console=plain

# Buscar referencias a XxxReadRepository
grep -r "XxxReadRepository" src/

# Buscar referencias a XxxWriteRepository
grep -r "XxxWriteRepository" src/

# Ejecutar tests
./gradlew test --console=plain
```

---

**Fecha:** 30 de noviembre de 2025  
**Versión:** 2.0 - Eliminación de CQRS  
**Estado:** Producto completado ✅
