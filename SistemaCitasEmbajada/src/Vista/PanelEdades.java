package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modelo.Usuario;

import java.awt.Font;

public class PanelEdades extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Ingreso de un String para mostrar la cantidad de usuarios que se encuentran en el rango de edad indicado
	 */
	private JTextField txtRango0_11;
	/**
	 * Ingreso de un String para mostrar la cantidad de usuarios que se encuentran en el rango de edad indicado
	 */
	private JTextField txtRango12_20;
	/**
	 * Ingreso de un String para mostrar la cantidad de usuarios que se encuentran en el rango de edad indicado
	 */
	private JTextField txtRango21_30;
	/**
	 * Ingreso de un String para mostrar la cantidad de usuarios que se encuentran en el rango de edad indicado
	 */
	private JTextField txtRango31_40;
	/**
	 * Ingreso de un String para mostrar la cantidad de usuarios que se encuentran en el rango de edad indicado
	 */
	private JTextField txtRango41_50;
	/**
	 * Ingreso de un String para mostrar la cantidad de usuarios que se encuentran en el rango de edad indicado
	 */
	private JTextField txtRango51_60;
	/**
	 * Ingreso de un String para mostrar la cantidad de usuarios que se encuentran en el rango de edad indicado
	 */
	private JTextField txtRango60;
	/**
	 * Botón para que el usuario pueda ver la cantidad de usuarios que son mayor de edad
	 */
	private JButton btnMayorEdad;
	/**
	 * Botón para que el usuario pueda ver quien es el usuario más viejo
	 */
	private JButton btnUsuarioMayor;
	/**
	 * Botón para que el usuario pueda ver quien es el usuario que más paga
	 */
	private JButton btnUsuarioMasPago;
	/**
	 * Constante que asocia el bóton de mayor con una determinada acción
	 */
	private static final String clickMayor = "C_MAYOR";
	/**
	 * Constante que asocia el bóton de usuario mayor con una determinada acción
	 */
	private static final String clickUsuarioMayor = "C_USUARIO";
	/**
	 * Constante que asocia el bóton de pago con una determinada acción
	 */
	private static final String clickPago = "C_PAGO";
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Tiene una relación con un dialogo de tipo DialogoEdades
	 */
	DialogoEdades dialogo;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se instancia un panel de tipo PanelEdades que muestra un panel con toda la información general de los usuarios registrados en 
	 * el sistema
	 */
	public PanelEdades(DialogoEdades pDialogo) {
		
		dialogo = pDialogo;
		
		setLayout(new BorderLayout());
		
		JLabel lblRango0_11 = new JLabel("Rango de 0-11 años");
		lblRango0_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRango0_11.setForeground(Color.WHITE);
		JLabel lblRango12_20 = new JLabel("Rango de 12-20 años");
		lblRango12_20.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRango12_20.setForeground(Color.WHITE);
		JLabel lblRango21_30 = new JLabel("Rango de 21-30 años");
		lblRango21_30.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRango21_30.setForeground(Color.WHITE);
		JLabel lblRango31_40 = new JLabel("Rango de 31-40 años");
		lblRango31_40.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRango31_40.setForeground(Color.WHITE);
		JLabel lblRango41_50 = new JLabel("Rango de 41-50 años");
		lblRango41_50.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRango41_50.setForeground(Color.WHITE);
		JLabel lblRango51_60 = new JLabel("Rango de 51-60 años");
		lblRango51_60.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRango51_60.setForeground(Color.WHITE);
		JLabel lblRango60 = new JLabel("Rango mayores de 60 años");
		lblRango60.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRango60.setForeground(Color.WHITE);
	
		txtRango0_11 = new JTextField();
		txtRango0_11.setEditable(false);
		txtRango12_20 = new JTextField();
		txtRango12_20.setEditable(false);
		txtRango21_30 = new JTextField();
		txtRango21_30.setEditable(false);
		txtRango31_40 = new JTextField();
		txtRango31_40.setEditable(false);
		txtRango41_50 = new JTextField();
		txtRango41_50.setEditable(false);
		txtRango51_60 = new JTextField();
		txtRango51_60.setEditable(false);
		txtRango60 = new JTextField();
		txtRango60.setEditable(false);
				
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.BLACK);
		JLabel lblTitulo = new JLabel("Estadísticas Usuarios registrados");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitulo.setForeground(Color.WHITE);
		panelTitulo.add(lblTitulo);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setForeground(Color.WHITE);
		panelDatos.setBackground(Color.BLACK);
		panelDatos.setLayout(new GridLayout(7,2,7,7));
		panelDatos.add(lblRango0_11);
		panelDatos.add(txtRango0_11);
		panelDatos.add(lblRango12_20);
		panelDatos.add(txtRango12_20);
		panelDatos.add(lblRango21_30);
		panelDatos.add(txtRango21_30);
		panelDatos.add(lblRango31_40);
		panelDatos.add(txtRango31_40);
		panelDatos.add(lblRango41_50);
		panelDatos.add(txtRango41_50);
		panelDatos.add(lblRango51_60);
		panelDatos.add(txtRango51_60);
		panelDatos.add(lblRango60);
		panelDatos.add(txtRango60);
		
		btnMayorEdad = new JButton("Mayores de edad");
		btnMayorEdad.setBackground(Color.LIGHT_GRAY);
		btnMayorEdad.addActionListener(this);
		btnMayorEdad.setActionCommand(clickMayor);
		
		btnUsuarioMayor = new JButton("Usuario mayor");
		btnUsuarioMayor.setBackground(Color.LIGHT_GRAY);
		btnUsuarioMayor.addActionListener(this);
		btnUsuarioMayor.setActionCommand(clickUsuarioMayor);
		
		btnUsuarioMasPago = new JButton("Usuario que más paga");
		btnUsuarioMasPago.setBackground(Color.LIGHT_GRAY);
		btnUsuarioMasPago.addActionListener(this);
		btnUsuarioMasPago.setActionCommand(clickPago);
		
		JPanel panelOpciones = new JPanel();
		panelOpciones.setLayout(new GridLayout(1,3));
		panelOpciones.setForeground(Color.WHITE);
		panelOpciones.setBackground(Color.BLACK);
		panelOpciones.add(btnMayorEdad);
		panelOpciones.add(btnUsuarioMayor);
		panelOpciones.add(btnUsuarioMasPago);
		
		
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
		if(evento.getActionCommand().equalsIgnoreCase(clickMayor)){
			
			int cantidad = dialogo.darCantidadMayores();
			JOptionPane.showMessageDialog(null, "En total hay " + cantidad + " usuarios que son mayores de edad");
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickUsuarioMayor)){
			
			Usuario usuarioMayor = dialogo.darUsuarioMayor();
			if(usuarioMayor != null){
				JOptionPane.showMessageDialog(null, "El usuario mayor registrado es: " + usuarioMayor.getNombre() + "\n" + "Edad: "
						+ usuarioMayor.getEdad());
			}else{
				JOptionPane.showMessageDialog(null, "No se ha registrado ningun usuario", "Error", 0);
			}
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickPago)){
			
			Usuario usuarioMasPaga = dialogo.darUsuarioMasPaga();
			if(usuarioMasPaga != null){
			JOptionPane.showMessageDialog(null, "El usuario que más pagó es: " + usuarioMasPaga.getNombre() + "\n" + "Total Pagado: "
											+ usuarioMasPaga.calcularValorVisa());
			}else{
				JOptionPane.showMessageDialog(null, "No se ha solicitado ningun tipo de visa", "Error", 0);
			}
		}
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt0_11(String cantidad){
		this.txtRango0_11.setText(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt12_20(String cantidad){
		this.txtRango12_20.setText(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt21_30(String cantidad){
		this.txtRango21_30.setText(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt31_40(String cantidad){
		this.txtRango31_40.setText(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt41_50(String cantidad){
		this.txtRango41_50.setText(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt51_60(String cantidad){
		this.txtRango51_60.setText(cantidad);
	}
	/**
	 * Actualiza el texto del rango actual
	 */
	public void actualizarTxt60(String cantidad){
		this.txtRango60.setText(cantidad);
	}

}
