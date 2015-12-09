package core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * 
 * la classe Grid rappresenta la griglia di gioco, 10x10, in cui avviene lo scontro.
 * L'IA e il giocatore umano la utilizzano in maniera diversa, ma il suo scopo resta il rappresentare la posizione delle navi e dei tiri colpiti.
 * 
 * @author Synesthesy
 *
 */
public class GridCore
{
	
	public static final int DIMENSION=100;
	public static final int MAX_COORDINATE=10;
	private boolean[] grid;

	/**
	 * costruttore standard che inizializza una griglia vuota di dimensione <i>DIMENSION</i>
	 */
	public GridCore()
	{
		
		grid=new boolean[DIMENSION];
		for(int i=0; i<DIMENSION; i++) {
			grid[i]=false;
		}
	}
	
	/**
	 * metodo per settare una casella come usata
	 * @param d la coordinata della casella
	 */
	public void deploy(int d) {
		grid[d]=true;
	}
	
	/**
	 * metodo per settare una casella come usata
	 * @param c la coordinata della casella
	 */
	public void deploy(Coordinate c) 
	{
	  grid[c.toInteger()]=true;
		//deploy(c.toInteger());
	}
	
	/**
	 * metodo per settare le coordinate occupate da una Ship come usate
	 * @param s un oggetto Ship valido
	 */
	public void deploy(Ship s) {
		
		int j=1;
		int change=0, nochange;
		
		if(s.getDirection().equals("UP")) {
			j=j*10;
			nochange=s.getHead().getColumn();
			change=s.getTail().getRow()*10;
		}
		else {
			nochange=s.getHead().getRow()*10;
			change=s.getTail().getColumn();
		}
		
		for(int i=0; i<s.getLength(); i++) {
			
			int c=change+nochange;
			grid[c]=true;
			change=change+j;			
		}		
	}


	/**
	 * metodo per settare una casella come usata
	 * @param d la coordinata della casella
	 */
	public void hit(int d) {
		grid[d]=true;
	}
	
	/**
	 * metodo per settare una casella come usata
	 * @param c la coordinata della casella
	 */
	public void hit(Coordinate c) {
		//hit(c.toInteger());
	  grid[c.toInteger()]=true;
	}
	
	
	public boolean getStatus(int d) {
		return grid[d];
	}
	
	/**
	 * metodo per conoscere lo status di una casella
	 * @param c la coordinata della casella richiesta
	 * @return lo stato della casella
	 */
	public boolean getStatus(Coordinate c) {
		return grid[c.toInteger()];
	}
	
	@Override
	public String toString() {
		
		String s="";
		
		for(int i=0; i<DIMENSION; i++) {
			
			if(grid[i]==true) s=s+"X";
			else s=s+" ";
			if(i%10==0) s=s+"\n";			
		}
		return s;		
	}
	
	

}
