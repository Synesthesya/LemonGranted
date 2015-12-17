package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import core.Coordinate;

/**
 * 
 * interfaccia stub Server
 * 
 * @author Alex
 *
 */
public interface ServerI extends Remote
{
	  /**
	   * <p>Chiamato da un client per caricare lo stub dei dati di gioco</p>
	   * <p>mockup</p>
	   * 
	   * @throws RemoteException
	   */
	public void caricaPlayer() throws RemoteException;
	  /**
	   * Registra un massimo di due giocatori
	   * 
	   * @throws RemoteException
	   */
	public boolean registraPlayer() throws RemoteException;
	  /**
	   * restituise l'id del giocatore (1,2) da usare solo al momento della registrazione del giocatore
	   * 
	   * @throws RemoteException
	   */
	public int getID() throws RemoteException;
	
	  /**
	   * Controlla che entrambi i client siano pronti
	   * @return true se entrambi i giocatori hanno finitodi schierare, false altrimenti
	   * 
	   * @throws RemoteException
	   */
	public void isReady() throws RemoteException;
	  /**
	   * MOCKUP
	   * 
	   * Chiamato dai client che richiedere l'elaborazione di uno sparo
	   * 
	   * WARNING: ha un nome diverso da quello che si aspetta
	   * 
	   * Il valore di return viene utilizzato dalla classe che elabora il sonoro
	   * 
	   * @return <b>true</b> se è stata colpita una nave, <b>false</b> altrimenti.
	   * @throws RemoteException 
	   */
	public boolean shot(int ID, Coordinate c) throws RemoteException;
	
	/**
	 * deregistra i giocatori
	 * @throws RemoteException
	 */
	public void deregistra() throws RemoteException;
}
