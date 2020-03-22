package Vista;

import javax.swing.JDialog;

import Modelo.Solicitud;
import Modelo.Usuario;

public class DialogoSolicitud extends JDialog {

	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Relaci�n con la interfaz grafica del sistema
	 */
	private InterfazGrafica interfaz;
	/**
	 * El dialogo tiene una relaci�n con un panel que permite al usuario ver la informaci�n m�s importante de la solicitud buscada
	 */
	private PanelSolicitud panelSolicitud;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se instancia un dialogo de tipo DialogoSolicitud que muestra un panel con la informaci�n m�s importante de la solicitud buscada
	 */
	public DialogoSolicitud(InterfazGrafica pInterfaz, Solicitud solicitudActual){
		
		interfaz = pInterfaz; 
		
		setSize(400, 350);
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		
		panelSolicitud = new PanelSolicitud(this, solicitudActual);
		add(panelSolicitud);
		
        setModal(true);
        setResizable(false);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//M�TODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea un panel de tipo PanelUsuario para mostrar la informaci�n del solicitante titular de la solicitud buscada
	 * @param usuarioRelacionado
	 */
	public void crearPanelUsuario(Usuario usuarioRelacionado){
		interfaz.crearPanelUsuario(usuarioRelacionado);
	}
}
