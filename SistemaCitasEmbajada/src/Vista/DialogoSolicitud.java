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
	 * Relación con la interfaz grafica del sistema
	 */
	private InterfazGrafica interfaz;
	/**
	 * El dialogo tiene una relación con un panel que permite al usuario ver la información más importante de la solicitud buscada
	 */
	private PanelSolicitud panelSolicitud;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se instancia un dialogo de tipo DialogoSolicitud que muestra un panel con la información más importante de la solicitud buscada
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
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Crea un panel de tipo PanelUsuario para mostrar la información del solicitante titular de la solicitud buscada
	 * @param usuarioRelacionado
	 */
	public void crearPanelUsuario(Usuario usuarioRelacionado){
		interfaz.crearPanelUsuario(usuarioRelacionado);
	}
}
