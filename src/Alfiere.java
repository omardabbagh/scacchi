public class Alfiere extends Pedina {
	/**
	 * 
	 * Costruttore della classe Alfiere
	 * 
	 * @param colore
	 */
	public Alfiere(Colore colore, int x, int y) {
		super(Nome.ALFIERE, colore,x,y);
	}

	/**
	 * 
	 * @param posPedina
	 * @param scacchiera
	 * @return le mosse possibili dell' alfiere per la prossima mossa ricordando
	 *         che si muove solo in diagonale controlla, in base alla posizione
	 *         in cui si trova, che le mosse rimagano dentro la scacchiera
	 */
	public int[][] mossePossibili(Casella[][] scacchiera) {
		int[][] mosse = new int[8][8];
		int r;
		int c;

		// obliquo dx su
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

		// obliquo sx su
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

		// obliquo dx giu
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

		// obliquo sx giu
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
