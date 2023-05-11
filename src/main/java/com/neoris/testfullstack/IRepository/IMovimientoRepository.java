package com.neoris.testfullstack.IRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neoris.testfullstack.Dto.IMovimientoDto;
import com.neoris.testfullstack.Entity.Movimiento;

/**
 * This is the repository interface for Movimiento entity, extends JpaRepository.
 */

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, Long> {
	
	/**
	 * Recupera una lista de movimientos para una cuenta dada dentro de un rango de fechas especificado.
	 *
	 * @param cuentaId el ID de la cuenta para recuperar los movimientos
	 * @param fechaInicio la fecha de inicio del rango de fechas para recuperar los movimientos
	 * @param fechaFin la fecha de fin del rango de fechas para recuperar los movimientos
	 * @return una lista de objetos IMovimientoDto que representan los movimientos
	 */
	@Query(value = " SELECT "
					+ "		mov.id AS id,"
					+ "		mov.fecha AS fecha,"
					+ "		mov.tipo_movimiento AS tipoMovimiento,"
					+ "		per.nombre AS nombre ,"
					+ "		cue.numero_cuenta AS numeroCuenta,"
					+ "		cue.tipo_cuenta AS tipoCuenta,"
					+ "		cue.saldo_inicial AS saldoInicial,"
					+ "		cue.estado AS estado,"
					+ "		mov.valor AS valor,"
					+ "		mov.saldo AS saldo"
					+ " FROM "
					+ "		persona per "
					+ " 	INNER JOIN cliente cli ON per.id = cli.persona_id"
					+ " 	INNER JOIN cuenta cue ON cli.persona_id = cue.cliente_id"
					+ " 	INNER JOIN movimiento mov ON cue.id  = mov.cuenta_id"	
				 	+ " WHERE UPPER(CONCAT(cue.estado, per.nombre )) LIKE CONCAT('%', UPPER(''), '%') ", nativeQuery = true)
	Page<IMovimientoDto> getDatatable(Pageable pageable, @Param("search") String search);
	
	@Query(value = " SELECT sum(valor) as gastado FROM movimiento "
					+ " WHERE cuenta_id = :cuentaId ", nativeQuery = true)
	Double SaldoCuentaById(Long cuentaId);
	
	
	/**
	* Obtiene los movimientos de una cuenta en un rango de fechas determinado.
	* @param cuentaId el id de la cuenta de la que se quieren obtener los movimientos.
	* @param fechaInicio la fecha de inicio del rango.
	* @param fechaFin la fecha de fin del rango.
	* @return una lista de objetos IMovimientoDto que representan los movimientos de la cuenta.
	*/
	@Query(value = " SELECT  "
			+ "	mov.id AS id, "
			+ "	mov.fecha AS fecha, "
			+ "	mov.tipo_movimiento AS tipoMovimiento, "
			+ "	per.nombre AS nombre , "
			+ " cue.id AS cuentaId, "
			+ "	cue.numero_cuenta AS numeroCuenta, "
			+ "	cue.tipo_cuenta AS tipoCuenta, "
			+ "	cue.saldo_inicial AS saldoInicial, "
			+ "	cue.estado AS estado, "
			+ "	mov.valor AS valor, "
			+ "	mov.saldo AS saldo "
			+ "FROM  "
			+ "	persona per  "
			+ "	INNER JOIN cliente cli ON per.id = cli.persona_id "
			+ "	INNER JOIN cuenta cue ON cli.persona_id = cue.cliente_id "
			+ "	INNER JOIN movimiento mov ON cue.id  = mov.cuenta_id "
			+ "WHERE  "
			+ "	cue.id = :cuentaId AND "
			+ " mov.fecha BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
	List<IMovimientoDto> ReportMovement(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);

}