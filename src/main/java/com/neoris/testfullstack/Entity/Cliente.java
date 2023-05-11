package com.neoris.testfullstack.Entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="cliente")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Cliente extends Persona{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @Schema(description = "Contraseña cliente", example = "")
    @Column(name = "contrasenia", nullable = false, length = 100)
    private String contrasenia;

    @Schema(description = "Estado del cliente", example = "true")
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    /**
     * Retorna el identificador único del objeto Cliente.
     *
     * @return el identificador único del objeto Cliente
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del objeto Cliente.
     *
     * @param id el identificador único del objeto Cliente a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna la contraseña del objeto Cliente.
     *
     * @return la contraseña del objeto Cliente
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del objeto Cliente.
     *
     * @param contrasenia la contraseña del objeto Cliente a establecer
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Retorna el estado del objeto Cliente.
     *
     * @return el estado del objeto Cliente
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * Establece el estado del objeto Cliente.
     *
     * @param estado el estado del objeto Cliente a establecer
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    } 
}