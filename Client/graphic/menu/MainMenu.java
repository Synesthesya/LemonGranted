package graphic.menu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.*;
import control.MenuController;


/**
 * Classe che rappresenta graficamente il menù principale
 * 
 * @author Giorgio
 * @author Alex
 *
 */
public class MainMenu extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		/**
		 * percorso dell'immagine di sfondo
		 */
		public static final String EMPIRE_BACKGROUND = ".//bin//vader.png";
		
		/**
		 * numero di bottoni del menù
		 */
		public static final int BUTTON_NUMBER = 4;
		
		/**
		 * i nomi dei pulsanti
		 */
		public static final String[] NAME = {"MULTIPLAYER", "SINGLEPLAYER","OPTIONS","QUIT"};
		
		/**
		 * i valori di altezza dei pulsanti
		 */
		public static final int[] BOUND = {50, 200, 350, 500};
		
	  	/**
	  	 * l'immagine di sfondo
	  	 */
		private Image img;
		
		/**
		 * i pulsanti del menù
		 */
		private JButton[] buttons=new JButton[BUTTON_NUMBER];
	  
		/**
		 * costruttore standard
		 * 
		 * @param mc il controller dei pulsanti
		 * @throws IOException
		 */
	  public MainMenu(MenuController mc) {
		/*
		 * Inizializzazione ed inserimento traccia musicale
		 */
		  try {
			String gongFile = ".//bin//Main.wav";
			InputStream in = new FileInputStream(gongFile);
			AudioStream audio = new AudioStream(in);
			AudioPlayer.player.start(audio);
		  }
		  catch(Exception e) {
			  ErrorPopUp er=new ErrorPopUp("errore musicale!");
		  }
		  
	    setImage(EMPIRE_BACKGROUND);
	    setLayout(null);
	    
	    for(int i=0; i<BUTTON_NUMBER; i++) {
	    	buttons[i]=new JButton(NAME[i]);
	    	buttons[i].addActionListener(mc);
	    	buttons[i].setActionCommand(control.MenuController.MAINMENU[i]);
	    	add(buttons[i]);
	    	buttons[i].setBounds(75,BOUND[i],300,126);
	    }
	    
	    /*
	     * Inizializzazione icone
	     */
	    ImageIcon iconB1 = new ImageIcon(".//bin//BattagliaSpazialeButton1.jpg");
	    ImageIcon iconB2 = new ImageIcon(".//bin//BattagliaSpazialeButton2.jpg");
	    ImageIcon iconB3 = new ImageIcon(".//bin//BattagliaSpazialeButton3.jpg");
	    ImageIcon iconB4 = new ImageIcon(".//bin//BattagliaSpazialeButton4.jpg");
	    
	    buttons[0].setIcon(iconB1);
	    buttons[1].setIcon(iconB2);
	    buttons[2].setIcon(iconB3);
	    buttons[3].setIcon(iconB4);
	    
	    /*
	     * Inizializzazione logo
	     */
	    JLabel title = new JLabel("");
	    ImageIcon iconTitle = new ImageIcon(".//bin//logoMain.png");
	    title.setIcon(iconTitle);
	    title.setBounds(750, 600, 400, 136);
	    add(title);
	    
	    /*
	     * Inizializzazione credits
	     */
	    JLabel credits = new JLabel("STAR WARS © TM 2016 LemonGranted Ltd. All rights reserved.");
	    credits.setForeground(Color.GRAY);
	    credits.setBounds(20, 660, 600, 136);
	    add(credits);
	  }
	  
	  /**
	   * metodo utilizzato per caricare un'immagine come sfondo
	   * 
	   * @param img
	   */
	  private void loadImage(Image img) {
	    try {
	      MediaTracker track = new MediaTracker(this);
	      track.addImage(img, 0);
	      track.waitForID(0);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
	  
	  /**
	   * metodo ereditato dalla classe JPanel
	   * 
	   */
	  @Override
	  protected void paintComponent(Graphics g) {
	    setOpaque(false);
	    g.drawImage(img, 0, 0, null);
	    super.paintComponent(g);
	  }
	  
	  /**
	   * metodo per settare un'immagine come sfondo
	   * @param path il percorso dell'immagine
	   */
	  public void setImage(String path) {
		  img = Toolkit.getDefaultToolkit().createImage(EMPIRE_BACKGROUND);
		  loadImage(img);
	  }
	  
	  /**
	   * metodo di get dell'immagine di sfondo
	   * @return
	   */
	  public Image getImage() {
		  return img;
	  }

	}
