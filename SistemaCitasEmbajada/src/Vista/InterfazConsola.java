package Vista;

import java.util.Scanner;


/**
 * Única encargada de leer datos del usuario y de imprimir resultados ya sea de entrada o de salida. 
 * Sus métodos deberán ser llamados y coordinados por la clase "ControladorCitasEmbajada".
 */
public class InterfazConsola {
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El atributo sc es el encargado de leer las opciones digitadas por el usuario.
	 */
	private Scanner sc;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El constructor de la vista crea al atributo sc para que pueda leer y almacenar todo lo que el usuario digite (Números o cadenas de caracteres)
	 */
	public InterfazConsola(){
		sc = new Scanner(System.in);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El método permite al controlador generar mensajes para el usuario. Pueden ser mensajes de avisos o mensajes de error.
	 * @param enunciado
	 * Mensaje que se le va a mostrar al usuario como consecuencia de una acción hecha anteriormente por este.
	 */
	public void mostrarMensaje(String enunciado){
		System.out.println(enunciado);
	}
	/**
	 * El método permite al usuario ingresar datos de tipo String.
	 * @param enunciado
	 * Mensaje que se le va a mostrar al usuario para que pueda digitar una opción deseada.
	 * @return
	 * variable de tipo String
	 */
	public String leerString(String enunciado){
		System.out.print(enunciado + " ");
		String respuesta = sc.nextLine();
		respuesta = sc.nextLine();
		return respuesta;
	}
	/**
	 * El método permite al usuario ingresar datos de tipo Entero.
	 * @param enunciado
	 * Mensaje que se le va a mostrar al usuario para que pueda digitar una opción deseada.
	 * @return
	 * variable de tipo Int
	 */
	public int leerInt(String enunciado){
		int respuesta;
		System.out.print(enunciado + " ");
		respuesta = sc.nextInt();
		return respuesta;
	}
	/**
	 * El método permite al usuario ingresar datos de tipo Double.
	 * @param enunciado
	 * Mensaje que se le va a mostrar al usuario para que pueda digitar una opción deseada.
	 * @return
	 * variable de tipo Double
	 */
	public double leerDouble(String enunciado){
		double respuesta;
		System.out.print(enunciado + " ");
		respuesta = sc.nextDouble();
		return respuesta;
	}
	/**
	 * El método permite al usuario ingresar datos de tipo Float.
	 * @param enunciado
	 * Mensaje que se le va a mostrar al usuario para que pueda digitar una opción deseada.
	 * @return
	 * variable de tipo Float
	 */
	public float leerFloat(String enunciado){
		float respuesta;
		System.out.print(enunciado + " ");
		respuesta = sc.nextFloat();
		return respuesta;
	}
	/**
	 * El método es el menú que se le muestra en pantalla al usuario al ingresar al sistema. Contiene todas las opciones posibles que el usuario
	 * puede digitar o puede consultar.
	 * @return
	 * La opción de tipo Int digitada por el usuario 
	 */
	public int menuPrincipal(){
		System.out.println("Sistema de Citas Embajada");
		System.out.println("--------------------------------------------------------");
		System.out.println("Menu Principal");
		System.out.println("--------------------------------------------------------");
		System.out.println("0. Asociar pais a Embajada");
		System.out.println("1. Ingresar solicitantes");
		System.out.println("2. Agregar un solicitante nuevo");
		System.out.println("3. Mostrar lista de solicitantes");
		System.out.println("4. Hacer solicitud de cita para visa de turismo");
		System.out.println("5. Hacer solicitud de cita para visa diferente a turismo");
		System.out.println("6. Ver solicitudes");
		System.out.println("7. Calcular valor de visa");
		System.out.println("8. Reporte de citas para una determinada fecha");
		System.out.println("9. Consultar la lista de requisitos de un tipo de Visa");
		System.out.println("10. Lista de Beneficiados");
		System.out.println("11. Leer modelo");
		System.out.println("SUSTENTACIÓN 1------------");
		System.out.println("12. Dar Porcentaje por Género");
		System.out.println("13. Dar rango de edades");
		System.out.println("14. Dar porcentaje de mayores y menores de edad");
		System.out.println("SUSTENTACIÓN 2------------");
		System.out.println("15. Salir");
		return leerInt("Digite una opción");
	}
}