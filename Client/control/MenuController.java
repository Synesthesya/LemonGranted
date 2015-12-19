package control;

import graphic.Frame;
import graphic.menu.ErrorPopUp;
import graphic.menu.OptionPanel;
import interfaces.Controller;
import interfaces.ServerI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JButton;
import core.Player;

/**
 * classe che controlla i men� principali
 * 
 * @author Alex
 *
 */
public class MenuController implements ActionListener {
	
	/**
	 * <p>
	 * elenco dei quattro comandi disponibili dal men� principale
	 * </p>
	 * 
	 * <p>
	 * <ul>
	 * MP: multiplayer, il comando che esegue il collegamento col server
	 * SP: singleplayer, il comando che lancia il gioco singolo
	 * OP: option
	 * QT: esci
	 * MM: main men� (Torna al men� principale)
	 * </ul>
	 * </p>
	 */
	public static final String[] MAINMENU = {"MP","SP","OP","QT","MM","NOME","EMPIRE","REBELS","END"};
	
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
				f.setGame(c);
				c.linkFrame(f);
				p.setController(c);
				//comunica lo stub al server
				s.caricaPlayer();
			}
			catch(Exception e) {
				ErrorPopUp er = new ErrorPopUp("impossibile collegarsi al server!\n"+e);
				f.setMenu(this);
				break;
			}
			f.playSound(5);			
		}
		case "SP": {
			
			Player p=null;
			
			try {
				p=new Player(f.getID());

			} catch (RemoteException e) {
				// INUTILE MA NECESSARIA PER L'EREDITARIETA'

				e.printStackTrace();
				return;
			}
			Controller c=new SPController(p);
			f.setGame(c);
			c.linkFrame(f);
			p.setController(c);
			p.cambiaTurno();
			f.playSound(5);
			break;
		}
		case "OP": {
			f.setOption(this);
			f.playSound(0,0);
			break;
		}
		case "QT": {
			System.exit(0);
		}
		case "MM": {
			f.setMenu(this);
			f.playSound(0,0);
			break;
		}
		case "NOME": {
			JButton but=(JButton)arg0.getSource();
			OptionPanel op=(OptionPanel)but.getParent();
			String s=op.setName();
			f.setName(s);
			break;
		}
		case "EMPIRE": {
			JButton but=(JButton)arg0.getSource();
			OptionPanel op=(OptionPanel)but.getParent();
			op.setFaction("EMPIRE");
			f.setID(1);
			f.playSound(6);
			break;
		}
		case "REBELS": {
			JButton but=(JButton)arg0.getSource();
			OptionPanel op=(OptionPanel)but.getParent();
			op.setFaction("REBELS");
			f.setID(2);
			f.playSound(6);
			break;
		}
		case "END": {
			f.setMenu(this);
			f.playSound(0,0);
			break;
		}
		}		
	}
}
