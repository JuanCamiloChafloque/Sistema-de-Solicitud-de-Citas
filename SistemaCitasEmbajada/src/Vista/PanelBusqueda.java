package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Modelo.Usuario;

import java.awt.GridLayout;

public class PanelBusqueda extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Ingreso de un String para decir el criterio de busqueda
	 */
	private JTextField textFieldBusqueda;
	/**
	 * Botón para que el usuario pueda realizar la busqueda
	 */
	private JButton btnBuscarUsuarios;
	/**
	 * Panel que se genera para mostrar los botones de los usuarios que se encontraron 
	 */
	private JPanel panelBuscar;
	/**
	 * Botón que se genera para que el usuario pueda ver los usuarios encontrados
	 */
	private JButton btnUsuario;
	/**
	 * Boton para regresar al menu principal
	 */
	private JButton btnMenuPrincipal;
	/**
	 * Lista de Botones que guarda todos los botones que se generan en la busqueda
	 */
	private List<JButton> btnUsuarios; 
	/**
	 * Constante que asocia el botón de buscar con una determinada acción
	 */
	private static final String clickBuscar = "C_BUSCAR";
	/**
	 * Constante que asocia el bóton de menu con una determinada acción
	 */
	private static final String clickMenu = "C_MENU";
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Relación con la interfaz grafica del sistema
	 */
	private InterfazGrafica interfaz;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se instancia un panel de tipo PanelBusqueda que muestra un panel con una barra de busqueda para que el usuario pueda ingresar criterios y
	 * encontrar otros usuarios
	 */
	public PanelBusqueda(InterfazGrafica pInterfaz) {
		
		interfaz = pInterfaz;
				
		setBackground(Color.BLACK);
		setSize(700, 800);
		setLayout(null);
		
		JLabel lblTituloMenu = new JLabel("Busqueda de Usuarios");
		lblTituloMenu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTituloMenu.setForeground(Color.WHITE);
		lblTituloMenu.setBounds(210, 30, 387, 55);
		add(lblTituloMenu);
		
		textFieldBusqueda = new JTextField();
		textFieldBusqueda.setBounds(251, 116, 163, 30);
		add(textFieldBusqueda);
		textFieldBusqueda.setColumns(10);
		
		btnBuscarUsuarios = new JButton("Buscar Usuarios");
		btnBuscarUsuarios.setBounds(251, 157, 163, 23);
		btnBuscarUsuarios.setBackground(Color.LIGHT_GRAY);
		btnBuscarUsuarios.setActionCommand(clickBuscar);
		btnBuscarUsuarios.addActionListener(this);
		add(btnBuscarUsuarios);
		
		btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.setBounds(251, 417, 163, 23);
		btnMenuPrincipal.setActionCommand(clickMenu);
		btnMenuPrincipal.addActionListener(this);
		btnMenuPrincipal.setBackground(Color.LIGHT_GRAY);
		add(btnMenuPrincipal);
		
		btnUsuarios = new ArrayList<JButton>();
		
		panelBuscar = new JPanel();
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que permite relacionar los botones del panel con una determinada acción dependiendo del boton que el usuario presionó
	 */
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getActionCommand().equalsIgnoreCase(clickBuscar)){
			
			if(darTxtBusqueda().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null, "No se ha digitado nada en la barra de busqueda", "Error", 0);
			}else{
				List<Usuario> usuariosBusqueda = interfaz.darUsuariosBusqueda();
				actualizarVistaPanel(usuariosBusqueda);
			}
		}
		
		if(evento.getActionCommand().equalsIgnoreCase(clickMenu)){
			interfaz.asignarVentanaActual(0);
		}
		
		List<Usuario> usuariosBusqueda = interfaz.darUsuariosBusqueda();
		for(Usuario usuarioActual: usuariosBusqueda){
			if(evento.getActionCommand().equalsIgnoreCase(usuarioActual.getNombre())){
				interfaz.crearPanelUsuario(usuarioActual);
			}
		}
	}
	/**
	 * Retorna el texto que se ingresó en el textField de busqueda
	 * @return
	 */
	public String darTxtBusqueda(){
		return textFieldBusqueda.getText();
	}
	/**
	 * Actualiza la vista del panel si es que se encuentran usuarios basados en el criterio que se ingresó en la barra de busqueda
	 */
	public void actualizarVistaPanel(List<Usuario> usuariosEncontrados){
		if(usuariosEncontrados.size() == 0){
			
			JOptionPane.showMessageDialog(null, "La búsqueda no obtuvo resultados", "Error", 0);
			
		}else{
			
			panelBuscar.removeAll();
			panelBuscar.setBounds(68, 198, 546, 215);
			panelBuscar.setLayout(new GridLayout(usuariosEncontrados.size(), 1, 5, 5));
			
			for(Usuario usuarioActual: usuariosEncontrados){
				btnUsuario = new JButton(usuarioActual.getNombre());
				btnUsuario.addActionListener(this);
				btnUsuario.setActionCommand(usuarioActual.getNombre());
				btnUsuarios.add(btnUsuario);
				panelBuscar.add(btnUsuario);
			}
			add(panelBuscar);
			panelBuscar.validate();
		}

	}
}
