

/**
 * La Classe Regina estende da Pedina 
 *  implementa le mosse della regina.
 *  
 * @author Omar Dabbagh
 *
 */
public class Regina extends Pedina {
	/**
	 * Istanzia una Regina.
	 * con colore: colore
	 * alla riga e colonna x e y.
	 * @param colore
	 * @param x
	 * @param y
	 */
	public Regina(Colore colore, int x, int y) {
		super(Nome.REGINA, colore, x, y);
	}
	/**
	 * Restituisce una matrice di interi 8 x 8
	 * corrispondeti alle mosse possibili della regina.
	 * dove: 	0 -> non può spostarsi
	 * 			1 -> può spostarsi senza mangiare
	 * 			2 -> può spostarsi mangiando una pedina avversaria
	 * @param scacchiera: scacchiera con le altre pedine in gioco
	 * @return matrice della mosse
	 */
	@Override
	public int[][] mossePossibili(Casella[][] scacchiera) {
		int[][] mosse = new int[8][8];
		int k;
		int l;

		//mosse sulla riga
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

		//mosse sulla colonna
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
		//mossa obliqua destra/alto
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
		//mossa obliqua sinistra/alto
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
		//mosssa obliqua destra/basso
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
		//mossa obliqua sinistra/basso
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
