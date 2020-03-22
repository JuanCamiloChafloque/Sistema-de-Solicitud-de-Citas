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

import Modelo.NiñoMayor;
import Modelo.NiñoMenor;
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
	 * Label que muestra un titulo que acompaña a la tabla de solicitantes
	 */
	private JLabel lblSolicitantes;
	/**
	 * Botón para que el usuario pueda seleccionar un archivo de texto
	 */
	private JButton btnArchivo;
	/**
	 * Botón para que se pueda agregar un usuario al sistema
	 */
	private JButton btnActualizarSolicitantes;
	/**
	 * Botón para que se pueda actualizar la tabla con los usuarios agregados por separado
	 */
	private JButton btnAgregarUsuario;
	/**
	 * Boton para regresar al menu principal
	 */
	private JButton btnMenPrincipal;
	/**
	 * Libreria de Archivo que permite obtener el nombre del archivo que el usuario seleccionó
	 */
	private JFileChooser archivo;
	/**
	 * Barra de arrastre de la tabla de usuarios
	 */
	private JScrollPane barraArrastre;
	/**
	 * Tabla que muestra la información de todos los usuarios registrados
	 */
	private JTable tablaUsuarios;
	/**
	 * Constante que asocia el botón de seleccionar archivo con una determinada acción
	 */
	private static final String clickArchivo = "C_ARCHIVO";
	/**
	 * Constante que asocia el botón de agregar usuario con una determinada acción
	 */
	private static final String clickUsuario = "C_USUARIO";
	/**
	 * Constante que asocia el botón de actualizar solicitantes con una determinada acción
	 */
	private static final String clickActualizar = "C_ACTUALIZAR";
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
	 * Se instancia un panel de tipo PanelIngresarSolicitantes que muestra un panel con diferentes opciones en forma de botones para que el usuario 
	 * pueda escojer la opción que desee y realizar acciones
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
		
		btnMenPrincipal = new JButton("Menú Principal");
		btnMenPrincipal.setBounds(256, 413, 145, 23);
		btnMenPrincipal.setBackground(Color.LIGHT_GRAY);
		btnMenPrincipal.addActionListener(this);
		btnMenPrincipal.setActionCommand(clickMenu);
		add(btnMenPrincipal);	
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que permite relacionar los botones del panel con una determinada acción dependiendo del boton que el usuario presionó
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
					JOptionPane.showMessageDialog(null, "En el archivo seleccionado no se encontró información sobre los usuarios");
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
	 * Retorna el archivo que seleccionó el usuario cuando presionó el botón de buscar archivo
	 * @return
	 */
	public String darArchivoUsuarios(){
		return archivo.getSelectedFile().getName();
	}
	/**
	 * Dada la lista de todos los usuarios registrados crea una tabla con la información de todos los usuarios
	 * @param usuarios
	 */
	public void crearTablaUsuarios(Map<String, Usuario> usuarios){
	
		String[] datosTabla = {"num Pasaporte", "Nombre", "País Origen", "Ciudad Origen", "Fecha Nac", "e-mail", "info Adicional"};
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
			if(solicitantes instanceof NiñoMenor){
				NiñoMenor usuario = (NiñoMenor) solicitantes;
				datosUsuarios.add(usuario.getAcudiente());
			}else if(solicitantes instanceof NiñoMayor){
				NiñoMayor usuario = (NiñoMayor) solicitantes;
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
