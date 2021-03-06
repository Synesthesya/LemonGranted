package graphic;

import interfaces.Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * pannello principale del gioco
 * 
 * @author Alex
 * @author Giorgio
 *
 */
public class Game extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * il logo
	 */
	public static final String LOGO = ".//bin//logo1.png";
	
	/**
	 * parte grafica delle griglie 
	 */
	private final GridCouple grids;
	
	/**
	 * parte grafica contenente le informazioni
	 */
	private final Information info;
	
	
	/**
	 * costruttore standard
	 * 
	 * @param ID id del giocatore 
	 * @param name il nome del giocatore
	 * @param c MyShipController
	 */
	public Game(int ID, String name, Controller c) {
		
		super();
		grids=new GridCouple(ID,c);
		grids.setBackground(Color.black); 
		info=new Information(ID, name);
		info.setBackground(Color.black); 
		
		
		setLayout(new BorderLayout());
		
		JLabel ab = new JLabel();
		ImageIcon icon = new ImageIcon(LOGO);
		ab.setIcon(icon);
		add(ab, BorderLayout.NORTH);
		add(info, BorderLayout.CENTER);
		add(grids, BorderLayout.SOUTH);
	}
	
	/**
	 * metodo per ottenere il contenitore delle griglie
	 * @return
	 */
	public GridCouple getGrids() {
		return grids;
	}
	
	/**
	 * metodo per ottenere il contenitore delle informazioni
	 * @return
	 */
	public Information getInfo() {
		return info;
	}
	
}
