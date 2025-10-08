package com.repuestos.accesorios.gestion_inventario_ventas.application.service.tipoMovimiento;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.movimiento.TipoMovimientoDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.tipoMovimiento.TipoMovimientoMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.application.mapper.tipoMovimiento.TipoMovimientoViewMapper;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.exception.MovimientoNoEncontradoException;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.model.inventario.TipoMovimiento;
import com.repuestos.accesorios.gestion_inventario_ventas.domain.repository.tipoMovimiento.TipoMovimientoWriteRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoMovimientoCommandService {

    private final TipoMovimientoWriteRepository tipoMovimientoWriteRepository;

    public TipoMovimientoCommandService(TipoMovimientoWriteRepository tipoMovimientoWriteRepository) {
        this.tipoMovimientoWriteRepository = tipoMovimientoWriteRepository;
    }

    public TipoMovimientoDto registrarTipoMovimiento(TipoMovimientoDto tipoMovimientoDto){
        TipoMovimiento tipoMovimiento = TipoMovimientoMapper.from(tipoMovimientoDto);
        tipoMovimiento = tipoMovimientoWriteRepository.save(tipoMovimiento);
        return TipoMovimientoViewMapper.from(tipoMovimiento);
    }

    public void eliminarTipoMovimiento(Integer id){
        TipoMovimiento tipoMovimiento = tipoMovimientoWriteRepository.findById(id).orElseThrow(()
                -> new MovimientoNoEncontradoException("El movimiento con ID " + " no encontrado"));
    }
}
