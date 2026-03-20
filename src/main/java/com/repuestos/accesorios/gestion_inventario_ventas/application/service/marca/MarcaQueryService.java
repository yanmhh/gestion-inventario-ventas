package com.repuestos.accesorios.gestion_inventario_ventas.application.service.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca.MarcaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MarcaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaQueryService {
    private final MarcaRepository marcaRepository;

    public MarcaQueryService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<MarcaView> listarTodasLasMarcas ( ){
        return marcaRepository.findAll().stream()
                .map(MarcaViewMapper::toView).collect(Collectors.toList());
    }

    public MarcaView obtenerMarcaPorId(Integer id){
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(()-> new MarcaNoEncontradaException("Marca con ID " + id + " no encontrado."));
        return  MarcaViewMapper.toView(marca);
    }
}
