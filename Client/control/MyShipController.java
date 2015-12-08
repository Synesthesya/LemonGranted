package control;

import graphic.Frame;
import graphic.Grid;
import interfaces.Controller;
import interfaces.PlayerI;
import interfaces.ServerI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import server.Phase;
import core.Coordinate;
import core.Player;
/**
 * si occupa dello schieramento delle navi
 * @author Francesco
 *
 */
public class MyShipController extends MouseAdapter implements Controller {
	
	private Player player;
	private ServerI server;
	private Grid left;
	private Grid right;
	
	public MyShipController(Player p, ServerI s) {
		super();
		player=p;
		server=s;
	}
	
	public void setGrids(Frame f)
	{
	  left=f.getPanel().getGrid().getLeft();
      left=f.getPanel().getGrid().getRight();
	}

	
	public void mouseClicked(MouseEvent e) 
	{
		
		Grid g=(Grid)e.getComponent();
		
		int x=e.getX()/50;
		int y=e.getY()/50;
		
		Coordinate c=new Coordinate(x,y);
		if(player.getPhase()==Phase.DEPLOYMENT && player.deploy(c) && g.getName().equals("left")) 
		{
			if(player.getID()==1)
			  g.getSlot(c).setImage("TF");
			else
			  g.getSlot(c).setImage("XW_Square");
		}
		else if(player.getPhase()==Phase.COMBAT && !player.getEnemyShip().getStatus(c) && g.getName().equals("right"))
		{
		  try
		  {
		    server.shot(player.getID(), c);
		  }
		  catch(Exception err)
		  {
		    System.err.println("errore: " +err.getMessage());
		  }
		}
	}
	
	public void checkDeployment()
	{
	  try
	  {
	    server.isReady();
	  }
	  catch(Exception e)
	  {
	    System.err.println(e.getMessage());
	  }
	}
	
	public void setImage(boolean b, Coordinate c)
	{
	  if(b)
	  {
	    left.getSlot(c).setImage(player.getID()==1 ? "empireLogo" : "rebelsLogo");
	  }
	  else
	  {
	    right.getSlot(c).setImage(player.getID()==1 ? "empireLogo" : "rebelsLogo");
	  }
	}

}
