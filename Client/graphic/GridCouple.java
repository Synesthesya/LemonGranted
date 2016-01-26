package graphic;

import interfaces.Controller;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <p>
 * classe utilizzata per rappresentare due griglie affiancate
 * </p>
 * <p>
 * la griglia di sinistra rappresenta le navi del giocatore, quella di destra viene utilizzata
 * per attaccare l'avversario
 * </p>
 * @author Alex
 *
 */
public class GridCouple extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * griglia rappresentante le navi del giocatore
	 */
	private final Grid left;
	/**
	 * griglia usata dal giocatore per colpire l'avversario
	 */
	private final Grid right;
	
	/**
	 * mockpup per ottenere maggiore spazio
	 */
	private JLabel a = new JLabel("                     ");	
	
	/**
	 * <p>
	 * costruttore standard: le due griglie appariranno affiancate
	 * </p>
	 * 
	 * @param ID l'id del giocatore
	 * @param c il controllore
	 */
	public GridCouple(int ID,Controller c) {
		left=new Grid(ID,c,"left");
		right=new Grid(ID,c,"right");
		add(left);
		add(a);
		add(right);
	}
	
	/**
	 * 
	 * metodo che ritorna la griglia del giocatore
	 * 
	 * @return Grid left
	 */
	public Grid getLeft() {
		return left;
	}
	
	/**
	 * 
	 * metodo che ritorna la griglia dell'avversario
	 * 
	 * @return Grid right
	 */
	public Grid getRight() {
		return right;
	}

}
