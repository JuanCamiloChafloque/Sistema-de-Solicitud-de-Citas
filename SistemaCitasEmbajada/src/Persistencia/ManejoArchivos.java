package Persistencia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Modelo.Estudiante;
import Modelo.ISistemaCitasEmbajada;
import Modelo.SistemaCitasEmbajada;
import Modelo.Solicitud;
import Modelo.Turismo;
import Modelo.Usuario;

/**
 * Contiene los métodos para leer archivos de datos.
 */
public class ManejoArchivos {
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Variable para poder leer archivos de texto;
	 */
	private static BufferedReader br;
	/**
	 * Variable para poder generar y escribir archivos de texto;
	 */
	private static PrintWriter pw;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea una instancia de la clase de ManejoArchivos que es creada por el controlador.
	 */
	public ManejoArchivos(){
		
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se lee el archivo PaisesEmbajada.txt para poder generar una nueva instancia del sistema de citas dependiendo del país que se quiera
	 * @param modelo
	 * @param ruta
	 * @param asociarP
	 * @throws Exception 
	 * Lanza una excepción si hay problemas al abrir el archivo
	 */
	public static List<String> leerPaises(ISistemaCitasEmbajada modelo, String ruta, String asociarP) throws Exception{
		String []datos = new String[5];
		List<String> paisAsignar = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		String linea = br.readLine();
		linea = br.readLine();
		linea = br.readLine();
		int cont = 0;
		
		while(!linea.equalsIgnoreCase("#FIN")){
			datos = linea.split("\\*");
			String id = datos[0].trim();
			String pais = datos[1].trim();
			String moneda = datos[2].trim();
			String impuesto = datos[3].trim();
			String tasaCambio = datos[4].trim();
			if(pais.equalsIgnoreCase(asociarP)){				
				paisAsignar.add(id);
				paisAsignar.add(pais);
				paisAsignar.add(moneda);
				paisAsignar.add(impuesto);
				paisAsignar.add(tasaCambio);
				cont++;
				break;
			}
			else{
				linea = br.readLine();
			}
		}
		br.close();
		if(cont == 0){
			throw new Exception("El pais al que se quiere asociar la embajada no existe en el sistema. Intente nuevamente");
		}
		return paisAsignar;
	}
	
	/**
	 * Se lee el archivo que haya digitado el usuario para poder generar nuevos objetos de tipo Usuario y agregarlos a la lista de usuarios que
	 * tiene el sistema
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 * Lanza una excepción si hay problemas al abrir el archivo
	 */
	public static int leerUsuarios(ISistemaCitasEmbajada modelo, String ruta) throws Exception{
		
		String []datosSolicitante = new String[8];
		String []auxFecha = new String[3];
		br = new BufferedReader(new FileReader(ruta));
		String lineaFecha;
		String linea = br.readLine();
		linea = br.readLine();
		linea = br.readLine();
		int cont = 0;
		
		
		while(!linea.equalsIgnoreCase("#FIN")){
			
			datosSolicitante = linea.split("\\*");
			String numPass = datosSolicitante[0].trim();
			String nombre = datosSolicitante[1].trim();
			String paisOrigen = datosSolicitante[2].trim();
			String ciudadNacimiento = datosSolicitante[3].trim();
			lineaFecha = datosSolicitante[4].trim();
			auxFecha = lineaFecha.split("\\-");
			int anio = Integer.parseInt(auxFecha[0].trim());
			int mes = Integer.parseInt(auxFecha[1].trim());
			int dia = Integer.parseInt(auxFecha[2].trim());
			LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
			String email = datosSolicitante[5].trim();
			String infoAdicional = null;
			if(LocalDate.now().compareTo(fechaNacimiento) <= 12){
				infoAdicional = datosSolicitante[6].trim();
			}
			
			Usuario buscado;
			buscado = modelo.buscarUsuario(numPass);
			if(buscado == null){
				modelo.agregarUsuarios(nombre, numPass, email, fechaNacimiento, paisOrigen, ciudadNacimiento, infoAdicional);
				cont++;
			}
			else{
				throw new Exception("El solicitante " + nombre + " no puede ser añadido a la lista de usuarios porque existen dos o mas usuarios"
						+ " con el mismo numero de pasaporte");
			}
			linea = br.readLine();
		}
		br.close();
		return cont;
	}
	
	/**
	 * Se lee el archivo que haya digitado el usuario para poder generar nuevos objetos de tipo Visa y agregarlos a la lista de usuarios que tiene 
	 * el sistema, además se crearan objetos de tipo Requisito para cada una de las visas.
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 */
	public static List<String> leerVisas(ISistemaCitasEmbajada modelo, String ruta, String nombre) throws Exception{

		br = new BufferedReader(new FileReader(ruta));
		String linea = br.readLine();
		List<String> requisitos = new ArrayList<String>();

		while(!linea.equalsIgnoreCase("#FIN")){
			if(linea.equalsIgnoreCase(nombre)){
				linea = br.readLine();
				linea = br.readLine();
				while((!linea.equalsIgnoreCase("#VISA")) && (!linea.equalsIgnoreCase("#FIN"))){
					requisitos.add(linea);
					linea = br.readLine();
				}
			}else{
				linea = br.readLine();
			}
		}
		br.close();
		return requisitos;
	}	
	
	/**
	 * Se lee el archivo que se haya enviado por parámetro y se busca el nombre del tipo de visa que el usuario digitó. Retornando el valor de este.
	 * Si no se encuentra el tipo de visa en el archivo, se le notifica al usuario que no existe el nombre de esa visa. 
	 * @param modelo
	 * @param ruta
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
	public static double leerDatosVisa(ISistemaCitasEmbajada modelo, String ruta, String tipo) throws Exception{
		
		String []datosVisa = new String[3];
		br = new BufferedReader(new FileReader(ruta));
		
		String linea = br.readLine();
		linea = br.readLine();
		while(!linea.equalsIgnoreCase("#FIN")){
			linea = br.readLine();
			datosVisa = linea.split("\\*");
			//int id = Integer.parseInt(datosVisa[0].trim());
			String tipoVisa = datosVisa[1].trim();
			double valorVisa = Double.parseDouble(datosVisa[2].trim());
			if(tipoVisa.equalsIgnoreCase(tipo)){
				return valorVisa;
			}
		}		
		throw new Exception("La visa digitada no se encuentra en nuestro sistema. Por favor intente nuevamente");
	}
	
	/**
	 * Este método genera un archivo de tipo texto con la información de las solicitudes que están pendientes dada una determinada fecha
	 * @param modelo
	 * @param ruta
	 * @param fechaReporte
	 * @throws Exception
	 */
	public static void generarReporte(ISistemaCitasEmbajada modelo, String ruta, LocalDate fechaReporte) throws Exception{
		int i = 1;
		pw = new PrintWriter(new FileWriter(ruta));
		
		pw.println("REPORTE SOLICITUDES EMBAJADA DE " + modelo.getPaisEmbajada());
		pw.println("Fecha: " + fechaReporte);
		pw.println("----------------------------------------");
		List<Solicitud> solicitud = modelo.darSolicitudesGenerales();
		List<Usuario> solicitantes;
		for(Solicitud s: solicitud){
			if(fechaReporte.isEqual(s.getFecha().toLocalDate())){
				solicitantes = s.darSolicitantes();
				for(Usuario us: solicitantes){
					pw.println("Solicitante #" + i);
					pw.println("---------------");
					pw.println("Nombre: " + us.getNombre());
					pw.println("Numero de Pasaporte: " + us.getNumPasaporte());
					if(s.getVisa() instanceof Turismo){
						pw.println("Tipo de visa: Turismo");
					}else if (s.getVisa() instanceof Estudiante){
						pw.println("Tipo de visa: Estudiante");
					}else{
						pw.println("Tipo visa: Trabajo");
					}
					pw.println("Numero de Solicitud: " + s.getCodigo());
					pw.println();
					i++;
				}
			}
			i = 1;
		}
		pw.close();
		if(i == 1){
			throw new Exception("---Para la fecha consultada no se encuentran solicitudes pendientes");
		}
	}
	
	/**
	 * Este método genera un archivo de tipo texto con la información de los usuarios que son beneficiarios
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 */
	public static void generarListaBeneficiados(ISistemaCitasEmbajada modelo, String ruta) throws Exception{
		double totalRecaudado = 0;
		pw = new PrintWriter(new FileWriter(ruta));
		List<Usuario> beneficiados = modelo.generarListaBeneficiados();
		Collections.sort(beneficiados);
		
		pw.println("---Lista de Beneficiarios");
		pw.println("-------------------------");
		pw.println();
		pw.println("#numPass-----------------------Nombre-----------------------valorTotal(Pesos)");
		for(Usuario ben: beneficiados){
			totalRecaudado += ben.calcularValorVisa();
			double valorVisa = ben.calcularValorVisa();
			pw.println("" + ben.getNumPasaporte() + "               " + ben.getNombre() + "               " + (int) valorVisa * 2550);
			pw.println();
		}
		pw.println("El valor total en pesos que se dejo de recaudar por visas de beneficiarios es: " + totalRecaudado * 2550);
		pw.close();
	}
	
	/**
	 * Este método lee un archivo de tipo texto con la información de los acompañantes de un usuario si este no va a viajar solo. Solo aplica para
	 * solicitar visas de tipo Turismo
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 */
	public static List <Usuario> leerAcompanantes(ISistemaCitasEmbajada modelo, String ruta) throws Exception{ 
		List<Usuario> acom = new ArrayList<Usuario>();
		br = new BufferedReader(new FileReader(ruta));
		String linea = br.readLine();
		linea = br.readLine();
		linea = br.readLine();
		while(!linea.equalsIgnoreCase("#FIN")){
			Usuario buscado = modelo.buscarUsuario(linea);
			if(buscado != null){
				acom.add(buscado);
			}else{
				throw new Exception("El usuario con el numero de pasaporte " + linea + " no se encuentra registrado. Por favor registrarlo antes"
						+ "de realizar una solicitud");
			}
			linea = br.readLine();
		}
		br.close();
		return acom;
	}
	
	/**
	 * Serializa el modelo principal del sistema de citas de la embajada
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 */
	public static void serializarModelo(ISistemaCitasEmbajada modelo, String ruta) throws Exception{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
		oos.writeObject(modelo);
		oos.close();
	}
	
	/**
	 * Lee un archivo de tipo binario con la información que guardo del modelo del sistema de citas de la embajada
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 */
	public static SistemaCitasEmbajada leerSerializacion(ISistemaCitasEmbajada modelo, String ruta) throws Exception{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
		SistemaCitasEmbajada objetoLeido = (SistemaCitasEmbajada) ois.readObject();
		ois.close();
		
		return objetoLeido;
	}
	//-------------------------------------------------------------SUSTENTACIÓN-------------------------------------------------------------------------------------
	/**
	 * Método que genera un archivo de texto con los porcentajes de usuarios que son mayores o menores de edad
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 */
	public static void generarReporteEdades(ISistemaCitasEmbajada modelo, String ruta) throws Exception{
		pw = new PrintWriter(new FileWriter(ruta));
		double porcentajeM = modelo.darPorcentajeMayoresEdad();
		pw.println("Reporte de porcentajes de mayores y menores de edad");
		pw.println("---------------------------------------------------");
		pw.println("El porcentaje de usuarios que son mayores de edad es del: " + porcentajeM*100 + "%");
		pw.println("El porcentaje de usuarios que son menores de edad es del: " + (1-porcentajeM) * 100 + "%");
		pw.close();
	}
	/**
	 * Método que genera un archivo de texto con la cantidad de personas que se encuentren en un determinado rango de edades
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 */
	public static void generarReporteRangos(ISistemaCitasEmbajada modelo, String ruta) throws Exception{
		pw = new PrintWriter(new FileWriter(ruta));
		int cantAuxiliar;
		pw.println("Reporte de cantidad de usuarios en un rango de edades");
		pw.println("-----------------------------------------------------");
		cantAuxiliar = modelo.darRangoEdad(0, 11);
		pw.println("La cantidad de usuarios que se encuentran en el rango de edades [0,11] es de: " + cantAuxiliar);
		cantAuxiliar = modelo.darRangoEdad(12, 17);
		pw.println("La cantidad de usuarios que se encuentran en el rango de edades [12,17] es de: " + cantAuxiliar);
		cantAuxiliar = modelo.darRangoEdad(18, 24);
		pw.println("La cantidad de usuarios que se encuentran en el rango de edades [18,24] es de: " + cantAuxiliar);
		cantAuxiliar = modelo.darRangoEdad(25, 30);
		pw.println("La cantidad de usuarios que se encuentran en el rango de edades [25,30] es de: " + cantAuxiliar);
		cantAuxiliar = modelo.darRangoEdad(31, 35);
		pw.println("La cantidad de usuarios que se encuentran en el rango de edades [31,35] es de: " + cantAuxiliar);
		cantAuxiliar = modelo.darRangoEdad(36, 40);
		pw.println("La cantidad de usuarios que se encuentran en el rango de edades [36,40] es de: " + cantAuxiliar);
		cantAuxiliar = modelo.darRangoEdad(41, 49);
		pw.println("La cantidad de usuarios que se encuentran en el rango de edades [41,49] es de: " + cantAuxiliar);
		cantAuxiliar = modelo.darRangoEdad(50, 60);
		pw.println("La cantidad de usuarios que se encuentran en el rango de edades [50,60] es de: " + cantAuxiliar);
		cantAuxiliar = modelo.darRangoEdad(61, 120);
		pw.println("La cantidad de usuarios que son mayores de 60 años es de: " + cantAuxiliar);
		
		pw.close();
	}
	/**
	 * Método que genera un archivo de texto con los porcentajes de usuarios dependiendo del género que sean
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 */
	public static void generarReporteGeneros(ISistemaCitasEmbajada modelo, String ruta) throws Exception{
		pw = new PrintWriter(new FileWriter(ruta));
		pw.println("Reporte de porcentaje de usuarios por género");
		pw.println("--------------------------------------------");
		List<Double> listaGen = modelo.darPorcentajeGenero();
		pw.println("El porcentaje de usuarios que son transexuales es del: " + listaGen.get(0) * 100 + "%");
		pw.println("El porcentaje de usuarios que son hombres es del: " + listaGen.get(1) * 100 + "%");
		pw.println("El porcentaje de usuarios que son mujeres es del: " + listaGen.get(2) * 100 + "%");
		pw.println("El porcentaje de usuarios que son transgénero es del: " + listaGen.get(3) * 100 + "%");
		
		pw.close();
	}
}