package graphic;

import java.awt.Dimension;
import java.io.IOException;
import graphic.menu.EndGame;
import graphic.menu.ErrorPopUp;
import graphic.menu.MainMenu;
import graphic.menu.OptionPanel;
import interfaces.Controller;
import sound.Effect;
import sound.MusicPlayer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import control.MenuController;


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
	 * l'ID del giocatore, ovvero la fazione
	 * 1 impero
	 * 2 ribelli
	 */
	private int ID=1;
	
	/**
	 * oggetto che si preoccupa dell'esecuzione degli effetti audio
	 */
	private final Effect sound;
	
	/**
	 * classe che si preoccupa dell'esecuzione della musica
	 */
	private final MusicPlayer music;
	
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
		panel=new Game(ID, null, c);
		add(panel); 
		this.setSize(1200, 800);
		sound=new Effect();
		music=new MusicPlayer();
	}
	
	/**
	 * costruttore standard
	 */
	public Frame() {
		
		super("StarBattle!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setIconImage(ICON.getImage());
		sound=new Effect();
		music=new MusicPlayer();
	}
	

	/**
	 * metodo per ottenere il pannello attualmente utilizzato
	 * @return un oggetto JPanel
	 */
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
		playMusic(0);
	}
	
	/**
	 * metodo per collocare il game all'interno del frame
	 * 
	 * @param msc il Controller del Game
	 */
	public void setGame(Controller msc) {
		
		this.remove(panel);
		panel=new Game(ID,nick, msc);		
		add(panel);
		revalidate();
		playMusic(ID);
	}
	
	/**
	 * metodo per aprire il pannello delle opzioni
	 * 
	 * @param msc
	 */
	public void setOption(MenuController msc) {
		
		this.remove(panel);
		panel=new OptionPanel(msc, ID);
		add(panel);
		revalidate();
		playMusic(3);
	}
	
	/**
	 * metodo per aprire il pannello di fine gioco
	 * 
	 * @param msc il controller
	 * @param win la fazione
	 */
	public void setEnd(MenuController msc, int win) {
		
		this.remove(panel);
		panel=new EndGame(msc,win);
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
	
	/**
	 * metodo per settare la fazione
	 * 
	 * @param i <b>1</b> impero, <b>2</b> ribelli
	 */
	public void setID(int i) {
		ID=i;
	}
	
	/**
	 * metodo per ottenere la fazione 
	 * 
	 * @return <b>1</b> impero, <b>2</b> ribelli
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * 
	 * metodo per far partire un effetto sonoro della propria fazione
	 * 
	 * @param number il numero della traccia da far partire
	 */
	public void playSound(int number) {
		
		playSound(ID,number);
	}
	
	/**
	 * metodo per far partire un qualunque effetto sonoro
	 * @param id la fazione, 1 impero, 2 ribelli, 0 neutrale
	 * @param number la traccia da far partire
	 */
	public void playSound(int id, int number) {
		
		try {
			sound.load(id, number);
		}
		catch(IOException e) {
			@SuppressWarnings("unused")
			ErrorPopUp er=new ErrorPopUp("errore: impossibile eseguire l'effetto audio "+id+" "+number);
		}
	}
	
	public void playMusic(int number) {
		
		try {
			music.load(number);
		} catch (IOException e) {
			@SuppressWarnings("unused")
			ErrorPopUp er=new ErrorPopUp("errore: impossibile eseguire il file musicale "+number);
		}
	}
	


}
