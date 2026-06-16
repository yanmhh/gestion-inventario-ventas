package com.repuestos.accesorios.gestion_inventario_ventas.application.service.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria.CategoriaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria.CategoriaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.CategoriaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaCommandService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaCommandService (CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaView registrarCategoria (CategoriaDto categoriaDto){
        Categoria categoria = CategoriaMapper.toDomain(categoriaDto);
        categoria = categoriaRepository.save(categoria);
        return CategoriaViewMapper.toView(categoria);
    }

    public CategoriaView actualizarCategoria(Integer id,CategoriaDto categoria){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNoEncontradaException("Categoria con ID " + id + " no encontrada."));
        CategoriaMapper.mapUpdateData(categoria, categoriaExistente);
        Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);
        return CategoriaViewMapper.toView(categoriaActualizada);
    }

    public void eliminarCategoria( Integer id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNoEncontradaException("Categoria con ID " + id + " no encontrada."));
        categoriaRepository.delete(categoria);
    }
}
