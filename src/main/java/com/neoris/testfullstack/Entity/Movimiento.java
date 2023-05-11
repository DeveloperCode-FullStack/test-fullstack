package com.neoris.testfullstack.Entity;

import java.time.LocalDateTime;

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
@Table(name="movimiento")
public class Movimiento{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del movimiento", example = "1")
    private Long id;
    
    @Schema(description  = "Fecha del movimiento", example = "10/2/2022")
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    
    @Schema(description  = "Tipo de movimiento (true: deposito, false: retiro)", example = "false")
    @Column(name = "tipo_movimiento", nullable = false)
    private Boolean tipoMovimiento;

    @Schema(description  = "Valor del movimiento", example = "575")
    @Column(name = "valor", nullable = false)
    private Double valor;   
    
    @Schema(description  = "Saldo del movimiento, valor calculado", example = "")
    @Column(name = "saldo", nullable = false)
    private Double saldo;  
        
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuentaId;

    /**
     * Obtiene el identificador del objeto Movimiento.
     * @return El identificador del objeto Movimiento.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del objeto Movimiento.
     * @param id El identificador del objeto Movimiento.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha del movimiento.
     * @return La fecha del movimiento.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del movimiento.
     * @param fecha La fecha del movimiento.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el tipo de movimiento.
     * @return El tipo de movimiento (true si es un ingreso, false si es un retiro).
     */
    public Boolean getTipoMovimiento() {
        return tipoMovimiento;
    }

    /**
     * Establece el tipo de movimiento.
     * @param tipoMovimiento El tipo de movimiento (true si es un ingreso, false si es un retiro).
     */
    public void setTipoMovimiento(Boolean tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    /**
     * Obtiene el valor del movimiento.
     * @return El valor del movimiento.
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Establece el valor del movimiento.
     * @param valor El valor del movimiento.
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * Obtiene el saldo después del movimiento.
     * @return El saldo después del movimiento.
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo después del movimiento.
     * @param saldo El saldo después del movimiento.
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtiene la cuenta a la que pertenece el movimiento.
     * @return La cuenta a la que pertenece el movimiento.
     */
    public Cuenta getCuentaId() {
        return cuentaId;
    }

    /**
     * Establece la cuenta a la que pertenece el movimiento.
     * @param cuentaId La cuenta a la que pertenece el movimiento.
     */
    public void setCuentaId(Cuenta cuentaId) {
        this.cuentaId = cuentaId;
    }
}
