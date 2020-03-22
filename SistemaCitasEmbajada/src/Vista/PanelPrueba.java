package Vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelPrueba extends JPanel {

	  private JLabel tituloMenu;
	    private JLabel tituloSeleccion;
	    private JButton btnSuscribirse;
	    private JButton btnBusqueda;
	    
	    //
	    //CONSTANTES
	    //
	    private static final String SUSCRIBIRSE = "Suscribirse";
	    private static final String BUSQUEDA = "Busqueda";
	    
	    //
	    //RELACIONES
	    //
	    private InterfazGrafica interfaz;
	    private JTextField textField;
	    
	    //
	    //CONSTRUCTOR
	    //
	    public PanelPrueba(){
	        

	        
	        setBackground(new Color(153, 204, 153));
	        setSize(500, 400);
	        setLayout(null);
	        
	        tituloMenu = new JLabel("SpotDeezer");
	        tituloMenu.setFont(new Font("Tahoma", Font.PLAIN, 30));
	        tituloMenu.setForeground(Color.BLACK);
	        tituloMenu.setBounds(167, 29, 198, 80);
	        add(tituloMenu);
	        
	        tituloSeleccion = new JLabel("Seleccione una opción");
	        tituloSeleccion.setFont(new Font("Tahoma", Font.BOLD, 15));
	        tituloSeleccion.setForeground(Color.WHITE);
	        tituloSeleccion.setBounds(10, 93, 198, 80);
	        add(tituloSeleccion);
	        
	        btnBusqueda = new JButton("Buscar Canción");
	        btnBusqueda.setBackground(Color.LIGHT_GRAY);
	        btnBusqueda.setBounds(139, 228, 212, 29);
	        add(btnBusqueda);
	        
	        btnSuscribirse = new JButton("Nuevo Usuario");
	        btnSuscribirse.setBackground(Color.LIGHT_GRAY);
	        btnSuscribirse.setBounds(139, 307, 212, 29);
	        add(btnSuscribirse);
	        
	        textField = new JTextField();
	        textField.setBounds(139, 203, 212, 20);
	        add(textField);
	        textField.setColumns(10);

	}
}
