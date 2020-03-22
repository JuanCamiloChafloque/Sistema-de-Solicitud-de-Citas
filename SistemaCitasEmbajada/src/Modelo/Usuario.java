package Modelo;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class Usuario implements Serializable, Comparable<Usuario>{

	
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Nombre del solicitante
	 */
	protected String nombre;
	/**
	 * Número de pasaporte del solicitante
	 */
	protected String numPasaporte;
	/**
	 * E-Mail del solicitante
	 */
	protected String email;
	/**
	 * Edad del solicitante
	 */
	protected int edad;
	/**
	 * Fecha de nacimiento del solicitante
	 */
	protected LocalDate fechaNacimiento;
	/**
	 * País donde nació el solicitante
	 */
	protected String paisNacimiento; 
	/**
	 * Ciudad donde nació el solicitante
	 */
	protected String ciudadNacimiento;
	/**
	 * Género del Usuario 0: Transexual, 1: Hombre, 2: Mujer, 3: Transgénero
	 */
	protected int genero;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Cada usuario tiene una solicitud pendiente que le es asignada por el sistema
	 */
	protected Solicitud solicitud;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea una nueva instancia de un objeto de tipo Usuario
	 * @param nombre
	 * @param numPasaporte
	 * @param email
	 * @param fechaNacimiento
	 * @param paisNacimiento
	 * @param ciudadNacimiento
	 */
	public Usuario(String nombre, String numPasaporte, String email, LocalDate fechaNacimiento, String paisNacimiento, String ciudadNacimiento){
		this.nombre = nombre;
		this.numPasaporte = numPasaporte;
		this.email = email;
		this.edad = LocalDate.now().compareTo(fechaNacimiento);
		this.fechaNacimiento = fechaNacimiento;
		this.paisNacimiento = paisNacimiento;
		this.ciudadNacimiento = ciudadNacimiento;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Asigna una solicitud determinada al usuario
	 * @param sol
	 */
	public void asignarSolicitud(Solicitud sol){
		this.setSolicitud(sol);
	}
	/**
	 * Método abstracto que calcula el valor de la solicitud de cada usuario dependiendo de su edad y del tipo de visa que solicitó
	 * @return
	 */
	public abstract double calcularValorVisa();
	
	/**
	 * Compara el nombre del usuario actual con otro usuario
	 * @param otroUsuario
	 * @return
	 */
	public int compareTo(Usuario otroUsuario){
		String nombreActual = this.getNombre();
		String nombreOtro = otroUsuario.getNombre();
		
		return nombreActual.compareTo(nombreOtro);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS - SETTERS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumPasaporte() {
		return numPasaporte;
	}
	public void setNumPasaporte(String numPasaporte) {
		this.numPasaporte = numPasaporte;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getPaisNacimiento() {
		return paisNacimiento;
	}
	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}
	public String getCiudadNacimiento() {
		return ciudadNacimiento;
	}
	public void setCiudadNacimiento(String ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
}
