package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelMenu extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Panel donde se ubica la bandera del pais seleccionado
	 */
	private JPanel panelBandera;
	/**
	 * Panel donde se ubica el titulo del país de la embajada
	 */
	private JPanel panelTituloEmbajada;
	/**
	 * Permite saber cual fue el archivo que se seleccinó para la serialización y deserialización del sistema
	 */
	private JFileChooser archivo;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel de asociar un país una vez se presiona
	 */
	private JButton btnAsociarPais;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel de calcular una visa una vez se presiona
	 */
	private JButton btnCalcularVisa;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel de ingresar solicitantes una vez se presiona
	 */
	private JButton btnIngresarSolicitantes;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel de la lista de los beneficiarios una vez se presiona
	 */
	private JButton btnBeneficiarios;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel del reporte de solicitudes una vez se presiona
	 */
	private JButton btnReporte;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel de la solicitud de una visa de estudiante una vez se presiona
	 */
	private JButton btnVisaEstudiante;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel de la solicitud de una visa de trabajo una vez se presiona
	 */
	private JButton btnVisaTrabajo;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel de la solicitud de una visa de turismo una vez se presiona
	 */
	private JButton btnVisaTurismo;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que guarde toda la información del sistema
	 */
	private JButton btnSalvarDatos;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que cargue toda la información del sistema
	 */
	private JButton btnCargarDatos;
	/** 
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel de busqueda de usuarios
	 */
	private JButton btnBuscarUsuarios;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel de estadisticas
	 */
	private JButton btnReporteEstadisticas;
	/**
	 * Tiene un atributo de tipo JButton que crea un botón para que muestre el panel de consultar una solicitud
	 */
	private JButton btnConsultarSolicitudes;
	/**
	 * Imagen de la bandera
	 */
	private ImageIcon imagen;
	/**
	 * Label donde se encuentra la imagen
	 */
	private JLabel lblImagen;
	/**
	 * Label donde se esncuentra el titulo de la embajada del país
	 */
	private JLabel lblTituloEmbajada;
	/**
	 * Constante que asocia el botón de asociar país con una determinada acción
	 */
	private static final String clickAsociarPais = "C_PAIS";
	/**
	 * Constante que asocia el botón de calcular visa con una determinada acción
	 */
	private static final String clickVisa = "C_VISA";
	/**
	 * Constante que asocia el botón de ingresar solicitantes con una determinada acción
	 */
	private static final String clickSolicitantes = "C_SOLICITANTES";
	/**
	 * Constante que asocia el botón de beneficiarios con una determinada acción
	 */
	private static final String clickBeneficiarios = "C_BENEFICIARIOS";
	/**
	 * Constante que asocia el botón de reportes con una determinada acción
	 */
	private static final String clickReporte = "C_REPORTE";
	/**
	 * Constante que asocia el botón de solicitud estudiante con una determinada acción
	 */
	private static final String clickEstudiante = "C_ESTUDIANTE";
	/**
	 * Constante que asocia el botón de solicitud turismo con una determinada acción
	 */
	private static final String clickTurismo = "C_TURISMO";
	/**
	 * Constante que asocia el botón de solicitud trabajo con una determinada acción
	 */
	private static final String clickTrabajo = "C_TRABAJO";
	/**
	 * Constante que asocia el botón de guardar sistema con una determinada acción
	 */
	private static final String clickSalvar = "C_SALVAR";
	/**
	 * Constante que asocia el botón de cargar sistema con una determinada acción
	 */
	private static final String clickCargar = "C_CARGAR";
	/**
	 * Constante que asocia el botón de buscar con una determinada acción
	 */
	private static final String clickBusqueda = "C_BUSQUEDA";
	/**
	 * Constante que asocia el botón de mostrar reporte edades con una determinada acción
	 */
	private static final String clickEdades = "C_EDADES";
	/**
	 * Constante que asocia el botón de consulat solicitud con una determinada acción
	 */
	private static final String clickSolicitud = "C_SOLICITUD";
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
	 * Se instancia un panel de tipo PanelMenu que muestra un menú principal con diferentes opciones en forma de botones para que el usuario pueda
	 * escojer la opción que desee y realizar acciones
	 */
	public PanelMenu(InterfazGrafica pInterfaz){
		
		interfaz = pInterfaz;
		
		setBackground(Color.BLACK);
		setSize(700, 800);
		setLayout(null);
		
		JLabel lblTituloMenu = new JLabel("Servicios del Sistema");
		lblTituloMenu.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblTituloMenu.setForeground(Color.WHITE);
		lblTituloMenu.setBounds(192, 31, 338, 55);
		add(lblTituloMenu);
		
		btnAsociarPais = new JButton("Asociar Pais A Embajada");
		btnAsociarPais.setBackground(Color.LIGHT_GRAY);
		btnAsociarPais.setBounds(58, 156, 179, 23);
		btnAsociarPais.setActionCommand(clickAsociarPais);
		btnAsociarPais.addActionListener(this);
		add(btnAsociarPais);
		
		btnIngresarSolicitantes = new JButton("Ingresar Solicitantes");
		btnIngresarSolicitantes.setBackground(Color.LIGHT_GRAY);
		btnIngresarSolicitantes.setBounds(58, 190, 179, 23);
		btnIngresarSolicitantes.setActionCommand(clickSolicitantes);
		btnIngresarSolicitantes.addActionListener(this);
		add(btnIngresarSolicitantes);
		
		btnVisaTurismo = new JButton("Solicitar Visa Turismo");
		btnVisaTurismo.setBackground(Color.LIGHT_GRAY);
		btnVisaTurismo.setBounds(58, 224, 179, 23);
		btnVisaTurismo.setActionCommand(clickTurismo);
		btnVisaTurismo.addActionListener(this);
		add(btnVisaTurismo);
		
		btnVisaEstudiante = new JButton("Solicitar Visa Estudiante");
		btnVisaEstudiante.setBackground(Color.LIGHT_GRAY);
		btnVisaEstudiante.setBounds(58, 258, 179, 23);
		btnVisaEstudiante.setActionCommand(clickEstudiante);
		btnVisaEstudiante.addActionListener(this);
		add(btnVisaEstudiante);
		
		btnVisaTrabajo = new JButton("Solicitar Visa Trabajo");
		btnVisaTrabajo.setBackground(Color.LIGHT_GRAY);
		btnVisaTrabajo.setBounds(58, 292, 179, 23);
		btnVisaTrabajo.setActionCommand(clickTrabajo);
		btnVisaTrabajo.addActionListener(this);
		add(btnVisaTrabajo);
		
		btnCalcularVisa = new JButton("Calcular Valor de Visa");
		btnCalcularVisa.setBackground(Color.LIGHT_GRAY);
		btnCalcularVisa.setBounds(430, 156, 179, 23);
		btnCalcularVisa.setActionCommand(clickVisa);
		btnCalcularVisa.addActionListener(this);
		add(btnCalcularVisa);
		
		btnReporte = new JButton("Mostrar Reporte de Citas");
		btnReporte.setBackground(Color.LIGHT_GRAY);
		btnReporte.setBounds(430, 190, 179, 23);
		btnReporte.setActionCommand(clickReporte);
		btnReporte.addActionListener(this);
		add(btnReporte);
		
		btnBeneficiarios = new JButton("Mostrar Beneficiarios");
		btnBeneficiarios.setBackground(Color.LIGHT_GRAY);
		btnBeneficiarios.setBounds(430, 224, 179, 23);
		btnBeneficiarios.setActionCommand(clickBeneficiarios);
		btnBeneficiarios.addActionListener(this);
		add(btnBeneficiarios);
		
		btnSalvarDatos = new JButton("Salvar Datos Sistema");
		btnSalvarDatos.setBackground(Color.LIGHT_GRAY);
		btnSalvarDatos.setBounds(255, 376, 162, 23);
		btnSalvarDatos.setIcon(new ImageIcon("*\\Imagenes*\\Salvar"));
		btnSalvarDatos.setActionCommand(clickSalvar);
		btnSalvarDatos.addActionListener(this);
		add(btnSalvarDatos);
		
		btnCargarDatos = new JButton("Cargar Datos Sistema");
		btnCargarDatos.setBackground(Color.LIGHT_GRAY);
		btnCargarDatos.setBounds(255, 410, 162, 23);
		btnCargarDatos.setActionCommand(clickCargar);
		btnCargarDatos.addActionListener(this);
		add(btnCargarDatos);
		
		btnBuscarUsuarios = new JButton("Buscar Usuarios");
		btnBuscarUsuarios.setBackground(Color.LIGHT_GRAY);
		btnBuscarUsuarios.setBounds(430, 258, 179, 23);
		btnBuscarUsuarios.setActionCommand(clickBusqueda);
		btnBuscarUsuarios.addActionListener(this);
		add(btnBuscarUsuarios);
		
		btnReporteEstadisticas = new JButton("Mostrar estadísticas");
		btnReporteEstadisticas.setBounds(430, 292, 179, 23);
		btnReporteEstadisticas.setBackground(Color.LIGHT_GRAY);
		btnReporteEstadisticas.addActionListener(this);
		btnReporteEstadisticas.setActionCommand(clickEdades);
		add(btnReporteEstadisticas);
		
		btnConsultarSolicitudes = new JButton("Consultar Solicitudes");
		btnConsultarSolicitudes.setBounds(430, 326, 179, 23);
		btnConsultarSolicitudes.setBackground(Color.LIGHT_GRAY);
		btnConsultarSolicitudes.addActionListener(this);
		btnConsultarSolicitudes.setActionCommand(clickSolicitud);
		add(btnConsultarSolicitudes);

	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que permite relacionar los botones del panel con una determinada acción dependiendo del boton que el usuario presionó
	 */
	public void actionPerformed(ActionEvent evento) {

		if(evento.getActionCommand().equalsIgnoreCase(clickAsociarPais)){	
			
			interfaz.asignarVentanaActual(1);
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickVisa)){

			interfaz.asignarVentanaActual(6);

		}else if(evento.getActionCommand().equalsIgnoreCase(clickBeneficiarios)){

			interfaz.asignarVentanaActual(7);

		}else if(evento.getActionCommand().equalsIgnoreCase(clickEstudiante)){
			
			interfaz.asignarVentanaActual(3);
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickReporte)){

			interfaz.asignarVentanaActual(8);

		}else if(evento.getActionCommand().equalsIgnoreCase(clickSolicitantes)){

			interfaz.asignarVentanaActual(2);

		}else if(evento.getActionCommand().equalsIgnoreCase(clickTrabajo)){

			interfaz.asignarVentanaActual(4);

		}else if(evento.getActionCommand().equalsIgnoreCase(clickTurismo)){

			interfaz.asignarVentanaActual(5);

		}else if(evento.getActionCommand().equalsIgnoreCase(clickCargar)){
			
			archivo = new JFileChooser();
			archivo.setCurrentDirectory(new java.io.File("."));
			archivo.setDialogTitle("Seleccione un Archivo");
			if(archivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				interfaz.leerSerializacion(archivo.getSelectedFile().getName());
			}
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickSalvar)){

			String ruta = JOptionPane.showInputDialog(null, "Digite un archivo para realizar la serialización");
			interfaz.darArchivoSerializacion(ruta);
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickBusqueda)){
	
			interfaz.asignarVentanaActual(9);
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickEdades)){
			
			interfaz.crearPanelEstadisticas();
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickSolicitud)){
			
			interfaz.asignarVentanaActual(10);
			
		}
	}
	/**
	 * Genera un panel con la bandera del país de la embajada
	 * @param pais
	 */
	public void mostrarBandera(String pais){
				
		panelBandera = new JPanel();
		panelBandera.setBounds(132, 46, 50, 40);
		panelBandera.setLayout(new BorderLayout());
		add(panelBandera);
		
		panelTituloEmbajada = new JPanel();
		panelTituloEmbajada.setBackground(Color.BLACK);
		panelTituloEmbajada.setBounds(250, 83, 195, 30);
		panelTituloEmbajada.setLayout(new BorderLayout());
		add(panelTituloEmbajada);

		imagen = new ImageIcon("./data/imagenes/" + pais.toUpperCase() + ".png");
		lblImagen = new JLabel();
		lblImagen.setIcon(imagen);
		
		lblTituloEmbajada = new JLabel("Embajada de " + pais.toLowerCase());
		lblTituloEmbajada.setForeground(Color.WHITE);
		lblTituloEmbajada.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		panelTituloEmbajada.add(lblTituloEmbajada, BorderLayout.CENTER);
		panelBandera.add(lblImagen, BorderLayout.CENTER);
	}
}
