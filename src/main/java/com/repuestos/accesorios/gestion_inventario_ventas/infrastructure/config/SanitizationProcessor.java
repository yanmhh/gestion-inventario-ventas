package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.config;
import com.repuestos.accesorios.gestion_inventario_ventas.application.shared.Sanitize;
import com.repuestos.accesorios.gestion_inventario_ventas.application.shared.SanitizerUtil;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.SanitizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

import org.springframework.lang.Nullable;


@Component
public class SanitizationProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(SanitizationProcessor.class);

    // Limita la sanitización solo a paquetes DTO específicos (hexagonal: capa de aplicación)
    private static final String[] ALLOWED_PACKAGES = {
            "com.repuestos.accesorios.gestion_inventario_ventas.application.dto"
    };

    // Lista de anotaciones consideradas para sanitización (actualmente solo @Sanitize)
    @SuppressWarnings("unchecked")
    private static final Class<? extends Annotation>[] SANITIZE_ANNOTATIONS = (Class<? extends Annotation>[]) new Class[]{
            Sanitize.class
    };

    @Override
    public Object postProcessBeforeInitialization(@Nullable Object bean, @Nullable String beanName) {
        if (bean == null) return null;

        if (!shouldSanitizeBean(bean)) return bean;

        try {
            sanitizeObject(bean);
        } catch (SanitizationException e) {
            logger.error("Sanitization failed on bean '{}': {}", beanName, e.getMessage(), e);
        }

        return bean;
    }

    /**
     * Verifica si el bean pertenece a los paquetes permitidos para sanitización.
     */
    private boolean shouldSanitizeBean(Object bean) {
        String className = bean.getClass().getName();
        for (String pkg : ALLOWED_PACKAGES) {
            if (className.startsWith(pkg)) return true;
        }
        return false;
    }

    /**
     * Sanitiza todos los campos de un objeto, incluidos objetos anidados y colecciones.
     */
    private void sanitizeObject(Object obj) {
        if (obj == null) return;

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value == null) continue;

                if (field.getType().equals(String.class) && hasSanitizeAnnotation(field)) {
                    // Sanitizar campo String anotado
                    String original = (String) value;
                    String sanitized = SanitizerUtil.sanitize(original);
                    field.set(obj, sanitized);
                } else if (value instanceof Collection<?>) {
                    // Recursividad en colecciones
                    sanitizeCollection((Collection<?>) value);
                } else if (isSanitizableClass(field.getType())) {
                    // Recursividad en objetos anidados
                    sanitizeObject(value);
                }

            } catch (IllegalAccessException e) {
                String errorMsg = String.format("Error sanitizing field '%s' of class '%s'", field.getName(), clazz.getName());
                throw new SanitizationException(errorMsg, e);
            }
        }
    }

    /**
     * Sanitiza cada objeto de una colección si es sanitizable.
     */
    private void sanitizeCollection(Collection<?> collection) {
        for (Object item : collection) {
            if (item != null && isSanitizableClass(item.getClass())) {
                sanitizeObject(item);
            }
        }
    }

    /**
     * Verifica si el campo tiene una anotación que requiere sanitización.
     */
    private boolean hasSanitizeAnnotation(Field field) {
        for (Class<? extends Annotation> annClass : SANITIZE_ANNOTATIONS) {
            if (field.isAnnotationPresent(annClass)) return true;
        }
        return false;
    }

    /**
     * Verifica si la clase no es interna del JDK (ej: String, Integer, etc).
     */
    private boolean isSanitizableClass(Class<?> clazz) {
        if (clazz.isPrimitive()) return false;
        String packageName = clazz.getPackage() != null ? clazz.getPackage().getName() : "";
        return !(packageName.startsWith("java.") || packageName.startsWith("javax."));
    }
}