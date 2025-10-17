package com.repuestos.accesorios.gestion_inventario_ventas.infrastructure.controller.marca;

import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaDto;
import com.repuestos.accesorios.gestion_inventario_ventas.application.dto.marca.MarcaView;
import com.repuestos.accesorios.gestion_inventario_ventas.application.service.marca.MarcaQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/marcas/query")
public class MarcaQueryController {

    private final MarcaQueryService marcaQueryService;

    public MarcaQueryController(MarcaQueryService marcaQueryService){
        this.marcaQueryService = marcaQueryService;
    }

    @GetMapping
    public ResponseEntity<List<MarcaView>> listarTodasLasMarcas(){
        List<MarcaView> marcas = marcaQueryService.listarTodasLasMarcas();
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaView> obtenerMarcaPorId(@PathVariable Integer id){
        MarcaView marca = marcaQueryService.obtenerMarcaPorId(id);
        return ResponseEntity.ok(marca);
    }
}
