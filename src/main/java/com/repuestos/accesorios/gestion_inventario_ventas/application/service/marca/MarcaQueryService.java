package com.repuestos.accesorios.gestion_inventario_ventas.application.service.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca.MarcaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MarcaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca.MarcaReadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MarcaQueryService {
    private final MarcaReadRepository marcaReadRepository;

    public MarcaQueryService(MarcaReadRepository marcaReadRepository) {
        this.marcaReadRepository = marcaReadRepository;
    }

    public Page<MarcaDto> listarTodasLasMarcas (Pageable pageable){
        return marcaReadRepository.findAll(pageable)
                .map(MarcaViewMapper::from);
    }

    public MarcaDto obtenerMarcaPorId(Integer id){
        Marca marca = marcaReadRepository.findById(id)
                .orElseThrow(()-> new MarcaNoEncontradaException("Marca con ID " + id + " no encontrado."));
        return  MarcaViewMapper.from(marca);
    }
}
