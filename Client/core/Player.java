package core;

import graphic.Frame;
import interfaces.Controller;
import interfaces.PlayerI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server.Phase;

//import javafx.scene.media.*;

public class Player extends UnicastRemoteObject implements PlayerI
{
    /**
     * Identificativo Player
     */
	private int ID;
	
	/**
	 * il numero di navi per flotta
	 */
	public static int FLEETNUMBER=5;
	
	/**
	 * la griglia contenente le navi
	 */
	private GridCore myShip;
	
	private GridCore enemyShip;
	
	/**
	 * l'elenco delle navi del giocatore
	 */
	private Ship[] fleet;
	
	/**
	 * il numero di navi ancora vive
	 */
	private int alive;
	
	/**
	 * flag per determinare se lo schieramento è terminato
	 */
	private boolean deployed=false;
	
	/**
	 * riferimento al Controller
	 */
	private Controller controller;
	
	private Phase fase = Phase.DEPLOYMENT;
	/**
	 * costruttore standard
	 */
	public Player(int ID) throws RemoteException
	{
	    this.ID=ID;
		myShip=new GridCore();
		enemyShip=new GridCore();
		fleet=new Ship[FLEETNUMBER];
		alive=0;		
	}
	
	public GridCore getMyShip() {
		return myShip;
	}
	
	public GridCore getEnemyShip()
	{
	  return enemyShip;
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
	/*public boolean hit(Coordinate c) {
		
		if(gridcore.getStatus(c)) {
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
	}*/
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
	
	public void setPhase(Phase fase)
	{
	  this.fase=fase;
	}
	
	public Phase getPhase()
	{
	  return fase;
	}
}