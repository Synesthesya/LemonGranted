package interfaces;

import graphic.Frame;

import java.util.Observer;

import core.Coordinate;

/**
 * interfaccia di base dei controller
 * @author Alex
 *
 */
public interface Controller
{

	/**
	 * 
	 * funzione che viene chiamata quando si ha schierata l'ultima nave: comunica col server 
	 * la fine della propria parte dello schieramento, e "attende" che sia possibile cambiare fase
	 * 
	 */
	public void checkDeployment();
	
	/**
	 * cambia l'immagine alle coordinate di una griglia
	 * @param b true=griglia di destra, false=griglia di sinistra
	 * @param c le coordinate
	 * @param s il nome della nuova immagine 
	 */
	public void setImage(boolean b, Coordinate c, String s);
	
	/**
	 * <p>
	 * associa le griglie grafiche al Controller
	 * </p>
	 * <p>
	 * non è definitivo: funzionerà solamente finché non ci sarà un menù principale
	 * 
	 * @param f il Frame del gioco
	 */
	public void linkFrame(Frame f);

	/**
	 * avverte la parte grafica di eseguire la sconfitta
	 */
	public void sconfitta();

	/**
	 * avverte la parte grafica di eseguire la vittoria
	 */
	public void vittoria();

	/**
	 * setta il messaggio del turno
	 * @param t true=proprio turno, turno avversario altrimenti
	 */
	public void cambiaTurno(boolean t);
	
	/**
	 * cambia il messaggio nel label status, composto da fase + il paramentro stringa
	 * @param s la stringa
	 */
	public void setMessage(String s);

	/**
	 * cambia la stringa fase con la nuova stringa
	 * @param string
	 */
	public void setFase(String string);
	
	/**
	 * cambia la stringa di testo2
	 * @param string
	 */
	public void setTesto2(String string);
	
	/**
	 * scala di uno il numero di navi possedute
	 */
	public void imHit();
	
	/**
	 * scala di uno il numero di navi del nemico
	 */
	public void isHit();
}
