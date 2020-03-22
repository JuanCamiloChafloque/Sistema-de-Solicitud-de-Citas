package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Modelo.Usuario;

import javax.swing.JButton;

public class PanelMostrarBeneficiarios extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Label que muestra el titulo del panel
	 */
	private JLabel lblTitulo;
	/**
	 * Ingreso de un String para decir el valor recaudado
	 */
	private JTextField txtValor;
	/**
	 * Botón para que el usuario pueda generar la lista de beneficiarios
	 */
	private JButton btnGenerarLista;
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
	 * Constante que asocia el botón de generar lista con una determinada acción
	 */
	private static final String clickLista = "C_LISTA";
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
	 * Se instancia un panel de tipo PanelMostrarBeneficiarios que muestra un panel con diferentes opciones en forma de botones para que el usuario 
	 * pueda ver el reporte de la lista de beneficiarios
	 */
	public PanelMostrarBeneficiarios(InterfazGrafica pInterfaz) {
		
		interfaz = pInterfaz;
		
		setBackground(Color.BLACK);
		setSize(700, 600);
		setLayout(null);
		
		lblTitulo = new JLabel("Reporte de Beneficiarios");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(208, 11, 299, 62);
		add(lblTitulo);
		
		JLabel lblValorTotal = new JLabel("Valor total que se dejó de recaudar");
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblValorTotal.setForeground(Color.WHITE);
		lblValorTotal.setBounds(84, 399, 274, 26);
		add(lblValorTotal);
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		txtValor.setBounds(439, 402, 137, 26);
		txtValor.setEditable(false);
		add(txtValor);
		
		btnGenerarLista = new JButton("Generar Lista");
		btnGenerarLista.setBounds(185, 73, 156, 23);
		btnGenerarLista.setActionCommand(clickLista);
		btnGenerarLista.setBackground(Color.LIGHT_GRAY);
		btnGenerarLista.addActionListener(this);
		add(btnGenerarLista);
		
		btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.setBounds(351, 73, 156, 23);
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
		
		if(evento.getActionCommand().equalsIgnoreCase(clickLista)){
			
			List<Usuario> beneficiarios = interfaz.generarReporteBeneficiarios();
			
			if(beneficiarios.size() > 0){
		
				String[] datosTabla = {"num Pasaporte", "Nombre", "Valor Total"};
				Vector<String> cabezera = new Vector<String>(Arrays.asList(datosTabla));
				Vector<String> datosUsuarios;
				Vector<List<String>> datos = new Vector<List<String>>();

				for(Usuario beneficiarioActual: beneficiarios){

					datosUsuarios = new Vector<String>();
					datosUsuarios.add(beneficiarioActual.getNumPasaporte());
					datosUsuarios.add(beneficiarioActual.getNombre());
					datosUsuarios.add("" + (int)(beneficiarioActual.calcularValorVisa() * 2550));

					datos.add(datosUsuarios);
				}

				barraArrastre = new JScrollPane();
				barraArrastre.setBounds(30, 217, 612, 106);
				add(barraArrastre);
				tablaUsuarios = new JTable();
				barraArrastre.setViewportView(tablaUsuarios);
				tablaUsuarios.setModel(new DefaultTableModel(datos, cabezera));
			}

		}else if(evento.getActionCommand().equalsIgnoreCase(clickMenu)){
			interfaz.asignarVentanaActual(0);
		}
	}
	/**
	 * Actualiza el texto de valor recaudado
	 */
	public void actualizarTxtValor(String valorRecaudado){
		this.txtValor.setText(valorRecaudado);
	}

}
