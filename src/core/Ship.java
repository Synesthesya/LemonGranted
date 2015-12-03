package core;

/**
 * 
 * oggetto che rappresenta una nave all'interno della griglia
 * 
 * @author Synesthesy
 *
 */
public class Ship {
	
	public static final String[] DIRECTIONS = {"UP","LEFT"};
	private final Coordinate head;
	private final Coordinate tail;
	private final String dir;
	/**
	 * la vita della nave
	 */
	private int hp;
	

	public Ship(Coordinate h, Coordinate t) {
		
		if(h.isHigher(t)) {
			head=h;
			tail=t;
		}
		else {
			head=t;
			tail=h;
		}
		
		if(head.getRow()==tail.getRow()) dir="LEFT";
		else dir="UP";
		
		hp=getLength();
		
	}
	
	public Ship(Coordinate h, int tc, int tr) {
		
		this(h, new Coordinate(tc,tr));
	}
	
	public Ship(int hc, int hr, int tc, int tr) {
		
		this(new Coordinate(hc,hr),new Coordinate(tc,tr));		
	}
	
	
	public int getHp() {
		return hp;
	}
	
	public void hit() {
		hp--;
	}
	
	/**
	 * metodo per sapere se la nave è affondata o meno
	 * 
	 * @return <b>true</b> se la nave è ancora viva, <b>false</b> altrimenti
	 */
	public boolean isAlive() {
		return hp>0;
	}
	
	/**
	 * metodo per ottenere la lunghezza di una nave (intesa come lunghezza totale, non come caselle non ancora colpite)
	 * @return la lunghezza della nave
	 */
	public int getLength() {
		
		if(head.getRow()==tail.getRow()) {
			return head.getColumn()-tail.getColumn();
		}
		else return head.getRow()-tail.getRow();
	}
	
	/**
	 * metodo per ottenere la direzione di una nave
	 * @return <b>UP</b> oppure <b>LEFT</b> per indicare verticale o orizzontale
	 */
	public String getDirection() {
		return dir;
	}
	
	/**
	 * metodo per ottenere la coordinata minore
	 * @return un oggetto coordinata
	 */
	public Coordinate getTail() {
		return tail;
	}
	
	/**
	 * metodo per ottenere la coordinata maggiore
	 * @return un oggetto coordinata
	 */
	public Coordinate getHead() {
		return head;
	}
	
	public boolean hasSameDirection(Ship s) {
		
		return this.dir.equals(s.dir);		
	}
	
	public boolean isInSameLine(Ship s) {
		
		if(hasSameDirection(s)) {
			switch(this.dir) {
			case "UP": return this.head.getColumn()==s.head.getColumn();
			default: return this.head.getRow()==s.head.getRow();
			}
		}
		else return false;		
	}
	
	/**
	 * 
	 * metodo per sapere se una data coordinata fa parte o meno di una nave
	 * 
	 * @param c
	 * @return
	 */
	public boolean isInside(Coordinate c) {
		
		
		if(c.getRow()==head.getRow()&&c.getRow()==tail.getRow()) {
			if(c.getColumn()>=tail.getColumn() && c.getColumn()<=head.getColumn()) return true;
		}
		else if(c.getColumn()==head.getColumn() && c.getColumn()==tail.getColumn()) {
			if(c.getRow()>=tail.getRow() && c.getRow()<=head.getRow()) return true;
		}
		return false;	
	}
	
	/**
	 * metodo per sapere se una nave si trova (parzialmente) all'interno di un'altra
	 * 
	 * @param s la nave da confrontare
	 * @return <b>true</b> se la nave occupa lo spazio dell'oggetto chiamato, <b>false</b> altrimenti
	 */
	public boolean isInside(Ship s) {
		
		if(this.getLength()<s.getLength()) return s.isInside(this);
		
		//da questo momento, this è la nave più lunga tra le due
		
		if(this.isInSameLine(s)) {
			return this.isInside(s.head) || this.isInside(s.tail); 
		}
		else if(!this.hasSameDirection(s)){
			switch(this.dir) {
			case "UP": return s.head.getRow()<=this.head.getRow() && s.head.getRow()>=this.tail.getRow() && s.head.getColumn()>=this.head.getColumn() && s.tail.getColumn()<=this.head.getColumn();
			default: return s.head.getRow()>=this.head.getRow() && s.tail.getRow()<=this.head.getRow() && s.head.getColumn()>=this.tail.getColumn() && s.head.getColumn()>=this.head.getColumn();
			}
		}
		else return false;
		
	}
	
	/**
	 * metodo per sapere se due navi sono compatibili tra di loro, ovvero se le loro coordinate sono o meno confinanti
	 * 
	 * @param s la nave da confrontare
	 * @return <b>true</b> se la nave s non tocca in alcun modo la nave chiamata, <b>false</b> altrimenti
	 */
	public boolean isCompatible(Ship s) {
		
		/*
		 * area è una zona composta dal bordo della nave this
		 */
		Ship[] area = new Ship[3];
		
		if(this.dir=="UP") {			
			area[0]=new Ship(this.tail.getColumn()+1,this.tail.getRow(),this.head.getColumn()-1,this.head.getRow());
			area[1]=new Ship(this.tail.getColumn()+1, this.tail.getRow()+1, this.head.getColumn()-1, this.head.getRow()+1);
			area[2]=new Ship(this.tail.getColumn()+1, this.tail.getRow()-1, this.head.getColumn()-1, this.head.getRow()-1);			
		}
		else {
			area[0]=new Ship(this.tail.getRow()-1, this.tail.getColumn(), this.head.getRow()-1, this.head.getColumn());
			area[1]=new Ship(this.tail.getRow()-1, this.tail.getColumn()+1, this.head.getRow()-1, this.head.getColumn()+1);
			area[2]=new Ship(this.tail.getRow()-1, this.tail.getColumn()-1, this.head.getRow()-1, this.head.getColumn()-1);
		}
		
		for(int i=0; i<3; i++) {
			if(area[i].isInside(s)) return false;
		}
		return true;		
	}
	
}
