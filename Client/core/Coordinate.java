package core;

import java.io.Serializable;


/**
 * 
 * <p>
 * questa classe è richiesta per memorizzare coppie di numeri e tradurli come coordinate (numeri, lettere)
 * </p>
 * 
 * @implements Serializable consente l'invio tramite connessione
 * 
 * @author Synesthesy
 *
 */
public class Coordinate implements Serializable
{
	
	/**
	 * WARNING
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * array di lettere (utilizzato solo in fase di debug)
	 */
	public static final char[] LETTERS = {'A','B','C','D','E','F','G','H','I','L'};
	
	/**
	 * la grandezza della griglia;
	 * essendo quadrata, è la dimensione del lato in caselle
	 */
	public static final int SIZE = 10;
	
	/**
	 * riga
	 */
	private final int row;
	/**
	 * colonna
	 */
	private final int column;

	/**
	 * costruttore standard 
	 * 
	 * @param row numero della riga
	 * @param column numero della colonna
	 */
	public Coordinate(int column, int row) {		
		this.row=row;
		this.column=column;		
	}
	
	/**
	 * costruttore nato per convertire gli interi in coordinate
	 * @param decimal un numero compreso tra <b>0</b> e <b>99</b>
	 */
	public Coordinate(int decimal) {
		row=decimal/SIZE;
		column=decimal%SIZE;
	}
	
	@Override
	public String toString() {
		return ""+LETTERS[column]+row;
	}
	
	public int getRow() {
		return row;
	}
	
	/**
	 * ottiene la colonna a partire dalla lettera
	 * @param c la lettera della colonna
	 * @return il numero della colonna
	 */
	public int getColumn(char c) {
		
		for(int i=0; i<LETTERS.length; i++) {
			if(LETTERS[i]==c) return LETTERS[i];
		}
		return -1;		
	}
	
	public int getColumn() {
		return column;
	}
	
	/**
	 * metodo per sapere se la coordinata c è più alta dell'attuale.
	 * A parità di altezza, vince la coordinata più a destra.
	 * 
	 * Viene usato solo nella modalità con navi maggiori di 1
	 * 
	 * @param c
	 */
	public boolean isHigher(Coordinate c) {
		
		if(this.row>c.row) return true;
		else if(this.row==c.row) return this.column>c.column;
		else return false;		
	}
	
	/**
	 * metodo per convertire l'oggetto in un intero
	 * @return un numero in cui le decine rappresentano le righe e le unità le colonne
	 */
	public int toInteger() {
		return row*SIZE + column;
	}
}
