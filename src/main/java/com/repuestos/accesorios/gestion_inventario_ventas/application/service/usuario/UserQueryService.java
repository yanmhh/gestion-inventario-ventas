package com.repuestos.accesorios.gestion_inventario_ventas.application.service.usuario;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.usuario.UsuarioView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.usuario.UsuarioViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.UsuarioNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.usuario.Usuario;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private final UsuarioRepository usuarioRepository;

    public UserQueryService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioView getById(Integer id){
        return usuarioRepository.findById(id).map(UsuarioViewMapper::toView)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
    }
}
