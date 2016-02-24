import java.awt.Color;

import javax.swing.JButton;

/**
 * La classe Casella implementa le funzionalità grafiche del gioco,
 * ciascuna casella può essere una Pedina o una casella in cui una 
 * pedina può spostarsi.
 * 
 * 
 * @author Omar Dabbagh
 *
 */
public class Casella extends JButton{// dvrebbe essere abstract
	
	
	/**
	 * Dimensione in pixel della cella  
	 */
	public static final int DIMENSIONE = 50;
	
	/**
	 * Posizione della casella nella scacchiera: riga
	 */
	public int riga;
	
	/**
	 * Posizione della casella nella scacchiera: colonna 
	 */
	public int colonna;
	
	/**
	 * Istanzia una casella alle corrispondente coordinate
	 * 
	 * Coordinate:
	 * @param riga
	 * @param colonna
	 */
	public Casella(int riga, int colonna){
		super();
		setBounds(colonna * DIMENSIONE, riga * DIMENSIONE, DIMENSIONE, DIMENSIONE);
		this.riga = riga;
		this.colonna = colonna;
		this.setBackground(Color.BLUE);
		setOpaque(false);
	}
	/**
	 * Aggiorna la posizione di una casella in seguito a uno spostamento
	 * 
	 * Coordinate nella scacchiera:
	 * @param riga
	 * @param colonna
	 */
	public void cambiaPosizione(int riga, int colonna){
		setBounds(colonna * DIMENSIONE, riga * DIMENSIONE, DIMENSIONE, DIMENSIONE);
		this.riga = riga;
		this.colonna = colonna;
	}
}
