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
	public void setGrids(Frame f);
}
