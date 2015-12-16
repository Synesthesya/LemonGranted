package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * classe che controlla i menù principali
 * 
 * @author Alex
 *
 */
public class MenuController implements ActionListener {
	
	/**
	 * <p>
	 * elenco dei quattro comandi disponibili dal menù principale
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * MP: multiplayer, il comando che esegue il collegamento col server
	 * SP: singleplayer, il comando che lancia il gioco singolo
	 * OP: option
	 * QT: esci
	 * </ul>
	 * </p>
	 */
	public static final String[] MAINMENU = {"MP","SP","OP","QT"};
	
	/**
	 * costruttore standard
	 */
	public MenuController() {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		switch(arg0.getActionCommand()) {
		case "MP": {
			//INSERIRE QUI IL COMANDO DEL MULTIPLAYER
			break;
		}
		case "SP": {
			//WARNING: manca il Singleplayer
			break;
		}
		case "OP": {
			//inserire le opzioni
			break;
		}
		case "QT": {
			System.exit(0);
		}
		}		
	}
}
