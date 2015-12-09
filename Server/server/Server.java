package server;

import interfaces.PlayerI;
import interfaces.ServerI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import core.Coordinate;

/**
 * Classe server per gestire i turni e le modifiche del gioco
 * Il server si lancia da /LemonGranted con: 
 * java -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase=http://127.0.0.1:8080/ -Djava.security.policy=bin/server/server.policy -cp "bin" server.Server
 * @author Francesco
 *
 */
public class Server extends UnicastRemoteObject implements ServerI
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * il numero massimo di giocatori consentiti
	 * questo numero non è modificabile: per le regole della battaglia navale, è sempre 2!
	 */
	public static final int NUMERO_GIOCATORI = 2;
  
  /**
   * stub
   */
	private PlayerI player1=null;
  /**
   * stub
   */
	private PlayerI player2=null;
  
	private boolean turno=false;
  
  /**
   * numero di giocatori registrati al server
   */
	private int giocatori=0;
  
  
  /**
   * Istanzia la classe e rimane in ascolto per i client
   * @throws RemoteException
   */
	public Server () throws RemoteException
	{
		try	{
	      //System.setProperty("java.security.policy", "server.policy");
	      if(System.getSecurityManager()==null)
	      {
	        System.setSecurityManager(new SecurityManager());
	      }
	      
	      java.rmi.registry.LocateRegistry.createRegistry(1677);
	      Naming.rebind("rmi://127.0.0.1:1677/server", this);
	    }
	    catch(Exception e)
	    {
	      System.err.println("Failed to bind to RMI Registry" + e);
	      System.exit(1);
	    }
	 }
	  
  /**
   * chiude il server
   */
protected void exit()
  {
    System.exit(0);
  }
  
  /**
   * restituise l'id del giocatore (1,2) da usare solo al momento della registrazione del giocatore
   */
  public int getID()
  {
    return giocatori;
  }
  
  /**
   * Registra un massimo di due giocatori
   */
  public boolean registraPlayer()
  {
    if(giocatori<NUMERO_GIOCATORI)
    {
      giocatori++;
      return true;
    }
    return false;
  }
  
  /**
   * <p>Chiamato da un client per caricare lo stub dei dati di gioco</p>
   * <p>mockup</p>
   */
  @Override
  public void caricaPlayer() {
	  
    try {	
    	if(player1==null) {
    		player1=(PlayerI)Naming.lookup("rmi://127.0.0.1:1677/player1");
    	}
    	else {
    		player2=(PlayerI)Naming.lookup("rmi://127.0.0.1:1677/player2");
    	}
    }
    catch(Exception e)
    {
      //Vuoto perchè verrà sempre generata un'eccezione che ci si aspetta
    }
  }
  
  /**
   * MOCKUP
   * 
   * Chiamato dai client che richiedere l'elaborazione di uno sparo
   * 
   * WARNING: ha un nome diverso da quello che si aspetta
 * @throws RemoteException 
   */
  @Override
  public void shot(int ID, Coordinate c) throws RemoteException
  {

    	/*controlla quale player è che spara 
    	 * e se l'altro player ha una nave a quelle coordinate
    	 */
      if(ID==1 && player2.getStatus(c))
      {
          player2.callHit(true, c);
          player1.callHit(false, c);
      }
      else if(player1.getStatus(c))
      {
        player1.callHit(true, c);
        player2.callHit(false, c);
      }
 

    }
  
  
  /**
   * Controlla che entrambi i client siano pronti
   * @return true se entrambi i giocatori hanno finitodi schierare, false altrimenti
   */
  public void isReady()
  {
    try
    {
      if(player1.getDeployed() && player2.getDeployed())
      {
        player1.setPhase(Phase.COMBAT);
        player2.setPhase(Phase.COMBAT);
        //System.out.println("uscita fase di schieramento");
      }
    }
    catch(Exception e)
    {
      System.err.println(e.getMessage());
    }
  }
    
  protected PlayerI getPlayer1() {
	  return player1;
  }
  protected PlayerI getPlayer2() {
	  return player2;
  }
  
  /**
   * Il main che istanzia il server
   * @param args
   */
  public static void main (String[] args)
  {
	  Server s=null;
    try
    {
      s=new Server();      
    }
    catch(Exception e)
    {
      System.out.println("Failed to create Server object" + e.getMessage());
      System.exit(-1);
    }
    
    Command cmd=new Command(s);
    cmd.run();
  }

}
