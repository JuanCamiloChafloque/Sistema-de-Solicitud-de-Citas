package Modelo;

import java.time.LocalDate;

public class AdultoMayor extends Usuario{


	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea un objeto de tipo AdultoMayor que a su vez es un usuario
	 * @param nombre
	 * @param numPasaporte
	 * @param email
	 * @param fechaNacimiento
	 * @param paisNacimiento
	 * @param ciudadNacimiento
	 */
	public AdultoMayor(String nombre, String numPasaporte, String email, LocalDate fechaNacimiento, String paisNacimiento,
			String ciudadNacimiento) {
		
		super(nombre, numPasaporte, email, fechaNacimiento, paisNacimiento, ciudadNacimiento);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Calcula el valor de la visa para un adulto mayor a 65 años
	 */
	public double calcularValorVisa(){
		Visa visaPedida = getSolicitud().getVisa();		
		double valorVisa = 0;
		if(visaPedida instanceof Turismo){
			valorVisa = 1.1*visaPedida.getValor();
		}
		valorVisa = valorVisa * 0.75;
		return valorVisa;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS - SETTERS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
}
