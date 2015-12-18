package server;

import interfaces.PlayerI;
import interfaces.ServerI;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import core.Coordinate;
import graphic.menu.ErrorPopUp;
import control.MyShipController;

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
	 * numero massimo di giocatori
	 */
	public static final int NUMERO_GIOCATORI = 2;
  
	/**
	 * stub
	 */
	private PlayerI player1;
	/**
	 * stub
	 */
	private PlayerI player2;
  
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
	    try
	    {
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
	  protected void uscita()
	  {
		  
		  try {
			if(player1!=null) {
				  player1.errorExit();
			  }
			  if(player2!=null) {
				  player2.errorExit();
			  }
			  System.exit(0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}		  
	  }
	  
	  /**
	   * restituise l'id del giocatore (1,2) da usare solo al momento della registrazione del giocatore
	   */
	  public synchronized int getID() 
	  {
		  giocatori++;
		  return giocatori;
	  }
	  
	  /**
	   * Registra un massimo di due giocatori
	   */
	  public boolean registraPlayer()
	  {
	    if(giocatori<NUMERO_GIOCATORI)
	    {
	      return true;
	    }
	    return false;
	  }
	  
	  /**
	   * <p>Chiamato da un client per caricare lo stub dei dati di gioco</p>
	   * <p>mockup</p>
	   */
	  @Override
	  public synchronized void caricaPlayer()
	  {
	    try
	    {
	    	if(player1==null)
		       player1=(PlayerI)Naming.lookup("rmi://127.0.0.1:1677/player1");
	    	else
		       player2=(PlayerI)Naming.lookup("rmi://127.0.0.1:1677/player2");
	    }
	    catch(Exception e)
	    {
	      System.err.println(e.getMessage());
	    }
	  }

  	/**
  	 * 
  	 */
  	@Override
	public synchronized boolean shot(int ID, Coordinate c) throws RemoteException
	{
  		boolean r=false;
  		if(ID==1) //giocatore 1 spara
  		{
  			if(player2.getStatus(c)) //true indica colpito
  			{
  				r=true;
  				player1.nemicoColpito(c);
  				player2.colpoSubito(c);
  			}
  			else //mancato
  			{
  				r=false;
  				player1.nemicoMancato(c);
  				player2.colpoSchivato(c);
  			}
  		}
  		else //giocatore 2 spara
  		{
  			if(player1.getStatus(c)) //true indica colpito
  			{
  				r=true;
  				player2.nemicoColpito(c);
  				player1.colpoSubito(c);
  			}
  			else //mancato
  			{
  				r=false;
  				player2.nemicoMancato(c);
  				player1.colpoSchivato(c);
  			}
  		}
  		player1.cambiaTurno();
  		player2.cambiaTurno();
  		if(!player1.isAlive())
  		{
  			player1.sconfitta();
  			player2.vittoria();
  			deregistra();
  		}
  		else if(!player2.isAlive())
  		{
  			player1.vittoria();
  			player2.sconfitta();
  			deregistra();
  		}
  		return r;
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
	        player1.cambiaTurno();
	        //System.out.println("uscita fase di schieramento");
	      }
	    }
	    catch(Exception e)
	    {
	      System.err.println(e.getMessage());
	    }
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
	      System.out.println(e.getMessage());
	      s.uscita();
	    }
	    
	    
	    Command c=new Command(s);
	    c.run();
	    
	  }
  
	  /**
	   * metodo per ottenere i due Player dal server
	   * 
	   * @return un array contenente in posizione 0 player1 e in posizione 1 player2
	   */
	  public PlayerI[] getPlayers() {
		  PlayerI[] i={player1, player2};
		  return i;
	  }

	@Override
	public void deregistra() throws RemoteException 
	{
		player1=null;
		player2=null;
		giocatori=0;
	}
	
}
