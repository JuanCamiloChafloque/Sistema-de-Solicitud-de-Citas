package Modelo;

import java.time.LocalDate;

public class Ni�oMayor extends Usuario{
	

	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Tipo de eduaci�n que tiene el usuario de tipo Ni�oMayor
	 */
	private String escolaridad;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea un objeto de tipo Ni�oMayor que a su vez es un usuario
	 * @param nombre
	 * @param numPasaporte
	 * @param email
	 * @param fechaNacimiento
	 * @param paisNacimiento
	 * @param ciudadNacimiento
	 * @param escolaridad
	 */
	public Ni�oMayor(String nombre, String numPasaporte, String email, LocalDate fechaNacimiento, String paisNacimiento,
			String ciudadNacimiento, String escolaridad) {

		super(nombre, numPasaporte, email, fechaNacimiento, paisNacimiento, ciudadNacimiento);
		this.escolaridad = escolaridad;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//M�TODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Calcula el valor de la visa para un ni�o menor a doce a�os y mayor a 2 a�os
	 */
	public double calcularValorVisa(){
		Visa visaPedida = getSolicitud().getVisa();
		
		int diferenciaEdad = 18 - this.edad;
		double valorVisa = 0;
		
		if(visaPedida instanceof Turismo){
			valorVisa = 1.2 * visaPedida.getValor();
		}
		if(visaPedida instanceof Estudiante){
			valorVisa = 0.7 * visaPedida.getValor();
		}
		for(int i=0; i<diferenciaEdad; i++){
			valorVisa = 0.95 * valorVisa;
		}
		return valorVisa;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS - SETTERS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public String getEscolaridad() {
		return escolaridad;
	}
	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}
}
