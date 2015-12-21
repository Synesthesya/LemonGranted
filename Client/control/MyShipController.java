package control;

import graphic.Frame;
import graphic.Grid;
import graphic.menu.ErrorPopUp;
import interfaces.Controller;
import interfaces.ServerI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import server.Phase;
import core.Coordinate;
import core.Player;
import graphic.Game;


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
	
	/**
	 * testo
	 */
	private String testo2="";;
	
	/**
	 * riferimento al Player
	 */
	protected Player player;
	/**
	 * stub del server
	 */
	private ServerI server;
	
	/**
	 * riferimento al frame
	 */
	protected Frame frame;
	/**
	 * griglia con le proprie navi
	 */
	protected Grid left;
	/**
	 * griglia con le navi nemiche (quella in cui si spara)
	 */
	protected Grid right;
	
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
		left=((Game)(frame.getPanel())).getGrids().getLeft();
		right=((Game)(frame.getPanel())).getGrids().getRight();
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
			g.deploy(frame.getID(),c);
			
			frame.playSound(3);
		}
		else if(player.getPhase()==Phase.COMBAT && player.getTurno() && g.getName().equals("right") && !player.getEnemyShip().getStatus(c))
		{
			shot(c);
		}
	}
	
	/**
	 * metodo che si occupa di comunicare al Server il colpo sparato
	 * 
	 * @param c la Coordinata colpita
	 */
	protected void shot(Coordinate c) {
		  try
		  {
		    boolean hit=server.shot(player.getID(), c);
		    if(hit) frame.playSound(0);
		    else frame.playSound(1);
		  }
		  catch(RemoteException err)
		  {
		    System.err.println("errore: " +err.getMessage());
		    @SuppressWarnings("unused")
			ErrorPopUp er=new ErrorPopUp("errore: disconnessione dal server\n"+err);
		    frame.setMenu(new MenuController(frame));
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
	    @SuppressWarnings("unused")
		ErrorPopUp er=new ErrorPopUp("errore: impossibile connettersi al server");
	    frame.setMenu(new MenuController(frame));
	  }
	}
	/**
	 * 
	 * metodo utilizzato per modificare un immagine in una griglia
	 * 
	 * @param b <b>true</b> indica la griglia di destra, <b>false</b> la griglia di sinistra
	 * @param c la Coordinata da modificare
	 * @param s il nome dell'immagine da inserire
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
		ending();
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
		ending();
		frame.setEnd(new MenuController(frame), 1+frame.getID());
		frame.playSound(4);
	}
	
	/**
	 * metodo che gestisce la fine di una partita, disconnettendo dal server e deregistrando il Controller
	 */
	protected void ending() {
		left.removeMouseListener(this);
		right.removeMouseListener(this);
		server=null;
		deregistra();
	}
	
	/**
	 * metoto che cambia graficamente la visualizzazione del turno
	 * 
	 * @param t <b>true</b> per farlo diventare il turno del giocatore, <b>false</b> per farlo diventare il turno dell'avversario
	 */
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
		((Game)(frame.getPanel())).getInfo().setStatus(fase + testo2 + s);
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
	
	/**
	 * metodo utilizzato per concludere una partita
	 */
	private void deregistra()
	{
		try
		{
			Naming.unbind("rmi://"+MenuController.IP+MenuController.DOOR+"/player" + player.getID());
		}
		catch(Exception e)
		{
			@SuppressWarnings("unused")
			ErrorPopUp er=new ErrorPopUp("errore durante la deregistrazione dal server!");			
		}
	}
	
	/**
	 * metodo per concludere in anticipo una partita
	 */
	public void errorExit() {
		
		ending();
		@SuppressWarnings("unused")
		ErrorPopUp er=new ErrorPopUp("errore: impossibile proseguire la partita!");
		frame.setMenu(new MenuController(frame));
	}
}
