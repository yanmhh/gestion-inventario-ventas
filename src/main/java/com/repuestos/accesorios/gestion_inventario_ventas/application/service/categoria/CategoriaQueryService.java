package com.repuestos.accesorios.gestion_inventario_ventas.application.service.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria.CategoriaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.CategoriaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria.CategoriaReadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaQueryService {

    private final CategoriaReadRepository categoriaReadRepository;

    public CategoriaQueryService(CategoriaReadRepository categoriaReadRepository) {
        this.categoriaReadRepository = categoriaReadRepository;
    }

    public Page<CategoriaDto> listarTodosLasCategorias(Pageable pageable){
        return categoriaReadRepository.findAll(pageable)
                .map(CategoriaViewMapper::from);
    }

    public CategoriaDto obtenerCategoriaPorId(Integer id){
        Categoria categoria = categoriaReadRepository.findById(id)
                .orElseThrow(()->new CategoriaNoEncontradaException("Categoria con ID " + id + " no encontrado."));

        return CategoriaViewMapper.from(categoria);
    }

}
