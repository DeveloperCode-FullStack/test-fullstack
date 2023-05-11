package com.neoris.testfullstack.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.neoris.testfullstack.Dto.IBasicDto;
import com.neoris.testfullstack.Entity.Persona;
import com.neoris.testfullstack.IRepository.IPersonaRepository;
import com.neoris.testfullstack.IService.IPersonaService;
import com.neoris.testfullstack.Utils.GlobalConstants;

/**
* This class provides the implementation for the IPersonaService interface.
* It's annotated with @Service to indicate that it's a Spring service component.
*/
@Service
public class PersonaService implements IPersonaService{
	
	/**
	 * Inyecta una instancia de IPersonaRepository en esta clase.
	 */
    @Autowired
	public IPersonaRepository repository;
    
    /**
     * Datos de la tabla
     * @param pageable
     * @param search
     * @return datos de la tabla
     */  
    @Override
	public Page<IBasicDto> getDatatable(Pageable pageable, String search) {
		return this.repository.getDatatable(pageable, search);
	}

    /**
     * Devuelve todas las entidad Persona de la base de datos.
     *
     * @return una lista de todas la entidade Persona
     */
	@Override
	public List<Persona> all() {		
		return repository.findAll();
	}

	/**
	 * Returns un Optional que contiene la entidad Persona con el id especificado, si existe en la base de datos.
	 *
	 * @param id el id de la entidad Persona a recuperar
	 * @return un Optional que contiene la entidad Persona con el id especificado, o un Optional vacío si no existe
	 */
	@Override
	public Persona findById(Long id) throws Exception {
		Optional<Persona> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
		
		return op.get();
	}

	/**
	 * Guarda la entidad Persona especificada en la base de datos.
	 *
	 * @param clientes la entidad Persona a guardar
	 * @return la entidad Persona guardada
	 */
	@Override
	public Persona save(Persona persona) {	
		return repository.save(persona);
	}
	
	/**
	 * Modifica la entidad Cliente especificada en la base de datos.
	 *
	 * @param cliente la entidad Persona a guardar
	 * @param id la entidad Persona a guardar
	 * @return la entidad Persona modificada
	 */
	@Override
	public void update(Long id, Persona persona) throws Exception {
		Optional<Persona> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
        
        Persona personaUpdate = op.get();
        BeanUtils.copyProperties(persona, personaUpdate, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0])); 
        
        this.repository.save(personaUpdate);
	}

	/**
	 * Elimina la entidad Persona con el id especificado de la base de datos.
	 *
	 * @param id el id de la entidad Persona a eliminar
	 */
	@Override
	public void delete(Long id) throws Exception {
		Optional<Persona> op = this.repository.findById(id);

        if (op.isEmpty()) throw new Exception("No se encontró registro");
        
        Persona personaUpdate = op.get();        
        
		repository.save(personaUpdate);
	}

	
}