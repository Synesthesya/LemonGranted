package launcher;

import interfaces.Controller;
import interfaces.ServerI;

import java.rmi.Naming;

import graphic.Frame;
import control.MyShipController;
import core.Player;
/**
 * Qui il client riceve dati dal server per cui servono le policy... bisogna lanciare il client da /LemonGranted con: 
 * java -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase=http://127.0.0.1:8081/ -Djava.security.policy=bin/launcher/client.policy -cp "bin" launcher.Start
 * @author Francesco
 * @author Alex
 *
 */
public class Start {
	

	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ServerI s;
		Player p;
		while(true)
		{
			try {
				s = (ServerI) Naming.lookup("rmi://127.0.0.1:1677/server");
				if (!s.registraPlayer()) {
					System.out.println("Numero massimo di giocatori raggiunto");
					System.exit(1);
				}
				Integer ID = s.getID();
				p = new Player(ID);
				//creazione stub
				Naming.bind("rmi://127.0.0.1:1677/player" + ID.toString(), p);
				Controller c = new MyShipController(p, s);
				p.setController(c);
				Frame f = new Frame(ID, c, null);
				c.linkFrame(f);
				//comunica lo stub al server
				s.caricaPlayer();
				break;
			}
			catch (Exception e) {
				System.err.println(e);
			}
		}
	}

}
