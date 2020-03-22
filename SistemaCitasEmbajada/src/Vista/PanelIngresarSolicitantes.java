package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modelo.Ni�oMayor;
import Modelo.Ni�oMenor;
import Modelo.Usuario;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


public class PanelIngresarSolicitantes extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Label que muestra el titulo del panel
	 */
	private JLabel lblTitulo;
	/**
	 * Label que muestra un titulo que acompa�a a la tabla de solicitantes
	 */
	private JLabel lblSolicitantes;
	/**
	 * Bot�n para que el usuario pueda seleccionar un archivo de texto
	 */
	private JButton btnArchivo;
	/**
	 * Bot�n para que se pueda agregar un usuario al sistema
	 */
	private JButton btnActualizarSolicitantes;
	/**
	 * Bot�n para que se pueda actualizar la tabla con los usuarios agregados por separado
	 */
	private JButton btnAgregarUsuario;
	/**
	 * Boton para regresar al menu principal
	 */
	private JButton btnMenPrincipal;
	/**
	 * Libreria de Archivo que permite obtener el nombre del archivo que el usuario seleccion�
	 */
	private JFileChooser archivo;
	/**
	 * Barra de arrastre de la tabla de usuarios
	 */
	private JScrollPane barraArrastre;
	/**
	 * Tabla que muestra la informaci�n de todos los usuarios registrados
	 */
	private JTable tablaUsuarios;
	/**
	 * Constante que asocia el bot�n de seleccionar archivo con una determinada acci�n
	 */
	private static final String clickArchivo = "C_ARCHIVO";
	/**
	 * Constante que asocia el bot�n de agregar usuario con una determinada acci�n
	 */
	private static final String clickUsuario = "C_USUARIO";
	/**
	 * Constante que asocia el bot�n de actualizar solicitantes con una determinada acci�n
	 */
	private static final String clickActualizar = "C_ACTUALIZAR";
	/**
	 * Constante que asocia el b�ton de menu con una determinada acci�n
	 */
	private static final String clickMenu = "C_MENU";
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Relaci�n con la interfaz grafica del sistema
	 */
	private InterfazGrafica interfaz;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se instancia un panel de tipo PanelIngresarSolicitantes que muestra un panel con diferentes opciones en forma de botones para que el usuario 
	 * pueda escojer la opci�n que desee y realizar acciones
	 */
	public PanelIngresarSolicitantes(InterfazGrafica pInterfaz){
		
		interfaz = pInterfaz;
		
		setBackground(Color.BLACK);
		setSize(700, 600);
		setLayout(null);
		
		lblTitulo = new JLabel("Ingresar Solicitantes");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(221, 11, 243, 62);
		add(lblTitulo);
		
		btnAgregarUsuario = new JButton("Agregar Usuario");
		btnAgregarUsuario.setBounds(87, 103, 145, 23);
		btnAgregarUsuario.setBackground(Color.LIGHT_GRAY);
		btnAgregarUsuario.setActionCommand(clickUsuario);
		btnAgregarUsuario.addActionListener(this);
		add(btnAgregarUsuario);	
		
		btnArchivo = new JButton("Buscar Archivo");
		btnArchivo.setBounds(256, 103, 145, 23);
		btnArchivo.setBackground(Color.LIGHT_GRAY);
		btnArchivo.setActionCommand(clickArchivo);
		btnArchivo.addActionListener(this);
		add(btnArchivo);
		
		lblSolicitantes = new JLabel("Solicitantes Registrados");
		lblSolicitantes.setForeground(Color.WHITE);
		lblSolicitantes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSolicitantes.setBounds(10, 165, 222, 23);
		add(lblSolicitantes);		
		
		btnActualizarSolicitantes = new JButton("Actualizar Solicitantes");
		btnActualizarSolicitantes.setBounds(424, 103, 167, 23);
		btnActualizarSolicitantes.setBackground(Color.LIGHT_GRAY);
		btnActualizarSolicitantes.setActionCommand(clickActualizar);
		btnActualizarSolicitantes.addActionListener(this);
		add(btnActualizarSolicitantes);
		
		btnMenPrincipal = new JButton("Men� Principal");
		btnMenPrincipal.setBounds(256, 413, 145, 23);
		btnMenPrincipal.setBackground(Color.LIGHT_GRAY);
		btnMenPrincipal.addActionListener(this);
		btnMenPrincipal.setActionCommand(clickMenu);
		add(btnMenPrincipal);	
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//M�TODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que permite relacionar los botones del panel con una determinada acci�n dependiendo del boton que el usuario presion�
	 */
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getActionCommand().equalsIgnoreCase(clickArchivo)){
			archivo = new JFileChooser();
			archivo.setCurrentDirectory(new java.io.File("."));
			archivo.setDialogTitle("Seleccione un Archivo");
			if(archivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				if(archivo.getSelectedFile().getName().equalsIgnoreCase("Solicitante.txt")){
					JOptionPane.showMessageDialog(null, "Archivo seleccionado exitosamente");
					interfaz.leerUsuarios();
				}else{
					JOptionPane.showMessageDialog(null, "En el archivo seleccionado no se encontr� informaci�n sobre los usuarios");
				}	
			}
		}else if(evento.getActionCommand().equalsIgnoreCase(clickUsuario)){
			interfaz.agregarSolicitante();
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickActualizar)){
			Map<String, Usuario> usuarios = interfaz.darUsuarios();
			crearTablaUsuarios(usuarios);
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickMenu)){
			interfaz.asignarVentanaActual(0);;
		}
	}
	/**
	 * Retorna el archivo que seleccion� el usuario cuando presion� el bot�n de buscar archivo
	 * @return
	 */
	public String darArchivoUsuarios(){
		return archivo.getSelectedFile().getName();
	}
	/**
	 * Dada la lista de todos los usuarios registrados crea una tabla con la informaci�n de todos los usuarios
	 * @param usuarios
	 */
	public void crearTablaUsuarios(Map<String, Usuario> usuarios){
	
		String[] datosTabla = {"num Pasaporte", "Nombre", "Pa�s Origen", "Ciudad Origen", "Fecha Nac", "e-mail", "info Adicional"};
		Vector<String> cabezera = new Vector<String>(Arrays.asList(datosTabla));
		Vector<String> datosUsuarios;
		Vector<List<String>> datos = new Vector<List<String>>();
	
		for(Usuario solicitantes: usuarios.values()){
			
			datosUsuarios = new Vector<String>();
			datosUsuarios.add(solicitantes.getNumPasaporte());
			datosUsuarios.add(solicitantes.getNombre());
			datosUsuarios.add(solicitantes.getPaisNacimiento());
			datosUsuarios.add(solicitantes.getCiudadNacimiento());
			datosUsuarios.add(solicitantes.getFechaNacimiento().toString());
			datosUsuarios.add(solicitantes.getEmail());
			if(solicitantes instanceof Ni�oMenor){
				Ni�oMenor usuario = (Ni�oMenor) solicitantes;
				datosUsuarios.add(usuario.getAcudiente());
			}else if(solicitantes instanceof Ni�oMayor){
				Ni�oMayor usuario = (Ni�oMayor) solicitantes;
				datosUsuarios.add(usuario.getEscolaridad());
			}else{
				datosUsuarios.add("");
			}
			
			datos.add(datosUsuarios);
		}
		barraArrastre = new JScrollPane();
		barraArrastre.setBounds(30, 217, 612, 106);
		add(barraArrastre);
		tablaUsuarios = new JTable();
		barraArrastre.setViewportView(tablaUsuarios);
		tablaUsuarios.setModel(new DefaultTableModel(datos, cabezera));
	}
}
