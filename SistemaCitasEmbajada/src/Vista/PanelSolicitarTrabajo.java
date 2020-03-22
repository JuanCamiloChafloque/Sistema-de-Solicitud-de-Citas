package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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

public class PanelSolicitarTrabajo extends JPanel implements ActionListener{


	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Label que muestra el titulo del panel
	 */
	private JLabel lblTitulo;
	/**
	 * Ingreso de un String para decir el número de solicitud
	 */
	private JTextField txtSolicitud;
	/**
	 * Ingreso de un String para decir la empresa
	 */
	private JTextField txtEmpresa;
	/**
	 * Ingreso de un String para decir el cargo
	 */
	private JTextField txtCargo;
	/**
	 * Botón para que el usuario pueda seleccionar un archivo de texto
	 */
	private JButton btnBuscarArchivo;
	/**
	 * Botón para que se pueda agregar un usuario a una solicitud
	 */
	private JButton btnAdicionarUsuario;
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
	 * Constante que asocia el botón de seleccionar archivo con una determinada acción
	 */
	private static final String clickArchivo = "C_ARCHIVO";
	/**
	 * Constante que asocia el botón de adicionar usuario con una determinada acción
	 */
	private static final String clickAdicionar = "C_ADICIONAR";
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
	 * Se instancia un panel de tipo PanelSolicitarTrabajo que muestra un panel con diferentes opciones en forma de botones para que el usuario 
	 * pueda solicitar una visa de trabajo
	 */
	public PanelSolicitarTrabajo(InterfazGrafica pInterfaz) {
		
		interfaz = pInterfaz;
		
		setBackground(Color.BLACK);
		setSize(700, 600);
		setLayout(null);
		
		lblTitulo = new JLabel("Solicitud Visa de Trabajo");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(193, 11, 298, 62);
		add(lblTitulo);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmpresa.setForeground(Color.WHITE);
		lblEmpresa.setBounds(72, 99, 171, 27);
		add(lblEmpresa);
		
		JLabel lblSolicitud = new JLabel("No Solicitud");
		lblSolicitud.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSolicitud.setForeground(Color.WHITE);
		lblSolicitud.setBounds(193, 174, 100, 27);
		add(lblSolicitud);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCargo.setBounds(355, 99, 171, 27);
		lblCargo.setForeground(Color.WHITE);
		add(lblCargo);
		
		txtEmpresa = new JTextField();
		txtEmpresa.setBounds(193, 102, 117, 27);
		add(txtEmpresa);
		txtEmpresa.setColumns(10);
		
		txtCargo = new JTextField();
		txtCargo.setColumns(10);
		txtCargo.setBounds(448, 105, 117, 27);
		add(txtCargo);
		
		txtSolicitud = new JTextField();
		txtSolicitud.setBounds(374, 172, 117, 27);
		txtSolicitud.setEditable(false);
		add(txtSolicitud);
		txtSolicitud.setColumns(10);
		
		btnBuscarArchivo = new JButton("Buscar Archivo");
		btnBuscarArchivo.setBounds(269, 138, 129, 23);
		btnBuscarArchivo.setBackground(Color.LIGHT_GRAY);
		btnBuscarArchivo.setActionCommand(clickArchivo);
		btnBuscarArchivo.addActionListener(this);
		add(btnBuscarArchivo);
		
		btnAdicionarUsuario = new JButton("Adicionar Usuario");
		btnAdicionarUsuario.setBounds(193, 419, 141, 23);
		btnAdicionarUsuario.setBackground(Color.LIGHT_GRAY);
		btnAdicionarUsuario.setActionCommand(clickAdicionar);
		btnAdicionarUsuario.addActionListener(this);
		add(btnAdicionarUsuario);
		
		btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.setBounds(350, 419, 141, 23);
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
			if(txtCargo.getText().equalsIgnoreCase("") || txtEmpresa.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null, "Digite cargo y/o empresa", "Error", 0);
			}else{
				archivo = new JFileChooser();
				archivo.setCurrentDirectory(new java.io.File("."));
				archivo.setDialogTitle("Seleccione un Archivo");
				if(archivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
					if(archivo.getSelectedFile().getName().equalsIgnoreCase("Tarifas.txt")){
						JOptionPane.showMessageDialog(null, "Archivo seleccionado exitosamente");
						JOptionPane.showMessageDialog(null, "Seleccione el usuario a adicionar a la solicitud");
						Map<String, Usuario> usuarios = interfaz.darUsuarios();
						crearTablaUsuarios(usuarios);

					}else{
						JOptionPane.showMessageDialog(null, "En el archivo seleccionado no se encontró información sobre los usuarios");
					}	
				}
			}
		}else if(evento.getActionCommand().equalsIgnoreCase(clickAdicionar)){
			
			if(txtCargo.getText().equalsIgnoreCase("") || txtEmpresa.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null, "Digite cargo y/o empresa", "Error", 0);
			}else{
				TableModel modelo = tablaUsuarios.getModel();
				int fila = tablaUsuarios.getSelectedRow();
				String numPasaporte = (String) modelo.getValueAt(fila, 0);

				Usuario usuarioPendiente = interfaz.buscarUsuarioSolicitud(numPasaporte);

				if(usuarioPendiente == null){
					JOptionPane.showMessageDialog(null, "El usuario que se quiere adicionar ya tiene una solicitud pendiente", "Error", 0);
				}else{
					interfaz.crearSolicitudTrabajo(usuarioPendiente);
					txtCargo.setText("");
					txtEmpresa.setText("");
					txtSolicitud.setText("");
				}
			}

		}else if(evento.getActionCommand().equalsIgnoreCase(clickMenu)){
			interfaz.asignarVentanaActual(0);
		}
	}
	/**
	 * Retorna el texto que se ingresó en el textField de empresa
	 * @return
	 */
	public String darTxtEmpresa(){
		return txtEmpresa.getText();
	}
	/**
	 * Retorna el texto que se ingresó en el textField de cargo
	 * @return
	 */
	public String darTxtCargo(){
		return txtCargo.getText();
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
