package Vista;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import Modelo.PaisEmbajada;
import java.awt.Color;

public class PanelAsociarPais extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//ATRIBUTOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Tiene un atributo de tipo JComboBox que muestra los diferentes paises que el sistema tiene habilitados para que el usuario escoja uno para
	 * relacionarlo con la embajada
	 */
	private JComboBox<PaisEmbajada> seleccionPais;
	/**
	 * Tiene un atributo de tipo JLabel que muestra un titulo que acompa�a al ComboBox
	 */
	private JLabel lblPais;
	/**
	 * Tiene un atributo de tipo JLabel que muestra un titulo para el panel
	 */
	private JLabel lblTitulo;
	/**
	 * Tiene un atributo de tipo JButton que permite al sistema acceder al archivo de paises cuando el usuario oprima el mismo
	 */
	private JButton btnArchivo;
	/**
	 * Boton para regresar al menu principal
	 */
	private JButton btnMenPrincipal;
	/**
	 * Constante que asocia el bot�n de de buscar archivo con una determinada acci�n
	 */
	private static final String clickBtn = "C_BTN";
	/**
	 * Constante que asocia el comboBox con una determinada acci�n
	 */
	private static final String clickCombo = "C_COMBO";
	/**
	 * Constante que asocia el b�ton de menu con una determinada acci�n
	 */
	private static final String clickMenu = "C_MENU";
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//RELACIONES
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	private InterfazGrafica interfaz;
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Se instancia un panel de tipo PanelAsociarPais que muestra toda la informaci�n para la relaci�n de pais-embajada
	 */
	public PanelAsociarPais(InterfazGrafica pInterfaz){
		
		interfaz = pInterfaz;
		
		setBackground(Color.BLACK);
		setSize(700, 600);
		setLayout(null);
		
		seleccionPais = new JComboBox<PaisEmbajada>();
		seleccionPais.setBounds(376, 162, 179, 20);
		seleccionPais.setBackground(Color.WHITE);
		seleccionPais.setActionCommand(clickCombo);
		seleccionPais.addActionListener(this);
		add(seleccionPais);
		
		lblPais = new JLabel("Pa�s");
		lblPais.setForeground(Color.WHITE);
		lblPais.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblPais.setBounds(169, 162, 46, 20);
		add(lblPais);
		
		lblTitulo = new JLabel("Asociar Pa�s a Embajada");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTitulo.setBounds(204, 11, 293, 62);
		add(lblTitulo);
		
		btnArchivo = new JButton("Buscar Archivo");
		btnArchivo.setBounds(204, 279, 134, 23);
		btnArchivo.setBackground(Color.LIGHT_GRAY);
		btnArchivo.setActionCommand(clickBtn);
		btnArchivo.addActionListener(this);
		add(btnArchivo);
		
		btnMenPrincipal = new JButton("Men� Principal");
		btnMenPrincipal.setBounds(359, 279, 127, 23);
		btnMenPrincipal.setBackground(Color.LIGHT_GRAY);
		btnMenPrincipal.setActionCommand(clickMenu);
		btnMenPrincipal.addActionListener(this);
		add(btnMenPrincipal);
		
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//M�TODOS
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que permite relacionar los botones del panel con una determinada acci�n dependiendo del boton que el usuario presion�
	 */
	public void actionPerformed(ActionEvent evento) {
		
		if(evento.getActionCommand().equalsIgnoreCase(clickBtn)){
			
			JFileChooser archivo = new JFileChooser();
			archivo.setCurrentDirectory(new java.io.File("."));
			archivo.setDialogTitle("Seleccione un Archivo");
			if(archivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				if(archivo.getSelectedFile().getName().equalsIgnoreCase("PaisesEmbajada.txt")){
					JOptionPane.showMessageDialog(interfaz, "Archivo seleccionado exitosamente");
					seleccionPais.setModel(new DefaultComboBoxModel<PaisEmbajada>(PaisEmbajada.values()));
					JOptionPane.showMessageDialog(interfaz, "Seleccione un pa�s");
				}else{
					JOptionPane.showMessageDialog(interfaz, "En el archivo seleccionado no se encontr� informaci�n sobre los pa�ses");
				}	
			}
		}
		
		if(evento.getActionCommand().equalsIgnoreCase(clickCombo)){
			interfaz.asignarPaisEmbajada();
			interfaz.crearBandera(darPaisSeleccionado());
		}
		
		if(evento.getActionCommand().equalsIgnoreCase(clickMenu)){
			interfaz.asignarVentanaActual(0);
		}
	}
	/**
	 * Retorna el pa�s que actualmente esta seleccionado en el Combo Box
	 * @return
	 */
	public String darPaisSeleccionado(){
		return seleccionPais.getSelectedItem().toString();
	}
}
