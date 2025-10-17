package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.UsuarioView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.usuario.UserQueryService;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.security.CustomUserDetails;
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
    public UsuarioView me(Authentication auth) {
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Integer userId = userDetails.getUsuario().getId();
        return userQueryService.getById(userId);
    }
}
