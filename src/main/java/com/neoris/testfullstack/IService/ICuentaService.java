package com.neoris.testfullstack.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neoris.testfullstack.Entity.Cuenta;
import com.neoris.testfullstack.Dto.ICuentaDto;

/**
* This interface defines the necessary methods to handle the Cuenta entity.
*/
public interface ICuentaService {

    /**
     * Obtiene una lista paginada de cuentas que coinciden con una cadena de búsqueda.
     *
     * @param pageable objeto que indica la página a obtener y el tamaño de la misma.
     * @param search cadena de búsqueda para filtrar las cuentas por número de cuenta o cliente asociado.
     * @return una página de objetos ICuentaDto que representan las cuentas.
     */
    public Page<ICuentaDto> getDatatable(Pageable pageable, String search);

    /**
     * Obtiene una lista de todas las cuentas.
     *
     * @return una lista de objetos Cuenta que representan las cuentas.
     */
    public List<Cuenta> all();

    /**
     * Busca una cuenta por su ID.
     *
     * @param id el ID de la cuenta a buscar.
     * @return un objeto Cuenta que representa la cuenta encontrada.
     * @throws Exception si no se encuentra la cuenta con el ID especificado.
     */
    public Cuenta findById(Long id) throws Exception;

    /**
     * Guarda una nueva cuenta en la base de datos.
     *
     * @param cuenta la cuenta a guardar.
     * @return un objeto Cuenta que representa la cuenta guardada.
     */
    public Cuenta save(Cuenta cuenta);

    /**
     * Actualiza una cuenta existente en la base de datos.
     *
     * @param id el ID de la cuenta a actualizar.
     * @param cuenta la cuenta actualizada.
     * @throws Exception si no se encuentra la cuenta con el ID especificado.
     */
    public void update(Long id, Cuenta cuenta) throws Exception;

    /**
     * Elimina una cuenta de la base de datos.
     *
     * @param id el ID de la cuenta a eliminar.
     * @throws Exception si no se encuentra la cuenta con el ID especificado.
     */
    public void delete(Long id) throws Exception;
}