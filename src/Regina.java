public class Regina extends Pedina {
	/**
	 * 
	 * Costruttore della classe Regina
	 * 
	 * @param colore
	 */
	public Regina(Colore colore, int x, int y) {
		super(Nome.REGINA, colore, x, y);
	}

	/**
	 * 
	 * @param posPedina
	 * @param scacchiera
	 * @return le mosse possibili della Regina per la prossima mossa ricordando
	 *         che la regina muove in qualsiasi direzione. Inoltre controlla, in
	 *         base alla posizione in cui si trova, che le mosse rimagano dentro
	 *         la scacchiera
	 */
	public int[][] mossePossibili(Casella[][] scacchiera) {
		int[][] mosse = new int[8][8];
		int k;
		int l;

		// la regina può muoversi per tutta la riga i
		// colonne sopra/sotto la posizione attuale
		for (k = colonna + 1; k < nColonne; k++) {
			if (!(scacchiera[riga][k] instanceof Pedina))
				mosse[riga][k] = 1;
			else {
				if (((Pedina)scacchiera[riga][k]).getColore() != this
						.getColore())
					mosse[riga][k] = 2;
				break;
			}
		}
		for (k = colonna - 1; k >= 0; k--) {
			if (!(scacchiera[riga][k] instanceof Pedina))
				mosse[riga][k] = 1;
			else {
				if (((Pedina)scacchiera[riga][k]).getColore() != this
						.getColore())
					mosse[riga][k] = 2;
				break;
			}
		}

		// la regina può muoversi per tutta la colonna j
		// righe dx/sx della posizione attuale
		for (k = riga + 1; k < nRighe; k++) {
			if (!(scacchiera[k][colonna] instanceof Pedina))
				mosse[k][colonna] = 1;
			else {
				if (((Pedina)scacchiera[k][colonna]).getColore() != this
						.getColore())
					mosse[k][colonna] = 2;
				break;
			}
		}
		for (k = riga - 1; k >= 0; k--) {
			if (!(scacchiera[k][colonna] instanceof Pedina))
				mosse[k][colonna] = 1;
			else {
				if (((Pedina)scacchiera[k][colonna]).getColore() != this
						.getColore())
					mosse[k][colonna] = 2;
				break;
			}
		}
		// obliquo dx su
		k = riga - 1;
		l = colonna + 1;
		while (k >= 0 && l < nColonne) {
			if (!(scacchiera[k][l] instanceof Pedina)) {
				mosse[k][l] = 1;
				k--;
				l++;
			} else {
				if (((Pedina)scacchiera[k][l]).getColore() != this.getColore())
					mosse[k][l] = 2;
				break;
			}
		}
		// obliquo sx su
		k = riga - 1;
		l = colonna - 1;
		while (k >= 0 && l >= 0) {
			if (!(scacchiera[k][l] instanceof Pedina)) {
				mosse[k][l] = 1;
				k--;
				l--;
			} else {
				if (((Pedina)scacchiera[k][l]).getColore() != this.getColore())
					mosse[k][l] = 2;
				break;
			}
		}
		// obliquo dx giu
		k = riga + 1;
		l = colonna + 1;
		while (k < nRighe && l < nColonne) {
			if (!(scacchiera[k][l] instanceof Pedina)) {
				mosse[k][l] = 1;
				k++;
				l++;
			} else {
				if (((Pedina)scacchiera[k][l]).getColore() != this.getColore())
					mosse[k][l] = 2;
				break;
			}
		}
		// obliquo sx giu
		k = riga + 1;
		l = colonna - 1;
		while (k < nRighe && l >= 0) {
			if (!(scacchiera[k][l] instanceof Pedina)) {
				mosse[k][l] = 1;
				k++;
				l--;
			} else {
				if (((Pedina)scacchiera[k][l]).getColore() != this.getColore())
					mosse[k][l] = 2;
				break;
			}
		}
		return mosse;

	}
}
