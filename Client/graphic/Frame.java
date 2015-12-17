package graphic;

import java.awt.Dimension;
import java.io.IOException;

import graphic.menu.MainMenu;
import graphic.menu.OptionPanel;
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
	
	/**
	 * le dimensioni del frame
	 */
	public static final Dimension SIZE = new Dimension(1200,800);
	
	/**
	 * il panel attualmente visualizzato nella finestra
	 */
	private JPanel panel;
	
	/**
	 * il nome del giocatore
	 */
	private String nick = "Giocatore 1";
	
	/**
	 * <p>
	 * costruttore che implica che si parta direttamente con la battaglia, escludendo il men�.
	 * </p>
	 * <p>
	 * � stato mantenuto per esigenze di debug
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
		
		panel=new Game(ID, null, c);
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
	 * metodo che colloca il Men� principale nel frame; si assume sia il primo metodo eseguito
	 * 
	 * @param mc il MenuController
	 * @throws IOException 
	 */
	public void initialize(MenuController mc) {
		
		panel=new MainMenu(mc);
		add(panel);	
		setVisible(true);
		this.setSize(1200,800);
	}
	
	/**
	 * metodo che colloca il men� principale nel frame; utilizzato dalla seconda volta in avanti
	 * 
	 * @param mc il MenuController del men� principale
	 * 
	 */
	public void setMenu(MenuController mc) {
		
		remove(panel);
		panel=new MainMenu(mc);
		add(panel);
		revalidate();
	}
	
	/**
	 * metodo per collocare il game all'interno del frame
	 * 
	 * @param msc il Controller del Game
	 */
	public void setGame(int id, Controller msc) {
		
		this.remove(panel);
		panel=new Game(id,nick, msc);		
		add(panel);
		revalidate();
	}
	
	public void setOption(MenuController msc) {
		
		this.remove(panel);
		panel=new OptionPanel(msc);
		add(panel);
		revalidate();
	}
	
	/**
	 * metodo per settare il nome del giocatore
	 * 
	 * @param s il nick del giocatore
	 */
	public void setName(String s) {
		nick=s;
	}
	
	/**
	 * metodo per ottenere il nick del giocatore
	 * 
	 * @return il nick del giocatore
	 */
	public String getName() {
		return nick;
	}


}
