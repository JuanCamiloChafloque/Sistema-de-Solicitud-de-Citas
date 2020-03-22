package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Modelo.NiñoMayor;
import Modelo.NiñoMenor;
import Modelo.Usuario;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class PanelSolicitarTurismo extends JPanel implements ActionListener{


	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Label que muestra el titulo del panel
	 */
	private JLabel lblTitulo;
	/**
	 * Ingreso de un String para decir los días de estadía
	 */
	private JTextField txtDiasEstadia;
	/**
	 * Ingreso de un String para decir el número de solicitud
	 */
	private JTextField txtSolicitud;
	/**
	 * Botón para que el usuario pueda seleccionar un archivo de texto
	 */
	private JButton btnBuscarArchivo;
	/**
	 * Botón para que se pueda agregar un usuario a una solicitud
	 */
	private JButton btnAdicionarUsuario;
	/**
	 * Botón que crea la solicitud con los usuarios que se hayan añadido a la lista
	 */
	private JButton btnCrearSolicitud;
	/**
	 * Boton para regresar al menu principal
	 */
	private JButton btnMenuPrincipal;
	/**
	 * Barra de arrastre de la tabla de usuarios
	 */
	private JScrollPane barraArrastre;
	/**
	 * Tabla que muestra la información de todos los usuarios registrados
	 */
	private JTable tablaUsuarios;
	/**
	 * Libreria de Archivo que permite obtener el nombre del archivo que el usuario seleccionó
	 */
	private JFileChooser archivo;
	/**
	 * Lista de solicitantes
	 */
	List<Usuario> solicitantes;
	/**
	 * Constante que asocia el botón de seleccionar archivo con una determinada acción
	 */
	private static final String clickArchivo = "C_ARCHIVO";
	/**
	 * Constante que asocia el botón de adicionar usuario con una determinada acción
	 */
	private static final String clickAdicionar = "C_ADICIONAR";
	/**
	 * Constante que asocia el botón de crear solicitud con una determinada acción
	 */
	private static final String clickSolicitud = "C_SOLICITUD";
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
	 * Se instancia un panel de tipo PanelSolicitarTurismo que muestra un panel con diferentes opciones en forma de botones para que el usuario 
	 * pueda solicitar una visa de turismo
	 */
	public PanelSolicitarTurismo(InterfazGrafica pInterfaz) {
		
		interfaz = pInterfaz;
		
		setBackground(Color.BLACK);
		setSize(700, 600);
		setLayout(null);
		
		lblTitulo = new JLabel("Solicitud Visa de Turismo");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(193, 11, 298, 62);
		add(lblTitulo);
		
		JLabel lblDiasDeEstadia = new JLabel("Dias de Estadía");
		lblDiasDeEstadia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDiasDeEstadia.setForeground(Color.WHITE);
		lblDiasDeEstadia.setBounds(193, 100, 171, 27);
		add(lblDiasDeEstadia);
		
		JLabel lblSolicitud = new JLabel("No Solicitud");
		lblSolicitud.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSolicitud.setForeground(Color.WHITE);
		lblSolicitud.setBounds(193, 174, 100, 27);
		add(lblSolicitud);
		
		txtDiasEstadia = new JTextField();
		txtDiasEstadia.setBounds(374, 102, 117, 27);
		add(txtDiasEstadia);
		txtDiasEstadia.setColumns(10);
		
		txtSolicitud = new JTextField();
		txtSolicitud.setBounds(374, 172, 117, 27);
		txtSolicitud.setEditable(false);
		add(txtSolicitud);
		txtSolicitud.setColumns(10);
		
		btnAdicionarUsuario = new JButton("Adicionar Usuario a lista");
		btnAdicionarUsuario.setBounds(79, 420, 178, 23);
		btnAdicionarUsuario.setBackground(Color.LIGHT_GRAY);
		btnAdicionarUsuario.setActionCommand(clickAdicionar);
		btnAdicionarUsuario.addActionListener(this);
		add(btnAdicionarUsuario);
		
		btnBuscarArchivo = new JButton("Buscar Archivo");
		btnBuscarArchivo.setBounds(269, 138, 129, 23);
		btnBuscarArchivo.setBackground(Color.LIGHT_GRAY);
		btnBuscarArchivo.setActionCommand(clickArchivo);
		btnBuscarArchivo.addActionListener(this);
		add(btnBuscarArchivo);
		
		btnCrearSolicitud = new JButton("Crear Solicitud");
		btnCrearSolicitud.setBounds(267, 420, 178, 23);
		btnCrearSolicitud.setBackground(Color.LIGHT_GRAY);
		btnCrearSolicitud.setActionCommand(clickSolicitud);
		btnCrearSolicitud.addActionListener(this);
		add(btnCrearSolicitud);
		
		btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.setBounds(455, 420, 178, 23);
		btnMenuPrincipal.setBackground(Color.LIGHT_GRAY);
		btnMenuPrincipal.setActionCommand(clickMenu);
		btnMenuPrincipal.addActionListener(this);
		add(btnMenuPrincipal);
		
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que permite relacionar los botones del panel con una determinada acción dependiendo del boton que el usuario presionó
	 */
	public void actionPerformed(ActionEvent evento) {
				
		if(evento.getActionCommand().equalsIgnoreCase(clickArchivo)){
			
			solicitantes = new ArrayList<Usuario>();
			
			if(txtDiasEstadia.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null, "Digite los días de estadia", "Error", 0);
			}else{
				archivo = new JFileChooser();
				archivo.setCurrentDirectory(new java.io.File("."));
				archivo.setDialogTitle("Seleccione un Archivo");
				if(archivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
					if(archivo.getSelectedFile().getName().equalsIgnoreCase("Tarifas.txt")){
						
						JOptionPane.showMessageDialog(null, "Archivo seleccionado exitosamente");
						JOptionPane.showMessageDialog(null, "Seleccione los usuarios a adicionar a la solicitud");
						Map<String, Usuario> usuarios = interfaz.darUsuarios();
						crearTablaUsuarios(usuarios);
						
					}else{
						JOptionPane.showMessageDialog(null, "En el archivo seleccionado no se encontró información sobre los usuarios");
					}	
				}
				
			}
		}else if(evento.getActionCommand().equalsIgnoreCase(clickAdicionar)){
			
			if(txtDiasEstadia.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null, "Digite los días de estadia", "Error", 0);
			}else{
				TableModel modelo = tablaUsuarios.getModel();
				int fila = tablaUsuarios.getSelectedRow();
				String numPasaporte = (String) modelo.getValueAt(fila, 0);

				Usuario usuarioPendiente = interfaz.buscarUsuarioSolicitud(numPasaporte);

				if(usuarioPendiente == null){
					JOptionPane.showMessageDialog(null, "El solicitante con el número de pasaporte " + numPasaporte + "ya tiene una solicitud pendiente", "Error", 0);
				}else{
					solicitantes.add(usuarioPendiente);
					JOptionPane.showMessageDialog(null, "El usuario con el número de pasaporte " + numPasaporte + " se añadió a la lista");
				}
			}
	
		}else if(evento.getActionCommand().equalsIgnoreCase(clickSolicitud)){
			if(txtDiasEstadia.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null, "Digite los días de estadia", "Error", 0);
			}else{
				interfaz.crearSolicitudTurismo(solicitantes);
			}	
		}else if(evento.getActionCommand().equalsIgnoreCase(clickMenu)){
			interfaz.asignarVentanaActual(0);
		}
	}
	/**
	 * Retorna el texto que se ingresó en el textField de días de estadia
	 * @return
	 */
	public String darTxtDiasEstadia(){
		return txtDiasEstadia.getText();
	}
	/**
	 * Actualiza el texto de solicitud
	 */
	public void actualizarTxtSolicitud(String solicitud){
		this.txtSolicitud.setText(solicitud);
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
