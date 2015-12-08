package core;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//import javafx.scene.media.*;

public class Player extends UnicastRemoteObject implements PlayerI
{
    /**
     * Identificativo Player
     */
	public int ID;
	
	/**
	 * il numero di navi per flotta
	 */
	public static int FLEETNUMBER=5;
	
	/**
	 * la griglia contenente le navi
	 */
	private Grid grid;
	
	/**
	 * l'elenco delle navi del giocatore
	 */
	private Ship[] fleet;
	
	/**
	 * il numero di navi ancora vive
	 */
	private int alive;
	
	/**
	 * costruttore standard
	 */
	public Player(int ID) throws RemoteException
	{
	    this.ID=ID;
		grid=new Grid();
		fleet=new Ship[FLEETNUMBER];
		alive=0;		
	}
	
	public Grid getGrid() {
		return grid;
	}
	
	public int getAlive() {
		return alive;
	}
	
	public boolean isAlive() {
		return alive>0;
	}
	
	/**
	 * metodo per il controllo delle caselle colpite: il metodo 
	 * colpisce una casella, controlla lo stato delle navi e ritorna
	 * 
	 * @param c la coordinata colpita
	 * @return <b>true</b> se è stata colpita una nave, <b>false</b> altrimenti
	 */
	public boolean hit(Coordinate c) {
		
		if(grid.getStatus(c)) {
			for(int i=0; i<alive; i++) {
				if(fleet[i].isInside(c)) {
					fleet[i].hit();
					if(!fleet[i].isAlive()) {
						Ship s=fleet[i];
						fleet[i]=fleet[alive];
						fleet[alive]=s;
						alive--;
					}
					break;
				}
			}
			return true;
		}
		else return false;		
	}
	
	/**
	 * metodo di mockup, serivirà un metodo (Ship s)
	 * @param c
	 * @return 
	 */
	public boolean deploy(Coordinate c) {
		
		if(alive<FLEETNUMBER && !grid.getStatus(c)) {
			alive++;
			grid.deploy(c);
			return true;
		}
		else return false;
	}
}
