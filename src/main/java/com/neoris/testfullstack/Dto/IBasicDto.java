package com.neoris.testfullstack.Dto;

/**
 * Interfaz utilizada para representar un DTO básico que contiene información esencial de una entidad.
 * Incluye el identificador, el nombre y el estado  como datos generales
 */
public interface IBasicDto {
    
    /**
     * Obtiene el identificador de la entidad.
     *
     * @return el identificador de la entidad
     */
    Long getId();
    
    /**
     * Obtiene el nombre cliente.
     *
     * @return el nombre cliente
     */
    String getNombre();
    
    /**
     * Obtiene el estado del registro.
     *
     * @return el estado del registro
     */
    Boolean getEstado();
}
