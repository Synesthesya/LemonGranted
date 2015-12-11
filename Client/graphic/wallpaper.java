package graphic;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class wallpaper extends JPanel {

  private Image img;

  public wallpaper() {
	String path = ".//bin//vader.png";
    img = Toolkit.getDefaultToolkit().createImage(path);
    loadImage(img);
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
