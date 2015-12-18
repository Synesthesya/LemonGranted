package graphic.menu;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import sound.Effect;

/**
 * 
 * popup che compare in caso di errore
 * 
 * @author Alex
 *
 */
public class ErrorPopUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * la dimensione del popup
	 */
	public static final Dimension DIM = new Dimension(200,400);
	
	/**
	 * costruttore standard, il popup visualizza una stringa
	 * 
	 * @param s la stringa da visualizzare
	 */
	public ErrorPopUp(String s) {
		
		super("Errore!");
		JLabel error=new JLabel(s);
		add(error);
		error.setSize(DIM);
		pack();
		setVisible(true);
		errorSound();
	}
	
	/**
	 * popup grafico
	 */
	public ErrorPopUp() {
		
		super("Errore!");
		JLabel error=new JLabel();
		add(error);
		error.setSize(DIM);
		setSize(400, 400);
		setVisible(true);
		
		JLabel errore = new JLabel();
		ImageIcon tornaMenu = new ImageIcon("/home/giorgio/Immagini/Img2/MainMenuButton3.jpg");
		errore.setIcon(tornaMenu);
		this.add(errore);
		errorSound();
	}
	
	private static void errorSound() {
		
		Effect e=new Effect();
		try {
			e.load(0, 1);
		} catch (IOException e1) {
			System.out.println("errore: impossibile eseguire il file audio di errore");
		}
	}
}
