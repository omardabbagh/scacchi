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
/**
 * La classe Scacchiera implementa
 * le funzionalità grafiche, logiche del gioco 
 * e gestisce anche l'interazione dei giocatori.
 * 
 * @author Omar Dabbagh
 *
 */

public class Scacchiera extends JFrame{

	/**
	 * Dimensione della scacchiera
	 */
	private static final int NUM = 8;

	/**
	 * Scacchiera contente pedine o caselle(vuote)
	 */
	Casella[][] griglia;

	/**
	 * ActionListener attaccato a ciascuna casella o pedine
	 * che gestisce l'interazione dell'utente
	 */
	private ActionListener action;

	/**
	 * Turno nella partita
	 */
	private Colore turno;

	/**
	 * Variabile che punt al re bianco
	 */
	private Pedina reBianco;

	/**
	 * Variabile che punta al re nero
	 */
	private Pedina reNero;

	/**
	 * controlla se durante questo turno c'è un pedone che può evolvere
	 */
	private  boolean pedinaDaEvolvere = false;

	/**
	 * Costanti ausiliarie
	 */
	public static final  int SCACCO_BIANCO = 1;
	public static final int SCACCO_NERO = -1;

	/**
	 * Istanzia la scacchiera con le pedine
	 * in base al parametro test.
	 * 
	 * @param test: true instanzia la classe in una configurazione per il test
	 * 				false instanzia la classe per una normale partita
	 */
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

