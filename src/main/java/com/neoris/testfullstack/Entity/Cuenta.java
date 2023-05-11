package com.neoris.testfullstack.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="cuenta")
public class Cuenta{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Schema(description  = "Número de cuenta ", example = "478758")
	@Column(name = "numero_cuenta", nullable = false, unique = true)
	private Long numeroCuenta;

	@Schema(description  = "Tipo de cuenta (true: corriente, false: ahorro)", example = "true")
    @Column(name = "tipo_cuenta", nullable = false)
	private Boolean tipoCuenta;
    
	@Schema(description  = "Saldo inicial", example = "2000")
    @Column(name = "saldo_inicial", nullable = false)
	private Double saldoInicial;
    
	@Schema(description  = "Estado cuenta", example = "true")
    @Column(name = "estado", nullable = false)
	private Boolean estado;	
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente clienteId;

    /**
     * Devuelve el identificador de la cuenta.
     * 
     * @return el identificador de la cuenta.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador de la cuenta.
     * 
     * @param id el identificador de la cuenta a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el número de cuenta.
     * 
     * @return el número de cuenta.
     */
    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Establece el número de cuenta.
     * 
     * @param numeroCuenta el número de cuenta a establecer.
     */
    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Devuelve el tipo de cuenta.
     * 
     * @return el tipo de cuenta.
     */
    public Boolean getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Establece el tipo de cuenta.
     * 
     * @param tipoCuenta el tipo de cuenta a establecer.
     */
    public void setTipoCuenta(Boolean tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Devuelve el saldo inicial de la cuenta.
     * 
     * @return el saldo inicial de la cuenta.
     */
    public Double getSaldoInicial() {
        return saldoInicial;
    }

    /**
     * Establece el saldo inicial de la cuenta.
     * 
     * @param saldoInicial el saldo inicial de la cuenta a establecer.
     */
    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    /**
     * Devuelve el estado de la cuenta.
     * 
     * @return el estado de la cuenta.
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la cuenta.
     * 
     * @param estado el estado de la cuenta a establecer.
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    /**
     * Devuelve el cliente asociado a la cuenta.
     * 
     * @return el cliente asociado a la cuenta.
     */
    public Cliente getClienteId() {
        return clienteId;
    }

    /**
     * Establece el cliente asociado a la cuenta.
     * 
     * @param clienteId el cliente asociado a la cuenta a establecer.
     */
    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }		
}
