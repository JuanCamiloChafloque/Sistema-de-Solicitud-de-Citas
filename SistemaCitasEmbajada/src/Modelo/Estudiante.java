package Modelo;

public class Estudiante extends Visa{
	
	
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Tipo de educaci�n que actualmente tiene el usuario que esta solicitando la visa de estudiante
	 */
	private Escolaridad escolaridad;
	/**
	 * Instituci�n a la que pertenecera el usuario que esta solicitando la visa de estudiante
	 */
	private String institucion;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea una visa de tipo Estudiante para relacionarla a una solicitud determinada
	 * @param valor
	 * @param escolaridad
	 * @param institucion
	 */
	public Estudiante(double valor, Escolaridad escolaridad, String institucion) {
		super(valor);
		this.escolaridad = escolaridad;
		this.institucion = institucion;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//M�TODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------


	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS - SETTERS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public Escolaridad getEscolaridad() {
		return escolaridad;
	}
	public void setEscolaridad(Escolaridad escolaridad) {
		this.escolaridad = escolaridad;
	}
	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

}
