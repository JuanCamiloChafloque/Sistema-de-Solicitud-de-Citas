package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modelo.Estudiante;
import Modelo.Solicitud;
import Modelo.Trabajo;
import Modelo.Turismo;
import Modelo.Usuario;

public class PanelSolicitud extends JPanel implements ActionListener{


	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Botón para que el usuario pueda ver quien es el usuario titular de la solicitud
	 */
	private JButton btnUsuario;
	/**
	 * Panel donde se encuentra toda la información de la solicitud
	 */
	private JPanel panelDatos;
	/**
	 * Titulo que acompaña la información de la institución
	 */
	private JLabel lblInstitucion;
	/**
	 * Titulo que acompaña la información de la escolaridad
	 */
	private JLabel lblEscolaridad;
	/**
	 * Titulo que acompaña la información de la empresa
	 */
	private JLabel lblEmpresa;
	/**
	 * Titulo que acompaña la información del cargo
	 */
	private JLabel lblCargo;
	/**
	 * Titulo que acompaña la información de los días de estadía
	 */
	private JLabel lblDias;
	/**
	 * Ingreso de un String para mostrar la institucion que digitó el usuario a la hora de realizar la solicitud
	 */
	private JTextField txtInstitucion;
	/**
	 * Ingreso de un String para mostrar la escolaridad que digitó el usuario a la hora de realizar la solicitud
	 */
	private JTextField txtEscolaridad;
	/**
	 * Ingreso de un String para mostrar la empresa que digitó el usuario a la hora de realizar la solicitud
	 */
	private JTextField txtEmpresa;
	/**
	 * Ingreso de un String para mostrar el cargo que digitó el usuario a la hora de realizar la solicitud
	 */
	private JTextField txtCargo;
	/**
	 * Ingreso de un String para mostrar los días de estadía que digitó el usuario a la hora de realizar la solicitud
	 */
	private JTextField txtDias;
	/**
	 * Constante que asocia el bóton de ver usuario con una determinada acción
	 */
	private static final String clickUsuario = "C_USUARIO";
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Tiene una relación con un dialogo de tipo DialogoSolicitud
	 */
	private DialogoSolicitud dialogo;
	/**
	 * El usuario titular de la solicitud buscada
	 */
	private Usuario usuarioRelacionado;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se instancia un panel de tipo PanelSolicitud que muestra un panel con la información importante de una solicitud
	 */
	public PanelSolicitud(DialogoSolicitud pDialogo, Solicitud solicitudActual) {

		dialogo = pDialogo;
		
		usuarioRelacionado = solicitudActual.getSolicitantes().get(0);
		
		setLayout(new BorderLayout());
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.BLACK);
		JLabel lblTitulo = new JLabel("Solicitud #" + solicitudActual.getCodigo());
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setForeground(Color.WHITE);
		panelTitulo.add(lblTitulo);
		
		JLabel lblTipoVisa = new JLabel("Tipo de Visa a Solicitar");
		lblTipoVisa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTipoVisa.setForeground(Color.WHITE);
		
		JLabel lblEstado = new JLabel("Estado Solicitud");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEstado.setForeground(Color.WHITE);
		
		JLabel lblTarifa = new JLabel("Total a Pagar");
		lblTarifa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTarifa.setForeground(Color.WHITE);
		
		JLabel lblFecha = new JLabel("Fecha de la Cita");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFecha.setForeground(Color.WHITE);
		
		JTextField txtTipoVisa = new JTextField();
		txtTipoVisa.setEditable(false);
		txtTipoVisa.setText(solicitudActual.getVisa().getClass().getSimpleName());
		
		JTextField txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setText(solicitudActual.getEstado());
		
		JTextField txtTarifa = new JTextField();
		txtTarifa.setEditable(false);
		txtTarifa.setText("" + (int) solicitudActual.calcularValorVisa());
		
		JTextField txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setText(solicitudActual.getFecha().toString());
		
