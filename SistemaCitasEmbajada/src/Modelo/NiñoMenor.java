package Modelo;

import java.time.LocalDate;

public class Ni�oMenor extends Usuario{
	

	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El acudiente que tiene el usuario
	 */
	private String acudiente;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea un objeto de tipo Ni�oMenor que a su vez es un usuario
	 * @param nombre
	 * @param numPasaporte
	 * @param email
	 * @param fechaNacimiento
	 * @param paisNacimiento
	 * @param ciudadNacimiento
	 * @param acudiente
	 */
	public Ni�oMenor(String nombre, String numPasaporte, String email, LocalDate fechaNacimiento, String paisNacimiento,
			String ciudadNacimiento, String acudiente) {
		
		super(nombre, numPasaporte, email, fechaNacimiento, paisNacimiento, ciudadNacimiento);
		this.acudiente = acudiente;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//M�TODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Calcula el valor de la visa para un ni�o menor de dos a�os
	 */
	public double calcularValorVisa(){
		Visa visaPedida = getSolicitud().getVisa();
		double valorVisa = 0;
		valorVisa = visaPedida.getValor() * 0.1;
		return valorVisa;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS - SETTERS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public String getAcudiente() {
		return acudiente;
	}
	public void setAcudiente(String acudiente) {
		this.acudiente = acudiente;
	}
}	
