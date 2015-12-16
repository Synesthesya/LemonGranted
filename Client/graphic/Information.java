package graphic;

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
	 * il nick del giocatore
	 */
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
	//private final Controller control;
	
	/**
	 * costruttore standard 
	 * @param ID l'id del giocatore
	 */
	public Information(int ID) {
		
		super();
		//control=c;
		player=new JLabel();
		ImageIcon icon = new ImageIcon(".//bin//lightsaber.png");
		player.setIcon(icon);
		player.setText("Giocatore "+ID);
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
		status=new JLabel();
		status.setForeground(Color.red);
		
		this.setLayout(new BorderLayout(100, 0));
		status.setAlignmentX(500);
		
		add(player, "North");
	    add(myShipPanel, "West");
		add(status, "Center");
		add(enemyPanel, "East");
	}
	
	public JLabel getMyShip()
	{
		return myShip1;
	}
	public JLabel getStatus()
	{
		return status;
	}
	
	/**
	 * cambia il testo del messagio
	 * @param s il nuovo messaggio
	 */
	public void setStatus(String s) {
		status.setText(s);
	}

}
