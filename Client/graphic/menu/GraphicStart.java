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
		    JFrame frame = new JFrame("Demo Grafica");
		    Background back = new Background(".//bin//vader.png");
		    frame.add(back);
		    frame.setSize(1200, 800);
		    frame.setVisible(true);
		    back.setLayout(null);
		    
		    JButton button1 = new JButton("PORCO DIO");
		    JButton button2 = new JButton("PORCA MADONNA");
		    JButton button3 = new JButton("DIO CANAGLIA DI DIO");
		    JButton button4 = new JButton("INGRANAGGIANO-ANO-ANO");
		    
		    back.add(button1);
		    back.add(button2);
		    back.add(button3);
		    back.add(button4);
		  
		    button1.setBounds(50, 50, 300, 100);
		    button2.setBounds(50, 200, 300, 100);
		    button3.setBounds(50, 350, 300, 100);
		    button4.setBounds(50, 500, 300, 100);
		  }
}