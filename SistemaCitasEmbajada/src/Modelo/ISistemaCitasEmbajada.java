package Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Enumera los servicios de negocio que ofrece la embajada a los usuarios y es el punto de entrada al nivel funcional del sistema
 *
 */
public interface ISistemaCitasEmbajada {

	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS ABSTRACTOS
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public boolean asignarPais(List<String> paisAsignado);
	public Usuario buscarUsuario(String numPasaporte);
	public Usuario buscarUsuarioSolicitud(String numPasaporte);
	public Visa crearVisaTurismo(int diasEstadia, double valor, List<String> requisitos);
	public Visa crearVisaTrabajo(String empresa, String cargo, double valor, List<String> requisitos);
	public Visa crearVisaEstudiante(Escolaridad escolaridad, String institucion, double valor, List<String> requisitos);
	public void agregarRequisitosVisa(List<String> requisitos, Visa visaActual);
	public void agregarUsuarios(String nombre, String numPass, String email, LocalDate fechaNacimiento, String paisOrigen, String ciudadNacimiento, String infoAdicional);
	public Solicitud solicitarSolicitud(Usuario usuario, Visa visa);
	public LocalDateTime asignarFechaHora(LocalDateTime fechaUltima);
	public List<Usuario> generarListaBeneficiados();
	public List<Visa> darVisas();
	public Map<String, Usuario> darUsuarios();
	public List<Solicitud> darSolicitudesGenerales();
	//Sustentación 1
	public double darPorcentajeMayoresEdad();
	public int darRangoEdad(int edadInicial, int edadFinal);
	public List<Double> darPorcentajeGenero();
	public int darCantidadMayor();
	public Usuario darUsuarioMayor();
	public Usuario darUsuarioMasPaga();
	//Getters - Setters
	public int getId();
	public void setId(int id);
	public PaisEmbajada getPaisEmbajada();
	public void setPaisEmbajada(PaisEmbajada paisEmbajada);
	public String getMoneda();
	public void setMoneda(String moneda);
	public double getCambio();
	public void setCambio(double cambio);
	public float getImpuesto();
	public void setImpuesto(float impuesto);
	public List<Visa> getVisas();
	public void setVisas(List<Visa> visas);
	public List<Solicitud> getSolicitudesGenerales();
	public void setSolicitudesGenerales(List<Solicitud> solicitudes);
	public Map<String, Usuario> getUsuarios();
	public void setUsuarios(Map<String, Usuario> usuarios);
}
