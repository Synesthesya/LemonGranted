package core;

/**
 * 
 * la classe Grid rappresenta la griglia di gioco, 10x10, in cui avviene lo scontro.
 * L'IA e il giocatore umano la utilizzano in maniera diversa, ma il suo scopo resta il rappresentare la posizione delle navi e dei tiri colpiti.
 * 
 * @author Synesthesy
 *
 */
public class GridCore
{
	
	/**
	 * dimensione della griglia
	 */
	public static final int DIMENSION=100;
	/**
	 * valore della coordinata massima: si assume che la griglia sia quadrata
	 */
	public static final int MAX_COORDINATE=10;
	/**
	 * la griglia vera e propria. Ogni casella contiene un valore booleano
	 */
	private boolean[] grid;

	/**
	 * costruttore standard che inizializza una griglia vuota di dimensione <i>DIMENSION</i>
	 * il valore vuoto � <b>false</b> per ogni casella
	 * <p>inoltre per la griglia di sinistra un valore true indica la presenza di una nave, 
	 * mentre su quella di destra indica che � stata cliccata per sparare</p>
	 */
	public GridCore()
	{
		
		grid=new boolean[DIMENSION];
		for(int i=0; i<DIMENSION; i++) {
			grid[i]=false;
		}
	}
	
	/**
	 * metodo per settare una casella come usata
	 * @param d la coordinata della casella
	 */
	public void deploy(int d) {
		grid[d]=true;
	}
	
	/**
	 * metodo per settare una casella come usata
	 * @param c la coordinata della casella
	 */
	public void deploy(Coordinate c) 
	{
	  grid[c.toInteger()]=true;
		
	}

	
	/**
	 * metodo che ritorna il valore di una casella
	 * @param d la coordinata (in integer)
	 * @return il valore booleano della casella
	 */
	public boolean getStatus(int d) {
		return grid[d];
	}
	
	/**
	 * metodo per conoscere lo status di una casella
	 * @param c la coordinata della casella richiesta
	 * @return lo stato della casella
	 */
	public boolean getStatus(Coordinate c) {
		return grid[c.toInteger()];
	}
	
	@Override
	public String toString() {
		
		String s="";
		
		for(int i=0; i<DIMENSION; i++) {
			
			if(grid[i]==true) s=s+"X";
			else s=s+" ";
			if(i%10==0) s=s+"\n";			
		}
		return s;		
	}
	
	/**
	 * setta il valore nella casella specificata
	 * @param b valore da settare
	 * @param c coordinate della cella
	 */
	public void setGridValue(boolean b, Coordinate c)
	{
		grid[c.toInteger()]=b;
	}
}
