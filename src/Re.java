import java.util.ArrayList;

public class Re extends Pedina {
	/**
	 * 
	 * Costruttore della classe Re
	 * 
	 * @param colore
	 */
	public Re(Colore colore, int x, int y) {
		super(Nome.RE, colore, x, y);

	}

	/**
	 * 
	 * @param posPedina
	 * @param scacchiera
	 * @return le mosse possibili che il Re per la prossima mossa ricordando che
	 *         si puo' muovere solo di una casella. Inoltre controlla, in base
	 *         alla posizione in cui si trova, che le mosse rimagano dentro la
	 *         scacchiera
	 */

	public int[][] mossePossibili(Casella[][] scacchiera) {
		int[][] mosse = new int[8][8];
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				if((i == 0 && j == 0) || (riga +i < 0 || colonna + j < 0
						|| riga + i >= nRighe  || colonna + j >= nColonne))
					continue;

				if (!(scacchiera[riga +i][colonna + j] instanceof Pedina))
					mosse[riga +i][colonna + j] = 1;
				else {
					if (((Pedina)scacchiera[riga +i][colonna + j]).getColore() != this
							.getColore())
						mosse[riga +i][colonna + j] = 2;
				}
			}
		}
		return mosse;
	}
}