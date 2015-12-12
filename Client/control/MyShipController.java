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
 * si occupa dello schieramento delle navi e della fase di combattimento
 * @author Francesco
 * 
 * @extends MouseAdapter
 * @implements Controller
 *
 */
public class MyShipController extends MouseAdapter implements Controller {
	
	/**
	 * riferimento al Player
	 */
	private Player player;
	/**
	 * stub del server
	 */
	private ServerI server;
	
	/**
	 * riferimento al frame
	 */
	private Frame frame;
	/**
	 * griglia con le proprie navi
	 */
	private Grid left;
	/**
	 * griglia con le navi nemiche (quella in cui si spara)
	 */
	private Grid right;
	
	/**
	 * costruttore standard
	 * 
	 * @param p il Player di riferimento
	 * @param s lo stub del Server
	 */
	public MyShipController(Player p, ServerI s) {
		super();
		player=p;
		server=s;
	}
	
	/**
	 * <p>
	 * associa le griglie grafiche al Controller
	 * </p>
	 * <p>
	 * non è definitivo: funzionerà solamente finché non ci sarà un menù principale
	 * 
	 * @param f il Frame del gioco
	 */
	@Override
	public void linkFrame(Frame f)
	{
		frame=f;
		left=frame.getPanel().getGrids().getLeft();
		right=frame.getPanel().getGrids().getRight();
	}

	/**
	 * <p>
	 * funzione che gestisce gli eventi su entrambe le griglie
	 * </p>
	 * <p>
	 * la funzione controlla entrambe le fasi: ad ogni fase è associata una griglia diversa.
	 * la fase di schieramento utilizza la griglia di sinistra, quella di combattimento la griglia di destra
	 * la fase viene stabilita all'interno dell'oggetto Player
	 * </p>
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
		Grid g=(Grid)e.getComponent();
		
		int x=e.getX()/50;
		int y=e.getY()/50;
		
		Coordinate c=new Coordinate(x,y);
		if(player.getPhase()==Phase.DEPLOYMENT && g.getName().equals("left") && player.deploy(c)) 
		{
			/*
			 * qui decide se usare IMpero o Ribelli
			 */
			if(player.getID()==1)
			  g.getSlot(c).setImage("TF");
			else
			  g.getSlot(c).setImage("XW_Square");
			
		}
		else if(player.getPhase()==Phase.COMBAT && player.getTurno() && g.getName().equals("right") && !player.getEnemyShip().getStatus(c))
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
	
	/**
	 * 
	 * funzione che viene chiamata quando si ha schierata l'ultima nave: comunica col server 
	 * la fine della propria parte dello schieramento, e "attende" che sia possibile cambiare fase
	 * 
	 */
	@Override
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
	/**
	 * b=true indica griglia di destra
	 * b=false indica griglia di sinistra
	 */
	@Override
	public void setImage(boolean b, Coordinate c, String s)
	{
	  if(b)
	  {
	    right.getSlot(c).setImage(s);
	  }
	  else
	  {
	    left.getSlot(c).setImage(s);
	  }
	}

	@Override
	public void sconfitta() 
	{
		frame.getPanel().getInfo().setStatus("Sconfitta");
		left.removeMouseListener(this);
		right.removeMouseListener(this);
	}

	@Override
	public void vittoria() 
	{
		frame.getPanel().getInfo().setStatus("Vittoria");
		left.removeMouseListener(this);
		right.removeMouseListener(this);
	}

}
