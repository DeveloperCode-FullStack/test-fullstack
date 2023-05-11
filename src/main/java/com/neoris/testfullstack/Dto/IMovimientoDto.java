package com.neoris.testfullstack.Dto;

import java.time.LocalDateTime;

/**
 * Represents a DTO (Data Transfer Object) interface for a Movimiento, extending the IBasicDto interface.
 */
public interface IMovimientoDto extends IBasicDto {	
	 /**
     * Obtiene el identificador de la persona asociada al movimiento.
     *
     * @return el identificador de la persona.
     */
    Long getPersonaId();
	   
    /**
     * Obtiene el número de cuenta asociado al movimiento.
     *
     * @return el número de cuenta.
     */
    Long getNumeroCuenta();

    /**
     * Obtiene el tipo de cuenta asociado al movimiento.
     *
     * @return el tipo de cuenta.
     */
    Boolean getTipoCuenta();

    /**
     * Obtiene el saldo inicial de la cuenta antes de realizar el movimiento.
     *
     * @return el saldo inicial de la cuenta.
     */
    Double getSaldoInicial();

    /**
     * Obtiene la fecha y hora en que se realizó el movimiento.
     *
     * @return la fecha y hora del movimiento.
     */
    LocalDateTime getFecha();

    /**
     * Obtiene el tipo de movimiento realizado en la cuenta (ingreso o retiro).
     *
     * @return el tipo de movimiento.
     */
    Boolean getTipoMovimiento();

    /**
     * Obtiene el valor del movimiento realizado en la cuenta (monto del ingreso o retiro).
     *
     * @return el valor del movimiento.
     */
    Double getValor();

    /**
     * Obtiene el saldo de la cuenta después de realizar el movimiento.
     *
     * @return el saldo de la cuenta.
     */
    Double getSaldo();
}
