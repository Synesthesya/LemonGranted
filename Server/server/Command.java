package server;

import java.util.Scanner;

/**
 * 
 * classe che serve all'interazione tra il server e il suo gestore
 * non essendo usata dall'utente finale, ma solo dal debugger, non è necessaria una interfaccia grafica, per cui si usa
 * una interfaccia testuale
 * 
 * @author Synesthesy
 *
 */
public class Command implements Runnable {
	
	/**
	 * elenco dei comandi disponibili
	 */
	public static final String[] CMD = {"help", "exit", "print"};
	
	/**
	 * oggetto server
	 */
	private Server server;

	public Command(Server s) {
		
		server=s;
	}

	@Override
	public void run() {
		
		System.out.println("inizializzazione del server terminata");
		
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			System.out.println("digitare un comando per proseguire, help per ottenere maggiori informazioni");
			String c=sc.next();
			switch(c) {
			case "help": {
				System.out.println("Questo è l'elenco dei comandi disponibili:");
				for(int i=0; i<CMD.length; i++) {
					System.out.println(CMD[i]);
				}
				break;
			}
			case "exit": {
				sc.close();
				server.exit();
			}
			case "print": {
				System.out.println("stampa del giocatore 1:\n"+server.getPlayer1()+"\n\nstampa del giocatore 2:\n"+server.getPlayer2());
			}
			}
		}		
	}
}
