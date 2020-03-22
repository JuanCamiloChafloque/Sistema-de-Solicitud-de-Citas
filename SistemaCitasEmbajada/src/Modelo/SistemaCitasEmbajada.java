package Modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import Modelo.Usuario;
import Modelo.Visa;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
/**
 * Implementa a la interfaz ISistemaCitasEmbajada 
 */
public class SistemaCitasEmbajada implements Serializable, ISistemaCitasEmbajada{

	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * ID del país
	 */
	private int id;
	/**
	 * Es el país que se le asigna a la embajada
	 */
	private PaisEmbajada paisEmbajada; 
	/**
	 * Moneda local que depende del país de asignación
	 */
	private String moneda;
	/**
	 * El tipo de cambio que tiene la moneda del país
	 */
	private double cambio;
	/**
	 * El impuesto que tiene el país
	 */
	private float impuesto;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El sistema de embajadas tiene una lista de visas. Cada objeto de visa tendrá una lista de requisitos
	 */
	private List<Visa> visas;
	/**
	 * El sistema de embajadas tiene una lista de solicitudes. Se encuentran todas las solicitudes de todos los tipos de visa que se tienen en el
	 * sistema
	 */
	private List<Solicitud> solicitudesGenerales;
	/**
	 * El sistema de embajadas tiene una lista de solicitantes.
	 */
	private Map<String, Usuario> usuarios;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea una instancia del modelo general del sistema
	 */
	public SistemaCitasEmbajada(){
		visas = new ArrayList<Visa>();
		solicitudesGenerales = new ArrayList<Solicitud>();
		usuarios = new HashMap<String, Usuario>();
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Asigna al objeto de tipo SistemaCitasEmbajada un país con sus respectivas monedas, impuestos y tipos de cambio
	 */
	public boolean asignarPais(List<String> paisAsignado){
		
		for(PaisEmbajada pais: PaisEmbajada.values()){
			if(pais.toString().equalsIgnoreCase(paisAsignado.get(1))){
				setId(Integer.parseInt(paisAsignado.get(0)));
				setPaisEmbajada(pais);
				setMoneda(paisAsignado.get(2));
				setImpuesto(Float.parseFloat(paisAsignado.get(3)));
				setCambio(Double.parseDouble(paisAsignado.get(4)));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca a un determinado usuario por su número de pasaporte. 
	 * @param pNumPasaporte
	 * @return
	 * Si encuentra a un usuario con ese número retorna al objeto. Si no retorna NULL.
	 */
	public Usuario buscarUsuario(String pNumPasaporte){
		Usuario encontrado = null;
		for(String key: usuarios.keySet()){
			if(pNumPasaporte.equalsIgnoreCase(key)){
				encontrado = usuarios.get(key);
				return encontrado;
			}
		}
		return encontrado;
	}
	
	public Usuario buscarUsuarioSolicitud(String numPasaporte){
		
		Usuario encontrado = null;
		int cont = 0;
		
		for(Solicitud solicitud: solicitudesGenerales){
			for(Usuario usuario: solicitud.getSolicitantes()){
				if(usuario.getNumPasaporte().equalsIgnoreCase(numPasaporte)){
					cont++;
					return encontrado;

				}
			}
		}

		if(cont == 0){
			for(Usuario usuario: usuarios.values()){
				if(usuario.getNumPasaporte().equalsIgnoreCase(numPasaporte)){
					encontrado = usuario;
					return encontrado;
				}
			}
		}
		return encontrado;
	}
	
	/**
	 * Crea una visa de tipo Turismo con su respectivo valor y los dias de estadia del solicitante. La visa creada se añade a la lista de visas que tiene
	 * el sistema
	 * @param diasEstadia
	 * @param valor
	 * @return
	 */
	public Visa crearVisaTurismo(int diasEstadia, double valor, List<String> requisitos){
		Visa nuevaVisa = new Turismo(valor, diasEstadia);
		visas.add(nuevaVisa);
		agregarRequisitosVisa(requisitos, nuevaVisa);
		return nuevaVisa;
	}
	
	/**
	 * Crea una visa de tipo Trabajo con su respectivo valor y la empresa y cargo del solicitante. La visa creada se añade a la lista de visas que tiene
	 * el sistema
	 * @param empresa
	 * @param cargo
	 * @param valor
	 * @return
	 */
	public Visa crearVisaTrabajo(String empresa, String cargo, double valor, List<String> requisitos){
		Visa nuevaVisa = new Trabajo(valor, empresa, cargo);
		visas.add(nuevaVisa);
		agregarRequisitosVisa(requisitos, nuevaVisa);
		return nuevaVisa;
	}
	
	/**
	 * Crea una visa de tipo Estudiante con su respectivo valor y la escolaridad e institución del solicitante. La visa creada se añade a la lista de visas
	 * que tiene el sistema
	 * @param escolaridad
	 * @param istitucion
	 * @param valor
	 * @return
	 */
	public Visa crearVisaEstudiante(Escolaridad escolaridad, String institucion, double valor, List<String> requisitos){
		Visa nuevaVisa = new Estudiante(valor, escolaridad, institucion);
		visas.add(nuevaVisa);
		agregarRequisitosVisa(requisitos, nuevaVisa);
		return nuevaVisa;
	}
	
	/**
	 * Dada una lista de requisitos los añade a la visa actual que se esta pidiendo en la solicitud y convierte los String de la lista en objetos
	 * de tipo Requisito
	 */
	public void agregarRequisitosVisa(List<String> requisitos, Visa visaActual){
			visaActual.agregarRequisito(requisitos);
	}
	
	/**
	 * Agrega a un solicitante nuevo a la lista de usuarios del sistema de la embajada
	 */
	public void agregarUsuarios(String nombre, String numPass, String email, LocalDate fechaNacimiento, String paisOrigen, String ciudadNacimiento, String infoAdicional){
		if(LocalDate.now().compareTo(fechaNacimiento) <= 2){
			Usuario nuevo = new NiñoMenor(nombre, numPass, email, fechaNacimiento, paisOrigen, ciudadNacimiento, infoAdicional);
			usuarios.put(numPass, nuevo);
		}
		if((LocalDate.now().compareTo(fechaNacimiento) <= 12) && (LocalDate.now().compareTo(fechaNacimiento) > 2)){
			Usuario nuevo = new NiñoMayor(nombre, numPass, email, fechaNacimiento, paisOrigen, ciudadNacimiento, infoAdicional);
			usuarios.put(numPass, nuevo);
		}
		if((LocalDate.now().compareTo(fechaNacimiento) <= 65) && (LocalDate.now().compareTo(fechaNacimiento) > 12)){
			Usuario nuevo = new Adulto(nombre, numPass, email, fechaNacimiento, paisOrigen, ciudadNacimiento);
			usuarios.put(numPass, nuevo);
		}
		if(LocalDate.now().compareTo(fechaNacimiento) > 65){
			Usuario nuevo = new AdultoMayor(nombre, numPass, email, fechaNacimiento, paisOrigen, ciudadNacimiento);
			usuarios.put(numPass, nuevo);
		}
		
	}
	
	/**
	 * Agrega una solicitud al sistema y la guarda en una lista
	 */
	public Solicitud solicitarSolicitud(Usuario usuario, Visa visa){
		Solicitud sol = null;
		if(solicitudesGenerales.isEmpty()){
			LocalDateTime fecha = LocalDateTime.of(2018, 5, 28, 8, 0);
			sol = new Solicitud(fecha, visa);
			usuario.asignarSolicitud(sol);
			visa.agregarSolicitud(sol);
			sol.agregarUsuarioSolicitud(usuario);
			solicitudesGenerales.add(sol);
		}else{
			Solicitud actual = solicitudesGenerales.get(solicitudesGenerales.size() - 1);
			LocalDateTime fechaUltima = actual.getFecha();
			LocalDateTime fechaAsignada = asignarFechaHora(fechaUltima);
			sol = new Solicitud(fechaAsignada, visa);
			usuario.asignarSolicitud(sol);
			visa.agregarSolicitud(sol);
			sol.agregarUsuarioSolicitud(usuario);
			solicitudesGenerales.add(sol);
		}
		return sol;
	}
	
	/**
	 * Dada la última fecha de las solicitudes se le asigna una nueva fecha y hora al solicitante nuevo. Si el último horario tiene la cita a las 3pm
	 * se deberá asignar la siguiente cita al día siguiente a las 8am. Si el día de la semana es viernes y la última cita es a las 3pm se deberá 
	 * asignar la siguiente cita hasta el lunes a las 8am
	 * @param fechaUltima
	 * @return
	 */
	public LocalDateTime asignarFechaHora(LocalDateTime fechaUltima){
		LocalDateTime fechaAsignada = null;
		if(fechaUltima.getHour() == 3){
			if(fechaUltima.getDayOfWeek() == DayOfWeek.FRIDAY){
				fechaAsignada = fechaUltima.plusDays(2);
				fechaAsignada = fechaUltima.plusHours(17);
			}else{
				fechaAsignada = fechaUltima.plusDays(1);
				fechaAsignada = fechaUltima.plusHours(17);
			}
		}else{
			fechaAsignada = fechaUltima.plusHours(1);
		}
		return fechaAsignada;
	}
	/**
	 * Busca en la lista de solicitudes todos los usuarios que tengan mas de 65 años o menos de 12 años para agregarlos a la lista de
	 * beneficiados
	 */
	public List<Usuario> generarListaBeneficiados(){
		List<Usuario> beneficiados = new ArrayList<Usuario>();
		
		for(Solicitud solGeneral: solicitudesGenerales){
			List<Usuario> usuarioSol = solGeneral.darSolicitantes();
			for(Usuario usuarioActual: usuarioSol){
				if(usuarioActual.getEdad() < 12 || usuarioActual.getEdad() > 65){
					beneficiados.add(usuarioActual);
				}
			}
		}
		return beneficiados;
	}
	
	
	
	/**
	 * Método que retorna la lista de visas que se encuentran registradas en el sistema de citas.
	 * @return
	 * Lista de Visas
	 */
	public List<Visa> darVisas(){
		return visas;
	}
	
	/**
	 * Método que retorna la lista de los usuarios que están registrados en el sistema de citas.
	 * @return
	 * Lista de Usuarios
	 */
	public Map<String, Usuario> darUsuarios(){
		return usuarios;
	}
	
	/**
	 * Método que retorna la lista de las solicitudes que se encuentran en espera en el sistema de citas
	 * @return
	 * Lista de Solicitudes
	 */
	public List<Solicitud> darSolicitudesGenerales(){
		return solicitudesGenerales;
	}
	//----------------------------------------------------------------SUSTENTACIÓN-------------------------------------------------------------------
	/**
	 * Dada una lista de usuarios se va a calcular el porcentaje de los usuarios que son mayores de edad
	 * @return
	 */
	public double darPorcentajeMayoresEdad(){
		double porcentajeMayores = 0;
		double mayorEdad = 0;
		Map<String, Usuario> usuarios = darUsuarios();
		double total = usuarios.size();
		if(total > 0){
			for(String key: usuarios.keySet()){
				if(usuarios.get(key).getEdad() >= 18){
					mayorEdad++;
				}
			}
			porcentajeMayores = mayorEdad / total;
			return porcentajeMayores;
		}
		return porcentajeMayores;
	}
	/**
	 * Dada una edad inicial y una edad final se va a determinar la cantidad de usuarios cuya edad se encuentra en este rango
	 * @param edadInicial
	 * @param edadFinal
	 * @return
	 */
	public int darRangoEdad(int edadInicial, int edadFinal){
		int cantidadUsuarios = 0;
		Map<String, Usuario> usuarios = darUsuarios();
		for(String key: usuarios.keySet()){
			if((usuarios.get(key).getEdad() >= edadInicial) && (usuarios.get(key).getEdad() <= edadFinal)){
				cantidadUsuarios++;
			}
		}
		return cantidadUsuarios;
	}
	/**
	 * Dada una lista de usuarios se va a calcular el porcentaje de los usuarios dependiendo del género que sean
	 * @return
	 */
	public List<Double> darPorcentajeGenero(){
		Map<String, Usuario> usuarios = darUsuarios();
		List<Double> listaGeneros = new ArrayList<Double>();
		double total = usuarios.size();
		double cantTransexual = 0;
		double cantHombre = 0;
		double cantMujer = 0;
		double cantTransgenero = 0;
		double porTransexual = 0;
		double porHombre = 0;
		double porMujer = 0;
		double porTransgenero = 0;
		if(total > 0){
			for(String key: usuarios.keySet()){
				if(usuarios.get(key).getGenero() == 0){
					cantTransexual++;
				}else if(usuarios.get(key).getGenero() == 1){
					cantHombre++;
				}else if(usuarios.get(key).getGenero() == 2){
					cantMujer++;
				}else if(usuarios.get(key).getGenero() == 3){
					cantTransgenero++;
				}
			}
			porTransexual = cantTransexual / total;
			porHombre = cantHombre / total;
			porMujer = cantMujer / total;
			porTransgenero = cantTransgenero / total;
			
			listaGeneros.add(porTransexual);
			listaGeneros.add(porHombre);
			listaGeneros.add(porMujer);
			listaGeneros.add(porTransgenero);
			
			return listaGeneros;
		}
		return null;
	}
	/**
	 * Retorna la cantidad de usuarios que son mayores de edad
	 */
	public int darCantidadMayor(){
		
		int cantidad = 0;
		
		for(Usuario usuarioActual: usuarios.values()){
			if(usuarioActual.getEdad() >= 18){
				cantidad++;
			}
		}
		
		return cantidad;
	}
	/**
	 * Retorna el usuario con mayor edad
	 */
	public Usuario darUsuarioMayor(){
		
		Usuario usuarioMayor = null;
		int edadMinima = 0;
		
		for(Usuario usuarioActual: usuarios.values()){
			if(usuarioActual.getEdad() > edadMinima){
				usuarioMayor = usuarioActual;
				edadMinima = usuarioActual.getEdad();
			}
		}
		
		return usuarioMayor;
	}
	/**
	 * Retorna el usuario que mas paga por una visa
	 */
	public Usuario darUsuarioMasPaga(){
		
		Usuario usuarioPaga = null;
		double pagoMinimo = 0;
		
		for(Solicitud solicitudActual: solicitudesGenerales){
			for(Usuario usuarioActual: solicitudActual.getSolicitantes()){
				if(usuarioActual.calcularValorVisa() > pagoMinimo){
					usuarioPaga = usuarioActual;
					pagoMinimo = usuarioActual.calcularValorVisa();
				}
			}
			
		}
		
		return usuarioPaga;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS - SETTERS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public PaisEmbajada getPaisEmbajada() {
		return paisEmbajada;
	}
	public void setPaisEmbajada(PaisEmbajada paisEmbajada) {
		this.paisEmbajada = paisEmbajada;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public double getCambio() {
		return cambio;
	}
	public void setCambio(double cambio) {
		this.cambio = cambio;
	}
	public float getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}
	public List<Visa> getVisas() {
		return visas;
	}
	public void setVisas(List<Visa> visas) {
		this.visas = visas;
	}
	public List<Solicitud> getSolicitudesGenerales() {
		return solicitudesGenerales;
	}
	public void setSolicitudesGenerales(List<Solicitud> solicitudes) {
		this.solicitudesGenerales = solicitudes;
	}
	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Map<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
