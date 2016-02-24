/**
 * La Classe Alfiere estende da Pedina 
 *  implementa le mosse dell'alfiere
 *  
 * @author Omar Dabbagh
 *
 */
public class Alfiere extends Pedina {
	
	/**
	 * Istanzia un alfiere.
	 * con colore: colore
	 * alla riga e colonna x e y.
	 * @param colore
	 * @param x
	 * @param y
	 */
	public Alfiere(Colore colore, int x, int y) {
		super(Nome.ALFIERE, colore,x,y);
	}
	
	/**
	 * Restituisce una matrice di interi 8 x 8
	 * corrispondeti alle mosse possibili dell'alfiere.
	 * dove: 	0 -> non può spostarsi
	 * 			1 -> può spostarsi senza mangiare
	 * 			2 -> può spostarsi mangiando una pedina avversaria
	 * @param scacchiera: scacchiera con le altre pedine in gioco
	 * @return matrice della mosse
	 */
	@Override
	public int[][] mossePossibili(Casella[][] scacchiera) {
		int[][] mosse = new int[8][8];
		int r;
		int c;

		// mossa obliqua: destra/alto.
		r = riga - 1;
		c = colonna + 1;
		while (r >= 0 && c < nColonne) {
			if (!(scacchiera[r][c] instanceof Pedina)) {
				mosse[r][c] = 1;
				r--;
				c++;
			} else {
				if (((Pedina)scacchiera[r][c]).getColore() != this.getColore())
					mosse[r][c] = 2;
				break;
			}
		}

		// mossa obliqua sinistra/alto
		r = riga - 1;
		c = colonna - 1;
		while (r >= 0 && c >= 0) {
			if (!(scacchiera[r][c] instanceof Pedina)) {
				mosse[r][c] = 1;
				r--;
				c--;
			} else {
				if (((Pedina)scacchiera[r][c]).getColore() != this.getColore())
					mosse[r][c] = 2;
				break;
			}
		}

		// mossa obliqua destra/basso
		r = riga + 1;
		c = colonna + 1;
		while (r < nRighe && c < nColonne) {
			if (!(scacchiera[r][c] instanceof Pedina)) {
				mosse[r][c] = 1;
				r++;
				c++;
			} else {
				if (((Pedina)scacchiera[r][c]).getColore() != this.getColore())
					mosse[r][c] = 2;
				break;
			}
		}

		// mossa obliqua sinistra/basso
		r = riga + 1;
		c = colonna - 1;
		while (r < nRighe && c >= 0) {
			if (!(scacchiera[r][c] instanceof Pedina)) {
				mosse[r][c] = 1;
				r++;
				c--;
			} else {
				if (((Pedina)scacchiera[r][c]).getColore() != this.getColore())
					mosse[r][c] = 2;
				break;
			}
		}

		return mosse;
	}
}
