package server;

import java.util.Scanner;

import core.Player;
import interfaces.PlayerI;

/**
 * 
 * interfaccia testuale del server, usata anche per implementare, da qualche parte, un thread separato
 * 
 * @author Synesthesy
 *
 */
public class Command implements Runnable {
	
	public static final String[] CMD = {"help","exit","print"};
	
	/**
	 * il server su cui si eseguono i comandi
	 */
	private Server server;

	public Command(Server s)  {
		
		server=s;		
	}

	@Override
	public void run() {
		
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			System.out.println("digitare un comando per proseguire, help per ottenere maggiori informazioni");
			
			switch(sc.next()) {
			case "help": {
				System.out.println("interfaccia testuale del server di Starship! Di seguito l'elenco dei comandi accettati:");
				for(int i=0; i<CMD.length; i++) {
					System.out.println(CMD[i]);
				}
				break;
			}
			case "exit": {
				System.out.println("disconnessione del server in corso...");
				sc.close();
				Server.uscita();
			}
			case "print": {
				PlayerI[] i=server.getPlayers();
				System.out.println("stampa del giocatore 1:\n"+i[0].toString());
				System.out.println("stampa del giocatore 2:\n"+i[1].toString());
				break;
			}
			default:
				System.out.println("errore: comando sconosciuto");
			}
			
			
			
		}
		
	}

}
