package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.Phase;
import core.Coordinate;

/**
 * interfaccia Player per lo stub
 * 
 * @author Alex
 *
 */
public interface PlayerI extends Remote
{
	/**
	 * restituisce la flag legata allo schieramento
	 */
  public boolean getDeployed() throws RemoteException;
	/**
	 * metodo per settare la fase
	 * 
	 * @param fase
	 */
  public void setPhase(Phase fase) throws RemoteException;
	/**
	 * Controlla se a quelle coordinate c'è una nave
	 */
  public boolean getStatus(Coordinate c) throws RemoteException;
	
	/**
	 * chiamto dal server per avvertire che si vuole agire a quelle coordinate
	 */
  public void callHit(boolean b, Coordinate c) throws RemoteException;
  
  /**
   * metodo per ottenere il nick del giocatore
   * @return il nick scelto
   */
  public String getName();
}
