
/**
 * La Classe Pedone estende da Pedina 
 *  implementa le mosse del pedone.
 *  
 * @author Omar Dabbagh
 *
 */
public class Pedone extends Pedina {


	/**
	 * Istanzia un pedone.
	 * con colore: colore
	 * alla riga e colonna x e y.
	 * @param colore
	 * @param x
	 * @param y
	 */
	public Pedone(Colore colore, int x, int y) {
		super(Nome.PEDONE, colore, x, y);
	}
	
	/**
	 * Restituisce una matrice di interi 8 x 8
	 * corrispondeti alle mosse possibili del pedone.
	 * dove: 	0 -> non può spostarsi
	 * 			1 -> può spostarsi senza mangiare
	 * 			2 -> può spostarsi mangiando una pedina avversaria
	 * @param scacchiera: scacchiera con le altre pedine in gioco
	 * @return matrice della mosse
	 */
	
	@Override
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
