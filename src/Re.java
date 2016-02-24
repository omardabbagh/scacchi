/**
 * La Classe Re estende da Pedina 
 *  implementa le mosse del re.
 *  
 * @author Omar Dabbagh
 *
 */
public class Re extends Pedina {
	
	/**
	 * Istanzia un re.
	 * con colore: colore
	 * alla riga e colonna x e y.
	 * @param colore
	 * @param x
	 * @param y
	 */
	public Re(Colore colore, int x, int y) {
		super(Nome.RE, colore, x, y);

	}
	/**
	 * Restituisce una matrice di interi 8 x 8
	 * corrispondeti alle mosse possibili del Re.
	 * dove: 	0 -> non può spostarsi
	 * 			1 -> può spostarsi senza mangiare
	 * 			2 -> può spostarsi mangiando una pedina avversaria
	 * @param scacchiera: scacchiera con le altre pedine in gioco
	 * @return matrice della mosse
	 */
	@Override
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