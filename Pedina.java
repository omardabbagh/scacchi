public abstract class Pedina {

	private final Nome nome;
	private final Colore colore;

	protected static int nRighe = 8;
	protected static int nColonne = 8;

	/**
	 * 
	 * 
	 * 
	 * @param 
	 * @param colore
	 */
	public Pedina(Nome nome, Colore colore) {
		this.nome = nome;
		this.colore = colore;
	}

	/**
	 * 
	 * @return nome
	 */
	public Nome getNome() {
		return this.nome;
	}

	/**
	 * 
	 * @return colore
	 */
	public Colore getColore() {
		return this.colore;
	}

	/**
	 * 
	 * @param posPedina
	 * @param scacchiera
	 * @return 
	 */
	public abstract int[][] mossePossibili(Posizione posPedina,
			Pedina[][] scacchiera);
}
