import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * La classe Pedina implementa le funzionalità grafiche delle pedine,
 * ciascuna pedina è una specializzazione della classe pedina.
 * Ognuna si diversificherà per: nome e colore
 * 
 * @author Omar Dabbagh
 *
 */
public abstract class Pedina extends Casella{
	
	/**
	 * Nome della pedina:
	 * Pedone, alfiere, cavallo torre, regina o re.
	 */
	private final Nome nome;
	
	/**
	 * Colore pedina: Bianco o Neron
	 */
	private final Colore colore;
	
	/**
	 * Numero righe della scacchiera
	 */
	protected static final int nRighe = 8;
	
	/**
	 * Numero colonne della scacchiera
	 */
	protected static final int nColonne = 8;
	
	/**
	 * Istanzia una pedina con rispettive caratteristiche,
	 * caricando anche la propria texture.
	 * 
	 * @param nome
	 * @param colore
	 * @param riga
	 * @param colonna
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
	 * @return colore della pedina: bianco o nero
	 */

	public Colore getColore() {
		return this.colore;
	}
	
	/**
	 * Restituisce una matrice di interi 8 x 8
	 * corrispondeti alle mosse possibili della pedina.
	 * dove: 	0 -> non può spostarsi
	 * 			1 -> può spostarsi senza mangiare
	 * 			2 -> può spostarsi mangiando una pedina avversaria
	 * @param scacchiera: scacchiera con le altre pedine in gioco
	 * @return matrice della mosse
	 */

	public abstract int[][] mossePossibili(Casella[][] scacchiera);
}
