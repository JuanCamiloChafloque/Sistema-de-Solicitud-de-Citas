package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modelo.Solicitud;

import javax.swing.JButton;

public class PanelBusquedaSolicitud extends JPanel implements ActionListener{


	private static final long serialVersionUID = 1L;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Ingreso de un String para decir el número de solicitud
	 */
	private JTextField txtSolicitud;
	/**
	 * Botón para que se pueda buscar una solicitud
	 */
	private JButton btnBuscarSolicitud;
	/**
	 * Boton para regresar al menu principal
	 */
	private JButton btnMenuPrincipal;
	/**
	 * Constante que asocia el bóton de buscar con una determinada acción
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
	 * Se instancia un panel de tipo PanelSolicitud que muestra un panel donde el usuario puede digitar el número de una solicitud para
	 * que el sistema muestre la información general de dicha solicitud
	 */
	public PanelBusquedaSolicitud(InterfazGrafica pInterfaz) {
		
		interfaz = pInterfaz;
		
		setBackground(Color.BLACK);
		setSize(700, 600);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Consulta de Solicitudes");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(204, 11, 293, 62);
		add(lblTitulo);
		
		JLabel lblNmeroDeSolicitud = new JLabel("Número de Solicitud");
		lblNmeroDeSolicitud.setForeground(Color.WHITE);
		lblNmeroDeSolicitud.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNmeroDeSolicitud.setBounds(189, 112, 139, 14);
		add(lblNmeroDeSolicitud);
		
		txtSolicitud = new JTextField();
		txtSolicitud.setBounds(388, 111, 86, 20);
		add(txtSolicitud);
		txtSolicitud.setColumns(10);
		
		btnBuscarSolicitud = new JButton("Buscar Solicitud");
		btnBuscarSolicitud.setBounds(189, 163, 130, 23);
		btnBuscarSolicitud.setBackground(Color.LIGHT_GRAY);
		btnBuscarSolicitud.addActionListener(this);
		btnBuscarSolicitud.setActionCommand(clickSolicitud);
		add(btnBuscarSolicitud);
		
		btnMenuPrincipal = new JButton("Menu Principal");
		btnMenuPrincipal.setBounds(344, 163, 130, 23);
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
		
		int encontrado = 0;
		
		if(evento.getActionCommand().equalsIgnoreCase(clickSolicitud)){
			if(txtSolicitud.getText().equalsIgnoreCase("")){
				JOptionPane.showMessageDialog(null, "Digite un número de solicitud", "Error", 0);
			}else{
				List<Solicitud> solicitudes = interfaz.darSolicitudes();
				if(solicitudes.size() == 0){
					JOptionPane.showMessageDialog(null, "No se han hecho solicitudes", "Error", 0);
				}else{
					
					for(Solicitud solicitudActual: solicitudes){
						if(solicitudActual.getCodigo() == Integer.parseInt(txtSolicitud.getText())){
							encontrado++;
							interfaz.crearPanelSolicitud(solicitudActual);
						}
					}
					
					if(encontrado == 0){
						JOptionPane.showMessageDialog(null, "No hay una solicitud con el número ingresado", "Error", 0);
					}
				}
				
			}
			
		}else if(evento.getActionCommand().equalsIgnoreCase(clickMenu)){
			interfaz.asignarVentanaActual(0);
		}
	}
	/**
	 * Retorna el texto que se ingresó en el textField de número de solicitud
	 * @return
	 */
	public String darTxtSolicitud(){
		return txtSolicitud.getText();
	}
}
