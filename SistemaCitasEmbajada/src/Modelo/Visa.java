package Modelo;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public abstract class Visa implements Serializable{

	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Tarifa o valor de la visa
	 */
	protected double valor;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Cada objeto de tipo Visa tiene una lista de requisitos 
	 */
	protected List<Requisito> requisitos;
	/**
	 * Cada objeto de tipo Visa tiene una lista de solicitudes
	 */
	protected List<Solicitud> solicitudesVisa;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea una nueva instancia de un objeto de tipo Visa
	 * @param tipo
	 * @param valor
	 */
	public Visa(double valor){
		this.valor = valor;
		requisitos = new ArrayList<Requisito>();
		solicitudesVisa = new ArrayList<Solicitud>();
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Agrega un objeto de tipo Solicitud a la visa
	 * @param solicitud
	 */
	public void agregarSolicitud(Solicitud solicitud){
		solicitudesVisa.add(solicitud);
	}
	/**
	 * Agrega un objeto de tipo Requisito a la visa
	 */
	public void agregarRequisito(List<String> requisitosVisa){
		for(String req: requisitosVisa){
			Requisito nuevo = new Requisito(req);
			requisitos.add(nuevo);
		}
	}
	/**
	 * Método que retorna la lista de requisitos de cada Visa que se encuentran registradas en el sistema de citas .
	 * @return
	 * Lista de Requisitos por Visa
	 */
	public List<Requisito> darRequisitos(){
		return requisitos;
	}
	/**
	 * Método que retorna la lista de solicitudes de cada Visa que se encuentran registradas en el sistema de citas.
	 * @return
	 */
	public List<Solicitud> darSolicitudes(){
		return solicitudesVisa;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS - SETTERS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public List<Requisito> getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(List<Requisito> requisitos) {
		this.requisitos = requisitos;
	}
	public List<Solicitud> getSolicitudes() {
		return solicitudesVisa;
	}
	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudesVisa = solicitudes;
	}
}