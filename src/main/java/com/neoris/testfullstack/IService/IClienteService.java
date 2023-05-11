package com.neoris.testfullstack.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neoris.testfullstack.Entity.Cliente;
import com.neoris.testfullstack.Dto.IClienteDto;

/**
* This interface defines the necessary methods to handle the Cliente entity.
*/
public interface IClienteService {

    /**
     * Recupera una lista paginada de clientes que coincidan con una cadena de búsqueda.
     *
     * @param pageable información sobre la paginación
     * @param search la cadena de búsqueda para filtrar los clientes
     * @return una página de objetos IClienteDto que representan los clientes encontrados
     */
    public Page<IClienteDto> getDatatable(Pageable pageable, String search); 

    /**
     * Recupera todos los clientes existentes.
     *
     * @return una lista de objetos Cliente que representan todos los clientes existentes
     */
    public List<Cliente> all();    
    
    /**
     * Recupera un cliente por su ID.
     *
     * @param id el ID del cliente a recuperar
     * @return el objeto Cliente correspondiente al ID proporcionado
     * @throws Exception si no se encuentra ningún cliente con el ID proporcionado
     */
    public Cliente findById(Long id) throws Exception;    
    
    /**
     * Guarda un cliente en la base de datos.
     *
     * @param cliente el objeto Cliente a guardar
     * @return el objeto Cliente guardado en la base de datos
     */
    public Cliente save(Cliente cliente);
    
    /**
     * Actualiza un cliente existente en la base de datos.
     *
     * @param id el ID del cliente a actualizar
     * @param cliente el objeto Cliente con los datos actualizados
     * @throws Exception si no se encuentra ningún cliente con el ID proporcionado
     */
    public void update(Long id, Cliente cliente) throws Exception;
    
    /**
     * Elimina un cliente existente de la base de datos.
     *
     * @param id el ID del cliente a eliminar
     * @throws Exception si no se encuentra ningún cliente con el ID proporcionado
     */
    public void delete(Long id) throws Exception;
}
