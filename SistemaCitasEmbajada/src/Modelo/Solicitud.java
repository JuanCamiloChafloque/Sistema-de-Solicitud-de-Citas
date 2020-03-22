package Modelo;
import java.io.Serializable;


import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Solicitud implements Serializable{

	
	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Código que identifica a la solicitud
	 */
	private int codigo;
	/**
	 * Número consecutivo que se le asigna a cada solicitud que se instancia
	 */
	private static int CONSECUTIVO = 0;
	/**
	 * Estado que tiene la solicitud. Por defecto esta solicitud siempre empieza en pendiente
	 */
	private String estado;
	/**
	 * Fecha en la cual el/los solicitantes deben presentarse en la embajada para solicitar su visa
	 */
	private LocalDateTime fecha;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Cada solicitud tiene una lista de solicitantes. 
	 */
	private List<Usuario> solicitantes;
	/**
	 * Cada solicitud tiene asociada un tipo de visa
	 */
	private Visa visa;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea una instancia de un objeto de tipo Solicitud
	 * @param fecha
	 */
	public Solicitud(LocalDateTime fecha, Visa pVisa){
		this.estado = "Pendiente";
		this.fecha = fecha;
		solicitantes = new ArrayList<Usuario>();
		visa = pVisa;
		this.codigo = ++CONSECUTIVO;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Agrega a un usuario a una solicitud
	 */
	public void agregarUsuarioSolicitud(Usuario usuario){
		solicitantes.add(usuario);
	}
	/**
	 * Método que retorna la lista de los solicitantes de una solicitud en específico
	 * @return
	 * Lista de Solicitantes para un Objeto Solicitud
	 */
	public List<Usuario> darSolicitantes(){
		return solicitantes;
	}
	
	/**
	 * Método que calcula el total a pagar para una determinada solicitud
	 * @return
	 */
	public double calcularValorVisa(){
		
		double valorVisa = 0;
		for(Usuario usuarioActual: solicitantes){
			valorVisa += usuarioActual.calcularValorVisa();
		}
		return valorVisa;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS - SETTERS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public static int getCONSECUTIVO() {
		return CONSECUTIVO;
	}
	public static void setCONSECUTIVO(int cONSECUTIVO) {
		CONSECUTIVO = cONSECUTIVO;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public List<Usuario> getSolicitantes() {
		return solicitantes;
	}
	public void setSolicitantes(List<Usuario> solicitantes) {
		this.solicitantes = solicitantes;
	}
	public Visa getVisa() {
		return visa;
	}
	public void setVisa(Visa visa) {
		this.visa = visa;
	}
}
