package graphic.menu;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * mockup della grafica non inerente al gioco:
 * 
 *usiamo questo main per vedere la rappresentazione grafica di tutto il resto
 * 
 * @author Giorgio
 *
 */
public class GraphicStart {
	
	/**
	 * metodo da lanciare per eseguire il mockup grafico
	 * @param argv
	 */
	 public static void main(String... argv) {
		 JFrame frame = new JFrame("Demo Background Image");
		 MainMenu menu = new MainMenu();
		 frame.add(menu);
		 frame.setSize(1200, 800);
		 frame.setResizable(false);
		 frame.setVisible(true);
		  }
}