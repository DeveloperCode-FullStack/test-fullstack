package com.neoris.testfullstack.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neoris.testfullstack.Dto.IClienteDto;
import com.neoris.testfullstack.Entity.Cliente;
import com.neoris.testfullstack.IRepository.IClienteRepository;
import com.neoris.testfullstack.IService.IClienteService;
import com.neoris.testfullstack.Utils.GlobalConstants;

/**
* This class provides the implementation for the IClienteService interface.
* It's annotated with @Service to indicate that it's a Spring service component.
*/
@Service
public class ClienteService implements IClienteService{
	
	/**
	 * Inyecta una instancia de IClienteRepository en esta clase.
	 */
    @Autowired
	public IClienteRepository repository;
    
    /**
     * Datos de la tabla
     * @param pageable
     * @param search
     * @return datos de la tabla
     */    
    @Override
	public Page<IClienteDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable);
	}

    /**
     * Devuelve todas las entidad Cliente de la base de datos.
     *
     * @return una lista de todas la entidade Cliente
     */
	@Override
	public List<Cliente> all() {		
		return repository.findAll();
	}

	/**
	 * Returns un Optional que contiene la entidad Cliente con el id especificado, si existe en la base de datos.
	 *
	 * @param id el id de la entidad Cliente a recuperar
	 * @return un Optional que contiene la entidad Cliente con el id especificado, o un Optional vacío si no existe
	 */
	@Override
	public Cliente findById(Long id) throws Exception {
		Optional<Cliente> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
		
		return op.get();
	}

	/**
	 * Guarda la entidad Cliente especificada en la base de datos.
	 *
	 * @param cliente la entidad Cliente a guardar
	 * @return la entidad Cliente guardada
	 */
	@Override
	public Cliente save(Cliente clientes) {	
		return repository.save(clientes);
	}
	
	/**
	 * Modifica la entidad Cliente especificada en la base de datos.
	 *
	 * @param cliente la entidad Cliente a guardar
	 * @param id la entidad Cliente a guardar
	 * @return la entidad Cliente modificada
	 */
	@Override
	public void update(Long id, Cliente clientes) throws Exception {
		Optional<Cliente> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
        
        Cliente clientesUpdate = op.get();
        BeanUtils.copyProperties(clientes, clientesUpdate, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0])); 
        
        this.repository.save(clientesUpdate);
	}

	/**
	 * Elimina la entidad Cliente con el id especificado de la base de datos.
	 *
	 * @param id el id de la entidad Cliente a eliminar
	 */
	@Override
	public void delete(Long id) throws Exception {
		Optional<Cliente> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
        
        Cliente clientesUpdate = op.get();        
        
        repository.deleteById(clientesUpdate.getId());
	}

	
}