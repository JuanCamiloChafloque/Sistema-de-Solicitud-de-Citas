package Modelo;

import java.time.LocalDate;

public class NiñoMayor extends Usuario{
	

	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Tipo de eduación que tiene el usuario de tipo NiñoMayor
	 */
	private String escolaridad;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea un objeto de tipo NiñoMayor que a su vez es un usuario
	 * @param nombre
	 * @param numPasaporte
	 * @param email
	 * @param fechaNacimiento
	 * @param paisNacimiento
	 * @param ciudadNacimiento
	 * @param escolaridad
	 */
	public NiñoMayor(String nombre, String numPasaporte, String email, LocalDate fechaNacimiento, String paisNacimiento,
			String ciudadNacimiento, String escolaridad) {

		super(nombre, numPasaporte, email, fechaNacimiento, paisNacimiento, ciudadNacimiento);
		this.escolaridad = escolaridad;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Calcula el valor de la visa para un niño menor a doce años y mayor a 2 años
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
