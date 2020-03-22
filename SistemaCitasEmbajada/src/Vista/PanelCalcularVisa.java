package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Modelo.Usuario;

import javax.swing.JButton;

public class PanelCalcularVisa extends JPanel implements ActionListener{


	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Label que muestra el titulo del panel
	 */
	private JLabel lblTitulo;
	/**
	 * Tiene un atributo de tipo JComboBox que muestra las diferentes opciones de busqueda que el sistema tiene habilitadas
	 */
	private JComboBox<String> seleccionBusqueda;
	/**
	 * Ingreso de un String para decir el número de la busqueda. Puede ser un número de pasaporte o un código de solicitud
	 */
	private JTextField txtNumero;
	/**
	 * Ingreso de un String para decir el valor total de la solicitud
	 */
	private JTextField txtValorTotal;
	/**
	 * Ingreso de un String para decir la tasa de cambio con la que trabaja el sistema
	 */
	private JTextField txtTasaCambio;
	/**
	 * Ingreso de un String para decir la moneda local del sistema
	 */
	private JTextField txtMonedaLocal;
	/**
	 * Ingreso de un String para decir el valor local de la solicitud
	 */
	private JTextField txtValorLocal;
	/**
	 * Botón para que el usuario pueda calcular el valor de la visa
	 */
	private JButton btnCalcular;
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
	 * Constante que asocia el botón de calcular visa con una determinada acción
	 */
	private static final String clickCalcular = "C_CALCULAR";
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
	 * Se instancia un panel de tipo PanelCalcularVisa que muestra un panel con diferentes opciones en forma de botones para que el usuario 
	 * pueda calcular el valor de la visa de su solicitud
	 */
	public PanelCalcularVisa(InterfazGrafica pInterfaz) {
		
		interfaz = pInterfaz;
		
		setBackground(Color.BLACK);
		setSize(700, 600);
		setLayout(null);
		
		lblTitulo = new JLabel("Calcular Valor Visa");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(230, 0, 233, 62);
		add(lblTitulo);
		
		JLabel lblBusqueda = new JLabel("Búsqueda Por:");
		lblBusqueda.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBusqueda.setForeground(Color.WHITE);
		lblBusqueda.setBounds(177, 73, 113, 26);
		add(lblBusqueda);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNumero.setForeground(Color.WHITE);
		lblNumero.setBounds(177, 110, 113, 26);
		add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(336, 113, 167, 26);
		add(txtNumero);
		txtNumero.setColumns(10);
		
		seleccionBusqueda = new JComboBox<String>();
		seleccionBusqueda.setModel(new DefaultComboBoxModel<String>(new String[] {"NP - Número de Pasaporte", "CP - Código Solicitud"}));
		seleccionBusqueda.setBounds(336, 76, 167, 26);
		add(seleccionBusqueda);
		
		btnCalcular = new JButton("Calcular Valor Visa");
		btnCalcular.setBounds(177, 150, 152, 23);
		btnCalcular.setBackground(Color.LIGHT_GRAY);
		btnCalcular.setActionCommand(clickCalcular);
		btnCalcular.addActionListener(this);
		add(btnCalcular);
		
		btnMenuPrincipal = new JButton("Menu Principal ");
		btnMenuPrincipal.setBounds(351, 150, 152, 23);
		btnMenuPrincipal.setBackground(Color.LIGHT_GRAY);
		btnMenuPrincipal.addActionListener(this);
		btnMenuPrincipal.setActionCommand(clickMenu);
		add(btnMenuPrincipal);
		
		JLabel lblValorTotal = new JLabel("Valor Total");
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblValorTotal.setForeground(Color.WHITE);
		lblValorTotal.setBounds(46, 370, 113, 26);
		add(lblValorTotal);
		
		JLabel lblTasaDeCambio = new JLabel("Tasa Cambio");
		lblTasaDeCambio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTasaDeCambio.setForeground(Color.WHITE);
		lblTasaDeCambio.setBounds(46, 409, 113, 26);
		add(lblTasaDeCambio);
		
		JLabel lblMonedaLocal = new JLabel("Moneda Local");
		lblMonedaLocal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMonedaLocal.setForeground(Color.WHITE);
		lblMonedaLocal.setBounds(382, 373, 113, 26);
		add(lblMonedaLocal);
		
		JLabel lblValorLocal = new JLabel("Valor Local");
		lblValorLocal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblValorLocal.setForeground(Color.WHITE);
		lblValorLocal.setBounds(382, 409, 113, 26);
		add(lblValorLocal);
		
		txtValorTotal = new JTextField();
		txtValorTotal.setColumns(10);
		txtValorTotal.setBounds(170, 373, 120, 26);
		txtValorTotal.setEditable(false);
		add(txtValorTotal);
		
		txtTasaCambio = new JTextField();
		txtTasaCambio.setColumns(10);
		txtTasaCambio.setBounds(169, 412, 120, 26);
		txtTasaCambio.setEditable(false);
		add(txtTasaCambio);
		
		txtMonedaLocal = new JTextField();
		txtMonedaLocal.setColumns(10);
		txtMonedaLocal.setBounds(505, 373, 120, 26);
		txtMonedaLocal.setEditable(false);
		add(txtMonedaLocal);
		
		txtValorLocal = new JTextField();
		txtValorLocal.setColumns(10);
		txtValorLocal.setBounds(505, 412, 120, 26);
		txtValorLocal.setEditable(false);
		add(txtValorLocal);
		
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que permite relacionar los botones del panel con una determinada acción dependiendo del boton que el usuario presionó
	 */
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getActionCommand().equalsIgnoreCase(clickCalcular)){
			
			if(txtNumero.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null, "Digite un número o codigo", "Error", 0);
			}else{
			
				List<Usuario> solicitantes = interfaz.calcularValorVisa();
				
				if(solicitantes != null){
										
					float impuesto = interfaz.darImpuestoPais();

					String[] datosTabla = {"num Pasaporte", "Nombre", "Fecha Nac", "Valor Visa", "Impuesto", "valor Total"};
					Vector<String> cabezera = new Vector<String>(Arrays.asList(datosTabla));
					Vector<String> datosUsuario;
					Vector<List<String>> datos = new Vector<List<String>>();

					for(Usuario us: solicitantes){

						datosUsuario = new Vector<String>();
						datosUsuario.add(us.getNumPasaporte());
						datosUsuario.add(us.getNombre());
						datosUsuario.add(us.getFechaNacimiento().toString());
						double valorParcial = (int) (us.calcularValorVisa() * 2550);
						datosUsuario.add("" + valorParcial);
						datosUsuario.add("" + (int)(valorParcial * impuesto));
						double valorFinal = (int) (valorParcial + (valorParcial * impuesto)); 
						datosUsuario.add("" + valorFinal);

						datos.add(datosUsuario);
					}

					barraArrastre = new JScrollPane();
					barraArrastre.setBounds(30, 217, 612, 106);
					add(barraArrastre);
					tablaUsuarios = new JTable();
					barraArrastre.setViewportView(tablaUsuarios);
					tablaUsuarios.setModel(new DefaultTableModel(datos, cabezera));
					
				}
			}
		}else if(evento.getActionCommand().equalsIgnoreCase(clickMenu)){
			interfaz.asignarVentanaActual(0);
		}
	}
	/**
	 * Retorna el texto que se ingresó en el textField de número
	 * @return
	 */
	public String darTxtNumero(){
		return txtNumero.getText();
	}
	/**
	 * Actualiza el texto de valor total
	 */
	public void actualizarTxtValorTotal(String valorTotal){
		this.txtValorTotal.setText(valorTotal);
	}
	/**
	 * Actualiza el texto de moneda local
	 */
	public void actualizarTxtMonedaLocal(String monedaLocal){
		this.txtMonedaLocal.setText(monedaLocal);
	}
	/**
	 * Actualiza el texto de valor local
	 */
	public void actualizarTxtValorLocal(String valorLocal){
		this.txtValorLocal.setText(valorLocal);
	}
	/**
	 * Actualiza el texto de tasa de cambio
	 */
	public void actualizarTxtTasaCambio(String tasaCambio){
		this.txtTasaCambio.setText(tasaCambio);
	}
	/**
	 * Retorna la búsqueda que actualmente esta seleccionada en el Combo Box
	 * @return
	 */
	public String darBusquedaSeleccionada(){
		return seleccionBusqueda.getSelectedItem().toString();
	}
}
