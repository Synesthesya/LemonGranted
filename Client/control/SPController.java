package control;

import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import core.Coordinate;
import core.Player;
import core.SPPlayer;
import graphic.Grid;
import graphic.menu.ErrorPopUp;
import server.Phase;

/**
 * classe che gestisce una partita in SinglePlayer
 * 
 * @author Synesthesy
 *
 */
public class SPController extends MyShipController {
	
	/**
	 * il giocatore controllato dal PC
	 */
	private SPPlayer enemy;
	
	/**
	 * costruttore standard
	 * @param p i dati del giocatore umano
	 */
	public SPController(Player p) {
		
		super(p,null);
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
	
	
	/**
	 * metodo che gestisce gli eventi di una partita
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	
		Grid g=(Grid)e.getComponent();
		
		int x=e.getX()/50;
		int y=e.getY()/50;
		
		Coordinate c=new Coordinate(x,y);
		
		if(player.getPhase()==Phase.DEPLOYMENT && g.getName().equals("left") && player.deploySP(c)) {
			
			g.deploy(frame.getID(),c);			
			frame.playSound(3);
		}
		else if(player.getPhase()==Phase.COMBAT && player.getTurno() && g.getName().equals("right") && !player.getEnemyShip().getStatus(c)) {
			
			boolean hit=enemy.reciveHit(c);
			if(hit) {
				frame.playSound(0);
				right.setHit(frame.getID(), c);
			}
			else {
				frame.playSound(1);
				right.setExplored(c);
			}
			
			endGame(true);
			
			player.cambiaTurno();
			
			try {
				Thread.sleep(500);
				enemyTurn();
			}
			catch(Exception e1) {
				@SuppressWarnings("unused")
				ErrorPopUp er=new ErrorPopUp(""+e);
				frame.setMenu(new MenuController(frame));
			}
			endGame(false);
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
	
	public void endGame(boolean a) {
		
		if(a && !enemy.isAlive()) {
			left.removeMouseListener(this);
			right.removeMouseListener(this);
			frame.setEnd(new MenuController(frame), frame.getID()+1);
			frame.playSound(4);
		}
		else if(!a && player.isAlive()) {
			left.removeMouseListener(this);
			right.removeMouseListener(this);
			frame.setEnd(new MenuController(frame), frame.getID()-1);
			frame.playSound(2);
		}		
	}
}
