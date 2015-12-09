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
	 * non � definitivo: funzioner� solamente finch� non ci sar� un men� principale
	 * 
	 * @param f il Frame del gioco
	 */
	//@Deprecated
	@Override
	public void setGrids(Frame f)
	{
		//DIOKHAN
	  left=f.getPanel().getGrids().getLeft();
      right=f.getPanel().getGrids().getRight();
	}

	/**
	 * <p>
	 * funzione che gestisce gli eventi su entrambe le griglie
	 * </p>
	 * <p>
	 * la funzione controlla entrambe le fasi: ad ogni fase � associata una griglia diversa.
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
		else if(player.getPhase()==Phase.COMBAT && g.getName().equals("right") && !player.getEnemyShip().getStatus(c))
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
	 * MOCKUP
	 * 
	 * cambia le immagini quando le caselle vengono cliccate
	 */
	@Override
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
