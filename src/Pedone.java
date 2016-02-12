public class Pedone extends Pedina {
	/**
	 * 
	 * Costruttore della classe Pedone
	 * 
	 * @param colore
	 */
	public Pedone(Colore colore, int x, int y) {
		super(Nome.PEDONE, colore, x, y);
	}

	/**
	 * 
	 * @param posPedina
	 * @param scacchiera
	 * @return le mosse possibili del Pedone per la prossima mossa controllando
	 *         che si possa muovere solo in avanti e possa mangiare solo in
	 *         diagonale. Inoltre controlla, in base alla posizione in cui si
	 *         trova, che le mosse rimagano dentro la scacchiera
	 */
	public int[][] mossePossibili(Casella[][] scacchiera) {
		int[][] mosse = new int[8][8];

		if (this.getColore() == Colore.NERO) {

		
			if (((riga + 1) < nRighe)
					&& (scacchiera[riga + 1][colonna] instanceof Casella))
				mosse[riga + 1][colonna] = 1;


			if (((riga + 1) < nRighe)
					&& ((colonna - 1) >= 0)
					&& (scacchiera[riga + 1][colonna - 1] instanceof Pedina))
				if (((Pedina)scacchiera[riga + 1][colonna - 1])
						.getColore() != this.getColore())
					mosse[riga + 1][colonna - 1] = 2;

			if (((riga + 1) < nRighe)
					&& ((colonna + 1) < nColonne)
					&& (scacchiera[riga + 1][colonna + 1] instanceof Pedina))
				if (((Pedina)scacchiera[riga + 1][colonna + 1])
						.getColore() != this.getColore())
					mosse[riga + 1][colonna + 1] = 2;

		} else { 

			if (((riga - 1) >= 0)
					&& (scacchiera[riga - 1][colonna] instanceof Casella))
				mosse[riga - 1][colonna] = 1;

			if (((riga - 1) >= 0)
					&& ((colonna - 1) >= 0)
					&& (scacchiera[riga - 1][colonna - 1] instanceof Pedina))
				if (((Pedina)scacchiera[riga - 1][colonna - 1])
						.getColore() != this.getColore())
					mosse[riga - 1][colonna - 1] = 2;

			if (((riga - 1) >= 0)
					&& ((colonna + 1) < nColonne)
					&& (scacchiera[riga - 1][colonna + 1] instanceof Pedina))
				if (((Pedina)scacchiera[riga - 1][colonna + 1])
						.getColore() != this.getColore())
					mosse[riga - 1][colonna + 1] = 2;

		}
		return mosse;
	}

}
