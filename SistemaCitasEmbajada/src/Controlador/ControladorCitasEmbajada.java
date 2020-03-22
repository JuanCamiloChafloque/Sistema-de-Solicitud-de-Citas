package Controlador;

import Modelo.Escolaridad;
import Modelo.Estudiante;
import Modelo.ISistemaCitasEmbajada;
import Modelo.NiñoMayor;
import Modelo.NiñoMenor;
import Modelo.SistemaCitasEmbajada;
import Modelo.Solicitud;
import Modelo.Trabajo;
import Modelo.Turismo;
import Modelo.Usuario;
import Modelo.Visa;
import Vista.InterfazConsola;
import Persistencia.ManejoArchivos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * SISTEMA DE CITAS DE UNA EMBAJADA
 * Proyecto Final Programación Orientada a Objetos 2018-1 Entrega II.
 * @author Juan Camilo Chafloque
 * @author Gabriel Forero
 */
//---------------------------------------------------------------------------------------------------------------------------------------------------
/**
 * Contiene el método Main. Es el que instancia un atributo de tipo "ISistemaCitasEmbajada". Es la encargada de coordinar y organizar el llamado 
 * de métodos entre el modelo y la vista. 
 */
public class ControladorCitasEmbajada {
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * La interfaz consola permite mostrar los mensajes a los usuarios del sistema. Esta relación le permite al constructor acceder a varios 
	 * métodos para poder imprimir mensajes y errores al usuario y también permite la entrada de datos por parte del usuario para realizar 
	 * las múltiples opciones que tienen.
	 */
	private InterfazConsola vista;
	/**
	 * La interfaz sistema de citas va a ser el modelo del sistema. El modelo es el encargado de almacenar los datos y mantenerlos actualizados para 
	 * que cuando el usuario haga una petición al controlador este pueda acceder al modelo y recuperar la información pedida por el usuario y 
	 * mostrarla en pantalla.
	 */
	private ISistemaCitasEmbajada sistemaCitas;
	/**
	 * El Manejo de archivos hace parte del paquete de Persistencia el cual permite al controlador acceder a los diferentes archivos que puede
	 * facilitar la entrada y salida de datos, principalmente para guardar o recuperar información importante que puede ser de gran uso en el 
	 * transcurso del proceso del sistema.
	 */
	//private ManejoArchivos archivo;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El constructor permite al controlador crear múltiples relaciones e instancias para los diferentes paquetes que son parte del sistema.
	 * Se crea una instancia al modelo, la vista y el manejo de archivos. También llama al método del menú para que el usuario ingrese sus opciones
	 * hasta que desee salir del sistema.
	 */
	public ControladorCitasEmbajada(){
		sistemaCitas = new SistemaCitasEmbajada();
		vista = new InterfazConsola();
		new ManejoArchivos();
		int opcion;
		do{
			opcion = vista.menuPrincipal();
			seleccionarOpcion(opcion);
		}while(opcion != 15);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MAIN
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El main crea una instancia al controlador del sistema de citas.
	 * @param args
	 */
	public static void main(String[] args) {
		new ControladorCitasEmbajada();
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El método es llamado por el constructor del controlador para poder generar los llamados a los diferentes métodos que existen en el sistema
	 * para satisfacer las peticiones del usuario.
	 * @param opción 
	 * Es la opción digitada por el usuario cuando se les muestra el menú principal
	 */
	public void seleccionarOpcion(int opcion){
		switch(opcion){
			case 0: asignarPaisEmbajada();
					break;
			case 1: leerSoilicitantes();
					break;
			case 2: agregarSolicitante();
					break;
			case 3: mostrarSolicitantes();
					break;
			case 4: realizarSolicitudVisaTurismo();
					break;
			case 5: realizarSolicitudVisaDiferente();
				 	break;
			case 6: verTodasSolicitudes();
					break;		
			case 7: calcularValorVisa();
					break;
			case 8: generarReporteCitas();
				 	break;
			case 9: consultarRequisitosVisa();
					break;
			case 10:generarListaBeneficiados();
					break;
			case 11:leerSerializacion();
					break;
			case 12:darPorcentajeGeneros();
					break;
			case 13:darRangoEdades();
					break;
			case 14:darPorcentajeEdad();
					break;		
			case 15: vista.mostrarMensaje("Gracias por hacer uso de nuestro sistema de embajadas. Vuelva Pronto");
					 escribirSerializacion();
					break;
			default: break;		
		}
	}

	/**
	 * Se muestra cada solicitud que existe en el sistema con el nombre y número de pasaporte de cada solicitante.
	 */
	public void verTodasSolicitudes() {
		int i = 1;
		int j = 1;
		List<Solicitud> solicitudesGenerales = sistemaCitas.darSolicitudesGenerales();
		for(Solicitud solicitudEspecifica: solicitudesGenerales){
			vista.mostrarMensaje("---Solicitud #:" + i);
			List<Usuario> usuarios = solicitudEspecifica.darSolicitantes();
			for(Usuario us: usuarios){
				vista.mostrarMensaje("Nombre Usuario # " + j + ": " + us.getNombre());
				vista.mostrarMensaje("Pasaporte Usuario # " + j + ": " + us.getNumPasaporte());
				j++;
			}
			if(solicitudEspecifica.getVisa() instanceof Turismo){
				vista.mostrarMensaje("Tipo de Visa solicitada: " + solicitudEspecifica.getVisa().getClass().getSimpleName());
				Turismo visaTurismo = (Turismo) solicitudEspecifica.getVisa();
				vista.mostrarMensaje("Dias de estadia: " + visaTurismo.getDiasEstadia());
			}else if(solicitudEspecifica.getVisa() instanceof Estudiante){
				vista.mostrarMensaje("Tipo de Visa solicitada: " + solicitudEspecifica.getVisa().getClass().getSimpleName());
				Estudiante visaEstudiante = (Estudiante) solicitudEspecifica.getVisa();
				vista.mostrarMensaje("Escolaridad: " + visaEstudiante.getEscolaridad());
				vista.mostrarMensaje("Institución: " + visaEstudiante.getInstitucion());
			}else{
				vista.mostrarMensaje("Tipo de Visa solicitada: " + solicitudEspecifica.getVisa().getClass().getSimpleName());
				Trabajo visaTrabajo = (Trabajo) solicitudEspecifica.getVisa();
				vista.mostrarMensaje("Empresa: " + visaTrabajo.getEmpresa());
				vista.mostrarMensaje("Cargo: " + visaTrabajo.getCargo());
			}
			vista.mostrarMensaje("Fecha de cita: " + solicitudEspecifica.getFecha());
			vista.mostrarMensaje("Codigo de Indentificación cita: " + solicitudEspecifica.getCodigo());
			i++;
			vista.mostrarMensaje("");
			j = 1;
		}
		vista.mostrarMensaje("Se han hecho un total de " + Solicitud.getCONSECUTIVO() + " solicitudes");
		vista.mostrarMensaje("");
	}
	
	/**
	 * El método pide al usuario que ingrese un tipo de visa en específico para que el sistema le muestre en pantalla los requisitos que se
	 * necesitan para poder pedir dicha visa.
	 */
	public void consultarRequisitosVisa(){
		int i = 1;
		vista.mostrarMensaje("---Lista de Requisitos para Visa");
		vista.mostrarMensaje("---Tipos de Visa");
		vista.mostrarMensaje("--------------------------------");
		vista.mostrarMensaje("Turismo");
		vista.mostrarMensaje("Estudiante");
		vista.mostrarMensaje("Trabajo");
		String visaEscogida = vista.leerString("---Por favor indicar el tipo de visa del que desea conocer los requisitos");
		List<String> requisitos = ingresarRequisitosVisa(visaEscogida);
		vista.mostrarMensaje("---Lista de Requisitos para la visa de tipo: " + visaEscogida);
		vista.mostrarMensaje("------------------------------------------------------");
		for(String req: requisitos){
			vista.mostrarMensaje("Requisito #" + i + ": " + req);
			i++;
		}
		vista.mostrarMensaje("");
	} 
	
	/**
	 * Genera un reporte en un archivo externo de las citas existentes en una determinada fecha. Llama al método de escritura en el paquete de 
	 * persistencia.
	 */
	public void generarReporteCitas(){
		vista.mostrarMensaje("---Digite la fecha a consultar");
		int anio = vista.leerInt("Año: ");
		int mes = vista.leerInt("Mes: ");
		int dia = vista.leerInt("Dia: "); 
		LocalDate fecha = LocalDate.of(anio, mes, dia);
		try {
			ManejoArchivos.generarReporte(sistemaCitas, "Reporte.txt", fecha);
			vista.mostrarMensaje("---El reporte se generó exitosamente");
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	/**
	 * Genera un reporte en un archivo externo de la lista de beneficiados. Llama al método de escritura en el paquete de persistencia
	 */
	public void generarListaBeneficiados(){
		try{
			ManejoArchivos.generarListaBeneficiados(sistemaCitas, "Beneficiados.txt");
			vista.mostrarMensaje("---El reporte se generó exitosamente");
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	/**
	 * Imprime los datos del usuario que viaja solo y el costo de la solicitud.
	 * @param buscado
	 * @param sol
	 */
	public void imprimirDatosCostosUsuario(Usuario buscado, Solicitud sol){
		int valorVisa;
		int valorImpuesto;
		int valorTotal;
		vista.mostrarMensaje("**Numero de pasaporte/codigo valido");
		vista.mostrarMensaje("---Datos del Usuario");
		vista.mostrarMensaje("");
		vista.mostrarMensaje("#numPass-----------Nombre----------------paisOrigen------------ciudadOrigen-------------fechaNac-----------eMail");
		vista.mostrarMensaje(buscado.getNumPasaporte() + "         " + buscado.getNombre() + "         " + buscado.getPaisNacimiento() 
							+ "         " + buscado.getCiudadNacimiento() + "         " + buscado.getFechaNacimiento() + "         " + buscado.getEmail());
		vista.mostrarMensaje("");
		vista.mostrarMensaje("");
		valorVisa = (int) buscado.calcularValorVisa() * 2550;
		valorImpuesto = (int) (valorVisa * sistemaCitas.getImpuesto());
		valorTotal = valorVisa + valorImpuesto;
		vista.mostrarMensaje("#numPass---------Nombre--------------fechaNac------------ValorVisa-------------Impuesto-----------ValorTotal");
		vista.mostrarMensaje(buscado.getNumPasaporte() + "         " + buscado.getNombre() + "         " + buscado.getFechaNacimiento() 
							 + "         " + valorVisa + "         " + valorImpuesto + "         " + valorTotal);
		vista.mostrarMensaje("");
		vista.mostrarMensaje("---El valor total de la visa es:");
		vista.mostrarMensaje("");
		darTasaCambio(valorTotal, sistemaCitas);
		vista.mostrarMensaje("");
	}
	
	/**
	 * Imprime los datos del usuario y de sus acompañantes y el costo de su solicitud
	 * @param usuarios
	 * @param principal
	 * @param sol
	 */
	public void imprimirDatosCostosUsuarios(List<Usuario> usuarios, Usuario principal, Solicitud sol){
		int valorVisa;
		int valorImpuesto;
		int valorTotal;
		int valorFinal = 0;
		vista.mostrarMensaje("**Numero de pasaporte/codigo valido");
		vista.mostrarMensaje("---Datos del Usuario");
		vista.mostrarMensaje("");
		vista.mostrarMensaje("#numPass---------Nombre--------------paisOrigen----------ciudadOrigen-----------fechaNac---------eMail");
		vista.mostrarMensaje(principal.getNumPasaporte() + "         " + principal.getNombre() + "         " + principal.getPaisNacimiento() 
							+ "         " + principal.getCiudadNacimiento() + "         " + principal.getFechaNacimiento() + "         " 
							+ principal.getEmail());
		vista.mostrarMensaje("");
		vista.mostrarMensaje("");
		vista.mostrarMensaje("---Solicitantes asociados");
		vista.mostrarMensaje("#numPass---------Nombre--------------fechaNac----------ValorVisa-----------Impuesto---------ValorTotal");
		vista.mostrarMensaje("");
		for(Usuario us: usuarios){
			valorVisa = (int) us.calcularValorVisa() * 2550;
			valorImpuesto = (int) (valorVisa * sistemaCitas.getImpuesto());
			valorTotal = valorVisa + valorImpuesto;
			vista.mostrarMensaje(us.getNumPasaporte() + "         " + us.getNombre() + "         " + us.getFechaNacimiento() 
								 + "         " + valorVisa + "         " + valorImpuesto + "         " + valorTotal);
			valorFinal += valorTotal;
		}
		vista.mostrarMensaje("");
		vista.mostrarMensaje("---El valor total de la visa es:");
		vista.mostrarMensaje("");
		darTasaCambio(valorFinal, sistemaCitas);	
		vista.mostrarMensaje("");
	}
	
	/**
	 * Calcula el cambio de tasa del valor final del costo de la visa (COP) a la moneda manejada por el país de la embajada
	 * @param valorVisa
	 * @param modelo
	 */
	public void darTasaCambio(int valorVisa, ISistemaCitasEmbajada modelo){
		int valorConvertido = 0;
		
		valorConvertido = (int) (valorVisa / modelo.getCambio());
			
		vista.mostrarMensaje("COP--------------Tasa de Cambio-----------------" + modelo.getMoneda());
		vista.mostrarMensaje(valorVisa + "          " + modelo.getCambio() + "          " + valorConvertido);
	}
	
	/**
	 * Se recibe el número de pasaporte o el número de solicitud de acuerdo a el tipo de visa que se pidió y a la edad del solicitante
	 */
	public void calcularValorVisa(){
		vista.mostrarMensaje("---Calcular valor visa para embajada de " + sistemaCitas.getPaisEmbajada());
		String opcion = vista.leerString("---Favor indicar CP si consulta por codigo o NP si consulta por numero de pasaporte");
		int cont = 0;
		
		if(opcion.equalsIgnoreCase("NP")){
			String numP = vista.leerString("Digite su numero de pasaporte");
			Usuario buscado = sistemaCitas.buscarUsuario(numP);
			if(buscado != null){
				List<Solicitud> solicitudesGenerales = sistemaCitas.darSolicitudesGenerales();
				for(Solicitud sol: solicitudesGenerales){
					List<Usuario> usuariosSolicitud = sol.darSolicitantes();
					for(Usuario us: usuariosSolicitud){
						if(us.getNumPasaporte().equalsIgnoreCase(numP)){
							cont++;
							if(usuariosSolicitud.size() > 1){
								imprimirDatosCostosUsuarios(usuariosSolicitud, buscado, sol);
								break;
							}else{
								imprimirDatosCostosUsuario(buscado, sol);
								break;
							}
						}
					}
				}
				if(cont == 0){
					vista.mostrarMensaje("---El usuario con ese numero de pasaporte no tiene ninguna solicitud pendiente");
					vista.mostrarMensaje("");
				}
			}
		}else if(opcion.equalsIgnoreCase("CP")){
				int numCodigo = vista.leerInt("Digite su codigo de solicitud");
				List<Solicitud> solicitudesGenerales = sistemaCitas.darSolicitudesGenerales(); 
				for(Solicitud sol: solicitudesGenerales){
					if(numCodigo == sol.getCodigo()){
						cont++;
						Usuario principal = sol.darSolicitantes().get(0);
						if(sol.darSolicitantes().size() > 1){
							imprimirDatosCostosUsuarios(sol.darSolicitantes(), principal, sol);
							break;
						}else{
							imprimirDatosCostosUsuario(principal, sol);
							break;
						}
					}
				}
				if(cont == 0){
					vista.mostrarMensaje("---El usuario con ese codigo no tiene ninguna solicitud pendiente");
					vista.mostrarMensaje("");
				}
			}
	}
	
	/**
	 * Se recibe como parámetro el número de pasaporte de un solicitante, se busca la visa a la que está solicitando y se pregunta si viaja
	 * solo o con más personas. Si es la segunda, se recibe como parámetro un archivo de texto para agregar a los demás registrados a la solicitud.
	 * Finalmente se les asigna una fecha para poder presentarse a la embajada y realizar los trámites.
	 */
	public void realizarSolicitudVisaTurismo(){
		vista.mostrarMensaje("---Solicitud Visa Turismo");
		vista.mostrarMensaje("-------------------------");
		String numPasaporte = vista.leerString("---Digite su numero de pasaporte");
		Usuario buscado = sistemaCitas.buscarUsuario(numPasaporte);
		if(buscado != null){
			vista.mostrarMensaje("Usuario: " + buscado.getNombre());
			vista.mostrarMensaje("-------------------------------");
			vista.mostrarMensaje("Usted solicito una visa de tipo: Turismo");
			int diasEstadia = vista.leerInt("Digite los días de estadía");
			double valor = leerDatosVisa("Turismo");
			vista.mostrarMensaje("El usuario va a viajar solo o con acampañantes?");
			vista.mostrarMensaje("1. Viaje Solo");
			vista.mostrarMensaje("2. Viaje Acompañado");
			int opcion = vista.leerInt("Digite una opción");
			if(buscado.getSolicitud() == null){
				if(opcion == 1){
					try{
						List<String> requisitos = ingresarRequisitosVisa("Turismo");
						Visa visaPedida = sistemaCitas.crearVisaTurismo(diasEstadia, valor, requisitos);
						Solicitud sol = sistemaCitas.solicitarSolicitud(buscado, visaPedida);
						vista.mostrarMensaje("Su solicitud a sido guardada con exito");
						vista.mostrarMensaje("Su codigo de solicitud es el numero: " + sol.getCodigo());
						vista.mostrarMensaje("Deberá presentarse en la embajada para solicitar su visa en la fecha " + sol.getFecha().toString());
						vista.mostrarMensaje("");
					}catch(Exception e){
						e.getMessage();
					}
				}
				if(opcion == 2){
					try{
						List<String> requisitos = ingresarRequisitosVisa("Turismo"); 
						Visa visaBuscada = sistemaCitas.crearVisaTurismo(diasEstadia, valor, requisitos);
						String archivoAcompanantes = vista.leerString("---Por favor digite el archivo con la información de los acompañantes");
						Solicitud sol = sistemaCitas.solicitarSolicitud(buscado, visaBuscada);
						vista.mostrarMensaje("---Su solicitud a sido guardada con exito");
						vista.mostrarMensaje("Su codigo de solicitud es el numero: " + sol.getCodigo());
						vista.mostrarMensaje("");
						try{
							List<Usuario> usuariosAd = ManejoArchivos.leerAcompanantes(sistemaCitas, archivoAcompanantes);
							for(Usuario us: usuariosAd){
								sol.agregarUsuarioSolicitud(us);
								vista.mostrarMensaje("---El usuario " + us.getNombre() + " con el numero de pasaporte " + us.getNumPasaporte() 
													 + " a sido guardado en la solicitud con exito");
								vista.mostrarMensaje("El codigo de solicitud es el numero: " + sol.getCodigo());
								vista.mostrarMensaje("Todos los solicitantes deberan presentarse en la embajada para solicitar su visa "
										+ "en la fecha " + sol.getFecha().toString());
								vista.mostrarMensaje("");
							}
						}catch(Exception e){
							e.getMessage();
						}
					}catch(Exception e){
						e.getMessage(); 
					}				 
				}	
			}else{
				vista.mostrarMensaje("El usuario ya tiene una solicitud pendiente. No puede solicitar una nueva hasta terminar el tramite anterior");
			}
		}else{
			vista.mostrarMensaje("---El numero de pasaporte no esta registrado en nuestro sistema. Por favor intente nuevamente");
		}	
	}
	
	/**
	 * Se recibe como parámetro el número de pasaporte de un solicitante, se busca la visa a la que está solicitando. Se le asigna una fecha 
	 * para poder presentarse a la embajada y realizar los trámites.
	 */
	public void realizarSolicitudVisaDiferente(){
		vista.mostrarMensaje("---Solicitud de Visas");
		vista.mostrarMensaje("---------------------");
		String numPasaporte = vista.leerString("---Digite su numero de pasaporte");
		Usuario buscado = sistemaCitas.buscarUsuario(numPasaporte);
		if(buscado != null){
			if(buscado.getSolicitud() == null){
				vista.mostrarMensaje("Usuario: " + buscado.getNombre());
				String tipoVisa = vista.leerString("---Digite el tipo de visa que quiere solicitar");
				Visa visaPedida = null;
				if(tipoVisa.equalsIgnoreCase("Estudiante")){
					double valor = leerDatosVisa(tipoVisa);
					String escol = vista.leerString("Digite su escolaridad");
					for(Escolaridad es: Escolaridad.values()){
						if(es.toString().equalsIgnoreCase(escol)){
							String institucion = vista.leerString("Digite su institución asignada");
							List<String> requisitos = ingresarRequisitosVisa(tipoVisa);
							visaPedida = sistemaCitas.crearVisaEstudiante(es, institucion, valor, requisitos);
						}
					}
				}else if(tipoVisa.equalsIgnoreCase("Trabajo")){
					double valor = leerDatosVisa(tipoVisa);
					String empresa = vista.leerString("Digite su empresa asignada");
					String cargo = vista.leerString("Digite su cargo");
					List<String> requisitos = ingresarRequisitosVisa(tipoVisa);
					visaPedida = sistemaCitas.crearVisaTrabajo(empresa, cargo, valor, requisitos);
				}else{
					vista.mostrarMensaje("La visa digitada no existe o es de turismo. Si es de turismo digite la opción de Solicitar visa Turismo");
				}
				Solicitud sol = sistemaCitas.solicitarSolicitud(buscado, visaPedida); 
				vista.mostrarMensaje("Su solicitud a sido guardada con exito");
				vista.mostrarMensaje("Su codigo de solicitud es el numero: " + sol.getCodigo());
				vista.mostrarMensaje("Deberá presentarse en la embajada para solicitar su visa en la fecha " + sol.getFecha().toString());
				vista.mostrarMensaje("");
			}else{
				vista.mostrarMensaje("El usuario ya tiene una solicitud pendiente. No puede solicitar una nueva hasta terminar el tramite anterior");
			}
		}else{
			vista.mostrarMensaje("---El numero de pasaporte no esta registrado en nuestro sistema. Por favor intente nuevamente");
			vista.mostrarMensaje("");
		}
	}
	
	/**
	 * Este método pide un archivo de texto con la información de todos los tipos de visa. Luego llama al paquete de persistencia para poder instanciar nuevos objetos de
	 * tipo Visa y a cada uno de esos objetos instanciar objetos de tipo Requisito.
	 */
	public List<String> ingresarRequisitosVisa(String nombreVisa) {
		String archivoVisas = vista.leerString("---Digite la ruta del archivo de texto con la información de las visas");
		try{
			List<String> requisitos = ManejoArchivos.leerVisas(sistemaCitas, archivoVisas, nombreVisa);
			vista.mostrarMensaje("---Los requerimientos fueron leidos exitosamente");
			vista.mostrarMensaje("");
			return requisitos;
		}catch(Exception e){
			vista.mostrarMensaje(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Este método pide un archivo de texto con la información de todos los solicitantes. Luego llama al paquete de persistencia para poder instanciar los objetos de tipo
	 * Usuario.
	 */
	public void leerSoilicitantes() {
		String archivoSolicitantes = vista.leerString("---Digite la ruta del archivo de texto con la información de los solicitantes");
		try {
			int usuariosGuardados = ManejoArchivos.leerUsuarios(sistemaCitas, archivoSolicitantes);
			vista.mostrarMensaje("---Se registraron un total de " + usuariosGuardados + " usuarios exitosamente");
			vista.mostrarMensaje("");
		} catch (Exception e) {
			vista.mostrarMensaje(e.getMessage());
		}
	}
	
	/**
	 * Luego de agregar los solicitantes iniciales desde el archivo, el sistema puede agregar nuevos solicitantes individualmente conforme pasa 
	 * el tiempo
	 */
	public void agregarSolicitante(){
		String nombre = vista.leerString("---Digite su nombre");
		String numPasaporte = vista.leerString("---Digite su numero de pasaporte");
		String email = vista.leerString("---Digite su correo electronico");
		int anioNacimiento = vista.leerInt("---Digite su año de nacimiento");
		int mesNacimiento = vista.leerInt("---Digite su mes de nacimiento");
		int diaNacimiento = vista.leerInt("---Digite su dia de nacimiento");
		LocalDate fechaNacimiento = LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento);
		String paisNacimiento = vista.leerString("---Digite su pais de nacimiento");
		String ciudadNacimiento = vista.leerString("---Digite su ciudad de nacimiento");
		String infoAdicional = null;
		if(LocalDate.now().compareTo(fechaNacimiento) <= 2){
			infoAdicional = vista.leerString("---Digite su acudiente");
		}
		if ((LocalDate.now().compareTo(fechaNacimiento) <= 12) && (LocalDate.now().compareTo(fechaNacimiento) > 2)){
			infoAdicional = vista.leerString("---Digite su escolaridad");
		}
		Usuario buscado = sistemaCitas.buscarUsuario(numPasaporte);
		if(buscado == null){
			sistemaCitas.agregarUsuarios(nombre, numPasaporte, email, fechaNacimiento, paisNacimiento, ciudadNacimiento, infoAdicional);
			vista.mostrarMensaje("---El usuario fue registrado exitosamente");
			vista.mostrarMensaje("");
		}else{
			vista.mostrarMensaje("---El usuario con el numero de pasaporte " + numPasaporte + " ya se encuentra registrado. No pueden haber dos usuarios "
					+ "con el mismo número de pasaporte");
			vista.mostrarMensaje("");
		}	
	}
	
	/**
	 * Muestra en pantalla la información de todos los usuarios que se encuentran registrados en el sistema
	 */
	public void mostrarSolicitantes(){
		Map<String, Usuario> usuario = sistemaCitas.darUsuarios();
		int i = 1;
		vista.mostrarMensaje("---Lista de Usuarios registrados");
		vista.mostrarMensaje("--------------------------------");
		vista.mostrarMensaje("");
		for(Usuario us: usuario.values()){
			vista.mostrarMensaje("Usuario #" + i + " :");
			vista.mostrarMensaje("Nombre: " + us.getNombre());
			vista.mostrarMensaje("Numero de Pasaporte: " + us.getNumPasaporte());
			vista.mostrarMensaje("E-Mail: " + us.getEmail());
			vista.mostrarMensaje("Edad: " + us.getEdad());
			vista.mostrarMensaje("Pais de Nacimiento: " + us.getPaisNacimiento());
			vista.mostrarMensaje("Ciudad de Nacimiento: " + us.getCiudadNacimiento());
			if(us instanceof NiñoMenor){
				NiñoMenor niñoMenor = (NiñoMenor) us;
				vista.mostrarMensaje("Acudiente: " + niñoMenor.getAcudiente());
			}
			if(us instanceof NiñoMayor){
				NiñoMayor niñoMayor = (NiñoMayor) us;
				vista.mostrarMensaje("Escolaridad: " + niñoMayor.getEscolaridad());
			}
			vista.mostrarMensaje("");
			i++;
		}
	}
	
	/**
	 * Este método pide un país para poder asignar la embajada a este. Luego llama al paquete de persistencia para poder comparar esa entrada
	 * con los datos que se tienen en el archivo respectivo.
	 */
	public void asignarPaisEmbajada() {
		String asociarP = vista.leerString("---Digite el pais para asociar la embajada");
		try {
			List<String> paisAsignado = ManejoArchivos.leerPaises(sistemaCitas, "PaisesEmbajada.txt", asociarP);
			boolean creado = sistemaCitas.asignarPais(paisAsignado);
			if(creado){
				vista.mostrarMensaje("---La embajada de " + asociarP + " fue creada exitosamente");
				vista.mostrarMensaje("");
			}else{
				vista.mostrarMensaje("---La embajada no pudo ser creada ya que el país buscado no existe. Intente nuevamente");
				vista.mostrarMensaje("");
			}

		} catch (Exception e) {
			vista.mostrarMensaje(e.getMessage());
		}
	}
	
	/**
	 * Llama al método que se encuentra en el paquete de Persistencia para que lea un archivo de texto que contiene la información de los valores de las
	 * visas que se encuentran en el sistema. Retorna ese valor para que pueda ser usado en las solicitudes
	 * @return
	 */
	public double leerDatosVisa(String tipoVisa){
		try{
			double valorVisa = ManejoArchivos.leerDatosVisa(sistemaCitas, "Tarifas.txt", tipoVisa);
			return valorVisa;
		}catch(Exception e){
			vista.mostrarMensaje(e.getMessage());
		}
		return 0;
	}
	
	/**
	 * Llama al método de serialización del paquete de persistencia para crear un archivo binario con la información de todo el modelo del sistema de
	 * la embajada
	 */
	public void escribirSerializacion(){
		String ruta = vista.leerString("Digite la ruta para serializar el modelo del Sistema de citas");
		try{
			ManejoArchivos.serializarModelo(sistemaCitas, ruta);
			vista.mostrarMensaje("Se guardo el modelo exitosamente");
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	/**
	 * Lee el archivo con la serialización del modelo guardado y la retorna para que otros métodos puedan utilizarla
	 * @return
	 */
	public SistemaCitasEmbajada leerSerializacion(){
		String ruta = vista.leerString("Digite la ruta para leer la serialización del modelo del Sistema de citas guardada anteriormente");
		try{
			SistemaCitasEmbajada modeloLeido = ManejoArchivos.leerSerializacion(sistemaCitas, ruta);
			vista.mostrarMensaje("El archivo fue leido exitosamente y se pudieron recuperar los datos del modelo");
			return modeloLeido;
		}catch(Exception e){
			e.getMessage();
		}
		return null;
	}
	//----------------------------------------------------------SUSTENTACIÓN---------------------------------------------------------------------------------
	/**
	 * Llama al paquete de persistencia para crear un archivo de texto que incluya los porcentajes de usuarios que son mayores y menores de edad
	 */
	public void darPorcentajeEdad(){
		try{
			ManejoArchivos.generarReporteEdades(sistemaCitas, "ReporteEdades.txt");
			vista.mostrarMensaje("El archivo con los porcentajes de mayores y menores de edad fue creado exitosamente");
			vista.mostrarMensaje("");
		}catch(Exception e){
			vista.mostrarMensaje("El arhivo no se pudo crear. Intente Nuevamente");
		}
	}
	/**
	 * Llama al paquete de persistencia para crear un archivo de texto que incluya la cantidad de usuarios que se encuentran en un determinado rango de edades
	 */
	public void darRangoEdades(){
		try{
			ManejoArchivos.generarReporteRangos(sistemaCitas, "ReporteRangos.txt");
			vista.mostrarMensaje("El archivo con la cantidad de usuarios en rangos de edades fue creado exitosamente");
			vista.mostrarMensaje(""); 
		}catch(Exception e){
			vista.mostrarMensaje("El archivo no se pudo crear. Intente Nuevamente");
		} 
	}
	/**
	 * Llama al paquete de persistencia para crear un archivo de texto que incluya los porcentajes de usuarios dependiendo del género que sean
	 */
	public void darPorcentajeGeneros(){
		try{
			ManejoArchivos.generarReporteGeneros(sistemaCitas, "ReporteGeneros.txt");
			vista.mostrarMensaje("El archivo con los porcentajes de usuarios por genero fue creado exitosamente");
			vista.mostrarMensaje("");
		}catch(Exception e){
			vista.mostrarMensaje("El archivo no se pudo crear. Intente Nuevamente");
		}
	}
}