package Modelo;

import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Adulto extends Usuario{


	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea un objeto de tipo Adulto que a su vez es un usuario
	 * @param nombre
	 * @param numPasaporte
	 * @param email
	 * @param fechaNacimiento
	 * @param paisNacimiento
	 * @param ciudadNacimiento
	 */
	public Adulto(String nombre, String numPasaporte, String email, LocalDate fechaNacimiento, String paisNacimiento,
			String ciudadNacimiento) {
		
		super(nombre, numPasaporte, email, fechaNacimiento, paisNacimiento, ciudadNacimiento);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Calcula el valor de la visa para un adulto menor a 65 años
	 */
	public double calcularValorVisa(){
		
		JOptionPane.showMessageDialog(null, "ENTRO AL MÉTODO CALCULAR");
				
		return solicitud.getVisa().getValor();
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS - SETTERS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
}
