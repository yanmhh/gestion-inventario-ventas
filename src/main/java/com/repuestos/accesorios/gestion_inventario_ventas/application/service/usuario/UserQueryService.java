package com.repuestos.accesorios.gestion_inventario_ventas.application.service.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.UsuarioNotFoundException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private final UsuarioRepository usuarioRepository;

    public UserQueryService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario getById(Integer id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));
    }
}
