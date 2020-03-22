package Vista;

import javax.swing.JFrame;
import Controlador.ControladorCitasEmbajadaGUI;
import Modelo.Solicitud;
import Modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.swing.JTabbedPane;

public class InterfazGrafica extends JFrame{

	private static final long serialVersionUID = 1L;
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Atributo que sirve de variable para poder crear el frame del sistema
	 */
	private JFrame menu;
	/**
	 * Contiene todas las ventanas de todos los paneles que se muestran en el menu principal
	 */
	private JTabbedPane ventanas;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * La interfaz tiene una relación con el controlador GUI
	 */
	private ControladorCitasEmbajadaGUI controlador;
	/**
	 * La interfaz tiene una relación con un panel que muestra el menú de opciones que tiene el usuario 
	 */
	private PanelMenu panelMenu;
	/**
	 * La interfaz tiene una relación con un panel que da la opción de escoger un país para relacionarlo con el sistema 
	 */
	private PanelAsociarPais panelAsociarPais;
	/**
	 * La interfaz tiene una relación con un panel que permite calcular y mostrar el valor de la visa de una determinada solicitud 
	 */
	private PanelCalcularVisa panelCalcularVisa; 
	/**
	 * La interfaz tiene una relación con un panel que permite ingresar y mostrar la lista de solicitantes que hacen parte del sistema 
	 */	
	private PanelIngresarSolicitantes panelIngresarSolicitantes; 
	/**
	 * La interfaz tiene una relación con un panel que muestra la lista de beneficiarios que tiene el sistema
	 */
	private PanelMostrarBeneficiarios panelBeneficiarios; 
	/**
	 * La interfaz tiene una relación con un panel que muestra el reporte de las solicitudes pendientes que tiene el sistema
	 */
	private PanelMostrarReporte panelReporte; 
	/**
	 * La interfaz tiene una relación con un panel que permite al usuario solicitar una visa de tipo estudiante
	 */
	private PanelSolicitarEstudiante panelEstudiante; 
	/**
	 * La interfaz tiene una relación con un panel que permite al usuario solicitar una visa de tipo trabajo
	 */
	private PanelSolicitarTrabajo panelTrabajo; 
	/**
	 * La interfaz tiene una relación con un panel que permite al usuario solicitar una visa de tipo turismo
	 */
	private PanelSolicitarTurismo panelTurismo; 
	/**
	 * La interfaz tiene una relación con un panel que permite al usuario buscar otros usuarios dependiendo de los criterios de busqueda
	 */
	private PanelBusqueda panelBusqueda;
	/**
	 * La interfaz tiene una relación con un panel que permite al usuario acceder a la información general de otro usuario
	 */
	//private PanelUsuario panelUsuario;
	/**
	 * La interfaz tiene una relación con un panel que permite al usuario consultar la información general de una solicitud determinada
	 */
	private PanelBusquedaSolicitud panelBusquedaSolicitud;
	/**
	 * La interfaz tiene una relación con un dialogo que permite al usuario ver las estadisticas de los usuarios registrados
	 */
	private DialogoEdades dialogoEdades;
	/**
	 * La interfaz tiene una relación con un dialogo que permite al usuario ver una solicitud 
	 */
	private DialogoSolicitud dialogoSolicitud;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se crea la interfaz grafica del sistema mostrando todas las ventanas y opciones que ofrece el sistema
	 */
	public InterfazGrafica(ControladorCitasEmbajadaGUI pControlador){
		
		controlador = pControlador;
		
		menu = new JFrame();
		menu.getContentPane().setBackground(Color.WHITE);
		menu.setSize(700, 600);
		menu.setTitle("Sistema Citas Embajada");
		menu.getContentPane().setLayout(new BorderLayout());
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		ventanas = new JTabbedPane(JTabbedPane.TOP);
		ventanas.setBounds(10, 35, 664, 515);
		
		panelMenu = new PanelMenu(this);
		ventanas.addTab("Menú Principal", null, panelMenu, null);
		
		panelAsociarPais = new PanelAsociarPais(this);
		ventanas.addTab("Asociar Pais Embajada", null, panelAsociarPais, null);
			
		panelIngresarSolicitantes = new PanelIngresarSolicitantes(this);
		ventanas.addTab("Ingresar Solicitantes", null, panelIngresarSolicitantes, null);
		
		panelEstudiante = new PanelSolicitarEstudiante(this);
		ventanas.addTab("Solicitar Visa Estudiante", null, panelEstudiante, null);		
		
		panelTrabajo = new PanelSolicitarTrabajo(this);
		ventanas.addTab("Solicitar Visa Trabajo", null, panelTrabajo, null);
		
		panelTurismo = new PanelSolicitarTurismo(this);
		ventanas.addTab("Solicitar Visa Turismo", null, panelTurismo, null);
		
		panelCalcularVisa = new PanelCalcularVisa(this);
		ventanas.addTab("Calcular Valor Visa", null, panelCalcularVisa, null);
		
		panelBeneficiarios = new PanelMostrarBeneficiarios(this);
		ventanas.addTab("Lista de Beneficiarios", null, panelBeneficiarios, null);
		
		panelReporte = new PanelMostrarReporte(this);
		ventanas.addTab("Reporte de Citas", null, panelReporte, null);
		
		panelBusqueda = new PanelBusqueda(this);
		ventanas.addTab("Busqueda Usuarios", null, panelBusqueda, null);
		
		panelBusquedaSolicitud = new PanelBusquedaSolicitud(this);
		ventanas.addTab("Consulta Solicitud", null, panelBusquedaSolicitud, null);
		
		//panelUsuario = new PanelUsuario(this);
				
		menu.getContentPane().add(ventanas, BorderLayout.CENTER);		
		menu.setResizable(false);
		menu.setVisible(true);
		
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Llama al controlador para que pueda serializar el sistema (Modelo) dado un archivo para guardar
	 * @param ruta
	 */
	public void darArchivoSerializacion(String ruta){
		controlador.serializarSistema(ruta);
	}
	/**
	 * Llama al controlador para que pueda cargar el sistema que se encuentra guardado en un archivo 
	 * @param ruta
	 */
	public void leerSerializacion(String ruta){
		controlador.cargarSistema(ruta);
	}
	/**
	 * Llama al controlador para que retorne el Map con todos los solicitantes que se encuentran registrados en el sistema
	 * @return
	 */
	public Map<String, Usuario> darUsuarios(){
		Map<String, Usuario> usuarios = controlador.darUsuarios();
		return usuarios;
	}
	/**
	 * Llama al controlador para que retorne una lista con todas las solicitudes que se encuentran registradas en el sistema
	 */
	public List<Solicitud> darSolicitudes(){
		return controlador.darSolicitudes();
	}
	/**
	 * Llama al controlador para que asigne el país escogido al sistema
	 * @param paisSeleccionado
	 */
	public void asignarPaisEmbajada(){
		controlador.asignarPaisEmbajada();
	}
	/**
	 * Llama al panel de asociar país 
	 * @return Retorna el país que se seleccionó en el Combo Box
	 */
	public String darPaisSeleccionado(){
		return panelAsociarPais.darPaisSeleccionado();
	}
	/**
	 * Esta relacionada con el panel menú. Cuando el usuario presiona alguno de los botones que se encuentran en este, ocacionara una acción y enviará
	 * el indíce de la ventana del menú que se presionó para que se actualice la vista y muestre la opción seleccionada 
	 * @param tab
	 */
	public  void asignarVentanaActual(int tab){
		ventanas.setSelectedIndex(tab);
	}
	/**
	 * Llama al constructor para que pueda pedir el archivo que seleccinó el usuario y así poder registrarlos
	 */
	public void leerUsuarios(){
		controlador.leerUsuarios();
	}
	/**
	 * Pide al panel de ingresar solicitantes el archivo seleccionado por el usuario
	 * @return
	 */
	public String darArchivoUsuarios(){
		return panelIngresarSolicitantes.darArchivoUsuarios();
	}
	/**
	 * Llama al constructor para pedir los datos de la persona que quiere registrarse
	 */
	public void agregarSolicitante(){
		controlador.agregarSolicitante();
	}
	/**
	 * Envía la lista de usuario al panel de ingresar solicitantes para que este pueda crear una tabla con la información necesaria
	 * @param usuarios
	 */
	public void crearTablaUsuarios(Map<String, Usuario> usuarios){
		panelIngresarSolicitantes.crearTablaUsuarios(usuarios);
	}
	/**
	 * Pide al panel de solicitud turismo el texto que se ingresó en el textField de días de estadia
	 * @return
	 */
	public String darTxtDiasEstadia(){
		return panelTurismo.darTxtDiasEstadia();
	}
	/**
	 * Pide al panel de solicitud trabajo el texto que se ingresó en el textField de empresa
	 * @return
	 */
	public String darTxtEmpresa(){
		return panelTrabajo.darTxtEmpresa();
	}
	/**
	 * Pide al panel de solicitud trabajo el texto que se ingresó en el textField de cargo
	 * @return
	 */
	public String darTxtCargo(){
		return panelTrabajo.darTxtCargo();
	}
	/**
	 * Pide al panel de solicitud estudiante el texto que se ingresó en el textField de institución
	 * @return
	 */
	public String darTxtInstitucion(){
		return panelEstudiante.darTxtInstitucion();
	}
	/**
	 * Pide al panel de solicitud estudiante el texto que se ingresó en el textField de escolaridad
	 * @return
	 */
	public String darTxtEscolaridad(){
		return panelEstudiante.darTxtEscolaridad();
	}
	/**
	 * Llama al panel de calcular visa
	 * @return Retorna el método de busqueda que se seleccionó en el Combo Box
	 */
	public String darBusquedaSeleccionada(){
		return panelCalcularVisa.darBusquedaSeleccionada();
	}
	/**
	 * Pide al panel de calcular visa el texto que se ingresó en el textField de número
	 * @return
	 */
	public String darTxtNumero(){
		return panelCalcularVisa.darTxtNumero();
	}
	/**
	 * Llama al panel de estudiante para que actualice el número de solicitud con el número que llego por parámetro
	 * @param solicitud
	 */
	public void actualizarTxtSolicitudEstudiante(String solicitud){
		panelEstudiante.actualizarTxtSolicitud(solicitud);
	}
	/**
	 * Llama al panel de turismo para que actualice el número de solicitud con el número que llego por parámetro
	 * @param solicitud
	 */
	public void actualizarTxtSolicitudTurismo(String solicitud){
		panelTurismo.actualizarTxtSolicitud(solicitud);
	}
	/**
	 * Llama al panel de trabajo para que actualice el número de solicitud con el número que llego por parámetro
	 * @param solicitud
	 */
	public void actualizarTxtSolicitudTrabajo(String solicitud){
		panelTrabajo.actualizarTxtSolicitud(solicitud); 
	}
	/**
	 * Llama al controlador para que busque en las listas de solicitudes un usuario con un número de pasaporte que llega por parámetro
	 * @param numPasaporte
	 * @return
	 */
	public Usuario buscarUsuarioSolicitud(String numPasaporte){
		Usuario usuarioPendiente = controlador.buscarUsuarioSolicitud(numPasaporte);
		return usuarioPendiente;
	}
	/**
	 * Dado una lista de usuarios, se llama al controlador para que cree una nueva instancia de la clase Solicitud vinculando a esta una visa de tipo turismo
	 * @param solicitantes
	 */
	public void crearSolicitudTurismo(List<Usuario> solicitantes){
		controlador.solicitarVisaTurismo(solicitantes);
	}
	/**
	 * Dado una lista de usuarios, se llama al controlador para que cree una nueva instancia de la clase Solicitud vinculando a esta una visa de tipo trabajo
	 * @param solicitante
	 */
	public void crearSolicitudTrabajo(Usuario solicitante){
		controlador.solicitarVisaTrabajo(solicitante);
	}
	/**
	 * Dado una lista de usuarios, se llama al controlador para que creee una nueva instancia de la clase Solicitud vinculando a esta una visa de tipo estudiante
	 * @param solicitante
	 */
	public void crearSolicitudEstudiante(Usuario solicitante){
		controlador.solicitarVisaEstudiante(solicitante);
	}
	/**
	 * Llama al controlador para que ejecute el método de calcular valor visa, el cual devuelve al panel encargado una lista con los solicitantes del número de solicitud
	 * que se digitó
	 * @return
	 */
	public List<Usuario> calcularValorVisa(){
			List<Usuario> solicitantes = controlador.calcularValorVisa();
			return solicitantes;
	}
	/**
	 * Llama al panel de calcular visa para que actualice el valor total con el string que llego por parámetro
	 * @param valor
	 */
	public void actualizarTxtValorTotal(String valor){
		panelCalcularVisa.actualizarTxtValorTotal(valor);
	}
	/**
	 * Llama al panel de calcular visa para que actualice la moneda local con el string que llego por parámetro
	 * @param moneda
	 */
	public void actualizarTxtMonedaLocal(String moneda){
		panelCalcularVisa.actualizarTxtMonedaLocal(moneda);
	}
	/**
	 * Llama al panel de calcular visa para que actualice la tasa de cambio con el string que llego por parámetro
	 * @param tasa
	 */
	public void actualizarTxtTasaCambio(String tasa){
		panelCalcularVisa.actualizarTxtTasaCambio(tasa);
	}
	/**
	 * Llama al panel de calcular visa para que actualice el valor local con el string que llego por parámetro
	 * @param valorLocal
	 */
	public void actualizarTxtValorLocal(String valorLocal){
		panelCalcularVisa.actualizarTxtValorLocal(valorLocal);
	}
	/**
	 * Llama al controlador para que pueda enviarle al panel de calcular valor visa el valor del impuesto que actualmente tiene el país asignado
	 * @return
	 */
	public float darImpuestoPais(){
		float impuesto = controlador.darImpuestoPais();
		return impuesto;
	}
	/**
	 * Llama al controlador para que genere una lista con todos los usuarios de todas las solicitudes que estan pendientes para el día que se digitó en el textField
	 * @return
	 */
	public List<Usuario> generarReporteFecha(){
		List<Usuario> usuariosHoy = controlador.generarReporteSolicitudes();
		return usuariosHoy;
	}
	/**
	 * Pide al panel de generar reporte el texto que se ingresó en el textField de fecha
	 * @return
	 */
	public String darTxtFecha(){
		return panelReporte.darTxtFecha();
	}
	/**
	 * Llama al controlador para que genera una lista con todos los usuarios de todas las solicitudes que estan en el programa de beneficiarios
	 * @return
	 */
	public List<Usuario> generarReporteBeneficiarios(){
		List<Usuario> beneficiarios = controlador.generarReporteBeneficiarios();
		return beneficiarios;
	}	
	/**
	 * Llama al panel de beneficisrios para que actualice el valor recaudado con el string que llego por parámetro
	 * @param valorRecaudado
	 */
	public void actualizarTxtValorRecaudado(String valorRecaudado){
		panelBeneficiarios.actualizarTxtValor(valorRecaudado);
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Retorna el texto que se ingreso en el textField de búsqueda para ver si se encontró un usuario con ese criterio
	 * @return
	 */
	public String darTxtBusqueda(){
		return panelBusqueda.darTxtBusqueda();
	}
	/**
	 * Llama al controlador para retornar la lista de los usuarios encontrados dado el criterio de busqueda
	 * @return
	 */
	public List<Usuario> darUsuariosBusqueda(){
		return controlador.darUsuariosBusqueda();
	}
	/**
	 * Crea la ventana de la búsqueda del usuario con toda la información importante del usuario seleccionado
	 * @param usuarioSeleccionado
	 */
	public void crearPanelUsuario(Usuario usuarioSeleccionado){
		
		//ventanas.addTab("Usuarios", null, panelUsuario, null);
		DialogoUsuario dialogoUsuario = new DialogoUsuario(usuarioSeleccionado);
		dialogoUsuario.actualizarDatos(usuarioSeleccionado);
		dialogoUsuario.setLocationRelativeTo(this);
		dialogoUsuario.setVisible(true);
		//ventanas.setSelectedIndex(10);
	}
	/**
	 * Crea la ventana del reporte de edades
	 */
	public void crearPanelEstadisticas(){
		dialogoEdades = new DialogoEdades(this);
		darValoresRango();
		dialogoEdades.setLocationRelativeTo(this);
		dialogoEdades.setVisible(true);
		
	}
	/**
	 * Crea la ventana de la busqueda de la solicitud con toda la información importante de la solicitud
	 */
	public void crearPanelSolicitud(Solicitud solicitudActual){
		dialogoSolicitud = new DialogoSolicitud(this, solicitudActual);
		dialogoSolicitud.setLocationRelativeTo(this);
		dialogoSolicitud.setVisible(true);
	}
	/**
	 * Se muestra la bandera del país de la embajada
	 * @param pais
	 */
	public void crearBandera(String pais){
		panelMenu.mostrarBandera(pais);
	}
	/**
	 * Da la cantidad de usuarios que hay en el sistema dado un rango de edad
	 */
	public void darValoresRango(){
		
		int cantidad;
		
		cantidad = controlador.darRangoEdades(0, 11);
		dialogoEdades.actualizarTxt0_11("" + cantidad);
		
		cantidad = controlador.darRangoEdades(12, 20);
		dialogoEdades.actualizarTxt12_20("" + cantidad);
		
		cantidad = controlador.darRangoEdades(21, 30);
		dialogoEdades.actualizarTxt21_30("" + cantidad);
		
		cantidad = controlador.darRangoEdades(31, 40);
		dialogoEdades.actualizarTxt31_40("" + cantidad);
		
		cantidad = controlador.darRangoEdades(41, 50);
		dialogoEdades.actualizarTxt41_50("" + cantidad);
		
		cantidad = controlador.darRangoEdades(51, 60);
		dialogoEdades.actualizarTxt51_60("" + cantidad);
		
		cantidad = controlador.darRangoEdades(61, 120);
		dialogoEdades.actualizarTxt60("" + cantidad);
	}
	/**
	 * Retorna la cantidad de usuarios que son mayores de edad
	 */
	public int darCantidadMayores(){
		return controlador.darCantidadMayores();
	}
	/**
	 * Retorna el usuario con mayor edad
	 */
	public Usuario darUsuarioMayor(){
		return controlador.darUsuarioMayor();
	}
	/**
	 * Retorna el usuario que mas paga por una visa
	 */
	public Usuario darUsuarioMasPaga(){
		return controlador.darUsuarioMasPaga();
	}
}
