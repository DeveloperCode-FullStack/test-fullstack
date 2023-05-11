package com.neoris.testfullstack.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neoris.testfullstack.Dto.IMovimientoDto;
import com.neoris.testfullstack.Entity.Cuenta;
import com.neoris.testfullstack.Entity.Movimiento;
import com.neoris.testfullstack.IRepository.ICuentaRepository;
import com.neoris.testfullstack.IRepository.IMovimientoRepository;
import com.neoris.testfullstack.IService.IMovimientoService;
import com.neoris.testfullstack.Utils.GlobalConstants;

/**
* This class provides the implementation for the IMovimientoService interface.
* It's annotated with @Service to indicate that it's a Spring service component.
*/
@Service
public class MovimientoService implements IMovimientoService{
	
	/**
	 * Inyecta una instancia de IMovimientoRepository en esta clase.
	 */
    @Autowired
	public IMovimientoRepository repository;
    
    @Autowired
	public ICuentaRepository repositoryCuenta;

    /**
     * Datos de la tabla
     * @param pageable
     * @param search
     * @return datos de la tabla
     */  
    @Override
	public Page<IMovimientoDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}

    /**
     * Devuelve todas las entidad Movimiento de la base de datos.
     *
     * @return una lista de todas la entidade Movimiento
     */
	@Override
	public List<Movimiento> all() {		
		return repository.findAll();
	}

	/**
	 * Returns un Optional que contiene la entidad Movimiento con el id especificado, si existe en la base de datos.
	 *
	 * @param id el id de la entidad Movimiento a recuperar
	 * @return un Optional que contiene la entidad Movimiento con el id especificado, o un Optional vacío si no existe
	 */
	@Override
	public Movimiento findById(Long id) throws Exception {
		Optional<Movimiento> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
		
		return op.get();
	}

	/**
	 * Guarda la entidad Movimiento especificada en la base de datos.
	 *
	 * @param movimiento la entidad Movimiento a guardar
	 * @return la entidad Movimiento guardada
	 */
	@Override
	public Movimiento save(Movimiento movimiento)  throws Exception{		
		Double saldoDisponible=0.0;

		//Obtener el saldo inicial de la cuenta
		Long cuentaId = movimiento.getCuentaId().getId();
		Optional<Cuenta> opCuenta = this.repositoryCuenta.findById(cuentaId);
		
		if (opCuenta.isEmpty()) throw new Exception("No se encontró registro");		
		Cuenta cuenta = opCuenta.get();
		
		if(movimiento.getTipoMovimiento()==true){
			if(cuenta.getSaldoInicial()<movimiento.getValor()) throw new Exception("Saldo insuficiente");			
			saldoDisponible = cuenta.getSaldoInicial()-movimiento.getValor();
			
		}else {
			saldoDisponible= cuenta.getSaldoInicial()+movimiento.getValor();
						
		}
				
		movimiento.setValor(movimiento.getTipoMovimiento()==true?movimiento.getValor()*-1:movimiento.getValor());
	    movimiento.setSaldo(saldoDisponible);
	    cuenta.setSaldoInicial(saldoDisponible);
	    
	    repositoryCuenta.save(cuenta);
	    return repository.save(movimiento);
	}
	
	/**
	 * Modifica la entidad Movimiento especificada en la base de datos.
	 *
	 * @param movimiento la entidad Movimiento a guardar
	 * @param id la entidad Movimiento a guardar
	 * @return la entidad Movimiento modificada
	 */
	@Override
	public void update(Long id, Movimiento movimiento) throws Exception {
		Optional<Movimiento> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
        
        Movimiento movimientoUpdate = op.get();
        BeanUtils.copyProperties(movimiento, movimientoUpdate, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0])); 
        
        this.repository.save(movimientoUpdate);
	}

	/**
	 * Elimina la entidad Movimiento con el id especificado de la base de datos.
	 *
	 * @param id el id de la entidad Movimiento a eliminar
	 */
	@Override
	public void delete(Long id) throws Exception {
		Optional<Movimiento> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
        
        Movimiento movimientoUpdate = op.get();        
        
		repository.deleteById(movimientoUpdate.getId());
	}

	/**
	 * Devuelve el saldo de la cuenta con el id especificado.
	 *
	 * @param id el id de la cuenta para obtener el saldo
	 * @return el saldo de la cuenta
	 * @throws Exception si ocurre un error al obtener el saldo de la cuenta
	 */
	@Override
	public Double getSaldoCuentaById(Long id) throws Exception {
		return repository.SaldoCuentaById(id);		
	}

	/**
	 * Devuelve una lista de objetos IMovimientoDto que representan los movimientos realizados en una cuenta dentro del rango de fechas especificado.
	 *
	 * @param cuentaId el id de la cuenta de la cual se desean obtener los movimientos
	 * @param fechaInicio la fecha de inicio del rango de fechas
	 * @param fechaFin la fecha de fin del rango de fechas
	 * @return una lista de objetos IMovimientoDto que representan los movimientos realizados en la cuenta especificada dentro del rango de fechas especificado
	 * @throws Exception si ocurre un error al ejecutar la consulta
	 */
	@Override
	public List<IMovimientoDto> reportMovement(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws Exception{
		return repository.ReportMovement(cuentaId, fechaInicio, fechaFin);
	}
}