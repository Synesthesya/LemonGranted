package sound;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import graphic.menu.ErrorPopUp;
import launcher.Start;
import sun.audio.*;

/**
 * classe che gestisce l'esecuzione della musica
 * 
 * @author Synesthesy
 *
 */
public class MusicPlayer extends Effect implements Runnable {
	
	/**
	 * elenco dei brani disponibili
	 */
	public static final String[] MUSIC = {"Main","EFight","RFight","Option"};
		
	/**
	 * costruttore standard, inizializza l'audio al file musicale zero (l'introduzione)
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public MusicPlayer() {
		
		InputStream in;
		try {
			in = new FileInputStream(Start.PATH+MUSIC[0]+FORMAT);
			audio=new AudioStream(in);
		} catch (FileNotFoundException e) {
			new ErrorPopUp("errore: impossibile inizializzare la musica");
		}
		catch(IOException e1) {
			new ErrorPopUp("errore: impossibile inizializzare la musica");
		}
		run();
	}
	
	/**
	 * metodo per stoppare la musica
	 */
	@SuppressWarnings("restriction")
	public void stop() {
		
		AudioPlayer.player.stop(audio);
	}
	
	/**
	 * metodo per cambiare la musica in ascolto
	 * @param m il numero della traccia da ascoltare
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public void load(int m) throws IOException {
		
		if(m<0||m>MUSIC.length) return;
		stop();
		audio=new AudioStream(new FileInputStream(Start.PATH+MUSIC[m]+FORMAT));
		play();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		play();
	}
}
