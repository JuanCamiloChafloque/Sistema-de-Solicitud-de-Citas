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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Modelo.Usuario;

import javax.swing.JButton;

public class PanelMostrarReporte extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Label que muestra el titulo del panel
	 */
	private JLabel lblTitulo;
	/**
	 * Botón para que el usuario pueda generar el reporte
	 */
	private JButton btnReporte;
	/**
	 * Boton para regresar al menu principal
	 */
	private JButton btnMenuPrincipal;
	/**
	 * Ingreso de un String para decir la fecha a buscar
	 */
	private JTextField txtFecha;
	/**
	 * Barra de arrastre de la tabla de usuarios
	 */
	private JScrollPane barraArrastre;
	/**
	 * Tabla que muestra la información de todos los usuarios registrados
	 */
	private JTable tablaUsuarios;
	/**
	 * Constante que asocia el botón de generar reporte con una determinada acción
	 */
	private static final String clickReporte = "C_REPORTE";
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
	 * Se instancia un panel de tipo PanelMostrarReporte que muestra un panel con diferentes opciones en forma de botones para que el usuario 
	 * pueda ver el reporte de solicitudes de una determinada fecha
	 */
	public PanelMostrarReporte(InterfazGrafica pInterfaz) {
		
		interfaz = pInterfaz;
		
		setBackground(Color.BLACK);
		setSize(700, 600);
		setLayout(null);
		
		lblTitulo = new JLabel("Reporte de Solicitudes");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(212, 11, 269, 62);
		add(lblTitulo);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setBounds(186, 84, 113, 26);
		add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(356, 84, 167, 26);
		add(txtFecha);
		
		btnReporte = new JButton("Generar Reporte");
		btnReporte.setBounds(186, 142, 156, 23);
		btnReporte.setBackground(Color.LIGHT_GRAY);
		btnReporte.setActionCommand(clickReporte);
		btnReporte.addActionListener(this);
		add(btnReporte);
		
		btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.setBounds(367, 142, 156, 23);
		btnMenuPrincipal.setBackground(Color.LIGHT_GRAY);
		btnMenuPrincipal.addActionListener(this);
		btnMenuPrincipal.setActionCommand(clickMenu);
		add(btnMenuPrincipal);
		
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MÉTODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que permite relacionar los botones del panel con una determinada acción dependiendo del boton que el usuario presionó
	 */
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getActionCommand().equalsIgnoreCase(clickReporte)){
			
			if(txtFecha.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null, "Digite una fecha para generar el reporte", "Error", 0);
			}else{
			
				List<Usuario> usuariosHoy = interfaz.generarReporteFecha();
				
				if(usuariosHoy.size() > 0){
					String[] datosTabla = {"num Pasaporte", "Nombre", "Tipo Visa", "Número Solicitud"};
					Vector<String> cabezera = new Vector<String>(Arrays.asList(datosTabla));
					Vector<String> datosUsuarios;
					Vector<List<String>> datos = new Vector<List<String>>();
					
					for(Usuario usuarioActual: usuariosHoy){
						
						datosUsuarios = new Vector<String>();
						datosUsuarios.add(usuarioActual.getNumPasaporte());
						datosUsuarios.add(usuarioActual.getNombre());
						datosUsuarios.add(usuarioActual.getSolicitud().getVisa().getClass().getSimpleName());
						datosUsuarios.add("" + usuarioActual.getSolicitud().getCodigo());
						
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
		}else if(evento.getActionCommand().equalsIgnoreCase(clickMenu)){
			interfaz.asignarVentanaActual(0);
		}
	}
	/**
	 * Retorna el texto que se ingresó en el textField de fecha
	 * @return
	 */
	public String darTxtFecha(){
		return txtFecha.getText();
	}
}
