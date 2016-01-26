package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Player;


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
	 * lo status delle navi del giocatore
	 */
	private final JLabel[] myShip=new JLabel[Player.FLEETNUMBER];
	
	/**
	 * lo status delle navi dell'avversario
	 */
	public final JLabel[] enemyShip=new JLabel[Player.FLEETNUMBER];
	
	/**
	 * contatore delle proprie navi ancora in vita
	 */
	private int myFleet=Player.FLEETNUMBER-1;
	
	/**
	 * contatore delle navi nemiche ancora in vita
	 */
	private int enemyFleet=0;
	
	
	/**
	 * lo status del turno
	 */
	private final JLabel status; 

	
	/**
	 * costruttore standard 
	 * @param ID l'id del giocatore
	 * @param name il nick del giocatore
	 */
	public Information(int ID, String name) {
		
		super();
		player=new JLabel();
		ImageIcon icon = new ImageIcon(".//bin//lightsaber.png");
		player.setIcon(icon);
		player.setText(name);
		player.setHorizontalTextPosition(JLabel.CENTER);
		player.setVerticalTextPosition(JLabel.CENTER);
		
// ---------------- POSIZIONAMENTO MIE NAVI --------------------------------

		
		ImageIcon myShipIcon = (ID==1) ? new ImageIcon(".//bin//TF.png") : new ImageIcon(".//bin//XW_Square.png");
		ImageIcon enemyShipIcon = (ID==2) ? new ImageIcon(".//bin//TF.png") : new ImageIcon(".//bin//XW_Square.png");
		ImageIcon myLogo = (ID==1) ? new ImageIcon(".//bin//empireLogo.png") : new ImageIcon(".//bin//rebelsLogo.png");
		ImageIcon enemyLogo = (ID==2) ? new ImageIcon(".//bin//empireLogo.png") : new ImageIcon(".//bin//rebelsLogo.png");
		
		JPanel myShipPanel = new JPanel();
		myShipPanel.setLayout(new FlowLayout());
		myShipPanel.setBackground(Color.black);
		
		JPanel enemyPanel= new JPanel();
		enemyPanel.setLayout(new FlowLayout());
		enemyPanel.setBackground(Color.black);
		
		myShipPanel.add(new JLabel(myLogo));
		
		for(int i=0; i<Player.FLEETNUMBER; i++) {
			myShip[i]=new JLabel();
			myShip[i].setIcon(myShipIcon);
			myShipPanel.add(myShip[i]);
			enemyShip[i]=new JLabel();
			enemyShip[i].setIcon(enemyShipIcon);
			enemyPanel.add(enemyShip[i]);
		}		
		
		enemyPanel.add(new JLabel(enemyLogo));
		
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
	
	public void setPlayerHit() {
		
		myShip[myFleet].setVisible(false);
		myFleet--;
	}
	
	public void setEnemyHit() {
		
		enemyShip[enemyFleet].setVisible(false);
		enemyFleet++;
	}
}
