
public class Cavallo extends Pedina {

	/**
	 * 
	 * Costruttore della classe Cavallo
	 * 
	 * @param colore
	 */
	public Cavallo(Colore colore, int x, int y) {
		super(Nome.CAVALLO, colore, x, y);
	}

	/**
	 * 
	 * @param posPedina
	 * @param scacchiera
	 * @return le mosse possibili del Cavallo per la prossima mossa ricordando
	 *         che si muove solo a L. Inoltre controlla in base alla posizione
	 *         in cui si trova guarda che le mosse rimagano dentro la scacchiera
	 */
	
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