package graphic;

import java.awt.Dimension;
import java.io.IOException;

import graphic.menu.MainMenu;
import interfaces.Controller;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.MenuController;
import control.MyShipController;

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
	public static final ImageIcon ICON = new ImageIcon(".//bin//Icon.png");
	
	public static final Dimension SIZE = new Dimension(1200,800);
	
	/**
	 * il panel attualmente visualizzato nella finestra
	 */
	private JPanel panel;
	
	/**
	 * <p>
	 * costruttore che implica che si parta direttamente con la battaglia, escludendo il menù.
	 * </p>
	 * <p>
	 * è stato mantenuto per esigenze di debug
	 * </p>
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
		
		panel=new Game(ID, c);
		add(panel); 
		this.setSize(1200, 800);
		
	}
	
	/**
	 * costruttore standard
	 */
	public Frame() {
		
		super("StarBattle!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(ICON.getImage());

	}
	
	/*
	//MOCKUP
	/**
	 * metodo che ritorna il panel sotto forma di Game
	 * @return il caste del panel come Game
	 */
	@Deprecated
	public Game getGamePanel() {
		return (Game)panel;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * metodo che colloca il Menù principale nel frame; si assume sia il primo metodo eseguito
	 * 
	 * @param mc il MenuController
	 * @throws IOException 
	 */
	public void initialize(MenuController mc) throws IOException {
		
		panel=new MainMenu(mc);
		add(panel);	
		setVisible(true);
		this.setSize(1200,800);
	}
	
	/**
	 * metodo che colloca il menù principale nel frame; utilizzato dalla seconda volta in avanti
	 * 
	 * @param mc il MenuController del menù principale
	 * @throws IOException 
	 */
	public void setMenu(MenuController mc) throws IOException {
		
		remove(panel);
		panel=new MainMenu(mc);
		add(panel);
	}
	
	/**
	 * metodo per collocare il game all'interno del frame
	 * 
	 * @param msc il Controller del Game
	 */
	public void setGame(int id, Controller msc) {
		
		this.remove(panel);
		panel=new Game(id,msc);		
		add(panel);
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


