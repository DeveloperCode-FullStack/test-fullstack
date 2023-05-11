package com.neoris.testfullstack.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neoris.testfullstack.Entity.Persona;
import com.neoris.testfullstack.Dto.IBasicDto;

/**
* This interface defines the necessary methods to handle the Persona entity.
*/
public interface IPersonaService {

	/**
     * Retorna una página de objetos IBasicDto representando una lista de Personas, de acuerdo a los filtros especificados.
     * @param pageable un objeto Pageable que especifica la paginación a aplicar a los resultados.
     * @param search un String que se utilizará para buscar personas en base a su nombre y apellido.
     * @return un objeto Page que contiene los resultados de la búsqueda.
     */
    public Page<IBasicDto> getDatatable(Pageable pageable, String search);

    /**
     * Retorna una lista de todas las Personas.
     * @return una lista de objetos Persona.
     */
    public List<Persona> all();
    
    /**
     * Retorna la Persona correspondiente al ID especificado.
     * @param id el ID de la Persona que se desea buscar.
     * @return un objeto Persona correspondiente al ID especificado.
     * @throws Exception si no se encuentra ninguna Persona con el ID especificado.
     */
    public Persona findById(Long id) throws Exception;
    
    /**
     * Crea una nueva Persona.
     * @param persona un objeto Persona que se desea guardar.
     * @return un objeto Persona que representa la Persona guardada.
     */
    public Persona save(Persona persona);
    
    /**
     * Actualiza la Persona correspondiente al ID especificado.
     * @param id el ID de la Persona que se desea actualizar.
     * @param persona un objeto Persona que contiene los campos actualizados.
     * @throws Exception si no se encuentra ninguna Persona con el ID especificado.
     */
    public void update(Long id, Persona persona) throws Exception;
    
    /**
     * Elimina la Persona correspondiente al ID especificado.
     * @param id el ID de la Persona que se desea eliminar.
     * @throws Exception si no se encuentra ninguna Persona con el ID especificado.
     */
    public void delete(Long id) throws Exception;
}
