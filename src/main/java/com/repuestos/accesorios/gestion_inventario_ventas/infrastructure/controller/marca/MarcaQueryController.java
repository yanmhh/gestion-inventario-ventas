package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.marca.MarcaQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/marcas")
public class MarcaQueryController {

    private final MarcaQueryService marcaQueryService;

    public MarcaQueryController(MarcaQueryService marcaQueryService){
        this.marcaQueryService = marcaQueryService;
    }

    @GetMapping
    public ResponseEntity<Page<MarcaDto>> listarTodasLasMarcas(Pageable pageable){
        Page<MarcaDto> marcas = marcaQueryService.listarTodasLasMarcas(pageable);
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDto> obtenerMarcaPorId(@PathVariable Integer id){
        MarcaDto marca = marcaQueryService.obtenerMarcaPorId(id);
        return ResponseEntity.ok(marca);
    }
}
