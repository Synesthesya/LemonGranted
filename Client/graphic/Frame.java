package graphic;

import interfaces.Controller;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * classe della finestra principale
 * 
 * @author Giorgio
 * @author Alex
 *
 */
public class Frame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * icona (quella visualizzata in alto a sinistra)
	 */
	public static final ImageIcon ICON = new ImageIcon("mockup");
	
	/**
	 * il panel attualmente visualizzato nella finestra
	 */
	private JPanel panel;
	
	/**
	 * costruttore che implica che si parta direttamente con la battaglia, escludendo il menù
	 * @param ID l'ID del giocatore
	 * @param c MyShipController
	 * @param i controllore dell'informazioni
	 */
	@Deprecated
	public Frame(int ID, Controller c, Controller i) {
		
		super("Starship!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		/*
		 * codice di mockup
		 */
		
		panel=new Game(ID, c, null);
		add(panel); 
	//	this.pack();
		this.setSize(1200, 800);
		
	}
	
	//MOCKUP
	/**
	 * metodo che ritorna il panel (sotto forma di Game)
	 * @return
	 */
	public Game getPanel() {
		return (Game)panel;
	}

}

/* VECCHIO CODICE
 * package graphic;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import control.Controller;

public class Frame extends JFrame {
	
	public static final ImageIcon ICON = new ImageIcon("mockup");
	private JPanel panel;
	
	public Frame(Controller l, Controller r, Controller i) {
		
		super("Starship!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	
		 * codice di mockup
		
		
		panel=new Game(l,r,i);
		add(panel);
		
		this.pack();
		
	}
	
	//MOCKUP
	public Game getPanel() {
		return (Game)panel;
	}

} */


