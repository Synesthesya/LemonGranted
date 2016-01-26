package graphic;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * 
 * classe casella 
 * 
 * la casella può contenere o meno una nave (o una parte di una nave)
 * 
 * @author Alex
 *
 */
public class Slot extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * percorso della cartella che contiene le immagini
	 */
	public static final String PATH = ".//bin//";
	
	/**
	 * formato delle immagini
	 */
	public static final String FORMAT = ".png";
	
	/**
	 * la dimensione di ogni cella in pixel
	 */
	public static final int CELLSIZE = 50;
	
	/**
	 * percorso dell'immagine vuota
	 */
	public static final String INESPLORATO = "SpaceSquareBorderAlternate";
	
	/**
	 * percorso dell'immagine dello spazio vuoto (ovvero colpito ma senza navi)
	 */
	public static final String ESPLORATO = "SpaceSquareBorder";
		
	/**
	 * costruttore standard: il costruttore genera una casella vuota
	 */
	public Slot() {
		this.setIcon(new ImageIcon(PATH+INESPLORATO+FORMAT));
	}
	
	/**
	 * metodo per settare l'immagine
	 * 
	 * @param img il nome dell'immagine senza formato
	 */
	public void setImage(String img) {
		this.setIcon(new ImageIcon(PATH+img+FORMAT));		
	}
	
	
	/**
	 * metodo che marca una casella come colpita ma vuota
	 * @param c la coordinata della casella
	 */
	public void setExplored() {
		setImage(ESPLORATO);
	}
}
