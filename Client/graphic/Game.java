package graphic;

//
import interfaces.Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
//


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {
	
	private final GridCouple grid;
	private final Information info;
	
	public Game(int ID, Controller c, Controller i) {
		
		super();
		grid=new GridCouple(ID,c);
		grid.setBackground(Color.black); // colore di prova
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
		ImageIcon icon = new ImageIcon(".//bin//logo1.png");
		ab.setIcon(icon);
		
		add(ab, BorderLayout.NORTH);
		add(info, BorderLayout.CENTER);
		add(grid, BorderLayout.SOUTH);

	}
	
	public GridCouple getGrid() {
		return grid;
	}
	
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
