package core;

import interfaces.Controller;
import interfaces.PlayerI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import server.Phase;
import control.MyShipController;


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
	 * l'id della fazione <b>1</b> impero <b>2</b> ribelli
	 */
	private final int faction;
	
	/**
	 * la griglia contenente le navi
	 */
	private final GridCore myShip;
	
	private final GridCore enemyShip;
		
	/**
	 * il numero di navi ancora vive
	 */
	private int alive;
	
	/**
	 * flag per determinare se il proprio schieramento � terminato
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
	 * indica il turno
	 */
	public boolean turno=false;
	
	/**
	 * costruttore standard
	 * 
	 * @param ID indica se impero/ribelli
	 */
	public Player(int ID, int f) throws RemoteException
	{
	    this.ID=ID;
	    faction=f;
		myShip=new GridCore();
		enemyShip=new GridCore();
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
	 * Controlla se a quelle coordinate c'� una nave
	 */
	public boolean getStatus(Coordinate c)
	{
	  return myShip.getStatus(c);
	}
	
	/**
	 * aggiorna la struttura dati della griglia per aggiungere o meno una nave
	 * se il numero massimo di navi � stato raggiunto, avverte il server che si � finita la fase di schieramento 
	 * @param c
	 * @return 
	 */
	public boolean deploy(Coordinate c) {
  		if(alive<FLEETNUMBER && !myShip.getStatus(c)) 
  		{
  		  alive++;
  		  myShip.deploy(c);
  		  controller.setMessage("Devi schierare ancora "+ (5-getAlive())+ " navi!");
  		  if(alive==FLEETNUMBER) //controlla se � stato raggiunto il numero max di navi
  		  {
  		    deployed=true;
  		    controller.setMessage("Attesa dell'avversario");
  		    controller.checkDeployment();
  		  }
  		  return true;
  		}
		return false;
	}
	
	/**
	 * metodo che aggiorna la struttra dati della griglia per aggiungere le navi;
	 * se il numero massimo di navi � stato raggiunto, viene inizializzata la fase di combattimento
	 * @param c
	 */
	public boolean deploySP(Coordinate c) {
		
		if(alive<FLEETNUMBER && !myShip.getStatus(c)) {
			alive++;
			myShip.deploy(c);
			controller.setMessage("Devi schierare ancora "+ (5-getAlive())+ " navi!");
			if(alive==FLEETNUMBER) {
				deployed=true;
				setPhase(Phase.COMBAT);
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
	  controller.setMessage("Devi schierare ancora 5 navi!");
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
		if(fase==Phase.COMBAT)
		{
			controller.setFase("Combattimento! ");
			controller.setMessage("E' il turno dell'avversario!");
		}
	}
	
	/**
	 * metodo per conoscere la fase
	 * @return
	 */
	public Phase getPhase()
	{
	  return fase;
	}
	
	
	@Override
	public String toString() {
		
		return "ID: "+ID+"\tnavi ancora in vita: "+alive+"\nnavi possedute:\n"+myShip+"\ncolpi sparati:\n"+enemyShip;		
	}
	

	@Override
	public void nemicoColpito(Coordinate c) throws RemoteException 
	{
		controller.setTesto2("Nemico colpito! ");
		controller.isHit();
		enemyShip.setGridValue(true, c); //la coordinata � stata usata
		if(faction==1)
			controller.setImage(true,c,"XW_RED");
		else
			controller.setImage(true, c, "TF_RED");
	}

	@Override
	public void nemicoMancato(Coordinate c) throws RemoteException 
	{
		controller.setTesto2("Nemico mancato! ");
		enemyShip.setGridValue(true, c);
		controller.setImage(true,c,"SpaceSquareBorder");
	}

	@Override
	public void colpoSubito(Coordinate c) throws RemoteException 
	{
		controller.setTesto2("Sei stato colpito! ");
		controller.imHit();
		myShip.setGridValue(false, c);
		alive--;
		if(faction==1)
			controller.setImage(false, c, "TF_RED");
		else
			controller.setImage(false, c, "XW_RED");
	}

	@Override
	public void colpoSchivato(Coordinate c) throws RemoteException 
	{
		controller.setTesto2("Sei stato mancato! ");
		//niente setGridValue perch� � gi� falso
		controller.setImage(false,c,"SpaceSquareBorder");
	}

	@Override
	public void sconfitta() throws RemoteException 
	{
		controller.sconfitta();
	}

	@Override
	public void vittoria() throws RemoteException 
	{
		controller.vittoria();
	}
	

	public boolean getTurno()
	{
		return turno;
	}
	
	@Override
	public void cambiaTurno()
	{
		turno=!turno;
		controller.cambiaTurno(turno);
	}

	@Override
	public String print() throws RemoteException {

		return this.toString();
	}
	
	/**
	 * metodo che causa l'uscita preventiva e il reset del gioco
	 */
	@Override
	public void errorExit() {
		((MyShipController)controller).errorExit();
	}
	
}