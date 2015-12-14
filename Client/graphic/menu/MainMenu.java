package graphic.menu;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe che rappresenta graficamente il menù principale
 * 
 * @author Giorgio
 * @author Alex
 *
 */
public class MainMenu extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * costruttore standard
	 */
	public MainMenu() {
		
		Background back = new Background(".//bin//vader.png");
		this.add(back);
		this.setSize(1200, 800);
	    back.setLayout(null);
	    
	    JButton button1 = new JButton("MULTIPLAYER");
	    JButton button2 = new JButton("SINGLEPLAYER");
	    JButton button3 = new JButton("OPZIONI");
	    JButton button4 = new JButton("ESCILE");
	    
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
