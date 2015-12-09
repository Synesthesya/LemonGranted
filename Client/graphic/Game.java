package graphic;

import interfaces.Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * pannello principale del gioco
 * 
 * @author Alex
 * @author Giorgio
 *
 */
public class Game extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String LOGO = ".//bin//logo1.png";
	
	/**
	 * parte grafica delle griglie 
	 */
	private final GridCouple grids;
	
	/**
	 * parte grafica contenente le informazioni
	 */
	private final Information info;
	
	
	/**
	 * costruttore standard
	 * 
	 * @param ID id del giocatore 
	 * @param c MyShipController
	 * @param i Controller delle informazioni
	 */
	public Game(int ID, Controller c, Controller i) {
		
		super();
		grids=new GridCouple(ID,c);
		grids.setBackground(Color.black); // colore di prova
		info=new Information(ID,i);
		info.setBackground(Color.black); // colore di prova
		
		/*
		 * setLayout(null);
		 * setSize();
		 * setPreferredSize();
		 * setMaximumSize();
		 * setMinimumSize();
		 * 
		 * add add
		 * 
		 * setBounds(a, b, c, d);
		 */
		/*
		 * BORDER LAYOUT CON ALTO, CENTRO E BASSO
		 */
		
		setLayout(new BorderLayout());
		
		JLabel ab = new JLabel();
		ImageIcon icon = new ImageIcon(LOGO);
		ab.setIcon(icon);
		
		add(ab, BorderLayout.NORTH);
		add(info, BorderLayout.CENTER);
		add(grids, BorderLayout.SOUTH);

	}
	
	/**
	 * metodo per ottenere il contenitore delle griglie
	 * @return
	 */
	public GridCouple getGrids() {
		return grids;
	}
	
	/**
	 * metodo per ottenere il contenitore delle informazioni
	 * @return
	 */
	public Information getInfo() {
		return info;
	}
	
}

/* VECCHIO CODICE

import java.awt.GridLayout;
import javax.swing.JPanel;
import control.Controller;

public class Game extends JPanel {
	
	private final GridCouple grid;
	private final Information info;
	
	public Game(Controller l, Controller r, Controller i) {
		
		super();
		grid=new GridCouple(l,r);
		info=new Information(i);
		
		setLayout(new GridLayout(2,1));
		add(info);
		add(grid);		
	}
	
	public GridCouple getGrid() {
		return grid;
	}
	
	public Information getInfo() {
		return info;
	}
	
} */
