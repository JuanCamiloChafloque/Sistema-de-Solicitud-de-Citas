package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Modelo.Usuario;

import javax.swing.ImageIcon;
//import javax.swing.JButton;

public class PanelUsuario extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Label que muestra el titulo del panel
	 */
	private JLabel lblTitulo;
	/**
	 * Label que muestra el titulo de la información del usuario
	 */
	private JLabel lblInformacionDelUsuario;
	/**
	 * Ingreso de un String para mostrar el nombre del usuario buscado
	 */
	private JTextField txtNombre;
	/**
	 * Ingreso de un String para mostrar el número de pasaporte del usuario buscado
	 */
	private JTextField txtPasaporte;
	/**
	 * Ingreso de un String para mostrar la edad del usuario buscado
	 */
	private JTextField txtEdad;
	/**
	 * Ingreso de un String para mostrar el país de nacimiento del usuario buscado
	 */
	private JTextField txtNacimiento;
	/**
	 * Ingreso de un String para mostrar el email del usuario buscado
	 */
	private JTextField txtMail;
	/**
	 * Botón para que el usuario pueda regresar al panel de busqueda
	 */
	//private JButton btnRegresar;
	/**
	 * Constante que asocia el botón de regresar con una determinada acción
	 */
	private static final String clickRegresar = "C_REGRESAR";
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Relación con la interfaz grafica del sistema
	 */
	private InterfazGrafica interfaz;
	
	//private DialogoUsuario dialogo;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se instancia un panel de tipo PanelUsuario que muestra un panel con toda la información general de la busqueda que realizó el usuario en el 
	 * panel de PanelBusqueda
	 */
	public PanelUsuario(DialogoUsuario pDialogo, Usuario usuarioActual) {
		
		//interfaz = pInterfaz;
		//dialogo = pDialogo;
		
		
		setBackground(Color.BLACK);
		setSize(700, 600);
		setLayout(null);
		
		lblTitulo = new JLabel(usuarioActual.getNombre());
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setBounds(193, 11, 298, 62);
		add(lblTitulo);
		
		lblInformacionDelUsuario = new JLabel("Información del Usuario");
		lblInformacionDelUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInformacionDelUsuario.setForeground(Color.WHITE);
		lblInformacionDelUsuario.setBounds(10, 108, 215, 20);
		add(lblInformacionDelUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBounds(44, 155, 70, 14);
		add(lblNombre);
		
		JLabel lblNmeroDePasaporte = new JLabel("Número de Pasaporte");
		lblNmeroDePasaporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNmeroDePasaporte.setForeground(Color.WHITE);
		lblNmeroDePasaporte.setBounds(44, 191, 146, 14);
		add(lblNmeroDePasaporte);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEdad.setForeground(Color.WHITE);
		lblEdad.setBounds(44, 231, 46, 14);
		add(lblEdad);
		
		JLabel lblPasDeNacimiento = new JLabel("País de Nacimiento");
		lblPasDeNacimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPasDeNacimiento.setBounds(44, 274, 123, 14);
		lblPasDeNacimiento.setForeground(Color.WHITE);
		add(lblPasDeNacimiento);
		
		JLabel lblEmail = new JLabel("e-mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(44, 320, 123, 14);
		lblEmail.setForeground(Color.WHITE);
		add(lblEmail);
		
		JPanel panelFoto = new JPanel();
		panelFoto.setBorder(new TitledBorder("Foto de Perfil"));
		panelFoto.setBounds(422, 142, 240, 245);
		add(panelFoto);
		
		ImageIcon imagen = new ImageIcon("./data/imagenes/Perfil.png");
		JLabel lblFotoDePerfil = new JLabel();
		lblFotoDePerfil.setIcon(imagen);
		panelFoto.add(lblFotoDePerfil);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(245, 152, 113, 20);
		txtNombre.setEditable(false);
		add(txtNombre);
		
		txtPasaporte = new JTextField();
		txtPasaporte.setColumns(10);
		txtPasaporte.setBounds(245, 188, 113, 20);
		txtPasaporte.setEditable(false);
		add(txtPasaporte);
		
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(245, 228, 113, 20);
		txtEdad.setEditable(false);
		add(txtEdad);
		
		txtNacimiento = new JTextField();
		txtNacimiento.setColumns(10);
		txtNacimiento.setBounds(245, 271, 113, 20);
		txtNacimiento.setEditable(false);
		add(txtNacimiento);
		
		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(245, 317, 113, 20);
		txtMail.setEditable(false);
		add(txtMail);
		
		/*btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(494, 357, 101, 23);
		btnRegresar.addActionListener(this);
		btnRegresar.setActionCommand(clickRegresar);
		add(btnRegresar);*/
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que permite relacionar los botones del panel con una determinada acción dependiendo del boton que el usuario presionó
	 */
	public void actionPerformed(ActionEvent evento) {
		if(evento.getActionCommand().equalsIgnoreCase(clickRegresar)){
			interfaz.asignarVentanaActual(9);
		}
	}
	/**
	 * Actualiza el texto de nombre
	 */
	public void actualizarTxtNombre(String nombre){
		this.txtNombre.setText(nombre);
	}
	/**
	 * Actualiza el texto de pasaporte
	 */
	public void actualizarTxtPasaporte(String pasaporte){
		this.txtPasaporte.setText(pasaporte);
	}
	/**
	 * Actualiza el texto de edad
	 */
	public void actualizarTxtEdad(int edad){
		this.txtEdad.setText("" + edad);
	}
	/**
	 * Actualiza el texto de pais
	 */
	public void actualizarTxtNacimiento(String pais){
		this.txtNacimiento.setText(pais);
	}
	/**
	 * Actualiza el texto de mail
	 */
	public void actualizarTxtMail(String mail){
		this.txtMail.setText(mail);
	}

}
