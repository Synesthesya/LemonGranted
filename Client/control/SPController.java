package control;

import interfaces.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import core.Coordinate;
import core.Player;
import core.SPPlayer;
import graphic.Frame;
import graphic.Game;
import graphic.Grid;
import graphic.menu.ErrorPopUp;
import server.Phase;

/**
 * classe che gestisce una partita in SinglePlayer
 * 
 * @author Synesthesy
 *
 */
public class SPController extends MouseAdapter implements Controller {
	
	/**
	 * il giocatore controllato dal PC
	 */
	private SPPlayer enemy;
	
	private Player player;
	
	private Frame frame;
	
	private Grid left;
	
	private Grid right;
	
	private String fase="Schieramento! ";
	
	/**
	 * testo
	 */
	private String testo2="";
	
	/**
	 * costruttore standard
	 * @param p i dati del giocatore umano
	 */
	public SPController(Player p) {
		
		player=p;
		enemy=new SPPlayer();
		System.out.println("hello");
		initialize();
	}
	
	/**
	 * metodo di inizializzazione, da eseguire prima dell'inizio della fase di schieramento
	 */
	public void initialize() {
		enemy.deploy();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		Grid g=(Grid)e.getComponent();
		
		int x=e.getX()/graphic.Slot.CELLSIZE;
		int y=e.getY()/graphic.Slot.CELLSIZE;
		
		Coordinate c=new Coordinate(x,y);
		if(player.getPhase()==Phase.DEPLOYMENT && g.getName().equals("left") && player.deploySP(c)) 
		{			
			g.deploy(frame.getID(),c);
			
			frame.playSound(3);
		}
		else if(player.getPhase()==Phase.COMBAT && player.getTurno() && g.getName().equals("right") && !player.getEnemyShip().getStatus(c))
		{
			shot(c);
		}
	}
	
	
	public void shot(Coordinate c) {
		
		boolean hit=enemy.reciveHit(c);
		player.getEnemyShip().deploy(c);
		if(hit) {
			frame.playSound(0);
			right.setHit(frame.getID(), c);
			isHit();
		}
		else {
			frame.playSound(1);
			right.setExplored(c);
		}
		try {
			ending(true);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
		player.cambiaTurno();
		
		try {
			Thread.sleep(1000);
			enemyTurn();
		}
		catch(Exception e1) {
			@SuppressWarnings("unused")
			ErrorPopUp er=new ErrorPopUp(""+e1);
			frame.setMenu(new MenuController(frame));
		}
		try {
			ending(false);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * metodo che gestisce il turno avversario
	 * @throws RemoteException ereditato dalla classe MyShipController che estende un oggetto remoto
	 */
	public void enemyTurn() throws RemoteException {
				
		Coordinate c=enemy.hit();
		
		if(player.getStatus(c)) {
			player.colpoSubito(c);
			frame.playSound(getOtherFaction(frame.getID()),0);
		}
		else {
			player.colpoSchivato(c);
			frame.playSound(getOtherFaction(frame.getID()),1);
		}
		player.cambiaTurno();
	}
	
	/**
	 * metodo che converte Impero in Ribelli e vice versa
	 * @param id la fazione di partenza
	 * @return 1 diventa 2, 2 diventa 1
	 */
	public static int getOtherFaction(int id) {
		
		if(id==1) return 2;
		else return 1;
	}
	
	/**
	 * metodo che gestisce la fine della partita in SinglePlayer
	 * 
	 * @param a <b>true</b> per controllare il player, <b>false</b> per controllare il computer
	 * @throws InterruptedException causata dall'attesa che si ha per consentire ai file sonori di essere eseguiti prima di passare al Panel di fine gioco
	 */
	public void ending(boolean a) throws InterruptedException {
		
		if(a && !enemy.isAlive()) {
			Thread.sleep(1000);
			left.removeMouseListener(this);
			right.removeMouseListener(this);
			frame.setEnd(new MenuController(frame), frame.getID()+1);
			frame.playSound(4);
		}
		else if(!a && !player.isAlive()) {
			Thread.sleep(1000);
			left.removeMouseListener(this);
			right.removeMouseListener(this);
			frame.setEnd(new MenuController(frame), frame.getID()-1);
			frame.playSound(2);
		}		
	}

	@Override
	public void checkDeployment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setImage(boolean b, Coordinate c, String s) {
		// TODO Auto-generated method stub
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
	public void linkFrame(Frame f) {
		frame=f;
		left=((Game)(frame.getPanel())).getGrids().getLeft();
		right=((Game)(frame.getPanel())).getGrids().getRight();
		
	}

	@Override
	public void sconfitta() {
		// TODO Auto-generated method stub
	}

	@Override
	public void vittoria() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiaTurno(boolean t) {
		// TODO Auto-generated method stub
		if(t)
			setMessage("è il tuo turno!");
		else
			setMessage("è il turno dell'avversario!");
	}

	@Override
	public void setMessage(String s) {
		// TODO Auto-generated method stub
		((Game)(frame.getPanel())).getInfo().setStatus(fase + testo2 + s);
	}

	@Override
	public void setFase(String string) {
		// TODO Auto-generated method stub
		fase=string;
	}

	@Override
	public void setTesto2(String string) {
		// TODO Auto-generated method stub
		testo2=string;
	}
	
	/**
	 * scala di uno il numero di navi possedute
	 */
	public void imHit() {
		
		((Game)frame.getPanel()).getInfo().setPlayerHit();		
	}
	
	/**
	 * scala di uno il numero di navi del nemico
	 */
	public void isHit() {
		((Game)frame.getPanel()).getInfo().setEnemyHit();
	}
}
