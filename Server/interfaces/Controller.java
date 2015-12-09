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
	 * MOCKUP
	 * 
	 * cambia le immagini quando le caselle vengono cliccate
	 */
	public void setImage(boolean b, Coordinate c);
	
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
