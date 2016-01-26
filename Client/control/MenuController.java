package control;

import graphic.Frame;
import graphic.menu.ErrorPopUp;
import graphic.menu.OptionPanel;
import interfaces.Controller;
import interfaces.ServerI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import javax.swing.JButton;
import core.Player;

/**
 * classe che controlla i menù principali
 * 
 * @author Alex
 * @implements ActionListener
 *
 */
public class MenuController implements ActionListener {
	
	
	/**
	 * l'IP del server
	 */
	public static String IP = "127.0.0.1";
	
	/**
	 * la porta del server
	 */
	public static String DOOR = ":1677";
	
	/**
	 * <p>
	 * elenco dei comandi disponibili dal menù 
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * MP: multiplayer, il comando che esegue il collegamento col server
	 * SP: singleplayer, il comando che lancia il gioco singolo
	 * OP: option
	 * QT: esci
	 * MM: main menù (Torna al menù principale)
	 * NOME: il pulsante che gestisce il nick del giocatore
	 * EMPIRE: il pulsante che seleziona l'Impero
	 * REBELS: il pulsante che seleziona i Ribelli
	 * END: il pulsante che ritorna al menù principale terminata una partita
	 * </ul>
	 * </p>
	 */
	public static final String[] MAINMENU = {"MP","SP","OP","QT","MM","NOME","EMPIRE","REBELS","END"};
	
	/**
	 * il frame a cui viene associato il Controller
	 */
	private Frame frame;
	
	/**
	 * costruttore standard
	 * 
	 * @param f il Frame da cui viene chiamato il Controller
	 */
	public MenuController(Frame f) {
		frame=f;
	}
	
	/**
	 * metodo ereditato da ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		switch(arg0.getActionCommand()) {
		case "MP": {
			
			try {
				ServerI s = (ServerI) Naming.lookup("rmi://"+IP+DOOR+"/server");
				if (!s.registraPlayer()) {
					@SuppressWarnings("unused")
					ErrorPopUp er = new ErrorPopUp("Numero massimo di giocatori raggiunto");
					return;
				}
				Integer ID = s.getID();
				Player p = new Player(ID, frame.getID());
				//creazione stub
				Naming.bind("rmi://"+IP+DOOR+"/player" + ID.toString(), p);
				Controller c = new MyShipController(p, s);
				frame.setGame(c);
				c.linkFrame(frame);
				p.setController(c);
				//comunica lo stub al server
				s.caricaPlayer();
			}
			catch(Exception e) {
				@SuppressWarnings("unused")
				ErrorPopUp er = new ErrorPopUp("impossibile collegarsi al server!\n"+e);
				frame.setMenu(this);
				break;
			}
			frame.playSound(5);
			break;
		}
		case "SP": {
			
			Player p=null;
			
			try {
				p=new Player(frame.getID(), frame.getID());

			} catch (RemoteException e) {
				// INUTILE MA NECESSARIA PER L'EREDITARIETA'

				e.printStackTrace();
				return;
			}
			Controller c=new SPController(p);
			frame.setGame(c);
			c.linkFrame(frame);
			p.setController(c);
			p.cambiaTurno();
			frame.playSound(5);
			break;
		}
		case "OP": {
			frame.setOption(this);
			frame.playSound(0,0);
			break;
		}
		case "QT": {
			System.exit(0);
		}
		case "MM": {
			frame.setMenu(this);
			frame.playSound(0,0);
			break;
		}
		case "NOME": {
			JButton but=(JButton)arg0.getSource();
			OptionPanel op=(OptionPanel)but.getParent();
			String s=op.setName();
			frame.setName(s);
			break;
		}
		case "EMPIRE": {
			JButton but=(JButton)arg0.getSource();
			OptionPanel op=(OptionPanel)but.getParent();
			op.setFaction("EMPIRE");
			frame.setID(1);
			frame.playSound(6);
			break;
		}
		case "REBELS": {
			JButton but=(JButton)arg0.getSource();
			OptionPanel op=(OptionPanel)but.getParent();
			op.setFaction("REBELS");
			frame.setID(2);
			frame.playSound(6);
			break;
		}
		case "END": {
			frame.setMenu(this);
			frame.playSound(0,0);
			break;
		}
		}		
	}
}
