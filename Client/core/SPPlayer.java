package core;

/**
 * classe che gestisce un giocatore controllato dal computer
 * 
 * @author Synesthesy
 *
 */
public class SPPlayer {
	
	/**
	 * la griglia che memorizza la posizione delle proprie navi
	 */
	private final GridCore left;
	/**
	 * la griglia che memorizza la posizione dei colpi eseguiti
	 */
	private final GridCore right;
	
	/**
	 * numero di navi ancora vive
	 */
	private int alive=0;

	public SPPlayer() {
		
		left=new GridCore();
		right=new GridCore();		
	}
	
	/**
	 * metodo per schierare <i>FLEETNUMBER</i> caccia; valido solo per navi di dimensione 1
	 */
	public void deploy() {
		
		while(alive<Player.FLEETNUMBER) {
			Coordinate c=new Coordinate((int)Math.random()*100);
			if(!left.getStatus(c)) {
				left.deploy(c);
				alive++;
			}
		}		
	}
	
	/**
	 * metodo che colpisce una casella
	 * @return la coordinata della casella colpita
	 */
	public Coordinate hit() {
		
		while(true) {
			Coordinate c=new Coordinate((int)Math.random()*100);
			if(!right.getStatus(c)) {
				right.hit(c);
				return c; 
			}			
		}		
	}
	
	/**
	 * metodo che riceve un colpo
	 * @param c la coordinata colpita
	 * @return <b>true</b> se è stata colpita una nave, <b>false</b> altrimenti
	 */
	public boolean reciveHit(Coordinate c) {
		
		if(!left.getStatus(c)) return false;
		else {
			left.setGridValue(false, c);
			alive--;
			return true;
		}
		
	}
	
	/**
	 * metodo per sapere se esistono ancora navi
	 * @return
	 */
	public boolean isAlive() {
		return alive>0;
	}

}
