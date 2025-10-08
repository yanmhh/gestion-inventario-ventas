package com.repuestos.accesorios.gestion_inventario_ventas.application.service.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.UsuarioNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioReadRepository;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private final UsuarioReadRepository usuarioReadRepository;

    public UserQueryService(UsuarioReadRepository usuarioReadRepository){
        this.usuarioReadRepository = usuarioReadRepository;
    }

    public Usuario getById(Integer id){
        return usuarioReadRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
    }
}
