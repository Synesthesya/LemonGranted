package graphic;

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
	
}
