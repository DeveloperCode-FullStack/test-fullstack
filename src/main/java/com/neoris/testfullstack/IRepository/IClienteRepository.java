package com.neoris.testfullstack.IRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neoris.testfullstack.Dto.IClienteDto;
import com.neoris.testfullstack.Entity.Cliente;

/**
 * This is the repository interface for Cliente entity, extends JpaRepository.
 */

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

	/**
     * Obtiene una lista paginada de objetos IClienteDto con los siguientes campos:
     * id, identificacion, nombre, genero, edad, direccion, telefono, estado.
     *
     * @param pageable un objeto Pageable que representa la información de paginación.
     * @return una página de objetos IClienteDto.
     */
    @Query(value = " SELECT "
            + "    cli.persona_id AS id, "
            + "    per.identificacion AS identificacion, "
            + "    per.nombre AS nombre, "
            + "    per.genero AS genero, "
            + "    per.edad AS edad, "
            + "    per.direccion AS direccion, "
            + "    per.telefono AS telefono, "
            + "    cli.estado "
            + "FROM cliente AS cli 	"
            + "INNER JOIN persona AS per ON cli.persona_id = per.id ", nativeQuery = true)
    Page<IClienteDto> getDatatable(Pageable pageable);
}
