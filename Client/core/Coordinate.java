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
	 * 
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
	public static int getColumn(char c) {
		
		for(int i=0; i<LETTERS.length; i++) {
			if(LETTERS[i]==c) return LETTERS[i];
		}
		return -1;		
	}
	
	/**
	 * metodo per ottenere la colonna
	 * @return
	 */
	public int getColumn() {
		return column;
	}	
	
	/**
	 * metodo per convertire l'oggetto in un intero
	 * @return un numero in cui le decine rappresentano le righe e le unità le colonne
	 */
	public int toInteger() {
		return row*SIZE + column;
	}
}
