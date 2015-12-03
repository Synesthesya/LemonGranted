package graphic;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import control.Controller;

public class Information extends JPanel {

	/**
	 * #crescile(R)
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * il nome del giocatore
	 */
	public static String playerName= "Giocatore 1";
	
	/**
	 * il nick del giocatore
	 */
	private final JLabel player;
	/**
	 * lo status delle navi del giocatore
	 */
	private final JLabel myShip;
	/**
	 * lo status delle navi dell'avversario
	 */
	private final JLabel enemyShip;
	/**
	 * lo status del turno
	 */
	private final JLabel status;
	/**
	 * il controllore che comunica tra i due giocatori
	 */
	private final Controller control;
	
	/**
	 * costruttore standard 
	 * @param c
	 */
	public Information(Controller c) {
		
		super();
		control=c;
		player=new JLabel(playerName);
		
		/*
		 * WARNING
		 * gestire correamente i label
		 */
		
		myShip=new JLabel();
		enemyShip=new JLabel();
		status=new JLabel();
		
		
		/*
		 * WARNING
		 * gestire corretamente il layout
		 */
		
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

}
