import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Scacchiera extends JFrame{

	private static final int NUM = 8;
	Casella[][] griglia;
	private ActionListener action;
	private Colore turno;
	private Pedina reBianco;
	private Pedina reNero;
	private  boolean pedinaDaEvolvere = false;
	public static final  int SCACCO_BIANCO = 1;
	public static final int SCACCO_NERO = -1;

	public Scacchiera(boolean test){
		if(test){
			griglia = new Casella[8][8];
			turno=Colore.NERO;

			for(int i = 0; i < NUM; i++){
				for(int j = 0; j < NUM; j++){
					griglia[i][j] = new Casella(i,j);
				}
			}

			griglia[0][3] = new Re(Colore.NERO, 0,3);
			griglia[2][1] = new Pedone(Colore.NERO, 2, 1);
			griglia[3][0] = new Pedone(Colore.BIANCO, 3, 0);
			griglia[4][3] = new Regina(Colore.NERO, 4, 3);
			griglia[4][7] = new Torre(Colore.NERO, 4, 7);
			griglia[5][0] = new Cavallo(Colore.BIANCO, 5, 0);
			griglia[5][6] = new Alfiere(Colore.BIANCO ,5, 6);
			griglia[6][0] = new Pedone(Colore.NERO ,6 ,0);
			griglia[7][3] = new Re(Colore.BIANCO, 7, 3);
			reNero = (Pedina)griglia[0][3];
			reBianco = (Pedina)griglia[7][3];
			turno=Colore.BIANCO;

		}else{

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			setSize(NUM*Casella.DIMENSIONE+5, NUM*Casella.DIMENSIONE + Casella.DIMENSIONE/2+4);
			setResizable(false);
			setLocation(10,10);
			setVisible(true);


			try {
				ImageIcon immagine = new ImageIcon(ImageIO.read(new File("./grafica/scacchiera.png")));
				JLabel sfondo = new JLabel(immagine);
				sfondo.setVisible(true);
				setContentPane(sfondo);

			} catch (IOException e) {
				e.printStackTrace();
			}

			inizializzaGriglia();
			setVisible(true);
		}

	}


	private void inizializzaGriglia() {
		griglia = new Casella[NUM][NUM];
		griglia[0][0] = new Torre(Colore.NERO, 0,0);
		griglia[0][1] = new Cavallo(Colore.NERO, 0,1);
		griglia[0][2] = new Alfiere(Colore.NERO, 0,2);
		griglia[0][3] = new Regina(Colore.NERO, 0,3);
		griglia[0][4] = new Re(Colore.NERO, 0,4);
		griglia[0][5] = new Alfiere(Colore.NERO, 0,5);
		griglia[0][6] = new Cavallo(Colore.NERO, 0,6);
		griglia[0][7] = new Torre(Colore.NERO, 0,7);
		for(int i = 0; i < NUM; i++){
			griglia[1][i] = new Pedone(Colore.NERO, 1,i);
		}
		for(int i = 2; i < NUM-2; i++){
			for(int j = 0; j < NUM; j++){
				griglia[i][j] = new Casella(i,j);
			}
		}

		griglia[7][0] = new Torre(Colore.BIANCO, 7,0);
		griglia[7][1] = new Cavallo(Colore.BIANCO, 7,1);
		griglia[7][2] = new Alfiere(Colore.BIANCO, 7,2);
		griglia[7][3] = new Regina(Colore.BIANCO, 7,3);
		griglia[7][4] = new Re(Colore.BIANCO, 7,4);
		griglia[7][5] = new Alfiere(Colore.BIANCO, 7,5);
		griglia[7][6] = new Cavallo(Colore.BIANCO, 7,6);
		griglia[7][7] = new Torre(Colore.BIANCO, 7,7);

		for(int i = 0; i < NUM; i++){
			griglia[6][i] = new Pedone(Colore.BIANCO, 6,i);
		}
		reBianco = (Pedina)griglia[7][4];
		reNero = (Pedina)griglia[0][4];
		
		action = new ActionListener() {
			private Pedina sorgente;
			private Casella destinazione;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sorgente == null){
					if(e.getSource() instanceof Pedina){
						sorgente = (Pedina)e.getSource();
					}
				}else{
					destinazione = (Casella)e.getSource();
					int mossa = sorgente
							.mossePossibili(griglia)[destinazione.riga][destinazione.colonna];
					int tempRiga = sorgente.riga;
					int tempColonna = sorgente.colonna;
					if(mossa(sorgente, destinazione)){
						getContentPane().remove(sorgente);
						getContentPane().remove(destinazione);
						destinazione.removeActionListener(action);
						//						griglia[destinazione.riga][destinazione.colonna] = sorgente;
						//						griglia[sorgente.riga][sorgente.colonna] = new Casella(sorgente.riga, sorgente.colonna);

						griglia[tempRiga][tempColonna].addActionListener(action);

						getContentPane().add(griglia[tempRiga][tempColonna]);

						//						sorgente.cambiaPosizione(destinazione.riga, destinazione.colonna);

						getContentPane().add(sorgente);
						Scacchiera.this.repaint();

						if(pedinaDaEvolvere)
							evoluzionePedina(sorgente);

						//
						//						if(turno.equals(Colore.BIANCO))turno=Colore.NERO;
						//						else{turno=Colore.BIANCO;}

						if(scacco() !=0 && scaccoMatto()){
							int scelta = JOptionPane.showConfirmDialog(null, turno+" hai perso!\nVuoi giocare una nuova partita?","Scacco Matto",JOptionPane.YES_NO_OPTION);
							if(scelta == 0)
								ricominciaPartita();
							else 
								System.exit(0);
						}

					}
					sorgente = null;
				}
			}
		};
		for(int i = 0; i < NUM; i++){
			for(int j = 0; j < NUM; j++){
				griglia[i][j].addActionListener(action);
				getContentPane().add(griglia[i][j]);
			}
		}

		turno=Colore.BIANCO;
	}

	private void evoluzionePedina(Pedina pedina) {

		if(pedina.riga==0){
			scegliPedina(Colore.BIANCO, pedina);
		}
		else if( pedina.riga==7){
			scegliPedina(Colore.NERO, pedina);
		}

		pedinaDaEvolvere = false;
	}

	private void scegliPedina(Colore colore, Pedina p){
		new ElencoPedine(colore, this, p);
	}

	public void sostituisciPedina(Pedina pedinaDaCambiare, Pedina nuovaPedina) {
		griglia[pedinaDaCambiare.riga][pedinaDaCambiare.colonna] = nuovaPedina;
		pedinaDaCambiare.removeActionListener(action);
		nuovaPedina.addActionListener(action);
		this.getContentPane().remove(pedinaDaCambiare);
		this.getContentPane().add(nuovaPedina);
		Scacchiera.this.repaint();
	}

	boolean mossa(Pedina sorgente, Casella destinazione) {
		if(!(sorgente instanceof Pedina) || sorgente.getColore() != turno)
			return false;
		if(!puoMuovere(sorgente, destinazione))
			return false;
		int mossa = sorgente
				.mossePossibili(griglia)[destinazione.riga][destinazione.colonna];
		if(mossa == 0 || !turno.equals(sorgente.getColore()))
			return false;
		griglia[destinazione.riga][destinazione.colonna] = sorgente;
		griglia[sorgente.riga][sorgente.colonna] = new Casella(sorgente.riga, sorgente.colonna);
		sorgente.cambiaPosizione(destinazione.riga, destinazione.colonna);

		if(turno==Colore.BIANCO && sorgente instanceof Pedone && sorgente.riga==0){
			pedinaDaEvolvere = true;
		}
		else if(turno==Colore.NERO && sorgente instanceof Pedone && sorgente.riga==7){
			pedinaDaEvolvere = true;
		}

		if(turno.equals(Colore.BIANCO))turno=Colore.NERO;
		else{turno=Colore.BIANCO;}


		return true;
	}

	private boolean puoMuovere(Pedina sorgente, Casella destinazione){

		int tempRiga;
		int tempColonna;
		boolean permesso = true;

		griglia[destinazione.riga][destinazione.colonna]=griglia[sorgente.riga][sorgente.colonna];
		tempRiga = sorgente.riga;
		tempColonna = sorgente.colonna;
		sorgente.riga = destinazione.riga;
		sorgente.colonna = destinazione.colonna;
		griglia[tempRiga][tempColonna]= new Casella(tempRiga, tempColonna);

		if(turno==Colore.BIANCO){
			if(scacco()== SCACCO_BIANCO)permesso = false;
		}else{
			if(scacco()== SCACCO_NERO)permesso = false;
		}
		griglia[tempRiga][tempColonna]=griglia[destinazione.riga][destinazione.colonna];
		sorgente.riga = tempRiga;
		sorgente.colonna = tempColonna;
		griglia[destinazione.riga][destinazione.colonna] = destinazione;

		return permesso;
	}


	public int scacco(){
		int[][] mosssePossibili;
		Pedina pedina;
		for(int i = 0; i < NUM; i++){
			for(int j = 0; j < NUM; j++){
				if(griglia[i][j] instanceof Pedina)	{
					pedina = (Pedina) griglia[i][j];
					if(pedina.getColore() == Colore.NERO){				//controllo se la pedina che dà scacco è di colore nero
						mosssePossibili = pedina.mossePossibili(griglia);
						if(mosssePossibili[reBianco.riga][reBianco.colonna] == 2)		//se è uguale alla posizione del re bianco, allora il re sarà sotto scacco
							return 1;
					}
					if(pedina.getColore() == Colore.BIANCO){
						mosssePossibili = pedina.mossePossibili(griglia);
						if( mosssePossibili[reNero.riga][reNero.colonna] == 2)
							return -1;
					}
				}
			}
		}

		return 0;
	}

	public boolean scaccoMatto(){

		Pedina re = null;
		if(turno == Colore.BIANCO)
			re = reBianco;
		else
			re = reNero;

		ArrayList<int []> mosseRe= getMovesArrayList(re);
		for(int[] coordinata : mosseRe){		
			if(puoMuovere(re,griglia[coordinata[0]][coordinata[1]]))
				return false;
		}
		if(!salvataggioRe())
			return true;
		else
			return false;
	}

	/**
	 * Metodo per controllare che nessuna pedina possa salvare il re
	 * @return true solo se con una mossa posso fare in modo che il re non sia più sotto scacco
	 */
	public boolean salvataggioRe(){
		Pedina pedina;
		for(int i = 0; i < NUM; i++){
			for(int j = 0; j < NUM; j++){
				if(griglia[i][j] instanceof Pedina)	{
					pedina = (Pedina) griglia[i][j];
					if(pedina.getColore().equals(turno)){
						ArrayList<int[]> mosse= getMovesArrayList(pedina);
						for(int[] coordinata :mosse){
							if(turno==Colore.BIANCO){
								if(puoMuovere(pedina,griglia[coordinata[0]][coordinata[1]] ) && scacco()!= SCACCO_BIANCO)
									//se true, esiste una mossa che mi salva dallo scacco
									return true;
							}else{
								if(puoMuovere(pedina, griglia[coordinata[0]][coordinata[1]]) && scacco()!= SCACCO_NERO)
									//se true, esiste una mossa che mi salva dallo scacco
									return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public ArrayList<int[]> getMovesArrayList(Pedina pedina){
		ArrayList<int[]> mosse=new ArrayList<int[]>();
		if(pedina.getColore().equals(turno)){
			int[][] moves= pedina.mossePossibili(griglia);
			for(int i=0;i<moves.length;i++)
				for(int j=0;j<moves[0].length;j++){
					if(moves[i][j]!=0){
						mosse.add(new int[]{i,j});
					}
				}
		}

		return mosse;
	}

	private void ricominciaPartita(){

		for(int i = 0; i < NUM; i++){
			for(int j = 0; j < NUM; j++){
				griglia[i][j].addActionListener(action);
				griglia[i][j].removeActionListener(action);
				getContentPane().remove(griglia[i][j]);
			}
		}
		inizializzaGriglia();
		Scacchiera.this.repaint();
	}

	void print(int[][] mosse){
		for(int i = 0; i < NUM; i++){
			for(int j = 0; j < NUM; j++){
				System.out.print(mosse[i][j]+ " ");
			}
			System.out.println("\n");
		}

	}


	public static void main(String[] argv){
		new Scacchiera(true);

	}




}
