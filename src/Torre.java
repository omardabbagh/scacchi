public class Torre extends Pedina {
	/**
	 * 
	 * Costruttore della classe Torre
	 * 
	 * @param colore
	 */
	public Torre(Colore colore,  int x, int y) {
		super(Nome.TORRE, colore, x, y);
	}

	/**
	 * 
	 * @param posPedina
	 * @param scacchiera
	 * @return le mosse possibili della Torre per la prossima mossa ricordando
	 *         che puo' muoversi solo in orizzontale e verticale. Inoltre
	 *         controlla, in base alla posizione in cui si trova, che le mosse
	 *         rimagano dentro la scacchiera
	 */
	public int[][] mossePossibili(Casella[][] scacchiera) {
		int[][] mosse = new int[8][8];
		int k;

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

		return mosse;
	}
}
