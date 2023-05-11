package com.neoris.testfullstack.Dto;

/**
 * Represents a DTO (Data Transfer Object) interface for a Cuenta, extending the IBasicDto interface.
 */
public interface ICuentaDto extends IBasicDto {
	/**
     * Obtiene el número de cuenta.
     *
     * @return el número de cuenta
     */
    Long getNumeroCuenta();

    /**
     * Indica si la cuenta es de tipo corriente o de ahorro.
     *
     * @return true si la cuenta es de tipo corriente, false si es de ahorro
     */
    Boolean getTipoCuenta();

    /**
     * Obtiene el saldo inicial de la cuenta.
     *
     * @return el saldo inicial de la cuenta
     */
    Double getSaldoInicial();

    /**
     * Obtiene el id del cliente al que pertenece la cuenta.
     *
     * @return el id del cliente al que pertenece la cuenta
     */
    Long getClienteId();
}
