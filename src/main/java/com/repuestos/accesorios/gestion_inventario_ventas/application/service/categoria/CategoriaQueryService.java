package com.repuestos.accesorios.gestion_inventario_ventas.application.service.categoria;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.categoria.CategoriaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.categoria.CategoriaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.CategoriaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.categoria.Categoria;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.categoria.CategoriaReadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaQueryService {

    private final CategoriaReadRepository categoriaReadRepository;

    public CategoriaQueryService(CategoriaReadRepository categoriaReadRepository) {
        this.categoriaReadRepository = categoriaReadRepository;
    }

    public List<CategoriaView> listarTodosLasCategorias( ){
        return categoriaReadRepository.findAll().stream()
                .map(CategoriaViewMapper::toView).collect(Collectors.toList());
    }

    public CategoriaView obtenerCategoriaPorId(Integer id){
        Categoria categoria = categoriaReadRepository.findById(id)
                .orElseThrow(()->new CategoriaNoEncontradaException("Categoria con ID " + id + " no encontrado."));

        return CategoriaViewMapper.toView(categoria);
    }

}
