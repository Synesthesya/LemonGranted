package graphic;

import interfaces.Controller;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {
	
	public static final ImageIcon ICON = new ImageIcon("mockup");
	private JPanel panel;
	
	public Frame(int ID, Controller c, Controller i) {
		
		super("Starship!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		/*
		 * codice di mockup
		 */
		
		panel=new Game(ID, c, null);
		add(panel); 
	//	this.pack();
		this.setSize(1200, 800);
		
	}
	
	//MOCKUP
	public Game getPanel() {
		return (Game)panel;
	}

}

/* VECCHIO CODICE
 * package graphic;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import control.Controller;

public class Frame extends JFrame {
	
	public static final ImageIcon ICON = new ImageIcon("mockup");
	private JPanel panel;
	
	public Frame(Controller l, Controller r, Controller i) {
		
		super("Starship!");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	
		 * codice di mockup
		
		
		panel=new Game(l,r,i);
		add(panel);
		
		this.pack();
		
	}
	
	//MOCKUP
	public Game getPanel() {
		return (Game)panel;
	}

} */


