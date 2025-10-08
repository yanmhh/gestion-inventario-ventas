package com.repuestos.accesorios.gestion_inventario_ventas.application.service.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria.CategoriaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria.CategoriaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.CategoriaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria.CategoriaWriteRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaCommandService {

    private final CategoriaWriteRepository categoriaWriteRepository;

    public CategoriaCommandService (CategoriaWriteRepository categoriaWriteRepository){
        this.categoriaWriteRepository = categoriaWriteRepository;
    }

    public CategoriaDto registrarCategoria (CategoriaDto categoriaDto){
        Categoria categoria = CategoriaMapper.from(categoriaDto);
        categoria = categoriaWriteRepository.save(categoria);
        return CategoriaViewMapper.from(categoria);
    }

    public void eliminarCategoria( Integer id){
        Categoria categoria = categoriaWriteRepository.findById(id)
                .orElseThrow(() -> new CategoriaNoEncontradaException("Categoria con ID " + id + " no encontrada."));
        categoriaWriteRepository.delete(categoria);
    }
}
