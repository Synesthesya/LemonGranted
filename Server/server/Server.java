package server;

import interfaces.PlayerI;
import interfaces.ServerI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe server per gestire i turni e le modifiche del gioco
 * Il server si lancia da /LemonGranted con: 
 * java -Djava.rmi.server.logCalls=true -Djava.rmi.server.codebase=http://127.0.0.1:8080/ -Djava.security.policy=bin/server/server.policy -cp "bin" server.Server
 * @author Francesco
 *
 */
public class Server extends UnicastRemoteObject implements ServerI
{
  public static final int NUMERO_GIOCATORI = 2;
  
  static private boolean ready1=false;
  static private boolean ready2=false;
  private PlayerI player1;
  private PlayerI player2;
  
  public int giocatori=0;
  
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
   * Chiamato da un metodo del server per chiudere il gioco
   */
  private void exit()
  {
    System.exit(0);
  }
  
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
   * Chiamato da un client per caricare lo stub dei dati di gioco
   */
  public void caricaPlayer()
  {
    try
    {
       player1=(PlayerI)Naming.lookup("rmi://127.0.0.1:1677/player1");
       player2=(PlayerI)Naming.lookup("rmi://127.0.0.1:1677/player2");
    }
    catch(Exception e)
    {
      //Vuoto perchè verrà sempre generata un'eccezione che ci si aspetta
    }
  }
  
  /**
   * Chiamato dai client che richiedere l'elaborazione di uno sparo
   */
  public void elabora()
  {
    
  }
  
  /**
   * Controlla che entrambi i client siano pronti
   * @return
   */
  private static boolean isReady()
  {
    return (ready1 && ready2);
  }
  
  /**
   * Il main che istanzia il server
   * @param args
   */
  public static void main (String[] args)
  {
    try
    {
      Server s=new Server();
    }
    catch(Exception e)
    {
      System.out.println("Failed to create Server object" + e.getMessage());
    }
  }
}
