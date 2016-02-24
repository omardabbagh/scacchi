

/**
 * La Classe Torre estende da Pedina 
 *  implementa le mosse della torre.
 *  
 * @author Omar Dabbagh
 *
 */
public class Torre extends Pedina {
	
	/**
	 * Istanzia una Torre.
	 * con colore: colore
	 * alla riga e colonna x e y.
	 * @param colore
	 * @param x
	 * @param y
	 */
	public Torre(Colore colore,  int x, int y) {
		super(Nome.TORRE, colore, x, y);
	}
	
	/**
	 * Restituisce una matrice di interi 8 x 8
	 * corrispondeti alle mosse possibili della torre.
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
