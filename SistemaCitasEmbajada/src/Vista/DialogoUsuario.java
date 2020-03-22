package Vista;

import javax.swing.JDialog;

import Modelo.Usuario;

public class DialogoUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * El dialogo contiene un panel de tipo PanelUsuario
	 */
	private PanelUsuario panelUsuario;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se instancia un dialogo de tipo DialogoUsuario que muestra un panel con la información del usuario que se buscó en el panel de 
	 * busqueda
	 */
	public DialogoUsuario(Usuario pUsuario) {
				
		setSize(700, 600);
		setLayout(null);
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		
		panelUsuario = new PanelUsuario(this, pUsuario);
		add(panelUsuario);
		
        setModal(true);
        setResizable(false);

	}	
	/**
	 * Actualiza los datos del usuario a mostrar
	 * @param usuarioSeleccionado
	 */
	public void actualizarDatos(Usuario usuarioSeleccionado){
		
		panelUsuario.actualizarTxtNombre(usuarioSeleccionado.getNombre());
		panelUsuario.actualizarTxtPasaporte(usuarioSeleccionado.getNumPasaporte());
		panelUsuario.actualizarTxtNacimiento(usuarioSeleccionado.getPaisNacimiento());
		panelUsuario.actualizarTxtEdad(usuarioSeleccionado.getEdad());
		panelUsuario.actualizarTxtMail(usuarioSeleccionado.getEmail());
		
	}


}
