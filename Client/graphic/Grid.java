package graphic;

import interfaces.Controller;
import launcher.Start;

import java.awt.BorderLayout;
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
	public static final String[] SHIP = {"TF","XW_SQUARE"};
	/**
	 * immagine del caccia colpito
	 */
	public static final String HIT = "_RED";
	/**
	 * formato delle immagini delle navi
	 */
	public static final String FORMAT = ".png";

	/**
	 * non so a cosa serva ma a eclipse piace tanto non so dirgli di no #escile
	 */
	private static final long serialVersionUID = 1L;
	
	private final Slot[] grid;
	private String name;
	
	public Grid(int ID,Controller c, String s) {
		
	    name=s;
		int size=Coordinate.SIZE;
		
		setLayout(new GridLayout(size, size));		
		grid= new Slot[size*size];
		
		for(int i=0; i<size*size; i++) {
			grid[i]=new Slot(new Coordinate(i));
			add(grid[i]);
		}
		
		
		this.addMouseListener((MouseAdapter)c);		
	}
	
	public Slot getSlot(Coordinate c) {
		
		return grid[c.toInteger()];		
	}
	
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
	 * @param id la fazione
	 * @param c la coordinata
	 */
	public void setHit(int id, Coordinate c) {
		
		grid[c.toInteger()].setImage(SHIP[id-1]+HIT);
	}
	
	
}

