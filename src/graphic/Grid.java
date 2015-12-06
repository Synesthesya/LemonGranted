package graphic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import core.Coordinate;
import control.Controller;


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
	 * non so a cosa serva ma a eclipse piace tanto non so dirgli di no #escile
	 */
	private static final long serialVersionUID = 1L;
	
	private final Slot[] grid;
	private Controller c;
	
	public Grid(Controller c) {
		
		int size=Coordinate.SIZE;
		this.c=c;
		
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
}

/* VECCHIO CODICE
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import core.Coordinate;
import control.Controller;



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
 *
public class Grid extends JPanel {

	
	 * non so a cosa serva ma a eclipse piace tanto non so dirgli di no #escile
	 *
	private static final long serialVersionUID = 1L;
	
	private final Slot[] grid;
	//superfluo
	//private Controller c;
	
	public Grid(Controller c) {
		
		int size=Coordinate.SIZE;
		//this.c=c;
		
		setLayout(new GridLayout(size, size));		
		grid= new Slot[size*size];
		
		for(int i=0; i<size*size; i++) {
			grid[i]=new Slot(new Coordinate(i));
			add(grid[i]);
		}
		
		//WARNING: boh
		this.addMouseListener((MouseAdapter)c);		
	}
	
	public Slot getSlot(Coordinate c) {
		
		return grid[c.toInteger()];		
	}
} 
*/