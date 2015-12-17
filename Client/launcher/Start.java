package launcher;

import interfaces.Controller;
import interfaces.ServerI;

import java.io.IOException;
import java.rmi.Naming;

import graphic.Frame;
import control.MenuController;
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
	
	public static final String PATH = ".//bin//";
	

	/**
	 * main
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Frame f=new Frame();
		f.initialize(new MenuController(f));
		
		
	}

}
