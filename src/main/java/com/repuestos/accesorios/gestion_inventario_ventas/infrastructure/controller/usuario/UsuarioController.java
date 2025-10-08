package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.service.usuario.UserQueryService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {
    private final UserQueryService userQueryService;

    public UsuarioController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping("/autenticado")
    public Usuario me(Authentication auth) {
        Integer userId = (Integer) auth.getPrincipal();
        return userQueryService.getById(userId);
    }
}
