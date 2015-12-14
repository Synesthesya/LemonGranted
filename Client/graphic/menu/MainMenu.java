package graphic.menu;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.ImageIcon;


/**
 * Classe che rappresenta graficamente il menù principale
 * 
 * @author Giorgio
 * @author Alex
 *
 */
public class MainMenu extends JPanel {

	  private Image img;

	  public MainMenu() {
		String path = ".//bin//vader.png";
	    img = Toolkit.getDefaultToolkit().createImage(path);
	    loadImage(img);
	    setLayout(null);
	    
	    JButton button1 = new JButton("MULTIPLAYER");
	    JButton button2 = new JButton("SINGLEPLAYER");
	    JButton button3 = new JButton("OPZIONI");
	    JButton button4 = new JButton("ESCILE");
	    
	    add(button1);
	    add(button2);
	    add(button3);
	    add(button4);
	    
	    button1.setBounds(50, 50, 300, 100);
	    ImageIcon iconB1 = new ImageIcon("/home/giorgio/Immagini/squirtle.png");
	    button1.setIcon(iconB1);
	    
	    button2.setBounds(50, 200, 300, 100);
	    button3.setBounds(50, 350, 300, 100);
	    button4.setBounds(50, 500, 300, 100);
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
	    g.drawImage(img, 0, 0, null);
	    super.paintComponent(g);
	  }

	}
