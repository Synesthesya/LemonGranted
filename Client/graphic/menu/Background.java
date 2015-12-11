package graphic.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 * classe che implementa graficamente gli sfondi dei menù
 * 
 * può venire potenzialmente usata come base per ogni schermata, eccetto quella di gioco
 * 
 * @author Giorgio
 * @author Alex
 * 
 */  
public class Background extends JPanel {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * l'immagine visualizzata come sfondo
	 */
	private Image img;

	/**
	 * costruttore standard
	 */
  public Background(String path) {
	  
	super();
	//String path = ".//bin//vader.png"; COMMENTATO CAUSA MOCKUP
	//img = Toolkit.getDefaultToolkit().createImage(path);
	//loadImage();
	setImage(path);
  }
  
  /**
   * metodo per caricare l'immaginenello sfondo
   * 
   */
  private void loadImage() {
    try {
      MediaTracker track = new MediaTracker(this);
      track.addImage(img, 0);
      track.waitForID(0);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * metodo ereditato dalla sovraclasse JPanel
   */
  @Override
  protected void paintComponent(Graphics g) {
    setOpaque(false);
    g.drawImage(img, 0, 0, null);
    super.paintComponent(g);
  }
  
  /**
   * metodo di get, può servire?
   * @return
   */
  public Image getImage() {
	  return img;
  }
  
  /**
   * metodo per settare l'immagine di sfondo
   * 
   * @param i il percorso dell'immagine scelta
   */
  public void setImage(String i) {
	  img=Toolkit.getDefaultToolkit().createImage(i);
	  loadImage();
  }

}
