package graphic.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * 
 * classe che rappresenta graficamente la fine del gioco
 * 
 * @author Giorgio
 * @author Alex
 *
 */
public class EndGame extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String[] IMG = {"noRebels","noEmpire","winRebels","winEmpire"};
	
	/**
	 * immagine di sfondo
	 */
	private int image;
	
	
	
	public EndGame(int f) {
		
		super();
		setLayout(null);
	//	String path = "/home/giorgio/Immagini/winRebels.jpg";
	//	String path = "/home/giorgio/Immagini/ROTS-DS.jpg";
	//	String path = "/home/giorgio/Immagini/Img2/noRebels.jpg";
	//	String path = "/home/giorgio/Immagini/Img2/noEmpire.png";
	    Image background = Toolkit.getDefaultToolkit().createImage(path);
	    loadImage(background);
    
	    JButton menu = new JButton("Torna al menù");
	    ImageIcon tornaMenu = new ImageIcon("/home/giorgio/Immagini/Img2/MainMenuButton3.jpg");
		menu.setIcon(tornaMenu);
		add(menu);
		menu.setBounds(100, 600, 290, 70);
		menu.setBorderPainted(true);
	}
	
	 private void loadImage(Image img) {
		    try {
		      MediaTracker track = new MediaTracker(this);
		      track.addImage(img, 0);
		      track.waitForID(0);
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }
		  }

		  protected void paintComponent(Graphics g) {
		    setOpaque(false);
		    g.drawImage(background, 0, 0, null);
		    super.paintComponent(g);
		  }
}
