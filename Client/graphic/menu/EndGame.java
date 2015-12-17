package graphic.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import control.MenuController;
import launcher.Start;


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
	
	public static final String[] IMG = {"noRebels.jpg","noEmpire.png","winRebels.jpg","winEmpire.jpg"};
	
	public static final String MENUBUTTON = "MainMenuButton3.jpg";
	
	/**
	 * immagine di sfondo
	 */
	private int image;
	
	private Image background;
	
	
	
	public EndGame(MenuController mc, int win) {
		
		super();
		setLayout(null);
	//	String path = "/home/giorgio/Immagini/winRebels.jpg";
	//	String path = "/home/giorgio/Immagini/ROTS-DS.jpg";
	//	String path = "/home/giorgio/Immagini/Img2/noRebels.jpg";
	//	String path = "/home/giorgio/Immagini/Img2/noEmpire.png";
	    background = Toolkit.getDefaultToolkit().createImage(Start.PATH+IMG[win]);
	    loadImage(background);
    
	    JButton menu = new JButton("Torna al menù");
	    ImageIcon tornaMenu = new ImageIcon(Start.PATH+MENUBUTTON);
		menu.setIcon(tornaMenu);
		menu.setActionCommand("END");
		menu.addActionListener(mc);
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
