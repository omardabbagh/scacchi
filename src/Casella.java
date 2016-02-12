import java.awt.Color;

import javax.swing.JButton;

public class Casella extends JButton{// dvrebbe essere abstract
	
	public static final int DIMENSIONE = 50;
	public int riga;
	public int colonna;
	
	public Casella(int riga, int colonna){
		super();
		setBounds(colonna * DIMENSIONE, riga * DIMENSIONE, DIMENSIONE, DIMENSIONE);
		this.riga = riga;
		this.colonna = colonna;
		this.setBackground(Color.BLUE);
		setOpaque(false);
	}
	
	public void cambiaPosizione(int riga, int colonna){
		setBounds(colonna * DIMENSIONE, riga * DIMENSIONE, DIMENSIONE, DIMENSIONE);
		this.riga = riga;
		this.colonna = colonna;
	}
	
	public int getRiga(){
		return riga;
	}
	
	public int getColonna(){
		return colonna;
	}
}
