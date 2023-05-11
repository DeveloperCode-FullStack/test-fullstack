package com.neoris.testfullstack.Dto;

/**
 * Represents a DTO (Data Transfer Object) interface for a Cliente, extending the IBasicDto interface.
 */
public interface IClienteDto extends IBasicDto{
	
	/**
	 * Returns the identification of the client.
	 * 
	 * @return the identification of the client
	 */
	String getIdentificacion();	
	
	/**
	 * Returns the gender of the client.
	 * 
	 * @return the gender of the client
	 */
	Boolean getGenero();
	
	/**
	 * Returns the age of the client.
	 * 
	 * @return the age of the client
	 */
	Byte getEdad();
	
	/**
	 * Returns the address of the client.
	 * 
	 * @return the address of the client
	 */
	String getDireccion();
	
	/**
	 * Returns the phone number of the client.
	 * 
	 * @return the phone number of the client
	 */
    String getTelefono();		
}
