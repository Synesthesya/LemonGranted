package graphic;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import core.Coordinate;


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
	
	public static final String[] IMAGE = {"TF_RED","XW_RED"};
	
	/**
	 * immagine del Pannello
	 */
	private String image;
	
	
	/**
	 * variabile che dice se il pannello è cliccabile o meno
	 */
	private boolean cliccabile;
	
	/**
	 * coordinata del label nella griglia 
	 */
	private final Coordinate where;
	
	/**
	 * costruttore standard: il costruttore genera una casella vuota
	 */
	public Slot(Coordinate co) {
		this.setIcon(new ImageIcon(PATH+INESPLORATO+FORMAT));
		where=co;
	}
	
	
	/**
	 * 
	 * metodo che dice se il pulsante è cliccabile o meno
	 * 
	 * @return <b>true</b> se è cliccabile, <b>false</b> altrimenti
	 */
	public boolean isCliccable() {
		return cliccabile;
	}
	
	/**
	 * metodo che imposta una casella come cliccabile o meno;
	 * il metodo non controlla il valore precedente
	 * 
	 * @param b <b>true</b> diventa cliccabile, <b>false</b> non cliccabile
	 */
	public void setCliccable(boolean b) {
		cliccabile=b;
	}
	
	/**
	 * metodo per settare l'immagine
	 * 
	 * @param img il nome dell'immagine senza formato
	 */
	public void setImage(String img) {
		image=img;
		this.setIcon(new ImageIcon(PATH+img+FORMAT));		
	}
	
	public void setImage(int img) {
		setImage(IMAGE[img]);
	}
	
	/**
	 * metodo per ottenere le coordinate di una casella
	 * @return le coordinate della casella
	 */
	public Coordinate getCoordinate() {
		return where;
	}
	
	/**
	 * metodo che marca una casella come colpita ma vuota
	 * @param c la coordinata della casella
	 */
	public void setExplored() {
		setImage(ESPLORATO);
	}
}
