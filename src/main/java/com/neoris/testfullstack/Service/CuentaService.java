package com.neoris.testfullstack.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neoris.testfullstack.Dto.ICuentaDto;
import com.neoris.testfullstack.Entity.Cuenta;
import com.neoris.testfullstack.IRepository.ICuentaRepository;
import com.neoris.testfullstack.IService.ICuentaService;
import com.neoris.testfullstack.Utils.GlobalConstants;

/**
* This class provides the implementation for the ICuentaService interface.
* It's annotated with @Service to indicate that it's a Spring service component.
*/
@Service
public class CuentaService implements ICuentaService{
	
	/**
	 * Inyecta una instancia de ICuentaRepository en esta clase.
	 */
    @Autowired
	public ICuentaRepository repository;

    /**
     * Datos de la tabla
     * @param pageable
     * @param search
     * @return datos de la tabla
     */  
    @Override
	public Page<ICuentaDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable);
	}

    /**
     * Devuelve todas las entidad Cuenta de la base de datos.
     *
     * @return una lista de todas la entidade Cuenta
     */
	@Override
	public List<Cuenta> all() {		
		return repository.findAll();
	}

	/**
	 * Returns un Optional que contiene la entidad Cuenta con el id especificado, si existe en la base de datos.
	 *
	 * @param id el id de la entidad Cuenta a recuperar
	 * @return un Optional que contiene la entidad Cuenta con el id especificado, o un Optional vacío si no existe
	 */
	@Override
	public Cuenta findById(Long id) throws Exception {
		Optional<Cuenta> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
		
		return op.get();
	}

	/**
	 * Guarda la entidad Cuenta especificada en la base de datos.
	 *
	 * @param cliente la entidad Cuenta a guardar
	 * @return la entidad Cuenta guardada
	 */
	@Override
	public Cuenta save(Cuenta cuenta) {	
		return repository.save(cuenta);
	}
	
	/**
	 * Modifica la entidad Cuenta especificada en la base de datos.
	 *
	 * @param cuenta la entidad Cuenta a guardar
	 * @param id la entidad Cuenta a guardar
	 * @return la entidad Cuenta modificada
	 */
	@Override
	public void update(Long id, Cuenta cuenta) throws Exception {
		Optional<Cuenta> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
        
        Cuenta cuentaUpdate = op.get();
        BeanUtils.copyProperties(cuenta, cuentaUpdate, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0])); 
        
        this.repository.save(cuentaUpdate);
	}

	/**
	 * Elimina la entidad Cuenta con el id especificado de la base de datos.
	 *
	 * @param id el id de la entidad Cuenta a eliminar
	 */
	@Override
	public void delete(Long id) throws Exception {
		Optional<Cuenta> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
        
        Cuenta cuentaUpdate = op.get();        
        
        repository.deleteById(cuentaUpdate.getId());
	}

	
}