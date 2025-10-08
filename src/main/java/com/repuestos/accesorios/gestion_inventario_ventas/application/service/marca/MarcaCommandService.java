package com.repuestos.accesorios.gestion_inventario_ventas.application.service.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
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

    public MarcaDto registrarMarca (MarcaDto marcaDto){
        Marca marca = MarcaMapper.from(marcaDto);
        marca = marcaWriteRepository.save(marca);
        return MarcaViewMapper.from(marca);
    }

    public void eliminarMarca(Integer id){
        Marca marca = marcaWriteRepository.findById(id)
                .orElseThrow(() -> new MarcaNoEncontradaException("Marca con ID " + id + " no encontrado."));
        marcaWriteRepository.delete(marca);
    }
}
