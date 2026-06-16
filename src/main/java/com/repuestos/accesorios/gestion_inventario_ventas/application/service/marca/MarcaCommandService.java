package com.repuestos.accesorios.gestion_inventario_ventas.application.service.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca.MarcaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca.MarcaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MarcaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca.MarcaRepository;
import org.springframework.stereotype.Service;

@Service
public class MarcaCommandService {
    private final MarcaRepository marcaRepository;

    public MarcaCommandService(MarcaRepository marcaRepository){
        this.marcaRepository = marcaRepository;
    }

    public MarcaView registrarMarca (MarcaDto marcaDto){
        Marca marca = MarcaMapper.toDomain(marcaDto);
        marca = marcaRepository.save(marca);
        return MarcaViewMapper.toView(marca);
    }

    public MarcaView actualizarMarca(Integer id, MarcaDto marca){
        Marca macaExistente = marcaRepository.findById(id)
                .orElseThrow(() -> new MarcaNoEncontradaException("Marca con ID " + id + " no encontrada."));
        MarcaMapper.mapUpdateData(marca, macaExistente);
        Marca marcaActualizada = marcaRepository.save(macaExistente);
        return MarcaViewMapper.toView(marcaActualizada);
    }

    public void eliminarMarca(Integer id){
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new MarcaNoEncontradaException("Marca con ID " + id + " no encontrado."));
        marcaRepository.delete(marca);
    }
}
