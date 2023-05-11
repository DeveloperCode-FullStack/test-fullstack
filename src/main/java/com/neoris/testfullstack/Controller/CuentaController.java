package com.neoris.testfullstack.Controller;

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
import com.neoris.testfullstack.Dto.ICuentaDto;
import com.neoris.testfullstack.Entity.Cuenta;
import com.neoris.testfullstack.IService.ICuentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/cuenta")
public class CuentaController {

	@Autowired
    private ICuentaService service;

	@Operation(summary = "Metodo para datatable", responses = {
			@ApiResponse(responseCode = "200", description = "Datatable obtenido"),
			@ApiResponse(responseCode = "404", description = "Datatable obtenido"),
	})
	@GetMapping("/datatable")
	public ResponseEntity<ApiResponseDto<Page<ICuentaDto>>> datatable(@RequestParam(name = "page") Integer page,
			@RequestParam(name = "size") Integer size,
			@RequestParam(name = "column_order") String columnOrder,
			@RequestParam(name = "column_direction") String columnDirection,
			@RequestParam(name = "search", required = false) String search) {
		try {

			List<Order> orders = new ArrayList<>();

			orders.add(new Order(columnDirection == "asc" ? Direction.ASC : Direction.DESC, columnOrder));

			Page<ICuentaDto> data = service.getDatatable(PageRequest.of(page, size, Sort.by(orders)), search);

			return ResponseEntity.ok(new ApiResponseDto<Page<ICuentaDto>>("Datos obtenidos", data, true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
					.body(new ApiResponseDto<Page<ICuentaDto>>(e.getMessage(), null, false));
		}
	}
    
    @Operation(summary = "Obtener todos los cuentas", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de cuentas obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron cuentas")
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Cuenta>>> all() {
        try {
        	return ResponseEntity.ok(new ApiResponseDto<List<Cuenta>>("Datos obtenidos", service.all(), true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<Cuenta>>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Obtener un cuenta por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Cuenta encontrado"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrado")
    })
    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cuenta>> show(@PathVariable Long id) {
    	try {
    		Cuenta cuenta = service.findById(id);

        	return ResponseEntity.ok(new ApiResponseDto<Cuenta>("Cuenta encontrado",cuenta, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Cuenta>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Crear un nuevo cuenta", responses = {
            @ApiResponse(responseCode = "201", description = "Cuenta creado")
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<Cuenta>> save(@RequestBody Cuenta cuenta) {
        try {
        	Cuenta cuentaSave = service.save(cuenta);
        	
        	return ResponseEntity.ok(new ApiResponseDto<Cuenta>("Cuenta guardado", cuentaSave, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Cuenta>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Actualizar un cuenta existente", responses = {
            @ApiResponse(responseCode = "200", description = "Cuenta actualizado"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrado")
    })
    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cuenta>> update(@PathVariable Long id, @RequestBody Cuenta cuenta) {
    	try {
    		service.update(id, cuenta);
        	return ResponseEntity.ok(new ApiResponseDto<Cuenta>("Cuenta actualizado", null, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Cuenta>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Eliminar un cuenta existente", responses = {
        @ApiResponse(responseCode = "204", description = "Cuenta eliminado"),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrado")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cuenta>> delete(@PathVariable Long id) {
        try {
        	service.delete(id);
        	return ResponseEntity.ok(new ApiResponseDto<Cuenta>("Cuenta eliminado", null, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Cuenta>(e.getMessage(), null, false));
		}
    }
}