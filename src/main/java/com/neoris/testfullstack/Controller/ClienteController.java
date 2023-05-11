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
import com.neoris.testfullstack.Entity.Cliente;
import com.neoris.testfullstack.IService.IClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/cliente")
public class ClienteController {

	@Autowired
    private IClienteService service;
	
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
	
    
    @Operation(summary = "Obtener todos los clientes", responses = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida"),
            @ApiResponse(responseCode = "404", description = "No se encontraron clientes")
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Cliente>>> all() {
        try {
        	return ResponseEntity.ok(new ApiResponseDto<List<Cliente>>("Datos obtenidos", service.all(), true));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponseDto<List<Cliente>>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Obtener un cliente por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cliente>> show(@PathVariable Long id) {
    	try {
    		Cliente cliente = service.findById(id);

        	return ResponseEntity.ok(new ApiResponseDto<Cliente>("Cliente encontrado",cliente, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Cliente>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Crear un nuevo cliente", responses = {
            @ApiResponse(responseCode = "201", description = "Cliente creado")
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<Cliente>> save(@RequestBody Cliente cliente) {
        try {
        	Cliente clienteSave = service.save(cliente);
        	
        	return ResponseEntity.ok(new ApiResponseDto<Cliente>("Cliente guardado", clienteSave, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Cliente>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Actualizar un cliente existente", responses = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cliente>> update(@PathVariable Long id, @RequestBody Cliente cliente) {
    	try {
    		service.update(id, cliente);
        	return ResponseEntity.ok(new ApiResponseDto<Cliente>("Cliente actualizado", null, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Cliente>(e.getMessage(), null, false));
		}
    }
    
    @Operation(summary = "Eliminar un cliente existente", responses = {
        @ApiResponse(responseCode = "204", description = "Cliente eliminado"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<Cliente>> delete(@PathVariable Long id) {
        try {
        	service.delete(id);
        	return ResponseEntity.ok(new ApiResponseDto<Cliente>("Cliente eliminado", null, true));
		} catch (Exception e) {
        	return ResponseEntity.internalServerError().body(new ApiResponseDto<Cliente>(e.getMessage(), null, false));
		}
    }
}