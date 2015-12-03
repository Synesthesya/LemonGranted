package graphic;

import javax.swing.JPanel;
import control.Controller;

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
	
	/**
	 * #escile
	 */
	private static final long serialVersionUID = 1L;
	private final Grid left;
	private final Grid right;
	
	
	/**
	 * <p>
	 * costruttore standard: le due griglie appariranno affiancate
	 * </p>
	 * 
	 * 
	 * @param l il MyShipController
	 * @param r il OpponentController
	 */
	public GridCouple(Controller l, Controller r) {
		left=new Grid(l);
		right=new Grid(r);
		add(left);
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
