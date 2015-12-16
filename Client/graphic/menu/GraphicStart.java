package graphic.menu;

import java.io.IOException;

import javax.swing.JFrame;
import control.MenuController;

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
	 * @throws IOException 
	 */
	 public static void main(String... argv) throws IOException {
		 JFrame frame = new JFrame("Demo Background Image");
		 MenuController mc=new MenuController(null);
		 MainMenu menu = new MainMenu(mc);
		 frame.add(menu);
		 frame.setSize(1200, 800);
		 frame.setResizable(false);
		 frame.setVisible(true);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }
}