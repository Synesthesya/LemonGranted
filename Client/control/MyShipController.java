package control;

import graphic.Frame;
import graphic.Grid;
import graphic.menu.ErrorPopUp;
import interfaces.Controller;
import interfaces.ServerI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;

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
public class MyShipController extends MouseAdapter implements Controller 
{
	/**
	 * stringa che indica la fase
	 */
	private String fase="Schieramento! ";
	
	private String testo2="";;
	
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
		left=frame.getGamePanel().getGrids().getLeft();
		right=frame.getGamePanel().getGrids().getRight();
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
			if(frame.getID()==1) {
			  g.getSlot(c).setImage("TF");
			}
			else
			  g.getSlot(c).setImage("XW_Square");
			
			frame.playSound(3);
		}
		else if(player.getPhase()==Phase.COMBAT && player.getTurno() && g.getName().equals("right") && !player.getEnemyShip().getStatus(c))
		{
		  try
		  {
		    boolean hit=server.shot(player.getID(), c);
		    if(hit) frame.playSound(0);
		    else frame.playSound(1);
		  }
		  catch(RemoteException err)
		  {
		    System.err.println("errore: " +err.getMessage());
		    ErrorPopUp er=new ErrorPopUp("errore: disconnessione dal server\n"+err);
		    frame.setMenu(new MenuController(frame));
		  }
		  catch(IOException e1) {
			  ErrorPopUp er=new ErrorPopUp("errore: impossibile eseguire il file audio "+frame.getID()+" 1");
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
	    ErrorPopUp er=new ErrorPopUp("errore: impossibile connettersi al server");
	    frame.setMenu(new MenuController(frame));
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
	
	/**
	 * metodo per gestire la sconfitta
	 */
	@Override
	public void sconfitta() 
	{
		fase="Fine gioco! ";
		//setMessage("Sconfitta! ");
		left.removeMouseListener(this);
		right.removeMouseListener(this);
		server=null;
		deregistra();
		frame.setEnd(new MenuController(frame), frame.getID()-1);
		frame.playSound(2);
	}
	
	/**
	 * metodo per gestire la vittoria
	 */
	@Override
	public void vittoria() 
	{
		fase="Fine gioco! ";
		//setMessage("Vittoria! ");
		left.removeMouseListener(this);
		right.removeMouseListener(this);
		server=null;
		deregistra();
		frame.setEnd(new MenuController(frame), 1+frame.getID());
		frame.playSound(4);
	}

	@Override
	public void cambiaTurno(boolean t)
	{
		if(t)
			setMessage("è il tuo turno!");
		else
			setMessage("è il turno dell'avversario!");
	}
	
	/**
	 * cambia il messaggio nel label status, composto da fase + il paramentro stringa
	 * @param s la stringa
	 */
	@Override
	public void setMessage(String s)
	{
		frame.getGamePanel().getInfo().setStatus(fase + testo2 + s);
	}

	@Override
	public void setFase(String string) 
	{
		fase=string;
	}

	@Override
	public void setTesto2(String string)
	{
		testo2=string;
	}
	
	private void deregistra()
	{
		try
		{
			Naming.unbind("rmi://127.0.0.1:1677/player" + player.getID());
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			
		}
	}
}
