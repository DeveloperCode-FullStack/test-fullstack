package com.neoris.testfullstack.IService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neoris.testfullstack.Entity.Movimiento;
import com.neoris.testfullstack.Dto.IMovimientoDto;

/**
* This interface defines the necessary methods to handle the Movimiento entity.
*/
public interface IMovimientoService {

	/**
	 * Obtiene una lista paginada de movimientos que corresponden a una cuenta determinada dentro de un rango de fechas especificado.
	 *
	 * @param pageable objeto Pageable que contiene información acerca de la paginación de resultados
	 * @param search una cadena de texto para filtrar los resultados
	 * @return una lista de objetos IMovimientoDto que representan los movimientos
	 */
	public Page<IMovimientoDto> getDatatable(Pageable pageable, String search);

	/**
	 * Obtiene una lista de todos los movimientos.
	 *
	 * @return una lista de objetos de tipo Movimiento
	 */
	public List<Movimiento> all();

	/**
	 * Obtiene un objeto de tipo Movimiento por su ID.
	 *
	 * @param id el ID del movimiento a buscar
	 * @return un objeto de tipo Movimiento
	 * @throws Exception si no se encuentra el movimiento con el ID especificado
	 */
	public Movimiento findById(Long id) throws Exception;

	/**
	 * Guarda un objeto de tipo Movimiento en la base de datos.
	 *
	 * @param movimiento el objeto de tipo Movimiento a guardar
	 * @return el objeto de tipo Movimiento guardado
	 * @throws Exception si ocurre un error al guardar el objeto de tipo Movimiento
	 */
	public Movimiento save(Movimiento movimiento)  throws Exception;

	/**
	 * Actualiza un objeto de tipo Movimiento en la base de datos.
	 *
	 * @param id el ID del objeto de tipo Movimiento a actualizar
	 * @param movimiento el objeto de tipo Movimiento actualizado
	 * @throws Exception si ocurre un error al actualizar el objeto de tipo Movimiento
	 */
	public void update(Long id, Movimiento movimiento) throws Exception;

	/**
	 * Elimina un objeto de tipo Movimiento de la base de datos.
	 *
	 * @param id el ID del objeto de tipo Movimiento a eliminar
	 * @throws Exception si ocurre un error al eliminar el objeto de tipo Movimiento
	 */
	public void delete(Long id) throws Exception;

	/**
	 * Obtiene el saldo actual de una cuenta.
	 *
	 * @param id el ID de la cuenta
	 * @return el saldo actual de la cuenta
	 * @throws Exception si ocurre un error al obtener el saldo
	 */
	public Double getSaldoCuentaById(Long id) throws Exception;

	/**
	 * Obtiene una lista de movimientos para una cuenta específica dentro de un rango de fechas especificado.
	 *
	 * @param cuentaId el ID de la cuenta a buscar
	 * @param fechaInicio la fecha de inicio del rango de fechas
	 * @param fechaFin la fecha final del rango de fechas
	 * @return una lista de objetos de tipo IMovimientoDto que representan los movimientos
	 * @throws Exception si ocurre un error al obtener la lista de movimientos
	 */
	public List<IMovimientoDto> reportMovement(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws Exception;
}
