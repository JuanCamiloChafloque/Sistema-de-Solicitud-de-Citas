package Controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import Modelo.Escolaridad;
import Modelo.ISistemaCitasEmbajada;
import Modelo.SistemaCitasEmbajada;
import Modelo.Solicitud;
import Modelo.Usuario;
import Modelo.Visa;
import Persistencia.ManejoArchivos;
import Vista.InterfazGrafica;
/**
 * Contiene el método Main. Es el que instancia un atributo de tipo "ISistemaCitasEmbajada". Es la encargada de coordinar y organizar el llamado 
 * de métodos entre el modelo y la vista. 
 */
public class ControladorCitasEmbajadaGUI {
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * La interfaz grafica permite mostrar todo el sistema a los usuarios. Esta relación le permite al constructor acceder a varios 
	 * métodos para poder mostrar mensajes y errores al usuario y también permite la entrada de datos por parte del usuario para realizar 
	 * las múltiples opciones que tienen.
	 */
	private InterfazGrafica vista;
	/**
	 * La interfaz sistema de citas va a ser el modelo del sistema. El modelo es el encargado de almacenar los datos y mantenerlos actualizados para 
	 * que cuando el usuario haga una petición al controlador este pueda acceder al modelo y recuperar la información pedida por el usuario y 
	 * mostrarla en pantalla.
	 */
	private ISistemaCitasEmbajada modelo;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El constructor permite al controlador crear múltiples relaciones e instancias para los diferentes paquetes que son parte del sistema.
	 * Se crea una instancia al modelo, la vista y el manejo de archivos. 
	 */
	public ControladorCitasEmbajadaGUI(){
		vista = new InterfazGrafica(this);
		modelo = new SistemaCitasEmbajada();
		new ManejoArchivos();
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MAIN
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El main crea una instancia al controlador del sistema de citas.
	 * @param args
	 */
	public static void main(String[] args) {
		new ControladorCitasEmbajadaGUI();
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Es llamada por la vista para que obtenga el país que se selecciono en el ComboBox y llame al paquete de persistencia para que
	 * asigne el país escogido al sistema de citas
	 */
	public void asignarPaisEmbajada(){
		try{
			String paisAsignado = vista.darPaisSeleccionado();
			List<String> sistemaEmbajada = ManejoArchivos.leerPaises(modelo, "PaisesEmbajada.txt", paisAsignado);
			modelo.asignarPais(sistemaEmbajada);
			JOptionPane.showMessageDialog(null, "El país de " + paisAsignado.toLowerCase() + " fue asignado exitosamente a la embajada");			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo seleccionado", "Error", 0);
		}
	}
	
	/**
	 * Es llamada por la vista para que obtenga el nombre del archivo que seleccinó el usuario y así llamar al paquete de persistencia para que
	 * pueda registrar todos las personas que se encuentren en ese archivo
	 */
	public void leerUsuarios(){
		try{
			String ruta = vista.darArchivoUsuarios();
			int usuariosAgregados = ManejoArchivos.leerUsuarios(modelo, ruta);
			vista.crearTablaUsuarios(modelo.darUsuarios());
			JOptionPane.showMessageDialog(null, "Se agregaron un total de " + usuariosAgregados + " usuarios exitosamente");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo seleccionado", "Error", 0);
		}
	}
	
	/**
	 * Es llamada por la vista para que pueda pedir toda la información necesaria para registrar a la persona actual
	 */
	public void agregarSolicitante(){
		String nombre = JOptionPane.showInputDialog(null, "Digite su nombre");
		String numPass = JOptionPane.showInputDialog(null, "Digite su número de pasaporte");
		String email = JOptionPane.showInputDialog(null, "Digite su correo electronico");
		int anioNacimiento = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite su año de nacimiento"));
		int mesNacimiento = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite su mes de nacimiento"));
		int diaNacimiento = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite su dia de nacimiento"));
		LocalDate fechaNacimiento = LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento);
		String paisNacimiento = JOptionPane.showInputDialog(null, "Digite su país de nacimiento");
		String ciudadNacimiento = JOptionPane.showInputDialog(null, "Digite su ciudad de macimiento");
		String infoAdicional = null;
		if(LocalDate.now().compareTo(fechaNacimiento) <= 2){
			infoAdicional = JOptionPane.showInputDialog(null, "Digite su acudiente");
		}
		if ((LocalDate.now().compareTo(fechaNacimiento) <= 12) && (LocalDate.now().compareTo(fechaNacimiento) > 2)){
			infoAdicional = JOptionPane.showInputDialog(null, "Digite su escolaridad");
		}
		Usuario buscado = modelo.buscarUsuario(numPass);
		if(buscado == null){
			modelo.agregarUsuarios(nombre, numPass, email, fechaNacimiento, paisNacimiento, ciudadNacimiento, infoAdicional);
			JOptionPane.showMessageDialog(null, "El solicitante fue registrado exitosamente");	
			vista.crearTablaUsuarios(modelo.darUsuarios());
		}else{
			JOptionPane.showMessageDialog(null, "El usuario con el número de pasaporte " + numPass + " ya se encuentra registrado", "Error", 0);
		}	
	}
	
	/**
	 * Se llama al paquete de modelo y se busca en todas las solicitudes que se han realizado en el sistema si hay un usuario con el número de pasaporte que llegó por 
	 * parámetro. Retorna null si se encontró el número en alguna solicitud y retorna el objeto del usuario con ese número si no se encontró en las solicitudes
	 * @param numPasaporte
	 * @return
	 */
	public Usuario buscarUsuarioSolicitud(String numPasaporte){
		
		Usuario usuarioActual = modelo.buscarUsuarioSolicitud(numPasaporte);
		return usuarioActual;
		
	}
	
	/**
	 * Llama al método que se encuentra en el paquete de Persistencia para que lea un archivo de texto que contiene la información de los valores de las
	 * visas que se encuentran en el sistema. Retorna ese valor para que pueda ser usado en las solicitudes
	 * @return
	 */
	public double leerDatosVisa(String tipoVisa){
		
		try{
			double valorVisa = ManejoArchivos.leerDatosVisa(modelo, "Tarifas.txt", tipoVisa);
			return valorVisa;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de las tarifas","Error", 0);
			return 0;
		}
	}
	
	/**
	 * Este método pide un archivo de texto con la información de todos los tipos de visa. Luego llama al paquete de persistencia para poder instanciar nuevos objetos de
	 * tipo Visa y a cada uno de esos objetos instanciar objetos de tipo Requisito.
	 */
	public List<String> ingresarRequisitosVisa(String nombreVisa) {
		try{
			List<String> requisitos = ManejoArchivos.leerVisas(modelo, "Visas.txt", nombreVisa);
			return requisitos;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de los requisitos","Error", 0);
			return null;
		}
	}
	
	/**
	 * Método que interactúa con el modelo y permite crear una instancia de una solicitud que este asociada a una visa de tipo turismo. En esta solicitud puede haber
	 * más de un solicitante. Reotrna a la vista el código nuevo que generó el sistema para mostrarlo en pantalla y ademas avisa la fecha y hora de la cita
	 */
	public void solicitarVisaTurismo(List<Usuario> solicitantesNuevos){
		
		int diasEstadia = Integer.parseInt(vista.darTxtDiasEstadia());
		double valorVisa = leerDatosVisa("Turismo");
		List<String> requisitos = ingresarRequisitosVisa("Turismo");
		
		Visa visaPedida = modelo.crearVisaTurismo(diasEstadia, valorVisa, requisitos);
				
		Solicitud nuevaSolicitud = modelo.solicitarSolicitud(solicitantesNuevos.get(0), visaPedida);
		
		for(Usuario usuarioActual: solicitantesNuevos){
			if(!(usuarioActual.getNumPasaporte().equalsIgnoreCase(solicitantesNuevos.get(0).getNumPasaporte()))){
				nuevaSolicitud.agregarUsuarioSolicitud(usuarioActual);
			}
		}
		
		vista.actualizarTxtSolicitudTurismo("" + nuevaSolicitud.getCodigo());
		
		JOptionPane.showMessageDialog(null, "La Solicitud fue creada exitosamente");
		JOptionPane.showMessageDialog(null, "La fecha de la cita es: " + nuevaSolicitud.getFecha().toString());
	
	}
	
	/**
	 * Método que interactúa con el modelo y permite crear una instancia de una solicitud que este asociada a una visa de tipo trabajo. En esta solicitud puede haber
	 * solo un solicitante. Reotrna a la vista el código nuevo que generó el sistema para mostrarlo en pantalla y ademas avisa la fecha y hora de la cita
	 */
	public void solicitarVisaTrabajo(Usuario solicitanteNuevo){
		
		String empresa = vista.darTxtEmpresa();
		String cargo = vista.darTxtCargo();
		double valorVisa = leerDatosVisa("Trabajo");
		List<String> requisitos = ingresarRequisitosVisa("Trabajo");
		
		Visa visaPedida = modelo.crearVisaTrabajo(empresa, cargo, valorVisa, requisitos);
		
		Solicitud nuevaSolicitud = modelo.solicitarSolicitud(solicitanteNuevo, visaPedida);
		
		vista.actualizarTxtSolicitudTrabajo("" + nuevaSolicitud.getCodigo());
		
		JOptionPane.showMessageDialog(null, "La Solicitud fue creada exitosamente");
		JOptionPane.showMessageDialog(null, "La fecha de la cita es: " + nuevaSolicitud.getFecha().toString());
	}
	
	/**
	 * Método que interactúa con el modelo y permite crear una instancia de una solicitud que este asociada a una visa de tipo estudiante. En esta solicitud puede haber
	 * solo un solicitante. Reotrna a la vista el código nuevo que generó el sistema para mostrarlo en pantalla y ademas avisa la fecha y hora de la cita
	 */
	public void solicitarVisaEstudiante(Usuario solicitanteNuevo){
		
		int cont = 0;
		String escol = vista.darTxtEscolaridad();
		
		for(Escolaridad es: Escolaridad.values()){
			if(es.toString().equalsIgnoreCase(escol)){
				cont++;
				String institucion = vista.darTxtInstitucion();
				double valorVisa = leerDatosVisa("Estudiante");
				List<String> requisitos = ingresarRequisitosVisa("Estudiante");
				Visa visaPedida = modelo.crearVisaEstudiante(es, institucion, valorVisa, requisitos);
				
				Solicitud nuevaSolicitud = modelo.solicitarSolicitud(solicitanteNuevo, visaPedida);
								
				vista.actualizarTxtSolicitudEstudiante("" + nuevaSolicitud.getCodigo());

				JOptionPane.showMessageDialog(null, "La Solicitud fue creada exitosamente");
				JOptionPane.showMessageDialog(null, "La fecha de la cita es: " + nuevaSolicitud.getFecha().toString());

			}
		}
		if(cont == 0){
			JOptionPane.showMessageDialog(null, "La escolaridad digitada no es valida", "Error", 0);
		}
	}
	
	/**
	 * Método que interactúa con el modelo y permite calcular el valor de la visa de una determinada solicitud. El método de búsqueda de esta solicitud puede ser por
	 * número de pasaporte o por el código de la solicitud. Se envía a la vista una lista con todos los usuarios que hacen parte de la solicitud y tambien se actualizan
	 * los texts fields con los montos totales a pagar y su respectiva conversión
	 */
	public List<Usuario> calcularValorVisa(){
		
		String busqueda = vista.darBusquedaSeleccionada();
		String numero = vista.darTxtNumero();
		int cont = 0;
			
		if(busqueda.equalsIgnoreCase("NP - Número de Pasaporte")){
			
			for(Solicitud solicitudActual: modelo.darSolicitudesGenerales()){
				
				for(Usuario solicitanteActual: solicitudActual.darSolicitantes()){
					if(solicitanteActual.getNumPasaporte().equalsIgnoreCase(numero)){
						
						cont++;
						double valorParcial = (int) (solicitudActual.calcularValorVisa() * 2550);
						double valorTotal = (int) (valorParcial + valorParcial * modelo.getImpuesto());
										
						vista.actualizarTxtValorTotal("" + valorTotal);
						vista.actualizarTxtMonedaLocal("" + modelo.getMoneda());
						vista.actualizarTxtTasaCambio("" + modelo.getCambio());
						double valorLocal = (int) (valorTotal / modelo.getCambio());
						vista.actualizarTxtValorLocal("" + valorLocal);
			
						return solicitudActual.darSolicitantes();
					}
				}
			}
			
			
		}else if(busqueda.equalsIgnoreCase("CP - Código Solicitud")){
			
			for(Solicitud solicitudActual: modelo.darSolicitudesGenerales()){
				if(solicitudActual.getCodigo() == Integer.parseInt(numero)){
								
					cont++;
					double valorParcial = (int) (solicitudActual.calcularValorVisa() * 2550);
					double valorTotal = (int) (valorParcial + valorParcial * modelo.getImpuesto());
			
					vista.actualizarTxtValorTotal("" + valorTotal);
					vista.actualizarTxtMonedaLocal("" + modelo.getMoneda());
					vista.actualizarTxtTasaCambio("" + modelo.getCambio());
					double valorLocal = (int) (valorTotal / modelo.getCambio());
					vista.actualizarTxtValorLocal("" + valorLocal);
					
					return solicitudActual.darSolicitantes();
				}
			}
		}
		
		if(cont == 0){
			JOptionPane.showMessageDialog(null, "No hay ninguna solicitud pendiente con el número digitado", "Error", 0);
		}
		
		return null;	
	}
	/**
	 * Genera el reporte de las solicitudes pendientes dada una determinada fecha y retorna al panel de reportes una lista con todos los usuariosque tengan una
	 * solicitud pendiente en esa fecha. 
	 */
	public List<Usuario> generarReporteSolicitudes(){
		
		String fecha = vista.darTxtFecha();
		String []auxFecha = new String[3];
		List<Usuario> usuariosHoy = new ArrayList<Usuario>();
		int cont = 0;

		auxFecha = fecha.split("\\-");
		int anio = Integer.parseInt(auxFecha[0].trim());
		int mes = Integer.parseInt(auxFecha[1].trim());
		int dia = Integer.parseInt(auxFecha[2].trim());
		LocalDate fechaReporte = LocalDate.of(anio, mes, dia);
		
		for(Solicitud solicitudActual: modelo.darSolicitudesGenerales()){
			if(solicitudActual.getFecha().toLocalDate().equals(fechaReporte)){
				cont++;
				usuariosHoy.addAll(solicitudActual.darSolicitantes());
				
			}
		}
		if(cont == 0){
			JOptionPane.showMessageDialog(null, "Para la fecha digitada no hay solicitudes pendientes");
		}
		
		return usuariosHoy;

		
	}
	/**
	 * Genera el reporte de los beneficiarios y retorna al panel de reportes una lista con todos los usuarios que hagan parte de los beneficios. Ademas retorna 
	 * el valor recaudado gracias a estos beneficios
	 */
	public List<Usuario> generarReporteBeneficiarios(){
		
		double valorRecaudado = 0;
		List<Usuario> beneficiarios = modelo.generarListaBeneficiados();
		for(Usuario beneficiarioActual: beneficiarios){
			valorRecaudado += (beneficiarioActual.calcularValorVisa() * 2550);
		}
		
		if(beneficiarios.size() == 0){
			JOptionPane.showMessageDialog(null, "No se encuentran solicitantes beneficiarios en el sistema");
		}
		
		vista.actualizarTxtValorRecaudado("" + (int)valorRecaudado);
		return beneficiarios;

	}
	/**
	 * Serializa el sistema cuado se presiona el boton de salvar sistema en el panel menú donde se pide la ruta para guardar el modelo
	 * @param ruta
	 */
	public void serializarSistema(String ruta){
		
		try{
			ManejoArchivos.serializarModelo(modelo, ruta);
			JOptionPane.showMessageDialog(null, "El sistema se guardó exitosamente");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "No se pudo generar el archivo de serialización", "Error", 0);
		}
	}
	/**
	 * Al presionar el boton de cargar sistema en el panel de menú se abre un jfilechooser donde se selecciona el archivo donde se guardó el modelo para poder
	 * deserializarlo
	 * @param ruta
	 */
	public void cargarSistema(String ruta){
		try{
			ManejoArchivos.leerSerializacion(modelo, ruta);
			JOptionPane.showMessageDialog(null, "El sistema se cargó exitosamente");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de serialización", "Error", 0);

		}
		
	}
	/**
	 * Retorna a la interfaz el Map de usuarios que se encuentran registrados en el sistema
	 * @return
	 */
	public Map<String, Usuario> darUsuarios(){
		return modelo.darUsuarios();
	}
	/**
	 *  Retorna a la interfazla lista de solicitudes
	 */
	public List<Solicitud> darSolicitudes(){
		return modelo.darSolicitudesGenerales();
	}
	/**
	 * Retorna al panel de calcular visa el valor del impuesto para poder generar los calculos y la tabla
	 * @return
	 */
	public float darImpuestoPais(){
		return modelo.getImpuesto();
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Retorna una lista con los usuarios que cumplen el criterio de busqueda que se realiza en el panel de busqueda
	 * @return
	 */
	public List<Usuario> darUsuariosBusqueda(){
		
		String busqueda = vista.darTxtBusqueda().toLowerCase();
		List<Usuario> usuariosBusqueda = new ArrayList<Usuario>();
		
		
		for(Usuario usuarioActual: modelo.darUsuarios().values()){
			if(usuarioActual.getNombre().toLowerCase().contains(busqueda)){
				usuariosBusqueda.add(usuarioActual);
			}
		}
		
		return usuariosBusqueda;
	}
	/**
	 * retorna la cantidad de usuarios que se encuentran en un determinado rango de edad
	 */
	public int darRangoEdades(int edadInicial, int edadFinal){
		
		return modelo.darRangoEdad(edadInicial, edadFinal);
	
	}
	/**
	 * Retorna la cantidad de usuarios que son mayores de edad
	 */
	public int darCantidadMayores(){
		
		return modelo.darCantidadMayor();
	}
	/**
	 * Retorna el usuario con mayor edad
	 */
	public Usuario darUsuarioMayor(){
		
		return modelo.darUsuarioMayor();
	}
	/**
	 * Retorna el usuario que mas paga por una visa
	 */
	public Usuario darUsuarioMasPaga(){
		
		return modelo.darUsuarioMasPaga();
	}
}

