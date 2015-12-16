package launcher;

import java.rmi.Naming;

import control.MyShipController;
import core.Player;
import graphic.Frame;
import interfaces.Controller;
import interfaces.ServerI;

public class DebugStart {
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
				Frame f = new Frame(ID, c, null);
				c.linkFrame(f);
				p.setController(c);
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
