package control;

import graphic.Grid;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import core.Coordinate;
import core.Player;

public class MyShipController extends MouseAdapter implements Controller {
	
	private Player player;
	
	public MyShipController(Player p) {
		super();
		player=p;
	}

//PORCO DIO
	
	public void mouseClicked(MouseEvent e) {
		
		//System.out.println("funziona!");
		
		Grid g=(Grid)e.getComponent();
		
		int x=e.getX()/50;
		int y=e.getY()/50;
		
		Coordinate c=new Coordinate(x,y);
		
		if(player.deploy(c)) {
			//mockup
			g.getSlot(c).setImage("TF");
		}		
	}

}
