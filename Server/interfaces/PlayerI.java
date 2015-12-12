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
	 * <p>controlla se a quelle coordinate c'è una nave</p>
	 * <p>usato principalmente per la griglia di sinistra</p>
	 */
  public boolean getStatus(Coordinate c) throws RemoteException;
  
  	/**
  	 * Dichiara le coordinate sulla griglia di destra come colpita
  	 * @param c le coordinate
  	 * @throws RemoteException
  	 */
  	public void nemicoColpito(Coordinate c) throws RemoteException;
  	/**
  	 * Dichiara le coordinate sulla griglia di destra come mancato
  	 * @param c le coordinate
  	 * @throws RemoteException
  	 */
  	public void nemicoMancato(Coordinate c) throws RemoteException;
  	/**
  	 * Dichiara le coordinate sulla griglia di sinistra come colpita
  	 * @param c le coordinate
  	 * @throws RemoteException
  	 */
  	public void colpoSubito(Coordinate c) throws RemoteException;
  	/**
  	 * Dichiara le coordinate sulla griglia di sinistra come mancato
  	 * @param c le coordinate
  	 * @throws RemoteException
  	 */
  	public void colpoSchivato(Coordinate c) throws RemoteException;
}
