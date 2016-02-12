import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Pedina extends Casella{

	private final Nome nome;
	private final Colore colore;

	protected static final int nRighe = 8;
	protected static final int nColonne = 8;


	/**
	 * 
	 * 
	 * 
	 * @param 
	 * @param colore
	 */
	public Pedina(Nome nome, Colore colore, int riga, int colonna) {
		super(riga, colonna);
		this.nome = nome;
		this.colore = colore;
		
		String nomeFile = "";
		switch(nome){
		case PEDONE:nomeFile += "pedone"; break;
		case CAVALLO:nomeFile += "cavallo"; break;
		case TORRE:nomeFile += "torre"; break;
		case ALFIERE:nomeFile += "alfiere"; break;
		case RE:nomeFile += "re"; break;
		case REGINA:nomeFile += "regina"; break;
		default:;
		}
		switch(colore){
		case BIANCO:nomeFile += "_b";break;
		case NERO:nomeFile += "_n";break;
		default:;
		}
		try {
		    Image img = ImageIO.read(new File("./grafica/"+nomeFile+".png"));
		    this.setIcon(new ImageIcon(img));
		  } catch (IOException ex) {
		  }
		
		
	}

	/**
	 * 
	 * @return nome
	 */
	public Nome getNome() {
		return this.nome;
	}

	/**
	 * 
	 * @return colore
	 */
	public Colore getColore() {
		return this.colore;
	}

	/**
	 * 
	 * @param posPedina
	 * @param griglia
	 * @return 
	 */
//	public int[][] mossePossibili(Posizione posPedina,
//			Pedina[][] scacchiera);
	public abstract int[][] mossePossibili(Casella[][] griglia);
}
