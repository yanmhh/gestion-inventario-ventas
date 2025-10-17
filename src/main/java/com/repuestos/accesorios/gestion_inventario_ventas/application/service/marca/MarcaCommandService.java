package com.repuestos.accesorios.gestion_inventario_ventas.application.service.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca.MarcaMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.marca.MarcaViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MarcaNoEncontradaException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.marca.Marca;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.marca.MarcaWriteRepository;
import org.springframework.stereotype.Service;

@Service
public class MarcaCommandService {
    private final MarcaWriteRepository marcaWriteRepository;

    public MarcaCommandService(MarcaWriteRepository marcaWriteRepository){
        this.marcaWriteRepository = marcaWriteRepository;
    }

    public MarcaView registrarMarca (MarcaDto marcaDto){
        Marca marca = MarcaMapper.toDomain(marcaDto);
        marca = marcaWriteRepository.save(marca);
        return MarcaViewMapper.toView(marca);
    }

    public MarcaView actualizarMarca(Integer id, MarcaDto marca){
        Marca macaExistente = marcaWriteRepository.findById(id)
                .orElseThrow(() -> new MarcaNoEncontradaException("Marca con ID " + id + " no encontrada."));
        MarcaMapper.mapUpdateData(marca, macaExistente);
        Marca marcaActualizada = marcaWriteRepository.save(macaExistente);
        return MarcaViewMapper.toView(marcaActualizada);
    }

    public void eliminarMarca(Integer id){
        Marca marca = marcaWriteRepository.findById(id)
                .orElseThrow(() -> new MarcaNoEncontradaException("Marca con ID " + id + " no encontrado."));
        marcaWriteRepository.delete(marca);
    }
}
