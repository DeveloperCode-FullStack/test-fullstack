package com.neoris.testfullstack.Entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID de la persona", example = "1")
	private Long id;
	
	@Schema(description  = "Número de identificación persona", example = "123456789")
	@Column(name = "identificacion", nullable = false, length = 12)
	private String identificacion;
	
	@Schema(description  = "Nombre de la persona", example = "Pepito Perez")
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Schema(description  = "Género de la persona (true: hombre, false: mujer)", example = "true")
    @Column(name = "genero", nullable = false)
	private Boolean genero;    

	@Schema(description  = "Edad de la persona", example = "20")
    @Column(name = "edad", nullable = false)
	private Byte edad;
    
	@Schema(description = "Dirección de la persona", example = "Calle x # 10-5")
    @Column(name = "direccion", nullable = false, length = 50)
	private String direccion;
    
	@Schema(description = "Teléfono de la persona", example = "3121112222")
    @Column(name = "telefono", nullable = false, length = 18)
	private String telefono;

	/**
	 * Obtiene el id del cliente.
	 *
	 * @return el id del cliente.
	 */
	public Long getId() {
	    return id;
	}

	/**
	 * Establece el id del cliente.
	 *
	 * @param id el id del cliente.
	 */
	public void setId(Long id) {
	    this.id = id;
	}

	/**
	 * Obtiene la identificación del cliente.
	 *
	 * @return la identificación del cliente.
	 */
	public String getIdentificacion() {
	    return identificacion;
	}

	/**
	 * Establece la identificación del cliente.
	 *
	 * @param identificacion la identificación del cliente.
	 */
	public void setIdentificacion(String identificacion) {
	    this.identificacion = identificacion;
	}

	/**
	 * Obtiene el nombre del cliente.
	 *
	 * @return el nombre del cliente.
	 */
	public String getNombre() {
	    return nombre;
	}

	/**
	 * Establece el nombre del cliente.
	 *
	 * @param nombre el nombre del cliente.
	 */
	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}

	/**
	 * Obtiene el género del cliente.
	 *
	 * @return el género del cliente.
	 */
	public Boolean getGenero() {
	    return genero;
	}

	/**
	 * Establece el género del cliente.
	 *
	 * @param genero el género del cliente.
	 */
	public void setGenero(Boolean genero) {
	    this.genero = genero;
	}

	/**
	 * Obtiene la edad del cliente.
	 *
	 * @return la edad del cliente.
	 */
	public Byte getEdad() {
	    return edad;
	}

	/**
	 * Establece la edad del cliente.
	 *
	 * @param edad la edad del cliente.
	 */
	public void setEdad(Byte edad) {
	    this.edad = edad;
	}

	/**
	 * Obtiene la dirección del cliente.
	 *
	 * @return la dirección del cliente.
	 */
	public String getDireccion() {
	    return direccion;
	}

	/**
	 * Establece la dirección del cliente.
	 *
	 * @param direccion la dirección del cliente.
	 */
	public void setDireccion(String direccion) {
	    this.direccion = direccion;
	}

	/**
	 * Obtiene el teléfono del cliente.
	 *
	 * @return el teléfono del cliente.
	 */
	public String getTelefono() {
	    return telefono;
	}

	/**
	 * Establece el teléfono del cliente.
	 *
	 * @param telefono el teléfono del cliente.
	 */
	public void setTelefono(String telefono) {
	    this.telefono = telefono;
	}

}