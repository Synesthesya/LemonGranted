package graphic;

import interfaces.Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * WARNING: non è il massimo dell'estetica (il codice, non la grafica)
 */

/**
 * 
 * classe che rappresenta il gruppo di informazioni necessarie durante il gioco
 * 
 * @author Alex
 * @author Giorgio
 *
 */
public class Information extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * il nome del giocatore
	 */
	public static String playerName="player";
	
	/**
	 * il nick del giocatore
	 */
	//private final JLabel player; // mettere JLabel
	private final JLabel player;
	
	/**
	 *  Label dell'impero
	 */
	private final JLabel empireLogo;
	
	/**
	 *  Label dei ribelli
	 */
	private final JLabel rebelsLogo;
	
	/**
	 * lo status delle navi del giocatore
	 */
	private final JLabel myShip1; // mettere JLabel
	private final JLabel myShip2;
	private final JLabel myShip3;
	private final JLabel myShip4;
	private final JLabel myShip5;
	/**
	 * lo status delle navi dell'avversario
	 */
	private final JLabel enemyShip1; // mettere JLabel
	private final JLabel enemyShip2;
	private final JLabel enemyShip3;
	private final JLabel enemyShip4;
	private final JLabel enemyShip5;
	/**
	 * lo status del turno
	 */
	private final JLabel status; // mettere JLabel
	/**
	 * il controllore che comunica tra i due giocatori
	 */
	private final Controller control;
	
	/**
	 * costruttore standard 
	 * @param ID l'id del giocatore
	 * @param c il controllore
	 */
	public Information(int ID,Controller c) {
		
		super();
		control=c;
		player=new JLabel();
		ImageIcon icon = new ImageIcon(".//bin//lightsaber.png");
		player.setIcon(icon);
		player.setText(playerName);
		player.setHorizontalTextPosition(JLabel.CENTER);
		player.setVerticalTextPosition(JLabel.CENTER);
		
// ---------------- POSIZIONAMENTO MIE NAVI --------------------------------
		empireLogo = new JLabel();
		myShip1 = new JLabel();
		myShip2 = new JLabel();
		myShip3 = new JLabel();
		myShip4 = new JLabel();
		myShip5 = new JLabel();
		ImageIcon myShipIcon = (ID==1) ? new ImageIcon(".//bin//TF.png") : new ImageIcon(".//bin//XW_Square.png");
		ImageIcon empireLogoIcon = new ImageIcon(".//bin//empireLogo.png");
		empireLogo.setIcon(empireLogoIcon);
		myShip1.setIcon(myShipIcon);
		myShip2.setIcon(myShipIcon);
		myShip3.setIcon(myShipIcon);
		myShip4.setIcon(myShipIcon);
		myShip5.setIcon(myShipIcon);
		
		JPanel myShipPanel = new JPanel();
		myShipPanel.setLayout(new FlowLayout());
		myShipPanel.setBackground(Color.black);
		myShipPanel.add(empireLogo);
		myShipPanel.add(myShip1);
		myShipPanel.add(myShip2);
		myShipPanel.add(myShip3);
		myShipPanel.add(myShip4);
		myShipPanel.add(myShip5);
		
		// ---------------- POSIZIONAMENTO NAVI NEMICHE --------------------------------
		rebelsLogo = new JLabel();
		enemyShip1=new JLabel();
		enemyShip2=new JLabel();
		enemyShip3=new JLabel();
		enemyShip4=new JLabel();
		enemyShip5=new JLabel();
		ImageIcon enemyIcon = (ID==1) ? new ImageIcon(".//bin//XW_Square.png") : new ImageIcon(".//bin//TF.png");
		ImageIcon rebelsLogoIcon = new ImageIcon(".//bin//rebelsLogo.png");
		rebelsLogo.setIcon(rebelsLogoIcon);
		enemyShip1.setIcon(enemyIcon);
		enemyShip2.setIcon(enemyIcon);
		enemyShip3.setIcon(enemyIcon);
		enemyShip4.setIcon(enemyIcon);
		enemyShip5.setIcon(enemyIcon);
		
		JPanel enemyPanel = new JPanel();
		enemyPanel.setLayout(new FlowLayout());
		enemyPanel.setBackground(Color.black);
		enemyPanel.add(enemyShip1);
		enemyPanel.add(enemyShip2);
		enemyPanel.add(enemyShip3);
		enemyPanel.add(enemyShip4);
		enemyPanel.add(enemyShip5);
		enemyPanel.add(rebelsLogo);
		
		// ---------------- POSIZIONAMENTO STATUS --------------------------------
		status=new JLabel("status");
		status.setForeground(Color.red);
		
		/*
		 * WARNING
		 * gestire corretamente il layout
		 */
		
		JLabel asd = new JLabel("\n\n");
		
		/*
		 *  WARNING
		 *  label alla cazzo per fare spazio
		 * 
		 */
		
		this.setLayout(new BorderLayout(100, 0));
		status.setAlignmentX(500);
		
		add(player, "North");
	    add(myShipPanel, "West");
		add(status, "Center");
		add(enemyPanel, "East");
		// RIMUOVERE
	//	add(asd, "South");
	}
	
	/*
	 * WARNING getMyShip anche per le altre ship
	 */
	public JLabel getMyShip() { // mettere JLabel
		return myShip1;
	}
/*	public JLabel getEnemyShip() { // mettere Jabel
		return enemyShip;
	} */
	public JLabel getStatus() { // mettere Jlabel
		return status;
	}
	
	//MOCKUP
	public void mockup(String s) {
		status.setText(s);
	}

}








/* package graphic;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import control.Controller;

public class Information extends JPanel {


	 * #crescile(R)
	 *
	private static final long serialVersionUID = 1L;
	
	/**
	 * il nome del giocatore
	 *
	public static String playerName= "Giocatore 1";
	
	/**
	 * il nick del giocatore
	 *
	private final JLabel player;
	/**
	 * lo status delle navi del giocatore
	 *
	private final JLabel myShip;
	/**
	 * lo status delle navi dell'avversario
	 *
	private final JLabel enemyShip;
	/**
	 * lo status del turno
	 *
	private final JLabel status;
	/**
	 * il controllore che comunica tra i due giocatori
	 *
	private final Controller control;
	
	/**
	 * costruttore standard 
	 * @param c
	 *
	public Information(Controller c) {
		
		super();
		control=c;
		player=new JLabel(playerName);
		
		/*
		 * WARNING
		 * gestire correamente i label
		 *
		
		myShip=new JLabel();
		enemyShip=new JLabel();
		status=new JLabel();
		
		
		/*
		 * WARNING
		 * gestire corretamente il layout
		 *
		
		this.setLayout(new GridLayout(2,2));
		
		add(player);
		add(myShip);
		add(status);
		add(enemyShip);		
	}
	
	public JLabel getMyShip() {
		return myShip;
	}
	public JLabel getEnemyShip() {
		return enemyShip;
	}
	public JLabel getStatus() {
		return status;
	}
	
	
	//MOCKUP
	public void mockup(String s) {
		status.setText(s);
	}

} */