		if(solicitudActual.getVisa() instanceof Estudiante){
			
			Estudiante solicitudEstudiante = (Estudiante) solicitudActual.getVisa();

			panelDatos = new JPanel();
			panelDatos.setBackground(Color.BLACK);
			panelDatos.setLayout(new GridLayout(6,2,7,7));
			
			lblInstitucion = new JLabel("Institución");
			lblInstitucion.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblInstitucion.setForeground(Color.WHITE);
			
			txtInstitucion = new JTextField();
			txtInstitucion.setEditable(false);
			txtInstitucion.setText(solicitudEstudiante.getInstitucion());
			
			lblEscolaridad = new JLabel("Escolaridad");
			lblEscolaridad.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblEscolaridad.setForeground(Color.WHITE);
			
			txtEscolaridad = new JTextField();
			txtEscolaridad.setEditable(false);
			txtEscolaridad.setText(solicitudEstudiante.getEscolaridad().toString());
			
			panelDatos.add(lblTipoVisa);
			panelDatos.add(txtTipoVisa);
			panelDatos.add(lblEstado);
			panelDatos.add(txtEstado);
			panelDatos.add(lblTarifa);
			panelDatos.add(txtTarifa);
			panelDatos.add(lblFecha);
			panelDatos.add(txtFecha);
			panelDatos.add(lblInstitucion);
			panelDatos.add(txtInstitucion);
			panelDatos.add(lblEscolaridad);
			panelDatos.add(txtEscolaridad);
		}
		
		if(solicitudActual.getVisa() instanceof Trabajo){
			
			Trabajo solicitudEstudiante = (Trabajo) solicitudActual.getVisa();

			panelDatos = new JPanel();
			panelDatos.setBackground(Color.BLACK);
			panelDatos.setLayout(new GridLayout(6,2));
			
			lblEmpresa = new JLabel("Empresa");
			lblEmpresa.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblEmpresa.setForeground(Color.WHITE);
			
			txtEmpresa = new JTextField();
			txtEmpresa.setEditable(false);
			txtEmpresa.setText(solicitudEstudiante.getEmpresa());
			
			lblCargo = new JLabel("Cargo");
			lblCargo.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblCargo.setForeground(Color.WHITE);
			
			txtCargo = new JTextField();
			txtCargo.setEditable(false);
			txtCargo.setText(solicitudEstudiante.getCargo());
			
			panelDatos.add(lblTipoVisa);
			panelDatos.add(txtTipoVisa);
			panelDatos.add(lblEstado);
			panelDatos.add(txtEstado);
			panelDatos.add(lblTarifa);
			panelDatos.add(txtTarifa);
			panelDatos.add(lblFecha);
			panelDatos.add(txtFecha);
			panelDatos.add(lblEmpresa);
			panelDatos.add(txtEmpresa);
			panelDatos.add(lblCargo);
			panelDatos.add(txtCargo);
		}
		
		if(solicitudActual.getVisa() instanceof Turismo){
			
			Turismo solicitudEstudiante = (Turismo) solicitudActual.getVisa();

			panelDatos = new JPanel();
			panelDatos.setBackground(Color.BLACK);
			panelDatos.setLayout(new GridLayout(5,2));
			
			lblDias = new JLabel("Días de Estadía");
			lblDias.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblDias.setForeground(Color.WHITE);
			
			txtDias = new JTextField();
			txtDias.setEditable(false);
			txtDias.setText("" + solicitudEstudiante.getDiasEstadia());
			
			panelDatos.add(lblTipoVisa);
			panelDatos.add(txtTipoVisa);
			panelDatos.add(lblEstado);
			panelDatos.add(txtEstado);
			panelDatos.add(lblTarifa);
			panelDatos.add(txtTarifa);
			panelDatos.add(lblFecha);
			panelDatos.add(txtFecha);
			panelDatos.add(lblDias);
			panelDatos.add(txtDias);
			
		}
		
		btnUsuario = new JButton("Ver Solicitante Titular");
		btnUsuario.setBackground(Color.LIGHT_GRAY);
		btnUsuario.addActionListener(this);
		btnUsuario.setActionCommand(clickUsuario);
		JPanel panelOpciones = new JPanel();
		panelOpciones.setBackground(Color.BLACK);
		panelOpciones.add(btnUsuario);
		
		add(panelTitulo, BorderLayout.NORTH);
		add(panelDatos, BorderLayout.CENTER);
		add(panelOpciones, BorderLayout.SOUTH);

	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que permite relacionar los botones del panel con una determinada acción dependiendo del boton que el usuario presionó
	 */
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getActionCommand().equalsIgnoreCase(clickUsuario)){
			dialogo.crearPanelUsuario(usuarioRelacionado);
		}
	}
}
