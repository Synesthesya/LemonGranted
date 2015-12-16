package graphic.menu;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class ErrorPopUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Dimension DIM = new Dimension(200,400);
	
	public ErrorPopUp(String s) {
		
		super("Errore!");
		JLabel error=new JLabel(s);
		add(error);
		error.setSize(DIM);
		pack();
		setVisible(true);
		
	}

}
