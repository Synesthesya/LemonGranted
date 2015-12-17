package graphic.menu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import control.MenuController;

/**
 * Classe che rappresenta graficamente il men� principale
 * 
 * @author Giorgio
 * @author Alex
 *
 */
public class OptionPanel extends JPanel {
	
	/**
	 * il percorso delle immagini
	 */
	public static final String PATH = ".//bin//";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * dove scrivere il nome del giocatore
	 */
	private JTextArea nuovo;
	/**
	 * il nome modificato del giocatore
	 */
	private JTextArea risultato;
	/**
	 * 
	 */
	private JTextArea risBlock;
	/**
	 * 
	 */
	private JTextArea actual;
	/**
	 * sfondo
	 */
	private Image background;

		/**
		 * costruttore standard
		 * @param mc TODO
		 */
	  @SuppressWarnings("deprecation")
	public OptionPanel(MenuController mc) {
		/*
		 * Richiama il metodo che dipinge l'immagine di sfondo.
		 */
		
		  super();
	    background = Toolkit.getDefaultToolkit().createImage(PATH+"what.jpg");
	    loadImage(background);
	    
	    JLabel forza = new JLabel("CLICCA SUL LOGO PER SCEGLIERE UNA FAZIONE");
	    forza.setForeground(Color.GRAY);
	    forza.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    add(forza);
	    
	    JLabel fazione = new JLabel("Attuale fazione: ");
	    fazione.setForeground(Color.GRAY);
	    fazione.setFont(new Font("Times New Roman", Font.BOLD, 16));
	    add(fazione);
	    
	    actual = new JTextArea("Impero");
		add(actual);
		actual.setFont(new Font("Times New Roman", Font.BOLD, 16));
		actual.setBackground(Color.BLACK);
		actual.setForeground(Color.GRAY);
		actual.enable(false);
	    
	    JLabel nomeAttuale = new JLabel("Nome attuale: ");
	    nomeAttuale.setForeground(Color.GRAY);
		add(nomeAttuale);
	    
		JLabel inserisciNome = new JLabel("Inserisci nome: ");
		inserisciNome.setForeground(Color.GRAY);
		add(inserisciNome);
		nuovo = new JTextArea("");
		add(nuovo);
		
		/*
		 * INIZIO PARTE PULSANTI
		 */
		
		JButton inputButton = new JButton("Conferma");
		inputButton.setActionCommand("NOME");
		inputButton.addActionListener(mc);
		add(inputButton);
		
		JButton back = new JButton("<- BACK");
		back.setActionCommand("MM");
		back.addActionListener(mc);
		add(back);
		
		JButton empire = new JButton("empire");
		empire.setActionCommand("EMPIRE");
		empire.addActionListener(mc);
		add(empire);
		ImageIcon logoImpero = new ImageIcon(PATH+"LogoImpero.png");
		empire.setIcon(logoImpero);
		empire.setBorderPainted(false);
		
		JButton rebels = new JButton("rebels");
		rebels.setActionCommand("REBELS");
		rebels.addActionListener(mc);
		add(rebels);
		ImageIcon logoRibelli = new ImageIcon(PATH+"LogoRibelli.png");
		rebels.setIcon(logoRibelli);
		rebels.setBorderPainted(false);
		
		risultato = new JTextArea("Giocatore 1");
		add(risultato);
		risultato.setBackground(Color.BLACK);
		risultato.setForeground(Color.GRAY);
		risultato.enable(false);
		
		//inputButton.addActionListener(this);
		
		/*
		 * INIZIO PARTE BOUNDS
		 */
		
		this.setLayout(null);
		back.setBounds(1050, 25, 100, 50);
		nomeAttuale.setBounds(50, 50, 800, 50);
		inserisciNome.setBounds(50, 150, 150, 50);
		nuovo.setBounds(200, 165, 200, 20);
		inputButton.setBounds(450, 155, 200, 40);
		risultato.setBounds(165, 68, 350, 50);
		actual.setBounds(255, 416, 500, 50);
		forza.setBounds(105, 370, 500, 50);
		fazione.setBounds(105, 400, 500, 50);
		empire.setBounds(70, 470, 200, 200);
		rebels.setBounds(350, 470, 200, 200);
	  }
	  
	  /*
		public void actionPerformed(ActionEvent e) {
			String errore = null;
			
			try {
				errore = nuovo.getText();
			} catch (Exception ecc) {System.out.println(ecc.getMessage());}
			
			risultato.setText(errore);
		}
		*/
		
		
		/**
		 * metodo che risponde alla pressione del pulsante conferma
		 */
		public String setName() {
			
			String s="";
			try {
				s=nuovo.getText();
			}
			catch(Exception e) {
				System.out.println("porco dio");
			}
			risultato.setText(s);	
			return s;
		}
		
		/**
		 * metodo per caricare l'immagine di sfondo
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
	   * metodo ereditato
	   */
	  @Override
	  protected void paintComponent(Graphics g) {
	    setOpaque(false);
	    g.drawImage(background, 0, 0, null);
	    super.paintComponent(g);
	  }

	}
