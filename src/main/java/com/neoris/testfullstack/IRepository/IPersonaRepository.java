package com.neoris.testfullstack.IRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neoris.testfullstack.Dto.IBasicDto;
import com.neoris.testfullstack.Entity.Persona;

/**
 * This is the repository interface for Persona entity, extends JpaRepository.
 */

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long> {
	
	/**
	 * Repositorio para la entidad Persona.
	 * 
	 * Proporciona un método para obtener una lista paginada de objetos IBasicDto con la siguiente información:
	 * fecha, tipo_persona.
	 *
	 * @param pageable un objeto Pageable que representa la información de paginación.
	 * @param search una cadena de texto para buscar registros que contengan el valor proporcionado (ignora mayúsculas y minúsculas).
	 * @return un objeto Page de IBasicDto.
	 */
	@Query(value = " SELECT * FROM persona "
				 + " WHERE UPPER(CONCAT(fecha, tipo_persona )) LIKE CONCAT('%', UPPER(:search), '%') ", nativeQuery = true)
	Page<IBasicDto> getDatatable(Pageable pageable, @Param("search") String search);

}