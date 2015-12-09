package core;

import interfaces.Controller;
import interfaces.PlayerI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import server.Phase;

//import javafx.scene.media.*;

/**
 * <p>
 * classe che contiene tutti i dati di un giocatore
 * </p>
 * <p>
 * i dati consistono in una coppia di GridCore contenenti le informazioni delle proprie navi e delle navi colpite,
 * e nell'elenco delle proprie navi sottoforma di oggetti Ship (questo usato maggiormente con navi di lunghezza maggiore di 1)
 * 
 * @author Alex
 * 
 * @extends UnicastRemoteObject per poter essere inviato a server
 * @implements PlayerI lo stub usato dal server
 *
 */
public class Player extends UnicastRemoteObject implements PlayerI
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	/**
	 * il numero di navi per flotta
	 */
	public static int FLEETNUMBER=5;
	
	/**
	 * <p>
     * Identificativo Player che indica la distinzione tra impero e ribelli
     * </p>
     * <p>
     * 1 Impero
     * altro ribelli
     * </p>
     */
	private final int ID;
	/**
	 * la griglia contenente le navi
	 */
	private final GridCore myShip;
	
	private final GridCore enemyShip;
	
	/**
	 * l'elenco delle navi del giocatore
	 */
	private final Ship[] fleet;
	
	/**
	 * il numero di navi ancora vive
	 */
	private int alive;
	
	/**
	 * flag per determinare se il proprio schieramento è terminato
	 */
	private boolean deployed=false;
	
	/**
	 * riferimento al Controller
	 */
	private Controller controller;
	
	/**
	 * fase del gioco in cui si trova il Player
	 */
	private Phase fase = Phase.DEPLOYMENT;
	
	/**
	 * il nome in gioco del giocatore
	 */
	private String name = "player";
	
	
	/**
	 * costruttore standard
	 * 
	 * @param ID indica se impero/ribelli
	 */
	public Player(int ID) throws RemoteException
	{
	    this.ID=ID;
		myShip=new GridCore();
		enemyShip=new GridCore();
		fleet=new Ship[FLEETNUMBER];
		alive=0;		
	}
	
	/**
	 * 
	 * metodo che ritorna la propria griglia
	 * 
	 * @return la griglia di sinistra
	 */
	public GridCore getMyShip() {
		return myShip;
	}
	
	/**
	 * 
	 * metodo che ritorna la griglia con le navi avversarie (in cui si spara)
	 * 
	 * @return la griglia di destra
	 */
	public GridCore getEnemyShip()
	{
	  return enemyShip;
	}
	
	/**
	 * metodo per conoscere le navi ancora vive
	 * 
	 * @return il numero di navi ancora vive
	 */
	public int getAlive() {
		return alive;
	}
	
	/**
	 * metodo che dice se il giocatore ha ancora navi o meno
	 * 
	 * @return <b>true</b> se il giocatore ha ancora navi, <b>false</b> altrimenti
	 */
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
		
		if(myShip.getStatus(c)) {
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
	 * Controlla se a quelle coordinate c'è una nave
	 */
	public boolean getStatus(Coordinate c)
	{
	  return myShip.getStatus(c);
	}
	
	/**
	 * chiamto dal server per avvertire che si vuole agire a quelle coordinate
	 */
	public void callHit(boolean b, Coordinate c)
	{
	  if(b)
	  {
	    myShip.hit(c);
	    controller.setImage(b,c);
	  }
	}
	
	/**
	 * aggiorna la struttura dati della griglia per aggiungere o meno una nave
	 * se il numero massimo di navi è stato raggiunto, avverte il server che si è finita la fase di schieramento 
	 * @param c
	 * @return 
	 */
	public boolean deploy(Coordinate c) {
  		if(alive<FLEETNUMBER && !myShip.getStatus(c)) 
  		{
  		  alive++;
  		  myShip.deploy(c);
  		  if(alive==FLEETNUMBER) //controlla se è stato raggiunto il numero max di navi
  		  {
  		    deployed=true;
  		    controller.checkDeployment();
  		  }
  		  return true;
  		}
		return false;
	}
	
	/**
	 * restituisce la flag legata allo schieramento
	 */
	public boolean getDeployed()
	{
	  return deployed;
	}
	
	/**
	 * aggiunge un riferimento al controller
	 * @param c
	 */
	public void setController(Controller c)
	{
	  controller=c;
	}
	
	/**
	 * restituisce l'ID
	 * @return
	 */
	public int getID()
	{
	  return ID;
	}
	
	/**
	 * metodo per settare la fase
	 * 
	 * @param fase
	 */
	public void setPhase(Phase fase)
	{
	  this.fase=fase;
	}
	
	/**
	 * metodo per conoscere la fase
	 * @return
	 */
	public Phase getPhase()
	{
	  return fase;
	}
	
	/**
	 * metodo per ottenere il nick del giocatore
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * metodo per settare il nick del giocatore
	 * @param n
	 */
	public void setName(String n) {
		name=n;
	}
	
	
	@Override
	public String toString() {
		
		return "Nome: "+name+"\tID: "+ID+"\tnavi ancora in vita: "+alive+"\nnavi possedute:\n"+myShip+"\ncolpi sparati:\n"+enemyShip;		
	}
	
}