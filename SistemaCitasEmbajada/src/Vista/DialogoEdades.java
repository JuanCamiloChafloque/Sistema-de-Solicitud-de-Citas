package Vista;

import javax.swing.JDialog;

import Modelo.Usuario;

public class DialogoEdades extends JDialog {

	private static final long serialVersionUID = 1L;
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Relación con la interfaz grafica del sistema
	 */
	private InterfazGrafica interfaz;
	/**
	 * El dialogo tiene una relación con un panel que permite al usuario ver las estadisticas de los usuarios registrados
	 */
	private PanelEdades panelEdades;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se instancia un dialogo de tipo DialogoEdades que muestra un panel con varias estadisticas y datos de los usuarios registrados en
	 * el sistema
	 */
	public DialogoEdades(InterfazGrafica pInterfaz){
		
		interfaz = pInterfaz; 
		
		setSize(550, 450);
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		
		panelEdades = new PanelEdades(this);
		add(panelEdades);
		
        setModal(true);
        setResizable(false);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt0_11(String cantidad){
		panelEdades.actualizarTxt0_11(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt12_20(String cantidad){
		panelEdades.actualizarTxt12_20(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt21_30(String cantidad){
		panelEdades.actualizarTxt21_30(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt31_40(String cantidad){
		panelEdades.actualizarTxt31_40(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt41_50(String cantidad){
		panelEdades.actualizarTxt41_50(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt51_60(String cantidad){
		panelEdades.actualizarTxt51_60(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt60(String cantidad){
		panelEdades.actualizarTxt60(cantidad);
	}
	/**
	 * Retorna la cantidad de usuarios que son mayores de edad
	 */
	public int darCantidadMayores(){
		
		return interfaz.darCantidadMayores();
	}
	/**
	 * Retorna el usuario con mayor edad
	 */
	public Usuario darUsuarioMayor(){
		
		return interfaz.darUsuarioMayor();
	}
	/**
	 * Retorna el usuario que mas paga por una visa
	 */
	public Usuario darUsuarioMasPaga(){
		
		return interfaz.darUsuarioMasPaga();
	}
}