	/*
	 * Detodo che inizializza la scacchiera.
	 * Inizializza il turno, le pedine, caselle 
	 * e attacca action listener a ciascuno di essi.
	 * Inoltre inizializza la parte grafca
	 */
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
				//sposta solo pedine
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
					// controlla se la mossa è stata eseguita
					if(mossa(sorgente, destinazione)){
						//aggiorna grafica in seguito alla spostamento
						getContentPane().remove(sorgente);
						getContentPane().remove(destinazione);
						//rimuovi listener dalla casella
						destinazione.removeActionListener(action);

						griglia[tempRiga][tempColonna].addActionListener(action);

						getContentPane().add(griglia[tempRiga][tempColonna]);

						getContentPane().add(sorgente);
						Scacchiera.this.repaint();
						
						// controlla se un pedone può evolvere
						if(pedinaDaEvolvere)
							evoluzionePedina(sorgente);
						//controlla se la partita è finita
						//chiede la rivincita
						if(inScacco() !=0 && inScaccoMatto()){
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

	/*
	 * sostituisce il pedone che ha raggiunto il bordo della scacchiera
	 */
	private void evoluzionePedina(Pedina pedina) {

		if(pedina.riga==0){
			scegliPedina(Colore.BIANCO, pedina);
		}
		else if( pedina.riga==7){
			scegliPedina(Colore.NERO, pedina);
		}

		pedinaDaEvolvere = false;
	}

	/*
	 * invoca il pannello per la scelta delle pedine
	 * per sostituire il pedone da evolvere
	 */
	private void scegliPedina(Colore colore, Pedina p){
		new ElencoPedine(colore, this, p);
	}

	/**
	 * Sostituisce nella scacchiera il pedone con la nuova pedina scelta
	 * 
	 * @param pedinaDaCambiare: Pedone
	 * @param nuovaPedina: pedina scelta
	 */
	public void sostituisciPedina(Pedina pedinaDaCambiare, Pedina nuovaPedina) {
		griglia[pedinaDaCambiare.riga][pedinaDaCambiare.colonna] = nuovaPedina;
		pedinaDaCambiare.removeActionListener(action);
		nuovaPedina.addActionListener(action);
		this.getContentPane().remove(pedinaDaCambiare);
		this.getContentPane().add(nuovaPedina);
		Scacchiera.this.repaint();
	}

	/**
	 * Controlla se la mossa richiesta è valida.
	 * se si la esegue
	 * @param sorgente: pedina spostata
	 * @param destinazione: casella in cui la pedina viene spostata
	 * @return true se la mossa è stata eseguita
	 */
	boolean mossa(Pedina sorgente, Casella destinazione) {
		//solo pedine possono essere spostate
		if(!(sorgente instanceof Pedina) || sorgente.getColore() != turno)
			return false;
		// non muovere se lo spostamento mette in scaco il proprio re
		if(!puoMuovere(sorgente, destinazione))
			return false;
		int mossa = sorgente
				.mossePossibili(griglia)[destinazione.riga][destinazione.colonna];
		//non vuovre se lo spostamento non rientra fra le mosse della pedina
		if(mossa == 0 || !turno.equals(sorgente.getColore()))
			return false;
		// esegui mossa
		griglia[destinazione.riga][destinazione.colonna] = sorgente;
		griglia[sorgente.riga][sorgente.colonna] = new Casella(sorgente.riga, sorgente.colonna);
		sorgente.cambiaPosizione(destinazione.riga, destinazione.colonna);

		//controlla se è un pedone che può evolvere
		if(turno==Colore.BIANCO && sorgente instanceof Pedone && sorgente.riga==0){
			pedinaDaEvolvere = true;
		}
		else if(turno==Colore.NERO && sorgente instanceof Pedone && sorgente.riga==7){
			pedinaDaEvolvere = true;
		}
		//aggiorna turno
		if(turno.equals(Colore.BIANCO))turno=Colore.NERO;
		else{turno=Colore.BIANCO;}


		return true;
	}
	/*
	 * Simula una mossa per vedere se il proprio re viene messo in scacco
	 * dalla mossa.
	 * 
	 */
	private boolean puoMuovere(Pedina sorgente, Casella destinazione){

		int tempRiga;
		int tempColonna;
		boolean permesso = true;
		//Simula mossa
		griglia[destinazione.riga][destinazione.colonna]=griglia[sorgente.riga][sorgente.colonna];
		tempRiga = sorgente.riga;
		tempColonna = sorgente.colonna;
		sorgente.riga = destinazione.riga;
		sorgente.colonna = destinazione.colonna;
		griglia[tempRiga][tempColonna]= new Casella(tempRiga, tempColonna);

		if(turno==Colore.BIANCO){
			if(inScacco()== SCACCO_BIANCO)permesso = false;
		}else{
			if(inScacco()== SCACCO_NERO)permesso = false;
		}
		//ripristina situzione precedente
		griglia[tempRiga][tempColonna]=griglia[destinazione.riga][destinazione.colonna];
		sorgente.riga = tempRiga;
		sorgente.colonna = tempColonna;
		griglia[destinazione.riga][destinazione.colonna] = destinazione;

		return permesso;
	}

	/**
	 * Controlla se durante questo turno il re è sotto scacco
	 * @return 	1 scacco al re bianco
	 * 			-1 scacco al re nero
	 * 			0 non in scacco
	 */
	public int inScacco(){
		int[][] mosssePossibili;
		Pedina pedina;
		for(int i = 0; i < NUM; i++){
			for(int j = 0; j < NUM; j++){
				if(griglia[i][j] instanceof Pedina)	{
					pedina = (Pedina) griglia[i][j];
					if(pedina.getColore() == Colore.NERO){				
						mosssePossibili = pedina.mossePossibili(griglia);
						if(mosssePossibili[reBianco.riga][reBianco.colonna] == 2)
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
	
	/**
	 * Controlla se il re è in scacco matto
	 * @return true scacco matto al re
	 *
	 */
	public boolean inScaccoMatto(){

		Pedina re = null;
		if(turno == Colore.BIANCO)
			re = reBianco;
		else
			re = reNero;
		// controlla se esiste una mossa del re che può metterlo in salvo
		ArrayList<int []> mosseRe= elencoMosse(re);
		for(int[] coordinata : mosseRe){		
			if(puoMuovere(re,griglia[coordinata[0]][coordinata[1]]))
				return false;
		}
		// controlla se esiste una pedina che può salvare il re
		if(!reFuoriScacco())
			return true;
		else
			return false;
	}

	/**
	 * Controla se esiste una pedina che può salvare il re in scacco
	 * @return true se esiste una mossa che può salvare il re
	 */
	public boolean reFuoriScacco(){
		Pedina pedina;
		for(int i = 0; i < NUM; i++){
			for(int j = 0; j < NUM; j++){
				if(griglia[i][j] instanceof Pedina)	{
					pedina = (Pedina) griglia[i][j];
					if(pedina.getColore().equals(turno)){
						ArrayList<int[]> mosse= elencoMosse(pedina);
						for(int[] coordinata :mosse){
							if(turno==Colore.BIANCO){
								if(puoMuovere(pedina,griglia[coordinata[0]][coordinata[1]] ) && inScacco()!= SCACCO_BIANCO)
									//esiste mossa che salva il re bianco
									return true;
							}else{
								if(puoMuovere(pedina, griglia[coordinata[0]][coordinata[1]]) && inScacco()!= SCACCO_NERO)
									//esiste mossa che salva re nero
									return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	
	/**
	 * Restituisce l'elenco delle possibili mosse
	 * sotto forma di lista contente riga e colonna della mossa
	 * @param pedina da muovere
	 * @return lista delle mosse, ciascun elemento della lista è un array contente 
	 * 			riga e colonna della mossa
	 */
	public ArrayList<int[]> elencoMosse(Pedina pedina){
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

	/*
	 * Rinizia la partita
	 */
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


	public static void main(String[] argv){
		new Scacchiera(false);
	}




}
