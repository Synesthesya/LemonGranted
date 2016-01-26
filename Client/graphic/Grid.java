package graphic;

import interfaces.Controller;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import core.Coordinate;


/**
 * <p>
 * classe che rappresenta graficamente la griglia della battaglia navale
 * </p>
 * <p>
 * la Grid viene utilizzata sia per rappresentare la griglia delle navi del giocatore che quella
 * delle navi dell'avversario
 * </p>
 * 
 * @author Alex
 *
 */
public class Grid extends JPanel {
	
	/**
	 * le immagini dei caccia
	 */
	public static final String[] SHIP = {"TF","XW"};
	/**
	 * immagine del caccia colpito
	 */
	public static final String HIT = "_RED";
	/**
	 * formato delle immagini delle navi
	 */
	public static final String FORMAT = ".png";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * la rappresentazione grafica della griglia
	 */
	private final Slot[] grid;
	
	/**
	 * il nome della griglia per distinguerle
	 */
	private String name;
	
	/**
	 * costruttore standard
	 * 
	 * @param ID l'ID rappresentante la fazione
	 * @param c il Controller
	 * @param s il nome della griglia (normalmente <b>LEFT</b> o <b>RIGHT</b>
	 */
	public Grid(int ID,Controller c, String s) {
		
	    name=s;
		int size=Coordinate.SIZE;
		
		setLayout(new GridLayout(size, size));		
		grid= new Slot[size*size];
		
		for(int i=0; i<size*size; i++) {
			grid[i]=new Slot();
			add(grid[i]);
		}
		
		this.addMouseListener((MouseAdapter)c);		
	}
	
	/**
	 * metodo per ottenere uno slot
	 * @param c la coordinata
	 * @return lo slot
	 */
	public Slot getSlot(Coordinate c) {
		
		return grid[c.toInteger()];		
	}
	
	/**
	 * metodo per ottenere il nome della griglia
	 */
	public String getName()
	{
	  return name;
	}
	
	/**
	 * metodo per aggiungere l'immagine di un caccia
	 * @param id la fazione
	 * @param c la coordinata
	 */
	public void deploy(int id, Coordinate c) {
		
		grid[c.toInteger()].setImage(SHIP[id-1]);		
	}
	
	/**
	 * metodo per aggiungere l'immagine di un caccia colpito
	 * @param id la fazione (NB: la nave colpita sarà della fazione opposta!)
	 * @param c la coordinata
	 */
	public void setHit(int id, Coordinate c) {
		
		if(id==1) grid[c.toInteger()].setImage(SHIP[1]+HIT);
		else grid[c.toInteger()].setImage(SHIP[0]+HIT);
	}
	
	/**
	 * metodo per le caselle colpite ma senza navi all'interno
	 * @param c la coordinata
	 */
	public void setExplored(Coordinate c) {
		grid[c.toInteger()].setExplored();
	}	
}

