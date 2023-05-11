package com.neoris.testfullstack.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neoris.testfullstack.Dto.ApiResponseDto;
import com.neoris.testfullstack.Dto.IMovimientoDto;
import com.neoris.testfullstack.Entity.Movimiento;
import com.neoris.testfullstack.IService.IMovimientoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/movimiento")
public class MovimientoController {

	@Autowired
    private IMovimientoService service;

	@Operation(summary = "Metodo para datatable", responses = {
            @ApiResponse(responseCode = "200", description = "Datatable obtenido"),
            @ApiResponse(responseCode = "404", description = "Datatable obtenido"),
    })

    @GetMapping("/datatable")
    public ResponseEntity<ApiResponseDto<Page<?>>> datatable(@RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size,
            @RequestParam(name = "column_order") String columnOrder,
            @RequestParam(name = "column_direction") String columnDirection,
            @RequestParam(name = "search", required = false) String search) {
        try {
            List<Order> orders = new ArrayList<>();

            orders.add(new Order(columnDirection == "asc" ? Direction.ASC : Direction.DESC, columnOrder));

            return ResponseEntity.ok(new ApiResponseDto<Page<?>>("Datos obtenidos",
                    service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search), true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Page<?>>(e.getMessage(), null, false));
        }
    }
    
    @Operation(summary = "Obtener todos los movimientos", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de movimientos obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron movimientos")
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Movimiento>>> all() {
        try {
        	return ResponseEntity.ok(new ApiResponseDto<List<Movimiento>>("Datos obtenidos", service.all(), true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<Movimiento>>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Obtener un movimiento por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Movimiento encontrado"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto<Movimiento>> show(@PathVariable Long id) {
    	try {
    		Movimiento movimiento = service.findById(id);

        	return ResponseEntity.ok(new ApiResponseDto<Movimiento>("Movimiento encontrado",movimiento, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Movimiento>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Crear un nuevo movimiento", responses = {
            @ApiResponse(responseCode = "201", description = "Movimiento creado")
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<Movimiento>> save(@RequestBody Movimiento movimiento) {
        try {
        	Movimiento movimientoSave = service.save(movimiento);
        	
        	return ResponseEntity.ok(new ApiResponseDto<Movimiento>("Movimiento guardado", movimientoSave, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Movimiento>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Actualizar un movimiento existente", responses = {
            @ApiResponse(responseCode = "200", description = "Movimiento actualizado"),
            @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<Movimiento>> update(@PathVariable Long id, @RequestBody Movimiento movimiento) {
    	try {
    		service.update(id, movimiento);
        	return ResponseEntity.ok(new ApiResponseDto<Movimiento>("Movimiento actualizado", null, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Movimiento>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Eliminar un movimiento existente", responses = {
        @ApiResponse(responseCode = "204", description = "Movimiento eliminado"),
        @ApiResponse(responseCode = "404", description = "Movimiento no encontrado")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<Movimiento>> delete(@PathVariable Long id) {
        try {
        	service.delete(id);
        	return ResponseEntity.ok(new ApiResponseDto<Movimiento>("Movimiento eliminado", null, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Movimiento>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Obtener todos los movimientos", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de movimientos obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron movimientos")
    })
    @GetMapping("/reportMovement/{cuentaId}/{fechaInicio}/{fechaFin}")
    public ResponseEntity<ApiResponseDto<List<IMovimientoDto>>> reportMovement(@PathVariable Long cuentaId, @PathVariable LocalDateTime fechaInicio, @PathVariable LocalDateTime fechaFin) {
        try {
        	return ResponseEntity.ok(new ApiResponseDto<List<IMovimientoDto>>("Datos obtenidos", service.reportMovement(cuentaId, fechaInicio, fechaFin), true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<IMovimientoDto>>(e.getMessage(), null, false));
		}
    }
}