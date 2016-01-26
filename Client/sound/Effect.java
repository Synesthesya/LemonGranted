package sound;

import java.io.FileInputStream;
import java.io.IOException;
import launcher.Start;
import sun.audio.*;

/**
 * classe che gestisce gli effetti audio come gli spari
 * 
 * @author Synesthesy
 *
 */
public class Effect {
	
	/**
	 * elenco degli effetti che cambiano da fazione a fazione
	 */
	public static final String[] EFFECTS = {"Hit","Water","No","Deploy","Win","NewGame","Join","Transition"};
	
	public static final String[] OTHERS = {"Transition","Error"};
	
	/**
	 * formato audio
	 */
	public static final String FORMAT = ".wav";
	
	/**
	 * effetto audio attivo
	 */
	@SuppressWarnings("restriction")
	protected AudioStream audio;
	
	/**
	 * costruttore standard
	 */
	public Effect() {
		audio=null;
	}
	
	/**
	 * metodo per far partire l'effetto se è stato caricato
	 */
	@SuppressWarnings("restriction")
	public void play() {
		
		if(audio!=null) AudioPlayer.player.start(audio);		
	}
	
	/**
	 * metodo che carica un effetto sonoro e lo esegue
	 * @param id la fazione dell'effetto: <b>2</b> come Ribelli o <b>1</b> come Impero, <b>0</b> per i suoni neutri
	 * @param s il numero dell'effetto nell'elenco
	 * @throws IOException
	 */
	@SuppressWarnings("restriction")
	public void load(int id, int s) throws IOException  {
		
		if(audio!=null) AudioPlayer.player.stop(audio);
		//CONTROLLO COERENZA NUMERO
		if(s<0 || s>=EFFECTS.length) return;
		switch(id) {
		case 2: {
			audio=new AudioStream(new FileInputStream(Start.PATH+"R"+EFFECTS[s]+FORMAT));
			break;
		}
		case 1: {
			audio=new AudioStream(new FileInputStream(Start.PATH+"E"+EFFECTS[s]+FORMAT));
			break;
		}case 0: {
			System.out.println("eseguo il load di "+Start.PATH+OTHERS[s]+FORMAT);
			audio=new AudioStream(new FileInputStream(Start.PATH+OTHERS[s]+FORMAT));
			break;
		}
		default: return;
		}
		play();				
	}
}

