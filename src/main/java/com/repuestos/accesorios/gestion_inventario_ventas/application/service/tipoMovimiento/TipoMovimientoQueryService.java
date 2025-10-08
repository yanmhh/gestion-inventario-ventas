package com.repuestos.accesorios.gestion_inventario_ventas.application.service.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.TipoMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.tipoMovimiento.TipoMovimientoViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MovimientoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.tipoMovimiento.TipoMovimientoReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoMovimientoQueryService {
    private final TipoMovimientoReadRepository tipoMovimientoReadRepository;

    public TipoMovimientoQueryService(TipoMovimientoReadRepository tipoMovimientoReadRepository){
        this.tipoMovimientoReadRepository = tipoMovimientoReadRepository;
    }

    public TipoMovimientoDto obtenerTipoMovimiento(Integer id){
        TipoMovimiento tipoMovimiento = tipoMovimientoReadRepository.findById(id).
                orElseThrow(()-> new MovimientoNoEncontradoException("Tipo de movimiento con ID " + id + " no encontrado."));
        return TipoMovimientoViewMapper.from(tipoMovimiento);
    }

    public List<TipoMovimientoDto> obtenerTodosLosTiposMovimientos(){
        return tipoMovimientoReadRepository.findAll().stream().map(TipoMovimientoViewMapper::from).collect(Collectors.toList());
    }
}
