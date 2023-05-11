package com.neoris.testfullstack.IRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neoris.testfullstack.Dto.ICuentaDto;
import com.neoris.testfullstack.Entity.Cuenta;

/**
 * This is the repository interface for Cuenta entity, extends JpaRepository.
 */

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {
	
	/**
	 * Repositorio para la entidad Cuenta que provee un método para obtener una lista paginada de objetos
	 * IClienteDto con los siguientes campos: id, estado, numeroCuenta, saldoInicial, tipoCuenta, clienteId y nombre.
	 * 
	 * @param pageable objeto Pageable que representa la información de paginación.
	 * @return una página de objetos IClienteDto.
	 */
	@Query(value = " SELECT "
				+ " cue.id AS id,"
				+ " cue.estado AS estado,"
				+ " cue.numero_cuenta AS numeroCuenta,"
				+ " cue. saldo_inicial AS saldoInicial,"
				+ " cue.tipo_cuenta AS tipoCuenta,"
				+ " cue.cliente_id AS clienteId, "
				+ " per.nombre AS nombre"
				+ " FROM cuenta cue "
				+ " INNER JOIN cliente cli ON cue.cliente_id = cli.persona_id "
				+ " INNER JOIN persona per ON cli.persona_id = per.id", nativeQuery = true)
	Page<ICuentaDto> getDatatable(Pageable pageable);

}