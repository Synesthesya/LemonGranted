package sound;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
	public static final String[] MUSIC = {"Main"};
		
	/**
	 * costruttore standard, inizializza l'audio al file musicale zero (l'introduzione)
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public MusicPlayer() throws IOException {
		
		InputStream in=new FileInputStream(MUSIC[0]);
		audio=new AudioStream(in);		
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
	public void load(int m) throws IOException {
		
		if(m<0||m>MUSIC.length) return;
		stop();
		audio=new AudioStream(new FileInputStream(MUSIC[m]));
		play();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		play();
	}

}
