/**
 * La Classe Cavallo estende da Pedina 
 *  implementa le mosse del cavallo
 *  
 * @author Omar Dabbagh
 *
 */
public class Cavallo extends Pedina {
	

	/**
	 * Istanzia un cavallo.
	 * con colore: colore
	 * alla riga e colonna x e y.
	 * @param colore
	 * @param x
	 * @param y
	 */
	public Cavallo(Colore colore, int x, int y) {
		super(Nome.CAVALLO, colore, x, y);
	}
	
	/**
	 * Restituisce una matrice di interi 8 x 8
	 * corrispondeti alle mosse possibili del cavallo.
	 * dove: 	0 -> non può spostarsi
	 * 			1 -> può spostarsi senza mangiare
	 * 			2 -> può spostarsi mangiando una pedina avversaria
	 * @param scacchiera: scacchiera con le altre pedine in gioco
	 * @return matrice della mosse
	 */
	@Override
	public int[][] mossePossibili(Casella[][] scacchiera) {
		int[][] mosse = new int[8][8];
		int[] righe = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
		int[] colonne = new int[]{-2,-1, 1, 2, 2, 1, -1, -2};
		for(int c = 0; c < 8; c++){
			int i = righe[c];
			int j = colonne[c];
			if(riga +i < 0 || colonna + j < 0
					|| riga + i >= nRighe  || colonna + j >= nColonne)
				continue;

			if (!(scacchiera[riga +i][colonna + j] instanceof Pedina))
				mosse[riga +i][colonna + j] = 1;
			else {
				if (((Pedina)scacchiera[riga +i][colonna + j]).getColore() != this
						.getColore())
					mosse[riga +i][colonna + j] = 2;
			}
		}
			
		return mosse;
		
	}
}