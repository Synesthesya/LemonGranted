package control;

import graphic.Frame;
import graphic.menu.ErrorPopUp;
import interfaces.Controller;
import interfaces.ServerI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import core.Player;

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
	 * MM: main menù (Torna al menù principale)
	 * </ul>
	 * </p>
	 */
	public static final String[] MAINMENU = {"MP","SP","OP","QT","MM"};
	
	private Frame f;
	
	/**
	 * costruttore standard
	 */
	public MenuController(Frame frame) {
		f=frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		switch(arg0.getActionCommand()) {
		case "MP": {
			
			try {
				ServerI s = (ServerI) Naming.lookup("rmi://127.0.0.1:1677/server");
				if (!s.registraPlayer()) {
					ErrorPopUp er = new ErrorPopUp("Numero massimo di giocatori raggiunto");
					return;
				}
				Integer ID = s.getID();
				Player p = new Player(ID);
				//creazione stub
				Naming.bind("rmi://127.0.0.1:1677/player" + ID.toString(), p);
				Controller c = new MyShipController(p, s);
				f.setGame(ID, c);
				c.linkFrame(f);
				p.setController(c);
				//comunica lo stub al server
				s.caricaPlayer();
				break;
			}
			catch(Exception e) {
				ErrorPopUp er = new ErrorPopUp("impossibile collegarsi al server!\n"+e);
				f.setMenu(this);
				return;
			}
		}
		case "SP": {
			//WARNING: manca il Singleplayer
			break;
		}
		case "OP": {
			f.setOption(this);
			break;
		}
		case "QT": {
			System.exit(0);
		}
		case "MM": {
			f.setMenu(this);
			break;
		}
		}		
	}
}
